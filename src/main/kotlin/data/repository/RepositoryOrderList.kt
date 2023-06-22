package data.repository

import data.model.Order

/**
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