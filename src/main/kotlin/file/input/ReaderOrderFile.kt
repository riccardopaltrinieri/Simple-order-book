package file.input

import data.model.Order

/**
 * @author Riccardo Paltrinieri <riccardo@paltrinieri.it>
 * @date 18/06/2023
 */
class ReaderOrderFile: ReaderOrder() {
    /**
     */
    override fun getOrderInput(): MutableList<Order> = throw NotImplementedError()

    /**
     */
    override fun getOrderInput(orderStringList: List<String>): MutableList<Order> =
        throw NotImplementedError()
}