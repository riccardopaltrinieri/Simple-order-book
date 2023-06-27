package test

import common.RandomOrderGenerator
import data.ManagerOrderBook
import data.ManagerTradeBook
import file.input.ReaderOrderString
import file.output.PrinterStdout
import file.output.PrinterString
import service.TradeService
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * @author Riccardo Paltrinieri <riccardo@paltrinieri.it>
 * @date 22/06/2023
 */
class TradeServiceTest {
    @Test
    fun testTradeServiceWithRandomInput() {
        val orderStringList = RandomOrderGenerator().generateOrderInput(100)
        val orderInput = ReaderOrderString().getOrderInput(orderStringList)

        TradeService().computeAllPossibleMatch(orderInput)

        PrinterStdout().printAllTrade(ManagerTradeBook().getTradeList())
        PrinterStdout().printAllOrder(ManagerOrderBook().getOrderList())
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

        assertEquals(
            "trade 10006,10001,100,500\n" +
                    "trade 10006,10002,100,10000\n" +
                    "trade 10006,10004,103,100\n" +
                    "trade 10006,10005,105,5400",
            PrinterString().generateAllTradeString(ManagerTradeBook().getTradeList()),
        )
        val actual = PrinterString().generateAllOrderString(ManagerOrderBook().getOrderList())
        assertEquals(
            "     50,000     99 |    105      14,600\n" +
                     "     25,500     98 |                   ",
            actual,
        )
    }
}