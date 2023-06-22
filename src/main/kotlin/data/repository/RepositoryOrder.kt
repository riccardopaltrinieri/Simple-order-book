package data.repository

import data.model.Order

/**
 * @author Riccardo Paltrinieri <riccardo@paltrinieri.it>
 * @date 20/06/2023
 */
interface RepositoryOrder {
    /**
     */
    fun insertOrder(order: Order);

    /**
     */
    fun getOrderList(): MutableList<Order>;
}