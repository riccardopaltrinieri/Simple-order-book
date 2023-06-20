package test

import common.RandomOrderGenerator
import data.OrderBookManager
import file.OrderReaderString
import org.junit.jupiter.api.Test

/**
 * @author Riccardo Paltrinieri <riccardo@paltrinieri.it>
 * @date 20/06/2023
 */
class MainTest {
    @Test
    fun testOrderListGenerator() {
        val orderStringList = RandomOrderGenerator().generateOrderInput(10)
        val orderInput = OrderReaderString().getOrderInput(orderStringList)
        OrderBookManager().storeOrderListNew(orderInput)
    }
}