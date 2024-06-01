import java.util.*;
import java.text.SimpleDateFormat;

public class Main {
    public static void main(String[] args) {
        SocialNetworkGraph network = new SocialNetworkGraph();
        Scanner scanner = new Scanner(System.in);

        // Adding some people for demonstration
        network.addPerson("John Doe", 25, Arrays.asList("reading", "hiking", "cooking"));
        network.addPerson("Jane Smith", 22, Arrays.asList("swimming", "cooking"));
        network.addPerson("Alice Johnson", 27, Arrays.asList("hiking", "painting"));
        network.addPerson("Bob Brown", 30, Arrays.asList("reading", "swimming"));
        network.addPerson("Emily Davis", 28, Arrays.asList("running", "swimming"));
        network.addPerson("Frank Wilson", 26, Arrays.asList("reading", "hiking"));

        // Adding friendships for demonstration
        network.addFriendship("John Doe", "Jane Smith");
        network.addFriendship("Jane Smith", "Bob Brown");
        network.addFriendship("Bob Brown", "Alice Johnson");
        network.addFriendship("Emily Davis", "Frank Wilson");
   

        do  
        {   //MENU OPTIONS
            System.out.println("\n======= Social Network Analysis Menu =======");
            System.out.println("1. Add person");
            System.out.println("2. Remove person");
            System.out.println("3. Add friendship");
            System.out.println("4. Remove friendship");
            System.out.println("5. Find shortest path");
            System.out.println("6. Suggest friends");
            System.out.println("7. Count clusters");
            System.out.println("8. Exit");
            System.out.print("Please select an option: ");

            
            int optionMenu;
            //Taking the interger for option menu
            //If option is integer
            if (scanner.hasNextInt()) {
                optionMenu = scanner.nextInt();
                scanner.nextLine(); 
            } 
            else {//If option is not integer
                System.out.println("Invalid input type. It should be integer!");
                scanner.nextLine(); 
                continue; // Exitting the program 
            }

            //If user don't enter 1 to 8 , then this if block will be run
            if(optionMenu > 8 || optionMenu < 1)
            {   
                //Invalid option message will be printed to screen 
                System.out.println("Invalid option you chose. It should be between 1 and 8.");
                continue;
            }

            //According to option, Relevant case will be executed
            switch (optionMenu) {
                case 1:
                    System.out.println("\n=============== ADDING A PERSON ===============");
                    System.out.print("Enter name: ");   //Taking name from the user
                    String name = scanner.nextLine();
                    System.out.print("Enter age: ");    //Taking age from the user
                    int age;

                    //Taking the integer and check is it valid or not
                    if (scanner.hasNextInt()) {
                        age = scanner.nextInt();
                        scanner.nextLine(); 
                    } 
                    else {//If option is not integer
                        System.out.println("Invalid input type. It should be integer!");
                        scanner.nextLine(); 
                        continue; // Exitting the program 
                    }

                
                    System.out.print("Enter hobbies (comma-separated): ");  //Taking hobbies from the user
                    List<String> hobbies = Arrays.asList(scanner.nextLine().split(","));    //Splitting according to ,
                    network.addPerson(name, age, hobbies);  //call the addPerson function
                    break;
                case 2:
                    System.out.println("\n=============== REMOVE A PERSON ===============");
                    System.out.print("\nEnter name: "); //Taking name from the user
                    name = scanner.nextLine();
                    System.out.print("\nEnter timestamp: ");    //Taking timestamp from the user
                    String tstamp = scanner.nextLine();
                    network.removePerson(name, tstamp);     //call the removePerson function
                    break;
                case 3:
                    System.out.println("\n=============== ADDING A FRIENDSHIP ===============");
                    System.out.print("\nEnter first person's name: "); //Taking name from the user
                    String firstPersonName = scanner.nextLine();

                    System.out.print("Enter first person’s timestamp: ");//Taking timestamp from the user
                    tstamp = scanner.nextLine();
                    
                    System.out.print("Enter second person's name: ");//Taking name from the user
                    String secondPersonName = scanner.nextLine();
                    
                    System.out.print("Enter second person’s timestamp:");//Taking timestamp from the user
                    String tstamp1 = scanner.nextLine();

                    //Call Getter method for the people map
                    Map<String, Person> people = network.getPeople();
                    Person person1 = people.get(firstPersonName);   //according to name get values from the map
                    Person person2 = people.get(secondPersonName);
                    
                    //Time format
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String str = sdf.format(person1.timestamp);
                    String str2 = sdf.format(person2.timestamp);
                   
                   //timestamp is compared because in same name different user can be
                    if (str.equals(tstamp) && str2.equals(tstamp1)) {
                        network.addFriendship(firstPersonName, secondPersonName);//addFriendship is called
                    }                   
                    break;
                case 4:
                    System.out.println("\n=============== REMOVING A FRIENDSHIP ===============");
                    System.out.print("\nEnter first person's name: ");//Taking name from the user
                    firstPersonName = scanner.nextLine();

                    System.out.print("Enter first person’s timestamp: ");//Taking timestamp from the user
                    tstamp = scanner.nextLine();

                    System.out.print("Enter second person's name: ");//Taking name from the user
                    secondPersonName = scanner.nextLine();

                    System.out.print("Enter second person’s timestamp:");//Taking timestamp from the user
                    tstamp1 = scanner.nextLine();

                     //Call Getter method for the people map
                    Map<String, Person> people1 = network.getPeople();
                    person1 = people1.get(firstPersonName);//according to name get values from the map
                    person2 = people1.get(secondPersonName);
                    
                    //Time format
                    sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    str = sdf.format(person1.timestamp);
                    str2 = sdf.format(person2.timestamp);
                   
                    //timestamp is compared because in same name different user can be
                    if (str.equals(tstamp) && str2.equals(tstamp1)) {
                        network.removeFriendship(firstPersonName, secondPersonName);//removeFriendship is called
                    }                   

                    break;
                case 5:
                    System.out.println("\n============ FINDING SHORTEST PATH ============");
                    System.out.print("\nEnter start person's name: ");//Taking name from the user
                    String startName = scanner.nextLine();
                    System.out.print("Enter end person's name: ");//Taking name from the user
                    String endName = scanner.nextLine();
                    network.findShortestPath(startName, endName); //findShortestPath is called
                    break;
                case 6:
                    System.out.println("\n=============== SUGGESTING FRIENDS ===============");
                    System.out.print("\nEnter person's name: ");//Taking name from the user
                    String personName = scanner.nextLine();
                    System.out.print("Enter the maximum number of friends to suggest: ");//Taking the number from the user
                    int maxSuggestions ;
                    
                      //Taking the integer and check is it valid or not
                    if (scanner.hasNextInt()) {
                        maxSuggestions = scanner.nextInt();
                        scanner.nextLine(); 
                    } 
                    else {//If option is not integer
                        System.out.println("Invalid input type. It should be integer!");
                        scanner.nextLine(); 
                        continue; // Exitting the program 
                    }



                    System.out.println();
                    network.suggestion(personName, maxSuggestions); //suggestion fnction is called
                    break;
                case 7:
                    System.out.println("\n=============== COUNTING CLUSTER ===============");
                    System.out.println("\nCounting clusters in the social network...");
                    network.countClusters();    //Counting cluster and print how many cluster there is
                    network.countClusters1();   //Printing the detail of the clusters
                    break;
                case 8:
                    //Closing
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid option is chosen!!!");
            }
        }while(true);
    }
}
