package common

import java.util.Random

/**
 * @author Riccardo Paltrinieri <riccardo@paltrinieri.it>
 * @date 19/06/2023
 */
class RandomOrderGenerator {
    fun generateOrderInput(length: Int): MutableList<String> {
        val allOrderString = mutableListOf<String>()
        for (i in 0..length) {
            val random = Random()

            val id = random.nextInt( 89999) + 10000
            val type = if (random.nextBoolean()) 'B' else 'S'
            val price = random.nextInt( 999999)
            val quantity = random.nextInt( 999999999)

            val orderString = listOf(id, type, price, quantity).joinToString(",")
            allOrderString.add(orderString)
        }

        return allOrderString
    }
}