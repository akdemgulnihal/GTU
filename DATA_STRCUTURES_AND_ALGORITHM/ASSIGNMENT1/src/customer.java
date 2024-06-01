import java.util.Scanner;
import java.io.File;

public class customer extends person {
    order[] orders;
    int operator_ID;

    public customer(String name, String surname, String address, String phone, int ID, int operator_ID) {
        super(name, surname, address, phone, ID);
        this.operator_ID = operator_ID;
        this.orders = new order[100];
    }

    public void define_orders(order[] theOrders) {  //All orders come, then
        int i = 0;
        for (order order : theOrders) {     //Then compare the customer ıds
            if (order != null && order.getCustomerId() == this.getId()) {
                this.orders[i] = order;     //If the condition is occured, then these orders store in orders in customer
                i++;
               
            }
        }
    }


    public void print_orders() {    //Printing the customer's orders (customerların orderları print ediliyor)
            for (order order : orders) {
                if(order!=null)
                    order.print_order();
            }
    }

    public void print_customer() {  //Printing the customer screen
        System.out.println("*** Customer Screen ***");
        System.out.println("Name & Surname: " + getName() + " " + getSurname());
        System.out.println("Address: " + getAddress());
        System.out.println("Phone: " + getPhone());
        System.out.println("ID: " + getId());
        System.out.println("Operator ID: " + operator_ID);
    }

    public String getType() {
        return "Default";
    }


    public int getOperatorId() {
        return operator_ID;
    }

    public static customer compareCustomerID(int ID, customer[] customers) {    //This function used in Main class
        for (customer customer : customers) {                                   //Id which is entered from the user and the customers id is compared
            if (customer != null && customer.getId() == ID) {
                return customer;                                                //then returned the related object
            }
        }
        return null;
    }
}
