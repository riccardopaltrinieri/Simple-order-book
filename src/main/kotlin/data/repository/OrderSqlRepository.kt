package data.repository

import SqlLiteDataConnection
import common.OrderType
import data.model.Order
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.time.Instant

/**
 * @author Riccardo Paltrinieri <riccardo@paltrinieri.it>
 * @date 20/06/2023
 */
class OrderSqlRepository(
    private val connection: Connection = SqlLiteDataConnection.getConnection()
): OrderRepository {
    /**
     */
    override fun insertOrder(order: Order) {
        val sql = "INSERT INTO `order` (id, created_at, type, price, quantity)" +
                " VALUES (?, ?, ?, ?, ?);"
        val statement: PreparedStatement = connection.prepareStatement(sql)

        try {
            statement.setString(1, order.getId())
            statement.setTimestamp(2, java.sql.Timestamp(Instant.now().epochSecond))
            statement.setInt(3, order.getType().ordinal)
            statement.setInt(4, order.getPrice())
            statement.setInt(5, order.getQuantity())
            statement.executeUpdate()

            statement.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     */
    override fun getOrderList(): MutableList<Order> {
        val sql = "SELECT * FROM `order`;"
        val statement: PreparedStatement = connection.prepareStatement(sql)
        val resultSet: ResultSet = statement.executeQuery()

        val orderList = mutableListOf<Order>()

        while (resultSet.next()) {
            val id = resultSet.getString("id")
            val createdAt = resultSet.getTimestamp("created_at")
            val type = resultSet.getString("type")
            val price = resultSet.getInt("price")
            val quantity = resultSet.getInt("quantity")

            val order = Order(
                id,
                createdAt.toInstant(),
                OrderType.valueOf(type),
                price,
                quantity
            )
            orderList.add(order)
        }

        resultSet.close()
        statement.close()

        return orderList
    }
}