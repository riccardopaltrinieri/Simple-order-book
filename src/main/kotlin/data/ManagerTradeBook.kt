package data

import data.model.Order
import data.model.Trade
import data.repository.RepositoryTrade
import data.repository.RepositoryTradeSql
import kotlin.math.min

/**
 * @author Riccardo Paltrinieri <riccardo@paltrinieri.it>
 * @date 22/06/2023
 */
class ManagerTradeBook(
    private val repository: RepositoryTrade = RepositoryTradeSql()
) {
    /**
     */
    fun insertTrade(aggressingOrder: Order, restingOrder: Order): Trade {
        val trade = Trade(
            aggressingOrder.getId(),
            restingOrder.getId(),
            aggressingOrder.getPrice(),
            min(aggressingOrder.getQuantity(), restingOrder.getQuantity()),
        )
        repository.insertTrade(trade)

        return trade
    }
}