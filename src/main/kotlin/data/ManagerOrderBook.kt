package data

import data.repository.RepositoryOrder
import data.model.Order
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
        return repository.getOrderList();
    }

    /**
     */
    fun storeOrderListNew(orderInput: List<Order>) {
        orderInput.forEach { order -> repository.insertOrder(order) }
    }
}