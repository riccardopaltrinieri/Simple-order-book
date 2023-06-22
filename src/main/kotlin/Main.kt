import data.ManagerOrderBook
import file.ReaderOrderStdin

fun main(args: Array<String>) {
    val orderInput = ReaderOrderStdin().getOrderInput()
    val orderList = ManagerOrderBook().getOrderList()
//    TODO("aggressive matching(orderInput, orderList)")
    ManagerOrderBook().storeOrderListNew(orderInput)
//    TODO("output result")
}