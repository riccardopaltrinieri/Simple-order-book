import data.ManagerOrderBook
import file.input.ReaderOrderStdin
import service.TradeService

fun main(args: Array<String>) {
    // Read all the orders from stdin
    val orderInput = ReaderOrderStdin().getOrderInput()

    // Perform the aggressive match between the orders
    val orderListUpdated = TradeService().computeAllPossibleMatch(orderInput)

    // Store the new list of orders
    ManagerOrderBook().storeOrderListNew(orderListUpdated)

    // Print out the result of the script
//    TODO("output result")
}