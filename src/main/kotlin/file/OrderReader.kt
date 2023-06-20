package file

import common.OrderType
import data.model.Order
import java.time.Instant

/**
 * @author Riccardo Paltrinieri <riccardo@paltrinieri.it>
 * @date 18/06/2023
 */
abstract class OrderReader {
    /**
     */
    abstract fun getOrderInput(): List<Order>

    /**
     */
    fun parseOrderFromString(line: String): Order {
        val values = line.split(",")

        val id = values[0]
        val type = parseOrderType(values[1])
        val price = values[2].toInt()
        val quantity = values[3].toInt()

        return Order(id, Instant.now(), type, price, quantity)
    }

    /**
     */
    private fun parseOrderType(type: String): OrderType {
        return when (type) {
            "B" -> OrderType.BUY
            "S" -> OrderType.SELL
            else -> throw IllegalArgumentException("Unknown order type $type")
        }
    }
}