import java.util.LinkedList;
import java.util.ArrayList;

public class Inventory
{
    private LinkedList<ArrayList<Device>> listofDevices;

    /**
     * default constructor
     * 
     *  Time complexity --> O(1)
     * Because this method's execution time is same everytime.
     * 
     * initialize the listofDevices
     */
    public Inventory() {
        this.listofDevices = new LinkedList<>();
    }

    /**
     * Getter for LinkedList 
     * This method call in main
     * Using in Add Part
     *       
     * Time complexity --> O(1)
     * Because this method's execution time is same everytime.
     * @return listofDevices
     */
    public LinkedList<ArrayList<Device>> getTotalList()
    {
    	return listofDevices;
    }


    /**
     * 
     * Adding device to the list
     * 
     * Time complexity --> O(1)
     * Because this method's execution time is same everytime.
     * 
     * @param device the object is added
     */
    public void addDevice(Device device)
    {

         System.out.println("-----------------------------------------------------------------------");
        ArrayList<Device> newList = new ArrayList<>();
        newList.add(device);
        listofDevices.add(newList);
		          

    }

    
    /**
     * Removing device from the List
     * 
     * Each object is checking in the loop
     * So Time complexity --> O(n)
     * 
     * @param device the object is removed
     */
    public void removeDevice(Device device)
    {
        System.out.println("-----------------------------------------------------------------------");
       int indexFlag=-1;
       for (ArrayList<Device> categoryList : listofDevices)
        {   int i=0;
            for (Device device2 : categoryList) 
            {
                /*if(device2.getCategory().equals(device.getCategory()) &&
                    device2.getName().equals(device.getName()) &&
                    device2.getPrice()==device.getPrice() &&
                    device2.getQuantity()==device.getQuantity()     )*/
                    if(
                    device2.getName().equals(device.getName())   )
                    {
                        indexFlag = i;
                        break;
                    }
                    if(indexFlag != -1)
                    {
                        break;
                    }
                i++;
            }

            
        }
        listofDevices.remove(indexFlag);     
    }


