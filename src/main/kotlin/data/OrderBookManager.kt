package data

import data.repository.OrderRepository
import data.model.Order
import data.repository.OrderSqlRepository

/**
 * @author Riccardo Paltrinieri <riccardo@paltrinieri.it>
 * @date 19/06/2023
 */
class OrderBookManager(
    private val repository: OrderRepository = OrderSqlRepository()
) {
    /**
     */
    fun getOrderList(): MutableList<Order> {
        return repository.getOrderList();
    }

    /**
     */
    fun storeOrderListNew(orderInput: List<Order>) {
        orderInput.forEach { order ->
            repository.insertOrder(order)
        }
    }
}