package file

import data.model.Order

/**
 * @author Riccardo Paltrinieri <riccardo@paltrinieri.it>
 * @date 18/06/2023
 */
class OrderReaderString: OrderReader() {
    /**
     */
    override fun getOrderInput(): List<Order> {
        throw NotImplementedError()
    }

    /**
     */
    fun getOrderInput(orderStringList: List<String>): List<Order> {
        val orderList = mutableListOf<Order>()

        // Read input until there are no more lines
        for (orderString in orderStringList) {
            orderList.add(parseOrderFromString(orderString))
        }

        return orderList
    }
}