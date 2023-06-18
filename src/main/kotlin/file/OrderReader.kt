package file

import common.OrderType
import data.Order

/**
 * @author Riccardo Paltrinieri <riccardo@paltrinieri.it>
 * @date 18/06/2023
 */
abstract class OrderReader {
    /**
     */
    abstract fun getOrderList(): List<Order>

    /**
     */
    fun parseOrderFromString(line: String): Order {
        val values = line.split(",")

        val id = values[0]
        val type = parseOrderType(values[1])
        val price = values[2].toInt()
        val quantity = values[3].toInt()

        return Order(id, type, price, quantity)
    }

    /**
     */
    private fun parseOrderType(value: String): OrderType {
        return when (value) {
            "B" -> OrderType.BUY
            "S" -> OrderType.SELL
            else -> TODO("Throw exception")
        }
    }
}