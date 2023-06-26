package common

import data.model.Order

/**
 * @author Riccardo Paltrinieri <riccardo@paltrinieri.it>
 * @date 26/06/2023
 */
object OrderList {
    private var orderList: MutableList<Order>? = null

    /**
     */
    fun get(): MutableList<Order> {
        if (orderList == null) {
            orderList = create()
        }

        return orderList!!
    }

    /**
     */
    private fun create(): MutableList<Order> {
        return mutableListOf()
    }
}