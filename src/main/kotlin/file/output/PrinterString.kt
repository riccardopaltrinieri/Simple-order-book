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
        throw NotImplementedError()

    /**
     */
    override fun printAllOrder(orderList: MutableList<Order>) =
        throw NotImplementedError()

    /**
     */
    fun generateAllTradeString(tradeList: MutableList<Trade>): String =
        tradeList.joinToString("\n") { generateTradeStringFormatted(it) }

    /**
     */
    fun generateAllOrderString(orderList: MutableList<Order>): String =
        generateOrderTableFormatted(orderList)
}