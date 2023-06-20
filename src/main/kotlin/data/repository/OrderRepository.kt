package data.repository

import data.model.Order

/**
 * @author Riccardo Paltrinieri <riccardo@paltrinieri.it>
 * @date 20/06/2023
 */
interface OrderRepository {
    /**
     */
    fun insertOrder(order: Order);

    /**
     */
    fun getOrderList(): MutableList<Order>;
}