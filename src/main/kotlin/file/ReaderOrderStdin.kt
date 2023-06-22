package file

import data.model.Order

/**
 * @author Riccardo Paltrinieri <riccardo@paltrinieri.it>
 * @date 18/06/2023
 */
class ReaderOrderStdin: ReaderOrder() {
    /**
     */
    override fun getOrderInput(): MutableList<Order> {
        val orderList = mutableListOf<Order>()

        // Read input until there are no more lines
        while (true) {
            val line = readlnOrNull()
            if (line.isNullOrEmpty()) {
                break
            } else {
                orderList.add(parseOrderFromString(line))
            }
        }

        return orderList
    }

    /**
     */
    override fun getOrderInput(orderStringList: List<String>): MutableList<Order> {
        TODO("Not yet implemented")
    }
}