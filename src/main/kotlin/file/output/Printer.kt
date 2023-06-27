package file.output

import common.OrderType
import data.model.Order
import data.model.Trade
import java.text.NumberFormat
import java.util.*
import kotlin.math.max

/**
 * @author Riccardo Paltrinieri <riccardo@paltrinieri.it>
 * @date 27/06/2023
 */
abstract class Printer {
    /**
     */
    abstract fun printAllTrade(tradeList: MutableList<Trade>)

    /**
     */
    abstract fun printAllOrder(orderList: MutableList<Order>)

    /**
     */
    fun generateOrderTableFormatted(orderList: MutableList<Order>): String {
        val placeHolderNullOrder = "                  "
        val orderBuyStringList = generateOrderStringListFormatted(orderList, OrderType.BUY).reversed()
        val orderSellStringList = generateOrderStringListFormatted(orderList, OrderType.SELL)
        val orderTableFormatted= mutableListOf<String>()

        for (i in 0 until max(orderBuyStringList.size, orderSellStringList.size)) {
            orderTableFormatted.add(
                "%s | %s".format(
                    orderBuyStringList.getOrNull(i) ?: placeHolderNullOrder,
                    orderSellStringList.getOrNull(i) ?: placeHolderNullOrder,
                )
            )
        }

        return orderTableFormatted.joinToString("\n")
    }

    /**
     */
    private fun generateOrderStringListFormatted(orderList: MutableList<Order>, orderType: OrderType): List<String> =
        orderList
            .filter { it.getType() == orderType }
            .map { order ->
                val price = order.getPrice()
                    .toString()
                    .padStart(6, ' ')

                val quantity = NumberFormat.getNumberInstance(Locale.US).format(order.getQuantity())
                    .toString()
                    .padStart(11, ' ')

                when (orderType) {
                    OrderType.SELL -> "%s %s".format(price, quantity)
                    OrderType.BUY -> "%s %s".format(quantity, price)
                }
            }


    /**
     */
    fun generateTradeStringFormatted(trade: Trade) =
        "trade %s,%s,%s,%s".format(
            trade.getAggressingOrderId(),
            trade.getRestingOrderId(),
            trade.getPrice(),
            trade.getQuantity(),
        )
}