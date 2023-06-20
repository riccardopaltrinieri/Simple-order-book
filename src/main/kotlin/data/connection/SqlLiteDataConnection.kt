import java.sql.Connection
import java.sql.DriverManager

/**
 * @author Riccardo Paltrinieri <riccardo@paltrinieri.it>
 * @date 20/06/2023
 */
object SqlLiteDataConnection {
    private var connection: Connection? = null

    fun getConnection(): Connection {
        if (connection == null || connection?.isClosed == true) {
            // Establish a new connection if it doesn't exist or is closed
            connection = createConnection()
        }

        return connection!!
    }

    private fun createConnection(): Connection {
        val jdbcUrl = "jdbc:sqlite:src/main/resources/database.sqlite"

        return DriverManager.getConnection(jdbcUrl)
    }
}