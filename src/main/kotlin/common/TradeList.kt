package common

import data.model.Trade

/**
 * @author Riccardo Paltrinieri <riccardo@paltrinieri.it>
 * @date 26/06/2023
 */
object TradeList {
    private var tradeList: MutableList<Trade>? = null

    /**
     */
    fun get(): MutableList<Trade> {
        if (tradeList == null) {
            tradeList = create()
        }

        return tradeList!!
    }

    /**
     */
    private fun create(): MutableList<Trade> {
        return mutableListOf()
    }
}