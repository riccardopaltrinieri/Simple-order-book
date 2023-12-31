package data.repository

import common.TradeList
import data.model.Trade

/**
 * @author Riccardo Paltrinieri <riccardo@paltrinieri.it>
 * @date 22/06/2023
 */
class RepositoryTradeList(
    private var tradeList: MutableList<Trade> = TradeList.tradeList
): RepositoryTrade {
    /**
     */
    override fun insertTrade(trade: Trade) {
        tradeList.add(trade)
    }

    /**
     */
    override fun getTradeList(): MutableList<Trade> = tradeList

    /**
     */
    override fun clearStorage() {
        tradeList.clear()
    }
}