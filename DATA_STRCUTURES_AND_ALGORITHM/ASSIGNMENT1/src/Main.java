import java.util.Scanner;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        operator[] operators = new operator[100];
        customer[] customers = new customer[100];
        order[] orders = new order[100];

        operator operator = null;
        customer customer = null;

        try {
            readFile(operators, customers, orders);

            Scanner scanner = new Scanner(System.in);
            System.out.println("Please enter your ID...");

            // Check if the input is a valid integer
            if (scanner.hasNextInt()) {
                int input = scanner.nextInt();

                operator = operator.compareOperatorID(input, operators);
                customer = customer.compareCustomerID(input, customers);

                if (operator != null) {
                    operator.print_operator();
                    operator.print_customers();
                } else if (customer != null) {
                    customer.print_customer();
                    customer.print_orders();
                } else {
                    System.out.println("No operator/customer was found with ID " + input + ". Please try again.");
                }
            } else {
                System.out.println("Invalid ID .");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void readFile(operator[] operators, customer[] customers, order[] orders) {
        try {
            String filename = "content.txt";
            File file = new File(filename);
            Scanner scanner = new Scanner(file);

            int operatorIndex = 0;
            int customerIndex = 0;
            int orderIndex = 0;

            while (scanner.hasNextLine()) {
              
                    String line = scanner.nextLine();
                    String[] parts = line.split(";");
                    

                    if("order".equals(parts[0])){
                            if(!containsNumber(parts[1]) && !containsChar(parts[2]) && !containsChar(parts[3]) && !containsChar(parts[4]) && !containsChar(parts[5]) 
                    && Integer.parseInt(parts[2]) > 0 && Integer.parseInt(parts[3]) > 0 && Integer.parseInt(parts[5]) > 0 &&
                     (Integer.parseInt(parts[4]) == 0 || Integer.parseInt(parts[4]) == 1  || Integer.parseInt(parts[4]) == 2  || Integer.parseInt(parts[4]) == 3)  )
                    {
                            if (parts.length == 6) {
                               
                                    orders[orderIndex] = new order(parts[1], Integer.parseInt(parts[2]),
                                            Integer.parseInt(parts[3]), Integer.parseInt(parts[4]), Integer.parseInt(parts[5]));
                                    orderIndex++;
                                
                            }} }
                        if("retail_customer".equals(parts[0])){
                            if(!containsNumber(parts[1])  && !containsNumber(parts[2])  && !containsChar(parts[5])  && !containsChar(parts[6])  && (Integer.parseInt(parts[5]) > 0)&& (Integer.parseInt(parts[6]) > 0))
                    {
                            if (parts.length == 7) {
                               
                                    customers[customerIndex] = new retail_customer(parts[1], parts[2], parts[3],
                                            parts[4], Integer.parseInt(parts[5]), Integer.parseInt(parts[6]));
                                    customerIndex++;
                                }
                            } }
                        if("corporate_customer".equals(parts[0])){
                            if(!containsNumber(parts[1])  && !containsNumber(parts[2])  && !containsChar(parts[5])  && !containsChar(parts[6])  && (Integer.parseInt(parts[5]) > 0)&& (Integer.parseInt(parts[6]) > 0))
                    {
                            if (parts.length == 8) {
                                    customers[customerIndex] = new corporate_customer(parts[1], parts[2], parts[3],
                                            parts[4], Integer.parseInt(parts[5]), Integer.parseInt(parts[6]),
                                            parts[7]);
                                    customerIndex++;
                                }
                            } }
                        if( "operator".equals(parts[0])){
                            if (parts.length == 7) {
                                
                                    operators[operatorIndex] = new operator(parts[1], parts[2], parts[3],
                                            parts[4], Integer.parseInt(parts[5]), Integer.parseInt(parts[6]));
                                    operatorIndex++;
                                
                            } }
               
            }

            // relationships between customers and operators
            for (operator operator : operators) {
                if (operator != null) 
                    operator.define_customers(customers);
            }

            // relationships between customers and orders
            for (customer customer : customers) {
                if (customer != null) 
                    customer.define_orders(orders);
            }

        }
        catch (Exception e) {
            
            e.printStackTrace();
        }
    }

     // Helper method to check if a string does not include digits
    private static boolean containsNumber(String input) {
        for (char c : input.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }

      private static boolean containsChar(String input) {
        for (char c : input.toCharArray()) {
            if (!Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }

   

}
