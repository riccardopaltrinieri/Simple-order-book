# Order Book Software with Gradle and Kotlin

This is a sample order book software implemented in Kotlin and built with Gradle. This software reads order data from
stdin or from a file and generates an order book based on the input.

## Prerequisites

Before running the software, make sure you have the following prerequisites installed on your system:

- Java Development Kit (JDK) 8 or above
- Gradle build tool

## Usage

To use the order book software, follow the steps below:

1. **Clone the Repository:** Clone this repository to your local machine using the following command:

   ```shell
   git clone https://github.com/riccardopaltrinieri/kotlin-order-book.git
   ```

2. **Build the Project:** Navigate to the project directory and build the project using Gradle:

   ```shell
   cd kotlin-order-book
   gradle build
   ```

3. **Run the Software:** Once the build is successful, run the order book software with the input file:

   ```shell
   ./exchange < input.txt
   ```

   Replace `input.txt` with the path to your input file containing the order data.

4. **View the Order Book:** The order book will be displayed in the terminal based on the input provided. The buy and sell orders will be sorted by price in descending order. The order book format will be as follows:

   ```
   <Buy Quantity> <Buy Price> | <Sell Price> <Sell Quantity>
   <Buy Quantity> <Buy Price> | <Sell Price> <Sell Quantity>
   ...
   ```

   The buy orders will be listed first, followed by the sell orders. Each line represents a price level in the order book, with the corresponding quantities for buy and sell orders.

## Example

As an example, let's consider the following input file `test1.txt`:

```
10000,B,98,25500
10005,S,105,20000
10001,S,100,500
10002,S,100,10000
10003,B,99,50000
10004,S,103,100
10006,B,105,16000
```

Running the order book software with this input file would be as follows:

```shell
./exchange < test1.txt
```

The resulting order book output will be:

```
trade 10006,10001,100,500
trade 10006,10002,100,10000
trade 10006,10004,103,100
trade 10006,10005,105,5400
      50,000     99 |    105      14,600
      25,500     98 |
```

In this example, the order book shows the buy and sell orders at different price levels. The buy orders are sorted in descending order of price, and the sell orders are sorted in ascending order of price.

Feel free to replace `test1.txt` with your own input file and explore the order book software.

## License

This order book software is released under the [MIT License](https://opensource.org/license/mit/). Feel free to modify and distribute it according to your needs.