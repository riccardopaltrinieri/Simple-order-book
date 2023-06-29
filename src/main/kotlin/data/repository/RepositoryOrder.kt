package data.repository

import data.model.Order

/**
 * @author Riccardo Paltrinieri <riccardo@paltrinieri.it>
 * @date 20/06/2023
 */
interface RepositoryOrder {
    /**
     */
    fun insertOrder(order: Order)

    /**
     */
    fun getOrderList(): MutableList<Order>

    /**
     */
    fun removeOrder(order: Order)

    /**
     */
    fun updateOrderQuantity(order: Order, quantity: Int)
}