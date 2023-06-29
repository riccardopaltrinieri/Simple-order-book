package data.repository

import DataConnectionSqlLite
import common.OrderType
import data.model.Order
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.time.Instant

/**
 * This repository stores all the orders and trades inside a SQLLite file/database. Choose this option if the
 * software will have to compute everything from multiple input file.
 * In case of a single input files we need to store everything somewhere: look `RepositoryOrderList`
 *
 * @author Riccardo Paltrinieri <riccardo@paltrinieri.it>
 * @date 20/06/2023
 */
class RepositoryOrderSql(
    private val connection: Connection = DataConnectionSqlLite.getConnection()
): RepositoryOrder {
    /**
     */
    override fun insertOrder(order: Order) {
        val sql = "INSERT INTO `order` (id, created_at, type, price, quantity)" +
                " VALUES (?, ?, ?, ?, ?);"
        val statement: PreparedStatement = connection.prepareStatement(sql)

        try {
            statement.setString(1, order.id)
            statement.setString(2, order.createdAt.toString())
            statement.setString(3, order.type.name)
            statement.setInt(4, order.price)
            statement.setInt(5, order.quantity)
            statement.executeUpdate()

            statement.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     */
    override fun getOrderList(): MutableList<Order> {
        val sql = "SELECT * FROM `order` ORDER BY price DESC, created_at;"
        val statement: PreparedStatement = connection.prepareStatement(sql)
        val resultSet: ResultSet = statement.executeQuery()

        val orderList = mutableListOf<Order>()

        while (resultSet.next()) {
            val id = resultSet.getString("id")
            val createdAt = resultSet.getString("created_at")
            val type = resultSet.getString("type")
            val price = resultSet.getInt("price")
            val quantity = resultSet.getInt("quantity")

            val order = Order(
                id,
                Instant.parse(createdAt),
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

    /**
     */
    override fun removeOrder(order: Order) {
        val sql = "DELETE FROM `order` WHERE id = (?);"
        val statement: PreparedStatement = connection.prepareStatement(sql)

        try {
            statement.setString(1, order.id)
            statement.executeUpdate()

            statement.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     */
    override fun updateOrder(order: Order) {
        val sql = "UPDATE `order` SET quantity = (?) WHERE id = (?);"
        val statement: PreparedStatement = connection.prepareStatement(sql)

        try {
            statement.setInt(1, order.quantity)
            statement.setString(2, order.id)
            statement.executeUpdate()

            statement.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     */
    fun truncateTable() {
        val sql = "DELETE FROM `order`"
        val statement: PreparedStatement = connection.prepareStatement(sql)

        try {
            statement.execute()

            statement.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}