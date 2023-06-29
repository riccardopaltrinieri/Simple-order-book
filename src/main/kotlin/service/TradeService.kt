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
    fun computeAllPossibleMatch(orderList: List<Order>): List<Order> {
        for (order in orderList) {
            computePossibleMatch(order)
        }

        return managerOrderBook.getOrderList()
    }

    /**
     */
    private fun computePossibleMatch(order: Order) {
        when (order.type) {
            OrderType.SELL -> matchWithAllPotentialBuyer(order)
            OrderType.BUY -> matchWithAllPotentialSeller(order)
        }

        if (order.quantity == 0) {
            managerOrderBook.remove(order)
        } else {
            managerOrderBook.insertOrderNew(order)
        }
    }

    /**
     */
    private fun matchWithAllPotentialBuyer(orderSell: Order): Boolean {
        val orderBuyList = managerOrderBook.getOrderList().filter {
            it.type == OrderType.BUY &&
                    it.price >= orderSell.price
        }

        for (orderBuy in orderBuyList) {
            if (orderSell.quantity == 0) {
                return true
            } else if (orderBuy.price >= orderSell.price) {
                executeTrade(aggressingOrder = orderSell, restingOrder = orderBuy)
            } else {
                // no match
            }
        }

        return false
    }

    /**
     */
    private fun matchWithAllPotentialSeller(orderBuy: Order): Boolean {
        val orderSellList = managerOrderBook.getOrderList().filter {
            it.type == OrderType.SELL &&
                    it.price <= orderBuy.price
        }

        for (orderSell in orderSellList) {
            if (orderBuy.quantity == 0) {
                return true
            } else if (orderBuy.price >= orderSell.price) {
                executeTrade(aggressingOrder = orderBuy, restingOrder = orderSell)
            } else {
                // no match
            }
        }

        // the order was not fulfilled, so it should be stored to be matched with the next ones
        return false
    }

    /**
     */
    private fun executeTrade(aggressingOrder: Order, restingOrder: Order): Trade {
        val trade = Trade(
            aggressingOrder.id,
            restingOrder.id,
            min(aggressingOrder.price, restingOrder.price),
            min(aggressingOrder.quantity, restingOrder.quantity),
        )

        managerTradeBook.insertTrade(trade)
        updateOrderQuantity(restingOrder, trade).also {order ->
            if (order != null) {
                managerOrderBook.update(order)
            }
        }

        return trade
    }

    /**
     */
    private fun updateOrderQuantity(order: Order, trade: Trade): Order? =
        if (order.quantity == trade.quantity) {
            managerOrderBook.remove(order)

            null
        } else {
            order.copy(quantity =  order.quantity - trade.quantity)
        }
}