     /**
      * Updating the information for chosen device
      * 
      *  Each object is checking in the loop
      * So Time complexity --> O(n)
      * 
     * @param deviceNameInput   The value to be updated is intended
     * @param priceInput        The value to be updated is intended
     * @param quantityInput        The value to be updated is intended
     */
    public void updateDevice(String deviceNameInput,double priceInput,int quantityInput)
    {
        //System.out.println("-----------------------------------------------------------------------");
       int indexFlag=-1;
         int i=0;
        Device temp=null;
        

        for (ArrayList<Device> categoryList : listofDevices)
        {   
           
            for (Device device2 : categoryList) 
            {   
                //System.out.println("Device Name: " + device2.getName() + ", deviceNameInput: " + deviceNameInput);

                //If device name and The value  which is intended to be updated are same
                if(device2.getName().equals(deviceNameInput))
                {
                    indexFlag = i;
                    if(device2.getCategory().equals("Tv"))  //If device category is Tv
                    {
                        if(priceInput != -1.0 && quantityInput != -1){  //If price input and quantity input is not blank
                            temp = new Tv(deviceNameInput,priceInput,quantityInput);//initializing the temp object
                        }
                        if(priceInput == -1.0 && quantityInput == -1)   //If price input and quantity input are blank
                        {
                            temp = new Tv(deviceNameInput,device2.getPrice(),device2.getQuantity());//initializing the temp object
                        }
                        if(priceInput == -1.0 && quantityInput != -1)   //If price input is blank but quantity is not blank
                        {
                            temp = new Tv(deviceNameInput,device2.getPrice(),quantityInput);//initializing the temp object
                        }
                        if(priceInput != -1.0 && quantityInput == -1)   //If price input is not blank but quantity is blank
                        {
                            temp = new Tv(deviceNameInput,priceInput,device2.getQuantity());//initializing the temp object
                        }
                       

                    }
                    else if(device2.getCategory().equals("Smart Phone"))//If device category is smart phone
                    {
                        if(priceInput != -1.0 && quantityInput != -1){  //If price input and quantity input is not blank
                            temp = new SmartPhone(deviceNameInput,priceInput,quantityInput);//initializing the temp object
                        }
                        if(priceInput == -1.0 && quantityInput == -1)   //If price input and quantity input are blank
                        {
                            temp = new SmartPhone(deviceNameInput,device2.getPrice(),device2.getQuantity());//initializing the temp object
                        }
                        if(priceInput == -1.0 && quantityInput != -1)   //If price input is blank but quantity is not blank
                        {
                            temp = new SmartPhone(deviceNameInput,device2.getPrice(),quantityInput);//initializing the temp object
                        }
                        if(priceInput != -1.0 && quantityInput == -1)   //If price input is not blank but quantity is blank
                        {
                            temp = new SmartPhone(deviceNameInput,priceInput,device2.getQuantity());//initializing the temp object
                        }
                    }
                    else if(device2.getCategory().equals("Smart Watch"))//If device category is samrt watch
                    {
                        if(priceInput != -1.0 && quantityInput != -1){//If price input and quantity input is not blank
                            temp = new SmartWatch(deviceNameInput,priceInput,quantityInput);//initializing the temp object
                        }
                        if(priceInput == -1.0 && quantityInput == -1)//If price input and quantity input are blank
                        {
                            temp = new SmartWatch(deviceNameInput,device2.getPrice(),device2.getQuantity());//initializing the temp object
                        }
                        if(priceInput == -1.0 && quantityInput != -1)   //If price input is blank but quantity is not blank
                        {
                            temp = new SmartWatch(deviceNameInput,device2.getPrice(),quantityInput);//initializing the temp object
                        }
                        if(priceInput != -1.0 && quantityInput == -1)   //If price input is not blank but quantity is blank
                        {
                            temp = new SmartWatch(deviceNameInput,priceInput,device2.getQuantity());//initializing the temp object
                        }
                    }
                    else if(device2.getCategory().equals("Laptop"))//If device category is laptop
                    {
                        if(priceInput != -1.0 && quantityInput != -1){//If price input and quantity input is not blank
                            temp = new Laptop(deviceNameInput,priceInput,quantityInput);//initializing the temp object
                        }
                        if(priceInput == -1.0 && quantityInput == -1)//If price input and quantity input are blank
                        {
                            temp = new Laptop(deviceNameInput,device2.getPrice(),device2.getQuantity());//initializing the temp object
                        }
                        if(priceInput == -1.0 && quantityInput != -1)   //If price input is blank but quantity is not blank
                        {
                            temp = new Laptop(deviceNameInput,device2.getPrice(),quantityInput);//initializing the temp object
                        }
                        if(priceInput != -1.0 && quantityInput == -1)   //If price input is not blank but quantity is blank
                        {
                            temp = new Laptop(deviceNameInput,priceInput,device2.getQuantity());//initializing the temp object
                        }
                    }
                    else if(device2.getCategory().equals("Headphones"))//If device category is headphones
                    {
                        if(priceInput != -1.0 && quantityInput != -1){//If price input and quantity input is not blank
                            temp = new HeadPhones(deviceNameInput,priceInput,quantityInput);//initializing the temp object
                        }
                        if(priceInput == -1.0 && quantityInput == -1)//If price input and quantity input are blank
                        {
                            temp = new HeadPhones(deviceNameInput,device2.getPrice(),device2.getQuantity());//initializing the temp object
                        }
                        if(priceInput == -1.0 && quantityInput != -1)   //If price input is blank but quantity is not blank
                        {
                            temp = new HeadPhones(deviceNameInput,device2.getPrice(),quantityInput);//initializing the temp object
                        }
                        if(priceInput != -1.0 && quantityInput == -1)   //If price input is not blank but quantity is blank
                        {
                            temp = new HeadPhones(deviceNameInput,priceInput,device2.getQuantity());//initializing the temp object
                        }
                    }
                     
                }
                
                 if(indexFlag != -1)
                    {
                        break;
                    }

                i++;
            }    
        }
            //when finding device, the index is stored in indexFlag, and remove the relavent part according to indexFlag
           listofDevices.remove(indexFlag);  


           ArrayList<Device> newList = new ArrayList<>();   //And then temp is addet to that indexFlag 
           newList.add(temp);
           listofDevices.add(indexFlag,newList);
           
           //updated message will be shown on terminal
           System.out.println(temp.getName() + " details updated: Price - " + temp.getPrice() + "$, Quantity - " + temp.getQuantity());
        

    }

