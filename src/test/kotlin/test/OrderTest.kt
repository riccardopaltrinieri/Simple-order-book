package test

import common.RandomOrderGenerator
import data.ManagerOrderBook
import data.repository.RepositoryOrderList
import data.repository.RepositoryOrderSql
import data.repository.RepositoryTradeList
import data.repository.RepositoryTradeSql
import file.input.ReaderOrderString
import org.junit.jupiter.api.BeforeAll
import kotlin.test.Test
import kotlin.test.assertContains

/**
 * @author Riccardo Paltrinieri <riccardo@paltrinieri.it>
 * @date 20/06/2023
 */
class OrderTest {
    companion object {
        /**
         * Not needed for testing persistent storage, but needed for the test with example input.
         */
        @BeforeAll
        @JvmStatic
        fun clearSqlDatabase() {
            RepositoryOrderSql().clearStorage()
            RepositoryTradeSql().clearStorage()
            RepositoryOrderList().clearStorage()
            RepositoryTradeList().clearStorage()
        }
    }

    @Test
    fun testOrderListInsertAndReadWithRepositoryList() {
        val managerOrderBook = ManagerOrderBook(RepositoryOrderList())

        val orderStringList = RandomOrderGenerator().generateOrderInput(10)
        val orderInput = ReaderOrderString().getOrderInput(orderStringList)
        managerOrderBook.storeOrderListNew(orderInput)
        val orderListStored = managerOrderBook.getOrderList()

        orderInput.forEach{ assertContains(orderListStored, it) }
    }

    @Test
    fun testOrderListInsertAndReadWithRepositorySql() {
        val managerOrderBook = ManagerOrderBook(RepositoryOrderSql())

        val orderStringList = RandomOrderGenerator().generateOrderInput(0)
        val orderInput = ReaderOrderString().getOrderInput(orderStringList)
        managerOrderBook.storeOrderListNew(orderInput)
        val orderListStored = managerOrderBook.getOrderList()

        orderInput.forEach{ assertContains(orderListStored, it) }
    }
}