import java.sql.Connection
import java.sql.DriverManager

/**
 * @author Riccardo Paltrinieri <riccardo@paltrinieri.it>
 * @date 20/06/2023
 */
object DataConnectionSqlLite {
    val connection: Connection by lazy {
        create()
    }

    /**
     */
    private fun create(): Connection {
        val jdbcUrl = "jdbc:sqlite:src/main/resources/database/database.sqlite"

        return DriverManager.getConnection(jdbcUrl)
    }
}