import java.util.*;
import java.text.SimpleDateFormat;

public class SocialNetworkGraph {
    Map<String, Person> people = new HashMap<>();
    Map<Person, List<Person>> friendships = new HashMap<>();

    /**
     * Getter method for the people map
     */
    public Map<String, Person> getPeople() {
        return people;
    }


    /**
     * Method to add a person
     * @param name person name
     * @param age person age
     * @param hobbies person's hobbies
     * 
     */
    public void addPerson(String name, int age, List<String> hobbies) 
    {
        //Creating person object (using name,age,hobbies and time) 
        Person person = new Person(name, age, hobbies, new Date());
        //Put person details to people map
        people.put(name, person);
        //Put person frienship detail to friendship map
        friendships.put(person, new ArrayList<>());
        //Added message is printed
        System.out.println("Person added: " + person);
    }

    
    /**
     * Method to remove a person
     * @param name
     * @param timestamp
     * 
     */
    public void removePerson(String name, String timestamp) 
    {   
        //name is key. Using name, get the value and assign to person object
        Person person = people.get(name);
        //If person object is not null
        if (person != null) 
        {   
            //Format of the date and time
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //timestamp is converting and storing in the string
            String str = sdf.format(person.timestamp);
            
            //If timestamp is matched
            if(str.equals(timestamp))
            {
                //removing using name
                people.remove(name);
                //remove from friendshp as well
                friendships.remove(person);


                //Removing the specified person from the list
                for (List<Person> listOfFriends : friendships.values()) 
                {
                    listOfFriends.remove(person);
                }   
                //printing the message
                System.out.println("According to given information. Person removed.: " + name);
            }
        } 
        else {
            System.out.println("There is no person.(name or timetsmap is not matched!)");
        }
    }

    
    /**
     * Method to add a friendship
     * @param name1
     * @param name2
     * 
     * Do friendship connection between name1 and name2
     */
    public void addFriendship(String name1, String name2) {
        Person person1 = people.get(name1);
        Person person2 = people.get(name2);
        if (person1 != null && person2 != null) {
            friendships.get(person1).add(person2);
            friendships.get(person2).add(person1);
            System.out.println("Friendship added between " + person1.name + " and " + person2.name);
        } else {
            System.out.println("One or both persons not found in the network.");
        }
    }

    
    /**
     * Method to remove a friendship
     * @param name1
     * @param name2
     * 
     * This function is like addFriendship jsu add parts changed with remove
     * 
     */
    public void removeFriendship(String name1, String name2) {
        Person person1 = people.get(name1);
        Person person2 = people.get(name2);
        if (person1 != null && person2 != null) {
            friendships.get(person1).remove(person2);
            friendships.get(person2).remove(person1);
            System.out.println("Friendship removed between " + person1.name + " and " + person2.name);
        } else {
            System.out.println("One or both persons not found in the network.");
        }
    }

    
    /**
     * Method to find the shortest path using BFS
     * @param startName
     * @param endName
     * 
     */
    public void findShortestPath(String startName, String endName) {
        Person startPerson = people.get(startName);
        Person endPerson = people.get(endName);
        
        //Keeping track of previous person to visit
        Queue<Person> queue = new LinkedList<>();

        //In order to avoid visiting persons again, keeping the track of them
        Set<Person> visited = new HashSet<>();

        //Storing the previous visited person in the shotest path from startName to endName
        Map<Person, Person> previousPerson = new HashMap<>();

        //If At least startName or endName is not exist ,then printing the messgae  
        if (startPerson == null || endPerson == null) {
            System.out.println("One or both persons not found in the network.");
            return;
        }

        //startPerson added to queue to initiate bfs
        queue.add(startPerson);
        
        //startPerson added to visited set for marking as visited
        visited.add(startPerson);

        //there is no previous person for startPerson. This is starting point of path
        previousPerson.put(startPerson, null);

        //This part of while block run as bfs. The datail explaint it bfs. Extra print the path and and previousPerson(it is neede when printing).
        while (!queue.isEmpty()) {
            Person currentPerson = queue.poll();
            //Checking If reach end or not
            if (currentPerson.equals(endPerson)) {
                //printing the path
                printPath(startPerson, endPerson, previousPerson);
                return;
            }
            for (Person neighbor : friendships.get(currentPerson)) {
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                    previousPerson.put(neighbor, currentPerson);
                }
            }
        }
        System.out.println("There is no path between " + startName + " and " + endName);
    }

    /**
     * Printing the ath via this function
     * @param start
     * @param end
     * @param prev
     */
    private void printPath(Person start, Person end, Map<Person, Person> prev) {
        List<Person> path = new ArrayList<>();
        //Iterates from end to start
        for (Person at = end; at != null; at = prev.get(at)) {
            path.add(at);//addede to path
        }
        //because of iterating from end to start, needs to reverse the path
        Collections.reverse(path);

        //In below part ,printing the path
        StringBuilder shortestPath = new StringBuilder("Shortest path: ");
        for (int i = 0; i < path.size(); i++) {
            shortestPath.append(path.get(i).name);
            if (i < path.size() - 1) {
                shortestPath.append(" -> ");
            }
        }
        System.out.println(shortestPath);
    }


    /**
     * This function just count the cluster
     */
    public void countClusters() {
        Set<Person> visited = new HashSet<>(); //keep tacking of the people whic are visited or not
        int clusterCount = 0;

        for (Person person : people.values()) {
            if (!visited.contains(person)) {    //If Checking the current person is visited or not
                List<Person> cluster = new ArrayList<>();
                bfs(person, visited, cluster);// Call bfs method to mark visited people and added those people to relevant cluster
                clusterCount++;
            }
        }
        System.out.println("Number of clusters found: " + clusterCount);
    }
    
    /**
     * Cluster details are printed via this function
     * Its work like countClusters jsut printing added to see which cluster which person/peoople have 
     */
    public void countClusters1() {
        Set<Person> visited = new HashSet<>();
        int clusterCount = 0;
    
       for (Person person : people.values()) {
            if (!visited.contains(person)) {//If Checking the current person is visited or not
                List<Person> cluster = new ArrayList<>();
                bfs(person, visited, cluster);// Call bfs method to mark visited people and added those people to relevant cluster
                System.out.println("Cluster " + (++clusterCount) + ":");//Cluster number is printed
                for (Person person_ : cluster) {
                    System.out.println(person_.name);//Person/People in relevant cluster is printed
                }
             }
        }
    
    }


 
    /**
     * breadth-first-serach
     * @param start starting person for bfs
     * @param visited   visited person to avoid revisiting
     * @param cluster   //connected person
     */
    private void bfs(Person start, Set<Person> visited, List<Person> cluster) {
        Queue<Person> queue = new LinkedList<>();
        queue.add(start);   //start added to queue to initiate bfs
        visited.add(start);  //start added to visited set for marking as visited

        //The loop executed untill queue is not empth
        while (!queue.isEmpty()) {
            Person current = queue.poll();  //Dequeue a current person ( removes and returning the head element of queue)
            cluster.add(current);   //current person added to cluster

            //Iterating over neighbours of current person
            for (Person neighbor : friendships.get(current)) {
                //this lines is checking whether neighbour is visited or not.
                //Ifit is not visited then the inside of if block will be run
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);    //neigbour added to queue
                    visited.add(neighbor);  //neigbour updated as visited
                }
            }
        }
    }

    /**
     * This function calculating the score and retruning the score value
     * @param person1
     * @param person2
     * @return double
     */
    public double calculateScore(Person person1, Person person2) {
        double totalScore = 0;
        List<Person> friends1 = friendships.get(person1);   //frienship of person1 is stored in list(friends1)
        List<Person> friends2 = friendships.get(person2);   //frienship of person2 is stored in list(friends2)
        //Counters
        int mutualFriendCount = 0;
        int commonHobbiesCount = 0;

        //When firends1 list iterates , 
        //if any person contains in friends2 list, tehn incerementing the mutualFriendCount
        for (Person friend : friends1) {
            if (friends2.contains(friend)) {
                mutualFriendCount++;
            }
        }
        
        //Like above it work for common hobbies
        for (String hobby : person1.hobbies) {
            if (person2.hobbies.contains(hobby)) {
                commonHobbiesCount++;
            }
        }
        //Total score calculated according to given formula in HW8.pdf
        totalScore = mutualFriendCount + (commonHobbiesCount * 0.5);
        //Returning the score
        return totalScore;
    }

    /**
     * This function for mutual friend and common hobby counts 
     * And also printed at the end of the function
     * 
     * @param person    persom
     * @param suggestedPerson suggested person
     *  
     */
    public void countMutualFriendsAndCommonHobbies(Person person, Person suggestedPerson) 
    {
        //Below two lines to retrieve the lists of friends for both person and
        // suggested person from friendships map
        //And Person's friwnds are assgined to a list of Person object
        List<Person> friend1 = friendships.get(person);
        List<Person> friend2 = friendships.get(suggestedPerson);

       
        //Retrieving the lists of hobbies for both person and suggested person
        List<String> hobbies1 = person.hobbies;
        List<String> hobbies2 = suggestedPerson.hobbies;

        //Counts for mutual friend and common hobbies
        int mutualFriendCount = 0;
        int commonHobbiesCount = 0;

        //This for block is checking for person and suggestedPerson whether have same friend or not 
        for (Person tmp : friend1) {
            if (friend2.contains(tmp)) {
                mutualFriendCount++;    //Increment the mutual friend counter
            }
        }
    
        //This for block is checking for person and suggestedPerson whether have same hobbies or not 
        for (String tmp1 : hobbies1) {
            if (hobbies2.contains(tmp1)) {
                commonHobbiesCount++;   //Increment the common hobbies counter
            }
        }

        //Printing message for mutual friends and coomon hobbies
        System.out.println(", Mutual Friends: " + mutualFriendCount + ", Common Hobbies: " + commonHobbiesCount + ")");
    }

    /**
     * This function is for suggestion
     * Number of suggested function is printed with their score, mutual friends and common hobbies
     * @param name
     * @param suggestionNumber user enter the number of max suggestion
     */
    public void suggestion(String name, int suggestionNumber) 
    {
        // name is key. Getting the Person object which the key is name
        Person person = people.get(name);
        
        //If person is null, then printing the below message
        if (person == null) {
            System.out.println("There is no person in the network.");
            return;
        }
        
        //This is a list which store the people which has not have same name and friendship
        List<Person> suggestedPeople = new ArrayList<>();

        for (Person personComp : people.values()) 
        {
            //If person is not same with the other person
            if (!personComp.equals(person)) 
            {
                //If there is no friendship between 
                if(!friendships.get(person).contains(personComp))
                {
                    // The score will be clculated via calculateScore function and return the value in double type
                    double score = calculateScore(person, personComp);
                    if (score > 0) {
                        suggestedPeople.add(personComp);    //Added to suggestedPeople List
                    }
               }
            }
        }

        // Sorting suggestedPeople according to descenting order (lambda expression)
        suggestedPeople.sort((person1, person2) -> 
        {
            double score1 = calculateScore(person, person1); // Calculating the score between person and person1
            double score2 = calculateScore(person, person2); // Calculating the score between person and person2
            return Double.compare(score2, score1);  //If score1 is less than score2, its returns a negative number
        });                                         //person2 should be printed before the person1 (for descending order)


       //This count for maximum number of friends to suggest
        int count = 0;
        for (Person suggestedPerson : suggestedPeople) {
            //If count is equal or bigger than suggestion number , then loop is broken
            if (suggestionNumber <= count) {
                break;
            }
            
            // The score will be clculated via calculateScore function and return the value in double type
            double score = calculateScore(person, suggestedPerson);

            //Printing the person name and the score of that person
            System.out.print(suggestedPerson.name + " (Score: " + score );

            //Call the below function to print mutual friend and common hobbie numbers
            countMutualFriendsAndCommonHobbies(person, suggestedPerson);
            count++;
        }
    }
}
