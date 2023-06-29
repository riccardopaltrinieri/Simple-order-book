package data

import data.model.Trade
import data.repository.RepositoryTrade
import data.repository.RepositoryTradeList

/**
 * @author Riccardo Paltrinieri <riccardo@paltrinieri.it>
 * @date 22/06/2023
 */
class ManagerTradeBook(
    private val repository: RepositoryTrade = RepositoryTradeList()
) {
    /**
     */
    fun insertTrade(trade: Trade) {
        repository.insertTrade(trade)
    }

    fun getTradeList(): MutableList<Trade> = repository.getTradeList()
}