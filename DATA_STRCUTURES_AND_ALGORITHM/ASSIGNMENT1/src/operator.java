import java.util.Scanner;
import java.io.File;

public class operator extends person {
    int wage;
    customer[] customers;

    public operator(String name, String surname, String address, String phone, int ID, int wage) {
        super(name, surname, address, phone, ID);
        this.wage = wage;
        this.customers = new customer[100];
    }

   

    public void define_customers(customer[] theCustomers) {
        int i = 0;
        for (customer customer : theCustomers) {     //All customers come, then
            if (customer != null && customer.getOperatorId() == this.getId()) { //Then compare the operator ıds
                this.customers[i] = customer;   //If the condition is occured, then these customers store in orders in customer
                i++;
            }
        }
    }

    public static operator compareOperatorID(int ID, operator[] operators) {
        for (operator operator : operators) {
            if (operator != null && operator.getId() == ID) {
                return operator;
            }
        }
        return null;
    }


    public void print_operator() {
        System.out.println("*** Operator Screen ***");
        System.out.println("-------------------------------------");
        System.out.println("Name & Surname: " + getName() + " " + getSurname());
        System.out.println("Address: " + getAddress());
        System.out.println("Phone: " + getPhone());
        System.out.println("ID: " + getId());
        System.out.println("Wage: " + wage);
        System.out.println("------------------------------------");
    }

    public void print_customers() {
        
            System.out.println("Customers:");
            int i=0;
            while( i < customers.length && customers[i] != null)
             {
                customer currentCustomer = customers[i];
                System.out.println("Customer #" + (i + 1) + " (a " + currentCustomer.getType() + " customer):");
                currentCustomer.print_customer();
                currentCustomer.print_orders();
                System.out.println("-------------------------------");
                i++;
            }
        
    }

}



