package file.output

import data.model.Order
import data.model.Trade

/**
 * @author Riccardo Paltrinieri <riccardo@paltrinieri.it>
 * @date 27/06/2023
 */
class PrinterStdout: Printer() {
    /**
     */
    override fun printAllTrade(tradeList: MutableList<Trade>) =
        tradeList.forEach{ println(generateTradeStringFormatted(it)) }

    /**
     */
    override fun printAllOrder(orderList: MutableList<Order>) =
        println(generateOrderTableFormatted(orderList))
}