    /**
     * This methos listing all devices information's
     * 
     * Each object is checking in the loop
     * So Time complexity --> O(n)
     */
    public void listDevice()
    {

         System.out.println("-----------------------------------------------------------------------");
        int i=1;
        System.out.println("Device list: ");
        for (ArrayList<Device> categoryList : listofDevices)
        {          
            for (Device device : categoryList) 
            {
                System.out.print((i++) + ". Category: " + device.getCategory() + ", ");
                System.out.print("Name: " + device.getName() + ", ");
                System.out.print("Price: " + device.getPrice() + "$, ");
                System.out.print("Quantity: " + device.getQuantity());
                System.out.println(); 
                
            }

            
        }
        System.out.println("-----------------------------------------------------------------------");
    }

    /**
     * This meyhod is find the cheapeast device.
     * 
     * Each object is checking in the loop
     * So Time complexity --> O(n)
     * 
     */
    public void cheapestDevice()
    {
        System.out.println("-----------------------------------------------------------------------");
        double minimumPrice = -1.0;     //initialized firstly
         Device temp=null;

        for (ArrayList<Device> categoryList : listofDevices)
        {          
            for (Device device : categoryList) 
            {
                if(minimumPrice == -1.0)    //First device minimum price assign to minimumPrice to compare with other price
                {
                    minimumPrice = device.getPrice();   //getting the price of the device and assign to minimumPrice

                    temp = device;  //assigning the device object to temp object


                }
                else if(device.getPrice() < minimumPrice)   //If the price amount is smaller than minimumPrice,
                {
                    minimumPrice = device.getPrice();   //THen the device's price assign to minimumPrice
                    temp = device;  //assigning the device object to temp object
                }
            }

            
        }
        
        //The cheapeast device information's is printed
        System.out.println("The cheapest device is:");
        System.out.println("Category: " + temp.getCategory() +  ", " +
                            "Name: " + temp.getName() + ", " +
                           "Price: " + temp.getPrice() + "$" + ", " + 
                           " Quantity: " +  temp.getQuantity());

        System.out.println("-----------------------------------------------------------------------");
    }

    /**
     * This method is finding the sort of prices of the devices in ascending order 
     * 
     * First loop O(n) second loop O(n) = O(2n) = O(n)
     * So Time complexity --> O(n) 
     * 
     */
    public void sortByPrice()
    {
        ArrayList<Device> theList = new ArrayList<>();      //Firts of all, all devices list is assigned to new variable to compare with others lately
        for (ArrayList<Device> deviceList : listofDevices) {    //and sorting the new list
            theList.addAll(deviceList);
        }

       
        //two objectt's price is compared in this nested loop
         for (int i = 0; i < theList.size(); i++) {
            for (int j = i + 1; j < theList.size(); j++) {
                
                //Temp objects
                Device temp1 = theList.get(i);  
                Device temp2 = theList.get(j);

                if (temp1.getPrice() > temp2.getPrice()) {  //If first object is bigger than second one
                  

                    theList.set(i,temp2);   //Then do assigning according to the new order
                    theList.set(j,temp1);
                }
            }
        }

        int i=0;                    //And printing the sorted list
        System.out.println("Devices sorted by price:");
        for (Device device : theList) {
            System.out.print((++i) + ". Category: " + device.getCategory() + ", ");
            System.out.print("Name: " + device.getName() + ", ");
            System.out.print("Price: " + device.getPrice() + "$, ");
            System.out.print("Quantity: " + device.getQuantity());
            System.out.println(); 
        }

        System.out.println("-----------------------------------------------------------------------");




      
    }

