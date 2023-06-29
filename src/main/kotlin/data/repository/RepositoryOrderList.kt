package data.repository

import common.OrderList
import data.model.Order

/**
 * This repository stores all the orders and trades inside mutable list. Choose this option if the
 * software will have to compute everything from a single input file.
 * In case of multiple input files we need to store everything somewhere: look `RepositoryOrderSql`
 *
 * @author Riccardo Paltrinieri <riccardo@paltrinieri.it>
 * @date 22/06/2023
 */
class RepositoryOrderList(
    private var orderList: MutableList<Order> = OrderList.orderList
): RepositoryOrder {
    /**
     */
    override fun insertOrder(order: Order) {
        val insertionIndex = orderList.indexOfLast {
            it.price < order.price ||
            (it.price == order.price && it.createdAt.isBefore(order.createdAt))
        } + 1
        orderList.add(insertionIndex, order)
    }

    /**
     */
    override fun getOrderList(): MutableList<Order> = orderList

    /**
     */
    override fun removeOrder(order: Order) {
        orderList.remove(order)
    }

    /**
     */
    override fun updateOrder(order: Order) {
        orderList.map {
            when (it.id) {
                order.id -> order
                else -> order
            }
        }
    }
}