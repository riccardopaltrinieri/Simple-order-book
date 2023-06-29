package data.model

import common.OrderType
import java.time.Instant

/**
 * @author Riccardo Paltrinieri <riccardo@paltrinieri.it>
 * @date 18/06/2023
 */
data class Order(
    val id: String,
    val createdAt: Instant,
    val type: OrderType,
    val price: Int,
    val quantity: Int,
)