package data.repository

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
    private var orderList: MutableList<Order> = mutableListOf()
): RepositoryOrder {
    override fun insertOrder(order: Order) {
        orderList = getOrderList()

        val insertionIndex = orderList.indexOfLast {
            it.getPrice() < order.getPrice() ||
            (it.getPrice() == order.getPrice() && it.getCreatedAt().isBefore(order.getCreatedAt()))
        } + 1
        orderList.add(insertionIndex, order)
    }

    override fun getOrderList(): MutableList<Order> {
        return orderList
    }
}