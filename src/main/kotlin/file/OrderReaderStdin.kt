package file

import data.Order

/**
 * @author Riccardo Paltrinieri <riccardo@paltrinieri.it>
 * @date 18/06/2023
 */
class OrderReaderStdin: OrderReader() {
    /**
     */
    override fun getOrderList(): List<Order> {
        val orderList = mutableListOf<Order>()

        // Read input until there are no more lines
        while (true) {
            val line = readlnOrNull()
            if (line != null) {
                orderList.add(parseOrderFromString(line))
            } else {
                break
            }
        }

        return orderList
    }
}