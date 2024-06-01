import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;


/**
 * @author gulnihalakdem
 *
 * 
 */
public class Main
{
    /**
     * Main function
     * 
     * First of all menu printing
     * Then taking choice from the user
     * Finally , according to chosen value the program runs 
     * 
     * Time complexity O(n)
     * Because of loop 
     * 
     */
    public static void main(String[] args) 
    {
        Inventory inventory = new Inventory();
        Scanner scanner = new Scanner(System.in);

        int choiceOfMenu;
     do{
        menuPrinting(); //Just printing the menu screen
        
        choiceOfMenu = getChoiceFromUser(scanner);  //Taking choice from the user, to select the menu

        menuChoice(choiceOfMenu,scanner,inventory); //After choices, the main part of the code is runned there.


     }while(choiceOfMenu != 0 );   
    }

    /**
     * The aim of this method is printing the menu
     */
    public static void menuPrinting()
    {
        System.out.print("\n*******************************************************************");
        System.out.println("\nWelcome to the Electronics Inventory Management System!\n");
        System.out.println("Please select an option:");
        System.out.println("1. Add a new device");
        System.out.println("2. Remove a device");
        System.out.println("3. Update device details");
        System.out.println("4. List all devices");
        System.out.println("5. Find the cheapest device");
        System.out.println("6. Sort devices by price");
        System.out.println("7. Calculate total inventory value");
        System.out.println("8. Restock a device");
        System.out.println("9. Export inventory report");
        System.out.println("0. Exit");
    }

    /**
     * This method is to take input from the user
     * Moreover checks :
     *      If the user do not enter between 0 and 9,
     *      also enter invalid type
     * When enter invalid input, then printing the error messagfe to the screen
     * 
     * @param scanner scanner object is needed for taking input from the user
     * @return input returning the input
     */
    public static int getChoiceFromUser(Scanner scanner)
    {
         
        while (true) {
            if (scanner.hasNextInt()) {
                int input = scanner.nextInt();
                if (input >= 0 && input <= 9) {
                    return input;
                } else {
                    System.out.println("Choice must be between 0 and 9. Please enter again.");
                }
            } else {
                System.out.println("Invalid input. Please enter an intege between 0 and 9.");
                scanner.nextLine(); // Clearing the buffer
               
            }
        }
    }

