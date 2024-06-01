public class HeadPhones implements Device
{
    //it's constant
    private final String category="Headphones";

    private String name;

    private double price;

    private int quantity;

    /**
     * Constructor : initialize the parameters to the this variable
     *
     * Time complexity --> O(1)
     * Because this method's execution time is same everytime.
     * 
     * @param name  This parameter is the name of the headphone
     * @param price This parameter is the price of the headphone
     * @param quantity  This parameter is the quantity of the headphone
     */
    public HeadPhones(String name, double price,int quantity)
    {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * Getter for Category
     * 
     *  Time complexity --> O(1)
     * Because this method's execution time is same everytime.
     * 
     * @return category
     */
    public String getCategory()
    {
        return category;
    }

    /**
     * Getter for name
     * 
     * Time complexity --> O(1)
     * Because this method's execution time is same everytime.
     * 
     * @return name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Getter for price
     * 
     * Time complexity --> O(1)
     * Because this method's execution time is same everytime.
     * 
     * @return price
     */
    public double getPrice()
    {
        return price;
    }

    /**
     * Getter for quantity
     * 
     * Time complexity --> O(1)
     * Because this method's execution time is same everytime.
     * 
     * @return quantity
     */
    public int getQuantity()
    {
        return quantity;
    }


    /**
     * Setter for name
     * 
     * Time complexity --> O(1)
     * Because this method's execution time is same everytime.
     * 
     * @param name assigning the new name to the variable of the class
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Setter for price
     * 
     * Time complexity --> O(1)
     * Because this method's execution time is same everytime.
     * 
     * @param price assigning the new price to the variable of the class
     */ 
    public void setPrice(double price)
    {
        this.price = price;
    }

    /**
     * Setter for quantity
     * 
     * Time complexity --> O(1)
     * Because this method's execution time is same everytime.
     * 
     * @param quantity assigning the new quantity to the variable of the class
     */
    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

}