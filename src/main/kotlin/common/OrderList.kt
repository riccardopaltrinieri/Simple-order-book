package common

import data.model.Order

/**
 * @author Riccardo Paltrinieri <riccardo@paltrinieri.it>
 * @date 26/06/2023
 */
object OrderList {
    val orderList: MutableList<Order> by lazy {
        create()
    }

    /**
     */
    private fun create(): MutableList<Order> = mutableListOf()
}