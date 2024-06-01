import java.util.*;
import java.text.SimpleDateFormat;

public class Person {
    String name;
    int age;
    List<String> hobbies;
    Date timestamp;

    /**
     * Constructor with parameter
     * When addPerson is run in SocialNetworkGraph , it calls that constructor
     * And person object is created
     * @param name
     * @param age
     * @param hobbies
     * @param timestamp
     */
    public Person(String name, int age, List<String> hobbies,  Date timestamp) {
        this.name = name;
        this.age = age;
        this.hobbies = new ArrayList<>(hobbies);
        this.timestamp = new Date();
    }

    /**
     * Printing the name and the timestamp of this person
     */
    @Override
    public String toString() {
        //Format of the date and time
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return name + " (Timestamp: " + sdf.format(timestamp) + ")";
    }
}
