package file.input

import data.model.Order

/**
 * @author Riccardo Paltrinieri <riccardo@paltrinieri.it>
 * @date 18/06/2023
 */
class ReaderOrderString: ReaderOrder() {
    /**
     */
    override fun getOrderInput(): MutableList<Order> =
        throw NotImplementedError()

    /**
     */
    override fun getOrderInput(orderStringList: List<String>): MutableList<Order> {
        val orderList = mutableListOf<Order>()

        // Read input until there are no more lines
        for (orderString in orderStringList) {
            orderList.add(parseOrderFromString(orderString))
        }

        return orderList
    }
}