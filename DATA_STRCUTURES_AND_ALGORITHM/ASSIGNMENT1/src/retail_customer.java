// retail_customer.java
public class retail_customer extends customer {
    public retail_customer(String name, String surname, String address, String phone, int ID, int operator_ID) {
        super(name, surname, address, phone, ID, operator_ID);
    }

  //When printing the type of custemor this function is needed
    public String getType() {
        return "retail";
    }
}
