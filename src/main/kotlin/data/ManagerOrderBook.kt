package data

import data.model.Order
import data.repository.RepositoryOrder
import data.repository.RepositoryOrderList

/**
 * @author Riccardo Paltrinieri <riccardo@paltrinieri.it>
 * @date 19/06/2023
 */
class ManagerOrderBook(
    private val repository: RepositoryOrder = RepositoryOrderList()
) {
    /**
     */
    fun getOrderList(): MutableList<Order> {
        return repository.getOrderList()
    }

    /**
     */
    fun storeOrderListNew(orderListNew: List<Order>) {
        orderListNew.forEach { repository.insertOrder(it) }
    }

    /**
     */
    fun insertOrderNew(order: Order) {
        repository.insertOrder(order)
    }

    /**
     */
    fun remove(order: Order) {
        repository.removeOrder(order)
    }
}