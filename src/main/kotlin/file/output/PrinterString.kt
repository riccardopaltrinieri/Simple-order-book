package file.output

import data.model.Order
import data.model.Trade

/**
 * @author Riccardo Paltrinieri <riccardo@paltrinieri.it>
 * @date 27/06/2023
 */
class PrinterString: Printer() {
    /**
     */
    override fun printAllTrade(tradeList: MutableList<Trade>) =
        // This can be handled in a nicer way, but since it is used only for testing
        // in this assignment an exception is enough.
        throw NotImplementedError("Use generateAllTradeString instead")

    /**
     */
    override fun printAllOrder(orderList: MutableList<Order>) =
        // This can be handled in a nicer way, but since it is used only for testing
        // in this assignment an exception is enough.
        throw NotImplementedError("Use generateAllOrderString instead")

    /**
     */
    fun generateAllTradeString(tradeList: MutableList<Trade>): String =
        tradeList.joinToString("\n") { generateTradeStringFormatted(it) }

    /**
     */
    fun generateAllOrderString(orderList: MutableList<Order>): String =
        generateOrderTableFormatted(orderList)
}