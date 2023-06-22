package data.repository

import data.model.Trade
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

/**
 * @author Riccardo Paltrinieri <riccardo@paltrinieri.it>
 * @date 22/06/2023
 */
class RepositoryTradeSql(
    private val connection: Connection = DataConnectionSqlLite.getConnection()
): RepositoryTrade {
    /**
     */
    override fun insertTrade(trade: Trade) {
        val sql = "INSERT INTO `trade` (aggressingOrderId, restingOrderId, price, quantity)" +
                " VALUES (?, ?, ?, ?)"
        val statement: PreparedStatement = connection.prepareStatement(sql)

        try {
            statement.setString(1, trade.getAggressingOrderId())
            statement.setString(2, trade.getRestingOrderId())
            statement.setInt(3, trade.getPrice())
            statement.setInt(4, trade.getQuantity())
            statement.executeUpdate()

            statement.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     */
    override fun getTradeList(): MutableList<Trade> {
        val sql = "SELECT * FROM `trade` ORDER BY id"
        val statement: PreparedStatement = connection.prepareStatement(sql)
        val resultSet: ResultSet = statement.executeQuery()

        val tradeList = mutableListOf<Trade>()

        while (resultSet.next()) {
            val aggressingOrderId = resultSet.getString("aggressingOrderId")
            val restingOrderId = resultSet.getString("restingOrderId")
            val price = resultSet.getInt("price")
            val quantity = resultSet.getInt("quantity")

            tradeList.add(Trade(aggressingOrderId, restingOrderId, price, quantity))
        }

        resultSet.close()
        statement.close()

        return tradeList
    }
}