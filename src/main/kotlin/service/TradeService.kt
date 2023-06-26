package service

import common.OrderType
import data.ManagerOrderBook
import data.ManagerTradeBook
import data.model.Order
import data.model.Trade

/**
 * @author Riccardo Paltrinieri <riccardo@paltrinieri.it>
 * @date 22/06/2023
 */
class TradeService(
    private var managerOrderBook: ManagerOrderBook = ManagerOrderBook(),
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
        if (order.getType() == OrderType.SELL) {
            matchWithAllPotentialBuyer(order)
        } else if (order.getType() == OrderType.BUY) {
            matchWithAllPotentialSeller(order)
        } else {
            throw IllegalArgumentException("Unexpected order type ${order.getType()}")
        }

        if (order.getQuantity() == 0) {
            managerOrderBook.remove(order)
        } else {
            managerOrderBook.insertOrderNew(order)
        }
    }

    /**
     */
    private fun matchWithAllPotentialBuyer(orderSell: Order): Boolean {
        val orderBuyList = managerOrderBook.getOrderList().filter {
            it.getType() == OrderType.BUY &&
            it.getPrice() >= orderSell.getPrice()
        }

        for (orderBuy in orderBuyList) {
            if (orderSell.getQuantity() == 0) {
                return true
            } else if (orderBuy.getPrice() >= orderSell.getPrice()) {
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
            it.getType() == OrderType.SELL &&
            it.getPrice() <= orderBuy.getPrice()
        }

        for (orderSell in orderSellList) {
            if (orderBuy.getQuantity() == 0) {
                return true
            } else if (orderBuy.getPrice() >= orderSell.getPrice()) {
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
    private fun executeTrade(aggressingOrder: Order,  restingOrder: Order): Trade {
        val trade = managerTradeBook.insertTrade(aggressingOrder, restingOrder)
        updateOrderQuantity(restingOrder, trade)
        aggressingOrder.setQuantity(aggressingOrder.getQuantity() - trade.getQuantity())

        return trade
    }

    /**
     */
    private fun updateOrderQuantity(order: Order, trade: Trade) {
        if (order.getQuantity() == trade.getQuantity()) {
            managerOrderBook.remove(order)
        } else {
            order.setQuantity(order.getQuantity() - trade.getQuantity())
        }
    }
}