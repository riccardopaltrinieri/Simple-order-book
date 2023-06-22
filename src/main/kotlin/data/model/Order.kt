package data.model

import common.OrderType
import java.time.Instant

/**
 * @author Riccardo Paltrinieri <riccardo@paltrinieri.it>
 * @date 18/06/2023
 */
data class Order(
    private val id: String,
    private val createdAt: Instant,
    private val type: OrderType,
    private val price: Int,
    private var quantity: Int,
) {
    fun getId(): String {
        return this.id
    }

    fun getCreatedAt(): Instant {
        return this.createdAt
    }

    fun getType(): OrderType {
        return this.type
    }

    fun getPrice(): Int {
        return this.price
    }

    fun getQuantity(): Int {
        return this.quantity
    }
}