package data

import common.OrderType

/**
 * @author Riccardo Paltrinieri <riccardo@paltrinieri.it>
 * @date 18/06/2023
 */
class Order(
    private val id: String,
    private val type: OrderType,
    private val price: Int,
    private val quantity: Int,
)