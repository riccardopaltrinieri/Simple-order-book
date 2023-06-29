package test

import common.RandomOrderGenerator
import data.ManagerOrderBook
import data.repository.RepositoryOrderList
import data.repository.RepositoryOrderSql
import data.repository.RepositoryTradeSql
import file.input.ReaderOrderString
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test
import kotlin.test.assertContains

/**
 * @author Riccardo Paltrinieri <riccardo@paltrinieri.it>
 * @date 20/06/2023
 */
class OrderTest {
    /**
     * Not needed for testing persistent storage, but nice to have.
     */
    @BeforeEach
    fun clearSqlDatabase() {
        RepositoryOrderSql().truncateTable()
        RepositoryTradeSql().truncateTable()
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