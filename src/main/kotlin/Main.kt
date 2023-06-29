import data.ManagerOrderBook
import data.ManagerTradeBook
import file.input.ReaderOrderStdin
import file.output.PrinterStdout
import service.TradeService

fun main() {
    // Read all the orders from stdin
    val orderInput = ReaderOrderStdin().getOrderInput()
    // Perform the aggressive match between the orders
    TradeService().computeAllPossibleMatch(orderInput)

    // Print out the result of the script
    PrinterStdout().apply {
        printAllTrade(ManagerTradeBook().getTradeList())
        printAllOrder(ManagerOrderBook().getOrderList())
    }
}