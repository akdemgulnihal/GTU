import java.util.Scanner;
import java.io.File;

public class order {
    String product_name;
    int count;
    int total_price;
    int status;
    int customer_ID;
    


    public order( String productName, int count, int totalPrice, int status, int customer_ID) {
        
        this.product_name = productName;
        this.count = count;
        this.total_price = totalPrice;
        this.status =status;
        this.customer_ID =customer_ID;
    }


    public void print_order() {
        System.out.print("Order #" + " => Product name: " + product_name +
                " - Count: " + count + " - Total price: " + total_price +
                " - Status: " );
                if(status == 0)
                {
                    System.out.println("Initialized.");
                }
                else if(status==1)
                {
                     System.out.println("Initialized.");
                }
                 else if(status==2)
                {
                     System.out.println("Processed.");
                }
                 else if(status==3)
                {
                     System.out.println("Cancelled.");
                }
    }


    public int getCustomerId() {
        return customer_ID;
    }
}
