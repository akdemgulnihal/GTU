import java.util.Scanner;
import java.io.File;

public class corporate_customer extends customer {
    String company_name;

    public corporate_customer(String name, String surname, String address, String phone, int ID, int operator_ID, String company_name) {
        super(name, surname, address, phone, ID, operator_ID);
        this.company_name = company_name;
    }

   //When printing the type of custemor this function is needed
    public String getType() {
        return "corporate";
    }

   
    public void print_customer() {
        super.print_customer();  // Calling the print_customer( superclass)

        //or the company name
        System.out.print("Company Name: " );
        System.out.println( company_name);
    }
}
