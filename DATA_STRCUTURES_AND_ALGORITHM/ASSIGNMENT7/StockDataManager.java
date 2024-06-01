public class StockDataManager {
    private AVLTree avlTree;

    /**
     * constructor
     */
    public StockDataManager() {
        avlTree = new AVLTree();
    }

    /**
     * This function is printing the preorder, inorder and postorder respectively via calling some functions from AVLTree class
     */
    public void printOrders()
    {
        System.out.print("\nPre Order: ");
        avlTree.preOrder();
       
        System.out.print("\nIn Order: ");
        avlTree.inOrder();
      
        System.out.print("\nPost Order: ");
        avlTree.postOrder();
        System.out.println("\n");
    }

    /**
     *  Add or update a stock
     * @param symbol
     * @param price
     * @param volume,marketCap
     * 
     * First of all , checking the symbol is existed or not. And the returning value stored in existingNode
     * Then, checking is not null, it means there is a node and its stock symbol is matched
     * So The node is updated
     * 
     * If the node is null then new node is added to the tree
     */

    public void addOrUpdateStock(String symbol, double price, long volume, long marketCap) {
        Node existingNode = avlTree.search(symbol); //Searching according to symbol
        if (existingNode != null) { //İf it is found
            Stock existingStock1 = existingNode.getStock();
            existingStock1.setPrice(price);
            existingStock1.setVolume(volume);
            existingStock1.setMarketCap(marketCap);
        } else {    //If it is not foun
            Stock newStock = new Stock(symbol, price, volume, marketCap);
            avlTree.insert(newStock);
        }
    }

    /**
     * Remove a stock
     * @param symbol
     *      */
    public void removeStock(String symbol) {
        avlTree.delete(symbol);
    }

    /**
     * Search for a stock
     * @param symbol
     */
    public Stock searchStock(String symbol) {
        Node node = avlTree.search(symbol);
        return (node != null) ? node.getStock() : null;
    }

    /**
     * Update stock 
     * @param symbol,newSymbol
     * @param newPrice
     * @param newMarketCap,newVolume
     * 
     * First of all,Seacrhing nodes to find to update
     * If it is not null to update
     * If it is null, there is no node to update
     */
    public void updateStock(String symbol,String newSymbol, double newPrice, long newVolume, long newMarketCap) {
        Stock stock = searchStock(symbol);
        if (stock != null) {
            stock.setSymbol(newSymbol);
            stock.setPrice(newPrice);
            stock.setVolume(newVolume);
            stock.setMarketCap(newMarketCap);
        }
    }
}
