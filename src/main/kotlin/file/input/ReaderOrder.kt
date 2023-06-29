package file.input

import common.OrderType
import data.model.Order
import java.time.Instant
import java.time.temporal.ChronoUnit

/**
 * @author Riccardo Paltrinieri <riccardo@paltrinieri.it>
 * @date 18/06/2023
 */
abstract class ReaderOrder {
    /**
     */
    abstract fun getOrderInput(): MutableList<Order>

    /**
     */
    abstract fun getOrderInput(orderStringList: List<String>): MutableList<Order>

    /**
     */
    fun parseOrderFromString(line: String): Order {
        val values = line.split(",")

        val id = values[0]
        val createdAt = Instant.now()
        val type = parseOrderType(values[1])
        val price = values[2].toInt()
        val quantity = values[3].toInt()

        return Order(id, createdAt, type, price, quantity)
    }

    /**
     */
    private fun parseOrderType(type: String): OrderType =
        when (type) {
            "B" -> OrderType.BUY
            "S" -> OrderType.SELL
            else -> throw IllegalArgumentException("Unknown order type $type")
        }
}