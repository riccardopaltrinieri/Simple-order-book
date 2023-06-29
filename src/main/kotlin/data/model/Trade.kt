package data.model

/**
 * @author Riccardo Paltrinieri <riccardo@paltrinieri.it>
 * @date 19/06/2023
 */
data class Trade(
    val aggressingOrderId: String,
    val restingOrderId: String,
    val price: Int,
    val quantity: Int,
)