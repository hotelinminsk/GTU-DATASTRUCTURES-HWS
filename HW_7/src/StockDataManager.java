/**
 * StockDataManager class to manage stock data
 * This class uses AVLTree to store stock data
 * It provides methods to add, update, remove and search for stock data
 */
public class StockDataManager {
    //private AVLTree avlTree;
    private AVLTree avlTree;

    // Constructor
    public StockDataManager() {
        avlTree = new AVLTree();
    }

    // Add or update a stock
    public void addOrUpdateStock(String symbol, double price, long volume, long marketCap) {
        Stock existingStock = avlTree.search(symbol);
        if (existingStock != null) {
            existingStock.setPrice(price);
            existingStock.setVolume(volume);
            existingStock.setMarketCap(marketCap);
        } else {
            Stock newStock = new Stock(symbol, price, volume, marketCap);
            avlTree.insert(newStock);
        }
    }
    // Remove a stock
    public void removeStock(String symbol) {
        avlTree.delete(symbol);
    }

    // Search for a stock
    public Stock searchStock(String symbol) {

        return avlTree.search(symbol);
    }

    public void inorderTraversal(){
        avlTree.inOrderTraversal();
    }
    public void postorderTraversal(){
        avlTree.postOrderTraversal();
    }

    public void preorderTraversal(){
        avlTree.preOrderTraversal();
    }

    // Update stock details
    public void updateStock(String symbol, double newPrice, long newVolume, long newMarketCap) {
        Stock stock = avlTree.search(symbol);
        if (stock != null) {
            stock.setPrice(newPrice);
            stock.setVolume(newVolume);
            stock.setMarketCap(newMarketCap);
        }
    }



}
