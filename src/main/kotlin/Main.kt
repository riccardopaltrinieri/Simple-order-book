import data.OrderBookManager
import file.OrderReaderStdin

fun main(args: Array<String>) {
    val orderInput = OrderReaderStdin().getOrderInput()
    val orderList = OrderBookManager().getOrderList()
//    TODO("aggressive matching(orderInput, orderList)")
    OrderBookManager().storeOrderListNew(orderInput)
//    TODO("output result")
}