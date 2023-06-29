package service

import common.OrderType
import data.ManagerOrderBook
import data.ManagerTradeBook
import data.model.Order
import data.model.Trade
import kotlin.math.min

/**
 * @author Riccardo Paltrinieri <riccardo@paltrinieri.it>
 * @date 22/06/2023
 */
class TradeService(
    private val managerOrderBook: ManagerOrderBook = ManagerOrderBook(),
    private val managerTradeBook: ManagerTradeBook = ManagerTradeBook()
) {
    /**
     */
    fun computeAllPossibleMatch(orderList: MutableList<Order>): MutableList<Order> {
        for (order in orderList) {
            computePossibleMatch(order)
        }

        return managerOrderBook.getOrderList()
    }

    /**
     */
    private fun computePossibleMatch(order: Order) {
        val orderAfterMatch = when (order.type) {
            OrderType.SELL -> matchWithAllPotentialBuyer(order)
            OrderType.BUY -> matchWithAllPotentialSeller(order)
        }

        if (orderAfterMatch != null && orderAfterMatch.quantity != 0) {
            managerOrderBook.insertOrderNew(orderAfterMatch)
        }
    }

    /**
     */
    private fun matchWithAllPotentialBuyer(orderSell: Order): Order? {
        var aggressingOrder = orderSell
        val orderBuyList = managerOrderBook.getOrderList().filter {
            it.type == OrderType.BUY &&
            it.price >= orderSell.price
        }

        for (orderBuy in orderBuyList) {
            if (aggressingOrder.quantity == 0) {
                return null
            } else if (orderBuy.price >= orderSell.price) {
                aggressingOrder = executeTrade(aggressingOrder, restingOrder = orderBuy)
            } else {
                // no match
            }
        }

        return aggressingOrder
    }

    /**
     */
    private fun matchWithAllPotentialSeller(orderBuy: Order): Order? {
        var aggressingOrder = orderBuy
        val orderSellList = managerOrderBook.getOrderList().filter {
            it.type == OrderType.SELL &&
            it.price <= orderBuy.price
        }

        for (orderSell in orderSellList) {
            if (aggressingOrder.quantity == 0) {
                return null
            } else if (orderBuy.price >= orderSell.price) {
                aggressingOrder = executeTrade(aggressingOrder, restingOrder = orderSell)
            } else {
                // no match
            }
        }

        // the order was not fulfilled, so it should be stored to be matched with the next ones
        return aggressingOrder
    }

    /**
     */
    private fun executeTrade(aggressingOrder: Order, restingOrder: Order): Order {
        val trade = Trade(
            aggressingOrder.id,
            restingOrder.id,
            min(aggressingOrder.price, restingOrder.price),
            min(aggressingOrder.quantity, restingOrder.quantity),
        )

        managerTradeBook.insertTrade(trade)
        removeOrderOrUpdateQuantity(restingOrder, trade)

        return aggressingOrder.copy(quantity = aggressingOrder.quantity - trade.quantity)
    }

    /**
     */
    private fun removeOrderOrUpdateQuantity(order: Order, trade: Trade) =
        if (order.quantity == trade.quantity) {
            managerOrderBook.remove(order)
        } else {
            managerOrderBook.update(order.copy(quantity = order.quantity - trade.quantity))
        }
}