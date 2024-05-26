/**
 * Stock class represents a stock in the stock market.
 * It has the following attributes:
 * - symbol: the symbol of the stock
 * - price: the price of the stock
 * - volume: the volume of the stock
 * - marketCap: the market capitalization of the stock
 *
 */
public class Stock {
    //Private attribute variables
    private String symbol;
    private double price;
    private long volume;
    private long marketCap;

    // Constructor of stock class

    public Stock(String symbol, double price, long volume, long marketCap) {
        this.symbol = symbol;
        this.price = price;
        this.volume = volume;
        this.marketCap = marketCap;
    }


    // Getters and Setters
    //get symbol method
    public String getSymbol() {
        return symbol;
    }

    //set symbol method
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    //get price method
    public double getPrice() {
        return price;
    }

    //set price method
    public void setPrice(double price) {
        this.price = price;
    }

    //get volume method
    public long getVolume() {
        return volume;
    }

    //set volume method
    public void setVolume(long volume) {
        this.volume = volume;
    }

    //get marketCap method
    public long getMarketCap() {
        return marketCap;
    }

    //set marketCap method
    public void setMarketCap(long marketCap) {
        this.marketCap = marketCap;
    }

    //toString method
    @Override
    public String toString() {
        return "Stock [symbol=" + symbol + ", price=" + price + ", volume=" + volume + ", marketCap=" + marketCap + "]";
    }
}