    /**
     * Calculate the value of the total inventory
     *  Each object is checking in the loop
     *  So Time complexity --> O(n) 
     */
    public void calculateTotalInventory()
    {
         System.out.println("-----------------------------------------------------------------------");

        double totalInventory = 0.0;

        for (ArrayList<Device> theList : listofDevices)
        {          
            for (Device device : theList) 
            {
                //quantity and price is are multiplied and then added to totalInvetory
                totalInventory = totalInventory +  (device.getQuantity() * device.getPrice());
                
            }

            
        }
        System.out.println("Total inventory value: $" + totalInventory);
        System.out.println("-----------------------------------------------------------------------");
    }
    
    /**
     * When restocking is wanted, then the name of devicei restock type(add or remove)
     * and new quantity values are needed in this method 
     * 
     * Because of loop
     * Time complexity --> O(n) 
     * 
     * @param deviceNameInput   the name of the device 
     * @param addOrRemove       add or remove type
     * @param newQuantity       new quantity value
     */
    public void restockDevice(String deviceNameInput,String addOrRemove,int newQuantity)
    {   int flag=-1;
        int indexFlag=-1;
        int i=0;
         Device temp=null;
        
        for (ArrayList<Device> theList : listofDevices)
        {          
            for (Device device : theList) 
            {
                if(device.getName().equals(deviceNameInput))    //If device name and deviceNameInput (taking from the user) are same
                {
                    indexFlag = i;  //This index is stored it indexFlag
                    temp = device;  //In this index, device assign to temp object
                    flag=0;         //The comparision is matched. So the nested loop can be break. Flag is used for this purpose.
                    break;

                }
                i++;

                
            }
            if(flag==0)
            {
                break;
            }
           
        }

        
            //ıf user enter "add"
            if(addOrRemove.equals("Add"))
            {
                temp.setQuantity((temp.getQuantity() + newQuantity));   //Adding new quantity from the quantity
            }
            //If user enter "remove"
            else if(addOrRemove.equals("Remove"))   
            {
                 temp.setQuantity((temp.getQuantity() - newQuantity));  //Removing new quantity from the quantity
            }
                        
            
            listofDevices.remove(indexFlag);  //  Device which want to updated of quantity is removing according to the indexFlag


           ArrayList<Device> newList = new ArrayList<>();   //Then updated this index in this part
           newList.add(temp);
           listofDevices.add(indexFlag,newList);
           
           //The new quantity and device name is printed
           System.out.println(deviceNameInput + " restocked. New quantity: " + temp.getQuantity());

            System.out.println("-----------------------------------------------------------------------");

    }


    /**
     * Reporting of the day will be printed in this method
     * 
     * Because of the loop
     * Time complexity --> O(n) 
     * 
     */
    public void exportingReport()
    {
        System.out.println("\n\n-----------------------------------------------------------------------");
        System.out.println("| No. |   Category |    Name    |  Price |   Quantity |");
        System.out.println("-----------------------------------------------------------------------");
        int i=0;
        double totalInventory = 0.0;

        //All Device information's are printed in this nested loop
        for (ArrayList<Device> categoryList : listofDevices)
        {          
            for (Device device : categoryList) 
            {
                System.out.print("|  " + (i+1) + "  | " + device.getCategory() + "  |    ");
                System.out.print( device.getName() + "  |    ");
                System.out.print( device.getPrice() + "  |    ");
                System.out.print( device.getQuantity() + "  |   ");
                System.out.println(); 
                totalInventory = totalInventory +  (device.getQuantity() * device.getPrice());
                
                i++;
            }

            
        }
         System.out.println("-----------------------------------------------------------------------");

        //Then total number of devices and the total value of inventory is printed
        System.out.println("\n\nSummary:");
        System.out.println("- Total Number of Devices: " + i);
        System.out.println("- Total Inventory Value: " + totalInventory);
        System.out.println("End of Report" );

    }   

}
