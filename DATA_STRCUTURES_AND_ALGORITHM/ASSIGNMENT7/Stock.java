
class Stock {
    //Private variables
    private String symbol;
    private double price;
    private long volume;
    private long marketCap;

    /**
     * Constructor with parameter
     * Initalizaition is occured
     * @param symbol
     */
    Stock(String symbol) {
        this.symbol = symbol;
    }

    /**
     * Constructor with parameters
     * Initalizaitionsare occured
     * @param symbol
     * @param price
     * @param colume
     * @param marketCap
     */
    Stock(String symbol, double price, long volume, long marketCap) {
        this.symbol = symbol;
        this.price = price;
        this.volume = volume;
        this.marketCap = marketCap;
    }

    /**
     * Getter for symbol
     * @return symbol
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Setter for symbol
     * @param symbol
     * 
     */
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    /**
     * Fetter for price
     * @return price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Setter for price
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Getter for Volume
     * @return volume
     */
    public long getVolume() {
        return volume;
    }

    /**
     * Setter for volume
     * @param volume
     */
    public void setVolume(long volume) {
        this.volume = volume;
    }
    
    /**
     * Getter for MarketCap
     * @return marketCap
     */
    public long getMarketCap() {
        return marketCap;
    }

    /**
     * Setter for MarketCap
     * @Param marketCap
     */
    public void setMarketCap(long marketCap) {
        this.marketCap = marketCap;
    }

     @Override
    public String toString() {
        return "Stock [symbol=" + symbol + ", price=" + price + ", volume=" + volume + ", marketCap=" + marketCap + "]";
    }
}