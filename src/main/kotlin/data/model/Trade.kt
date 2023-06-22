package data.model

/**
 * @author Riccardo Paltrinieri <riccardo@paltrinieri.it>
 * @date 19/06/2023
 */
data class Trade(
    private val aggressingOrderId: String,
    private val restingOrderId: String,
    private val price: Int,
    private val quantity: Int,
) {
    fun getAggressingOrderId(): String {
        return this.aggressingOrderId
    }

    fun getRestingOrderId(): String {
        return this.restingOrderId
    }

    fun getPrice(): Int {
        return this.price
    }

    fun getQuantity(): Int {
        return this.quantity
    }
}