    /** After user choice in the main,
     * then these parameters are come to this method
     * And due to the choice, run other functions from the inventory class
     * For example; when choice is 0,
     * then category name,device name, price and quantity values are taking from the user
     * ,and calling the addDevice method in inventory to add this new object 
     * 
     * Time complexity O(n) 
     * 
     * 
     * @param choice    user choice the menu
     * @param scanner   scanner object is needed for taking input from the user
     * @param inventory this object have the list of devices
     */
    public static void menuChoice(int choice,Scanner scanner,Inventory inventory)
    {
        Device theDevice;
        String deviceNameInput,categoryInput,addOrRemove;
        double priceInput = 0;
        int quantityInput = 0;



        switch(choice)
        {
            case 0:
                break;
            case 1:
                
                System.out.println("-----------------------------------------------------------------------");
                scanner.nextLine();
                System.out.print("Enter category name: ");  //Taking category name from the user
                categoryInput = scanner.nextLine();
                
               
                
                System.out.print("Enter device name: ");    //Taking device name from the user
                deviceNameInput = scanner.nextLine();
                
                LinkedList<ArrayList<Device>> linkedlist;	
                linkedlist = inventory.getTotalList();	//Getting the linkedlist from the inventory class
                int flag=-1;			//This nested loop for checkin whether device already added or not
                for (ArrayList<Device> aList : linkedlist)
        	{   
            		for (Device device2 : aList) 
            		{
            			if((device2.getName().equals(deviceNameInput)))
            			{
            				flag=0;
            				break;
            			}
             		 }
                }

                priceInput = 0.0;
                System.out.print("Enter price: ");      //Taking price from the user
                if (scanner.hasNextDouble())    //check input is double or not
                 {
                     priceInput = scanner.nextDouble();
                } 
                else 
                {
                    System.out.println("Invalid input.It must be double.");
                    scanner.nextLine(); // Clearing the buffer
                }

                

                System.out.print("Enter quantity: ");   //Taking quantity name from the user
                quantityInput = scanner.nextInt();

               
                if(categoryInput.equals("Tv") && flag!=0)  //If category input which is taking from the user is equals to Tv
                {
                    theDevice = new Tv(deviceNameInput, priceInput, quantityInput); //creating a new object
                    inventory.addDevice(theDevice); //Call the function from the inventory class to add to the list
                }
                else if(categoryInput.equals("Smart Phone")&& flag!=0)    //If category input which is taking from the user is equals to Samrt phone
                {
                    theDevice = new SmartPhone(deviceNameInput, priceInput, quantityInput); //creating a new object
                    inventory.addDevice(theDevice); //Call the function from the inventory class to add to the list
                }
                else if(categoryInput.equals("Laptop")&& flag!=0) //If category input which is taking from the user is equals to Laptop
                {
                    theDevice = new Laptop(deviceNameInput, priceInput, quantityInput);//creating a new object
                    inventory.addDevice(theDevice);     //Call the function from the inventory class to add to the list
                }
                else if(categoryInput.equals("Smart Watch")&& flag!=0)    //If category input which is taking from the user is equals to Smart watch
                {
                    theDevice = new SmartWatch(deviceNameInput, priceInput, quantityInput);//creating a new object
                    inventory.addDevice(theDevice); //Call the function from the inventory class to add to the list
                }
                else if(categoryInput.equals("Headphones")&& flag!=0) //If category input which is taking from the user is equals to Headphones
                {
                    theDevice = new HeadPhones(deviceNameInput, priceInput, quantityInput);//creating a new object
                    inventory.addDevice(theDevice); //Call the function from the inventory class to add to the list
                }
                else
                {
                    System.out.println("There is an error about category.");
                }

                break;
            case 2:
                System.out.println("-----------------------------------------------------------------------");
                scanner.nextLine();
                System.out.print("Enter category name: ");  //Taking category name from the user
                categoryInput = scanner.nextLine();
                
                
                System.out.print("Enter device name: ");    //Taking device name from the user
                deviceNameInput = scanner.nextLine();
/*
                priceInput = 0.0;
                System.out.print("Enter price: ");  //Taking price from the user
                if (scanner.hasNextDouble())    //check input is double or not
                 {
                     priceInput = scanner.nextDouble();
                } 
                else 
                {
                    System.out.println("Invalid input.It must be double.");
                    scanner.nextLine(); // Clearing the buffer
                }

                

                System.out.print("Enter quantity: ");      //Taking quantity from the user
                quantityInput = scanner.nextInt();

               */priceInput = 0.0;
               quantityInput=0;
               
                if(categoryInput.equals("Tv"))
                {
                    theDevice = new Tv(deviceNameInput, priceInput, quantityInput); //creating a new object
                    inventory.removeDevice(theDevice);   //Call the function from the inventory class to remove to the list
                }
                else if(categoryInput.equals("Smart Phone"))
                {
                    theDevice = new SmartPhone(deviceNameInput, priceInput, quantityInput);//creating a new object
                    inventory.removeDevice(theDevice);//Call the function from the inventory class to remove to the list
                }
                else if(categoryInput.equals("Laptop"))
                {
                    theDevice = new Laptop(deviceNameInput, priceInput, quantityInput);//creating a new object
                    inventory.removeDevice(theDevice);//Call the function from the inventory class to remove to the list
                }
                else if(categoryInput.equals("Smart Watch"))
                {
                    theDevice = new SmartWatch(deviceNameInput, priceInput, quantityInput);//creating a new object
                    inventory.removeDevice(theDevice);//Call the function from the inventory class to remove to the list
                }
                else if(categoryInput.equals("Headphones"))
                {
                    theDevice = new HeadPhones(deviceNameInput, priceInput, quantityInput);//creating a new object
                    inventory.removeDevice(theDevice);//Call the function from the inventory class to remove to the list
                }
                else
                {
                    System.out.println("There is an error about category.");
                }
                break;
            case 3:
                 scanner.nextLine();//when want to take string two times respectively, the problem occurs ,due to avoid this issue . use this line
                System.out.println("-----------------------------------------------------------------------");
                System.out.print("Enter the name of the device to update: ");   //Taking name from the user to update
                deviceNameInput = scanner.nextLine();

                System.out.print("Enter new price (leave blank to keep current price): ");  //Taking price from the user
               String priceInputStr = scanner.nextLine();
                
                if (!priceInputStr.isEmpty()) { //If the price is not blank
                    try {
                        priceInput = Double.parseDouble(priceInputStr);//priceInputStr is converted to double
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid price");
                    }
                }
                if(priceInputStr.isEmpty()) //If the price is blank
                {
                    priceInput = -1.0;
                }

                System.out.print("Enter new quantity (leave blank to keep current quantity): ");
                String quantityInputStr = scanner.nextLine();
          
                if (!quantityInputStr.isEmpty()) {  //If the price is not blank
                    try {
                        quantityInput = Integer.parseInt(quantityInputStr);     //priceInputStr is converted to integer
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid quantity.");
                    }
                }
                 if(quantityInputStr.isEmpty()) //If the price is  blank
                {
                    quantityInput = -1;
                }

                inventory.updateDevice(deviceNameInput,priceInput,quantityInput);   //calling the updateDevice method in update class to update the information
                
                break;
            case 4:
                    inventory.listDevice(); //Calling listDevice in inventory class to print all devices
                break;
            case 5:
                    inventory.cheapestDevice(); //calling cheapestDevice method to find the cheapest device
                break;
            case 6:
                    System.out.println("-----------------------------------------------------------------------");
                    inventory.sortByPrice();    //calling sortByPrice method to sort devices according to price in ascending order
                break;
            case 7:
                    inventory.calculateTotalInventory();    //Calling this method in inventory class to calculate inventory value
                break;
            case 8:
                    scanner.nextLine();
                    System.out.println("\n-----------------------------------------------------------------------");
                    System.out.print("Enter the name of the device to restock: ");  //Taking name of the device from the user to restock
                    deviceNameInput = scanner.nextLine();

                    System.out.print("Do you want to add or remove stock? (Add/Remove): "); //add or remove status for restcock
                    addOrRemove = scanner.nextLine();

                    System.out.print("Enter the quantity to add or remove: "); //quantity of adding/removing 
                    quantityInput = scanner.nextInt();

                    inventory.restockDevice(deviceNameInput,addOrRemove,quantityInput); //calling this method in inventory class to resock device

                break;
            case 9:
                    System.out.println("\n-----------------------------------------------------------------------");
                    System.out.println("Electronics Shop Inventory Report");
                    System.out.print("Generated on: ");


                    Date currentDate = new Date();
        
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd");
                    SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
                    SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
                    
                    String formattedDate = dateFormat.format(currentDate);
                    String formattedMonth = monthFormat.format(currentDate);
                   
                    String month = null;
                    if(formattedMonth.equals("01")) //
                    {
                        month = "January";
                    }
                    else if(formattedMonth.equals("02"))
                    {
                        month = "February";
                    }
                    else if(formattedMonth.equals("03"))
                    {
                        month = "March";
                    }
                    else if(formattedMonth.equals("04"))
                    {
                        month = "April";
                    }
                    else if(formattedMonth.equals("05"))
                    {
                        month = "May";
                    }
                    else if(formattedMonth.equals("06"))
                    {
                        month = "June";
                    }
                    else if(formattedMonth.equals("07"))
                    {
                        month = "July";
                    }
                    else if(formattedMonth.equals("08"))
                    {
                        month = "August";
                    }
                    else if(formattedMonth.equals("09"))
                    {
                        month = "September";
                    }
                    else if(formattedMonth.equals("10"))
                    {
                        month = "October";
                    }
                    else if(formattedMonth.equals("11"))
                    {
                        month = "November";
                    }
                    else if(formattedMonth.equals("12"))
                    {
                        month = "December";
                    }
                    
                    String formattedYear = yearFormat.format(currentDate);  //printing the current date
                    System.out.println(formattedDate + "th" + " " + month + " " + formattedYear);
                    
                    inventory.exportingReport();    //callinf the exportingReport to print 9 option

                break;

            default:
                System.out.println("Invalid choice");
                break;

        }
    }

}
