package test

import common.RandomOrderGenerator
import data.ManagerOrderBook
import data.repository.RepositoryOrderList
import data.repository.RepositoryOrderSql
import file.input.ReaderOrderString
import kotlin.test.Test
import kotlin.test.assertContains

/**
 * @author Riccardo Paltrinieri <riccardo@paltrinieri.it>
 * @date 20/06/2023
 */
class MainTest {
    @Test
    fun testOrderListInsertAndReadWithRepositoryList() {
        val managerOrderBook = ManagerOrderBook(RepositoryOrderList())

        val orderStringList = RandomOrderGenerator().generateOrderInput(10)
        val orderInput = ReaderOrderString().getOrderInput(orderStringList)
        managerOrderBook.storeOrderListNew(orderInput)
        val orderListStored = managerOrderBook.getOrderList()

        orderInput.forEach{ order -> assertContains(orderListStored, order) }
    }

    @Test
    fun testOrderListInsertAndReadWithRepositorySql() {
        val managerOrderBook = ManagerOrderBook(RepositoryOrderSql())

        val orderStringList = RandomOrderGenerator().generateOrderInput(0)
        val orderInput = ReaderOrderString().getOrderInput(orderStringList)
        managerOrderBook.storeOrderListNew(orderInput)
        val orderListStored = managerOrderBook.getOrderList()

        orderInput.forEach{ order ->
            assertContains(orderListStored, order)
        }
    }
}