package service

import common.OrderType
import data.ManagerTradeBook
import data.model.Order
import data.model.Trade

/**
 * @author Riccardo Paltrinieri <riccardo@paltrinieri.it>
 * @date 22/06/2023
 */
class TradeService(
    private var orderListStored: MutableList<Order>,
    private val managerTradeBook: ManagerTradeBook = ManagerTradeBook()
) {
    /**
     */
    fun computeAllPossibleMatch(orderList: MutableList<Order>): MutableList<Order> {
        for (order in orderList) {
            computePossibleMatch(order)
        }

        return orderListStored
    }

    /**
     */
    private fun computePossibleMatch(order: Order) {
        val isOrderFulfilled =
            if (order.getType() == OrderType.SELL) {
                matchWithAllPotentialBuyer(order)
            } else if (order.getType() == OrderType.BUY) {
                matchWithAllPotentialSeller(order)
            } else {
                throw IllegalArgumentException("Unexpected order type ${order.getType()}")
            }

        if (isOrderFulfilled) {
            // No need to insert the order in the list, continue.
        } else {
            orderListStored.add(order)
        }
    }

    /**
     */
    private fun matchWithAllPotentialBuyer(orderSell: Order): Boolean {
        val orderBuyList = orderListStored.filter {
            it.getType() == OrderType.BUY &&
            it.getPrice() > orderSell.getPrice()
        }

        for (orderBuy in orderBuyList) {
            if (orderBuy.getPrice() > orderSell.getPrice()) {
                if (executeTrade(aggressingOrder = orderSell, restingOrder = orderBuy)) return true
            } else {
                // no match
            }
        }

        // the order was not fulfilled, so it should be stored to be matched with the next ones
        return false
    }

    /**
     */
    private fun matchWithAllPotentialSeller(orderBuy: Order): Boolean {
        val orderSellList = orderListStored.filter {
            it.getType() == OrderType.SELL &&
            it.getPrice() < orderBuy.getPrice()
        }

        for (orderSell in orderSellList) {
            if (orderBuy.getPrice() > orderSell.getPrice()) {
                if (executeTrade(aggressingOrder = orderBuy, restingOrder = orderSell)) return true
            } else {
                // no match
            }
        }

        // the order was not fulfilled, so it should be stored to be matched with the next ones
        return false
    }

    /**
     */
    private fun executeTrade(aggressingOrder: Order,  restingOrder: Order): Boolean {
        val trade = managerTradeBook.insertTrade(aggressingOrder, restingOrder)
        updateOrderQuantity(restingOrder, trade)

        val quantityRemainder = aggressingOrder.getQuantity() - trade.getQuantity()

        if (quantityRemainder == 0) {
            return true
        } else {
            aggressingOrder.setQuantity(quantityRemainder)
        }

        return false
    }

    /**
     */
    private fun updateOrderQuantity(orderBuy: Order, trade: Trade) {
        if (orderBuy.getQuantity() == trade.getQuantity()) {
            orderListStored.remove(orderBuy)
        } else {
            orderBuy.setQuantity(orderBuy.getQuantity() - trade.getQuantity())
        }
    }
}