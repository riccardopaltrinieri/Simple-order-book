package data.repository

import data.model.Trade

/**
 * @author Riccardo Paltrinieri <riccardo@paltrinieri.it>
 * @date 22/06/2023
 */
interface RepositoryTrade {
    /**
     */
    fun insertTrade(trade: Trade)

    /**
     */
    fun getTradeList(): MutableList<Trade>
}