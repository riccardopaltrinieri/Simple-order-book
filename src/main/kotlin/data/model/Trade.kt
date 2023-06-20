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
)