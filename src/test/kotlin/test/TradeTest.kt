package test

import common.RandomOrderGenerator
import data.ManagerOrderBook
import data.ManagerTradeBook
import data.repository.RepositoryOrderSql
import data.repository.RepositoryTradeSql
import file.input.ReaderOrderString
import file.output.PrinterStdout
import file.output.PrinterString
import org.junit.jupiter.api.BeforeEach
import service.TradeService
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * @author Riccardo Paltrinieri <riccardo@paltrinieri.it>
 * @date 22/06/2023
 */
class TradeTest {
    /**
     * Not needed for testing persistent storage, but nice to have.
     */
    @BeforeEach
    fun clearSqlDatabase() {
        RepositoryOrderSql().truncateTable()
        RepositoryTradeSql().truncateTable()
    }

    /**
     * Only checks the validity of the code, not the validity of the result. With more time
     * I would have added proper assertion on every order and trade
     */
    @Test
    fun testTradeServiceWithRandomInput() {
        val orderStringList = RandomOrderGenerator().generateOrderInput(100)
        val orderInput = ReaderOrderString().getOrderInput(orderStringList)

        TradeService().computeAllPossibleMatch(orderInput)

        PrinterStdout().printAllTrade(ManagerTradeBook().getTradeList())
        PrinterStdout().printAllOrder(ManagerOrderBook().getOrderList())
    }

    @Test
    fun testTradeServiceWithRandomInputAndRepositorySql() {
        val orderStringList = RandomOrderGenerator().generateOrderInput(100)
        val orderInput = ReaderOrderString().getOrderInput(orderStringList)

        val managerOrderBook = ManagerOrderBook(RepositoryOrderSql())
        val managerTradeBook = ManagerTradeBook(RepositoryTradeSql())

        TradeService(managerOrderBook, managerTradeBook).computeAllPossibleMatch(orderInput)

        PrinterStdout().printAllTrade(managerTradeBook.getTradeList())
        PrinterStdout().printAllOrder(managerOrderBook.getOrderList())
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
        assertEquals(
            "     50,000     99 |    105      14,600\n" +
                     "     25,500     98 |                   ",
            PrinterString().generateAllOrderString(ManagerOrderBook().getOrderList()),
        )
    }
}