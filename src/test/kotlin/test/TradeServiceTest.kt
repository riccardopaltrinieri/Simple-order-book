package test

import common.RandomOrderGenerator
import data.ManagerOrderBook
import data.ManagerTradeBook
import file.input.ReaderOrderString
import service.TradeService
import kotlin.test.Test

/**
 * @author Riccardo Paltrinieri <riccardo@paltrinieri.it>
 * @date 22/06/2023
 */
class TradeServiceTest {
    @Test
    fun testTradeServiceWithRandomInput() {
        val orderStringList = RandomOrderGenerator().generateOrderInput(10)
        val orderInput = ReaderOrderString().getOrderInput(orderStringList)

        TradeService().computeAllPossibleMatch(orderInput)

        ManagerTradeBook().getTradeList().forEach{ println(it) }
        ManagerOrderBook().getOrderList().forEach{ println(it) }
    }

    @Test
    fun testTradeServiceWithExample() {
        val orderStringList = listOf(
            "10000,B,98,25500",
            "10005,S,105,20000",
            "10001,S,100,500",
            "10002,S,100,10000",
            "10003,B,99,50000",
            "10004,S,103,100",
            "10006,B,105,16000"
        )
        val orderInput = ReaderOrderString().getOrderInput(orderStringList)

        TradeService().computeAllPossibleMatch(orderInput)

        ManagerTradeBook().getTradeList().forEach{ println(it) }
        ManagerOrderBook().getOrderList().forEach{ println(it) }
    }
}