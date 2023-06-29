package common

import data.model.Trade

/**
 * @author Riccardo Paltrinieri <riccardo@paltrinieri.it>
 * @date 26/06/2023
 */
object TradeList {
    val tradeList: MutableList<Trade> by lazy {
        create()
    }

    /**
     */
    private fun create(): MutableList<Trade> = mutableListOf()
}