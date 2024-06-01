import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * This class represents the Social Network.
 * It has methods to add a person, remove a person, add a friendship, remove a friendship,
 * print all people in the network, find the shortest path between two people, suggest friends for a person and count the clusters in the network.
 */
public class SocialNetwork {
    //The scanner object to read the inputs
    private Scanner reader;
    //The People map to keep track of the people in the network
    private Map<String , Person> People;
    //The Graph map to keep track of the friendships between the people
    private Map<Person, List<Person>> Graph;

    /**
     * Constructor for the SocialNetwork class.
     * @param sc the scanner object to read the inputs
     */
    public SocialNetwork(Scanner sc){
        reader = sc;
        People = new HashMap<>();
        Graph = new HashMap<>();
    }
    /**
     * A private helper method to check if the name given by the user is valid.
     * The name can only contain alphabets and spaces.
     * @param sendedName the name given by the user
     * @return true if the name is valid, false otherwise
     */
    private boolean nameChecker(String sendedName){
        for(char x : sendedName.toCharArray()){
            if(!Character.isLetter(x) && x != ' '){
                return false;
            }

        }
        return true;
    }
    /**
     * A private helper method to check if the name exists in the People map.
     * @param name the name to be checked
     * @return true if the name exists, false otherwise
     */
    private boolean nameExists(String name){
        return People.containsKey(name);
    }

    /**
     * The hobbys are enums , i wanted to use enums instead of string to make sure people give the correct hobby.
     * I have given the user the option to choose the hobbies by giving the number of the hobby.
     * I have also given the user the option to give multiple hobbies.
     *
     * So when adding a new person i dont allow the user to give invalid values for person credentials.
     * I am using do-while loops and try-catch blocks to make sure sure that user giving valid inputs for every field.*
     * After creating the person with valid credentials i am adding the person to the People map.
     *
     */
    public void addPerson(){
        System.out.println("Adding a person to the social network please give the following details about person.");
        String validName = "";
        //clear the buffer

        do {
            System.out.print("Name : ");
            String name = reader.nextLine();
            if(nameChecker(name)){
                validName = name;
                break;
            }else{
                System.out.println("Name can only contain alphabets and spaces.");
            }
        } while (true);

        System.out.print("Age : ");
        int age;
        while(true){
            try {
                age = Integer.parseInt(reader.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Age can only be a number.");
            }
        }



        System.out.print("Hobby : ");
        boolean valid = true;
        for(Hobby h : Hobby.values()){
            System.out.print(h + " ");
        }
        System.out.println();



        ArrayList<Hobby> validHobbies = null;
        do {
            String line;
            String[] hobbies;
            System.out.println("Please give the hobbys  numbers starting from 1 with ',' seperated.");
            line = reader.nextLine();
            try{
                hobbies = line.split(",");
                if(hobbies.length < 1){
                    throw new InputMismatchException("Please give atleast one hobby.");
                }else{
                    validHobbies = new ArrayList<>();
                    for(String hobby : hobbies){
                        int hobbyNum = Integer.parseInt(hobby);
                        if(hobbyNum < 1 || hobbyNum > 10){
                            continue;
                        }else{
                            validHobbies.add(Hobby.getHobby(hobbyNum));
                        }
                    }
                    valid = true;
                }

            }catch (Exception e){
                System.out.println(e.toString());
                valid=false;
            }


        }while(!valid);

        Timestamp personCreated = new Timestamp(System.currentTimeMillis());

        if(validHobbies == null){
            System.out.println("No hobbies given.");
            return;
        }
        //Creating the person with valid credentials
        try{
            Person newPerson = new Person(age, validName, validHobbies, personCreated);
            //Adding the person to the People map
            People.put(newPerson.getName(),newPerson);
            //Creating the person's friends list, initially empty.
            List<Person> newPersonFriends = new ArrayList<>();
            //Adding person and his friends list to the Graph map.
            Graph.put(newPerson,newPersonFriends);
            System.out.println(String.format("Person created succesfully.\nCredentials:\nName : %s\nAge : %d\nHobbies: %s\nCreated Time : %s",newPerson.getName(),newPerson.getAge(),newPerson.getHobbies(),newPerson.getJoinedTheNetwork()));

        }catch (Exception e) {
            System.out.println(e.toString());
            System.err.println("Person creation failed. Terminating the program.");
            System.exit(1);
        }

    }

    /**
     * For removing a person from the social network i am asking the user to give the name of the person and the timestamp of when the person joined the network.
     * I am checking if the person exists in the People map and if the timestamp given by the user is the same as the timestamp of when the person joined the network.
     * If the person exists and the timestamps are the same i am removing the person from the People map and also removing the person from the Graph map.
     * I am also removing the person from the friends list of all the other people in the Graph map.
     *
     * Note to instructor : HashMaps has unique key values so there is no need to ask the user for timestamps or checking them for validity.
     * I have added the timestamp check for the sake of the assignment requirements.
     * But it is not necessary for the program to work correctly.
     *

     */
    public Person removePerson(){
        String given_name = "";
        Timestamp validTimestamp = null;
        System.out.println("For removing a person from the social network please give the following details about person.");
        System.out.print("Name : ");
        given_name = reader.nextLine();
        Person removedPerson = null;
        if(nameExists(given_name)){
            String givenTimestamp = "";
            do {
                System.out.print("\nGive the timestamp of when the person joined the network(format is like this : yyyy-MM-dd HH:mm:ss.SSS) : ");
                givenTimestamp = reader.nextLine();
                try{
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                    Date parsedDate = dateFormat.parse(givenTimestamp);
                    validTimestamp = new Timestamp(parsedDate.getTime());
                    break;
                }catch (java.text.ParseException e){
                    System.out.println("Invalid timestamp format.");
                    continue;
                }

            }while(true);
            if(People.get(given_name).getJoinedTheNetwork().equals(validTimestamp)){
                removedPerson=People.get(given_name);
                People.remove(given_name);
                for(Person person : Graph.keySet()){
                   List<Person> personsFriends = Graph.get(person);
                     if(personsFriends.contains(removedPerson)){
                          personsFriends.remove(removedPerson);
                          Graph.put(person,personsFriends);
                     }
                }
                Graph.remove(removedPerson);

                System.out.println(String.format("%s removed from the social network succesfully.",given_name));
                return removedPerson;

            }else{
                System.out.println("No such person exists.");
            }

            }else{
            System.out.println("No such person exists.");
        }


        return removedPerson;
    }

    //This method is used to add a friendship between two people.
    /**
     * For adding a friendship between two people i am asking the user to give the names of the two people and the timestamps of when they joined the network.
     * I am checking if the people exist in the People map and if the timestamps given by the user are the same as the timestamps of when the people joined the network.
     * If the people exist and the timestamps are the same i am adding the people to each others friends list in the Graph map.
     * If the friendship already exists i am not adding the friendship again.
     *
     * Note to instructor : HashMaps has unique key values so there is no need to ask the user for timestamps or checking them for validity.
     * I have added the timestamp check for the sake of the assignment requirements.
     * But it is not necessary for the program to work correctly.
     *
     */
    public void addFriendShip(){
        //For adding a friendship between two people i am asking the user to give the names of the two people and the timestamps of when they joined the network.
        System.out.println("For adding a friendship between two people please give the following details.");
        String person1_name=null,person2_name=null;
        Timestamp person1_timestamp=null,person2_timestamp=null;

        System.out.print("Name of the first person : ");
        person1_name = reader.nextLine();
        if (nameExists(person1_name)) {
            //Checking if the first person exists.
            System.out.print("\nTimestamp of when the first person joined the network(format is like this : yyyy-MM-dd HH:mm:ss.SSS) : ");
            String givenTimestamp = reader.nextLine();
            try {
                //Parsing the timestamp given by the user.
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                Date parsedDate = dateFormat.parse(givenTimestamp);
                person1_timestamp = new Timestamp(parsedDate.getTime());
            } catch (java.text.ParseException e) {
                //If the timestamp is not in the correct format.
                System.out.println("\nInvalid timestamp format. Cant add friendship. Try again.");
                return;
            }
            //Checking if the first person exists.
            Person person1 = People.get(person1_name);
            System.out.print("Name of the second person : ");
            person2_name = reader.nextLine();
            //Checking if the second person exists.
            if (nameExists(person2_name)){
                System.out.print("\nTimestamp of when the second person joined the network(format is like this : yyyy-MM-dd HH:mm:ss.SSS) : ");
                givenTimestamp = reader.nextLine();
                //Parsing the timestamp given by the user.
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                    Date parsedDate = dateFormat.parse(givenTimestamp);
                    person2_timestamp = new Timestamp(parsedDate.getTime());
                } catch (java.text.ParseException e) {
                    //If the timestamp is not in the correct format.
                    System.out.println("\nInvalid timestamp format. Cant add friendship. Try again.");
                    return;
                }
                //Checking if the people exist in the People map and if the timestamps are the same as the timestamps of when the people joined the network.
                if(People.get(person1_name).getJoinedTheNetwork().equals(person1_timestamp) && People.get(person2_name).getJoinedTheNetwork().equals(person2_timestamp)){
                    //If the people exist in the People map and the timestamps are the same as the timestamps of when the people joined the network.
                    Person person2 = People.get(person2_name);
                    //Getting the friends list of the first person and the second person.
                    List<Person> person1_friends = Graph.get(People.get(person1_name));
                    List<Person> person2_friends = Graph.get(People.get(person2_name));
                    //Checking if the friendship already exists.
                    if(person1_friends.contains(person2) && person2_friends.contains(person1)){
                        System.out.println("Friendship already exists.");
                        return;
                    }else{
                        //Adding the friendship between the two people.
                        person1_friends.add(person2);
                        person2_friends.add(person1);
                        Graph.put(person1,person1_friends);
                        Graph.put(person2,person2_friends);
                        System.out.println(String.format("Friendship added between %s and %s succesfully.",person1_name,person2_name));
                        return;
                    }

                }else{
                    //If the timestamps are not the same as the timestamps of when the people joined the network.
                    System.out.println("No such person exists.");
                    return;
                }
            }
        }else{
            //If the first person does not exist.
            System.out.println("No such person exists. Please try again with a valid name.");
            return;
        }

    }

    /**
     * For removing a friendship between two people i am asking the user to give the names of the two people and the timestamps of when they joined the network.
     * I am checking if the people exist in the People map and if the timestamps are the same as the timestamps of when the people joined the network.
     * If the people exist and the timestamps are the same i am removing the friendship between the two people.
     * When removing the friendship i am also removing the eachperson from others friends list.
     */

    public void removeFriendShip() {
        //For removing a friendship between two people i am asking the user to give the names of the two people and the timestamps of when they joined the network.
        System.out.println("For removing a friendship between two people please give the following details.");
        String person1_name = null, person2_name = null;
        Timestamp person1_timestamp = null, person2_timestamp = null;

        //Asking the user for the name of the first person.
        System.out.print("Name of the first person : ");
        person1_name = reader.nextLine();
        if (nameExists(person1_name)) {
            //If first person exists asking the user for the timestamp of when the first person joined the network.
            System.out.print("\nTimestamp of when the first person joined the network(format is like this : yyyy-MM-dd HH:mm:ss.SSS) : ");
            String givenTimestamp = reader.nextLine();
            try {
                //Parsing the timestamp given by the user.
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                Date parsedDate = dateFormat.parse(givenTimestamp);
                person1_timestamp = new Timestamp(parsedDate.getTime());
            } catch (java.text.ParseException e) {
                //If the timestamp is not in the correct format.
                System.out.println("\nInvalid timestamp format.Cant remove friendship. Try again.");
                return;
            }
            //Getting the first person from the People map.
            Person person1 = People.get(person1_name);
            //Asking the user for the name of the second person.
            System.out.print("Name of the second person : ");
            person2_name = reader.nextLine();
            if (nameExists(person2_name)) {
                //If the second person exists asking the user for the timestamp of when the second person joined the network.
                System.out.print("\nTimestamp of when the second person joined the network(format is like this : yyyy-MM-dd HH:mm:ss.SSS) : ");
                givenTimestamp = reader.nextLine();
                try {
                    //Parsing the timestamp given by the user.
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                    Date parsedDate = dateFormat.parse(givenTimestamp);
                    person2_timestamp = new Timestamp(parsedDate.getTime());
                } catch (java.text.ParseException e) {
                    //If the timestamp is not in the correct format cant remove the friendship.
                    System.out.println("\nInvalid timestamp format. Cant remove friendship. Try again.");
                    return;
                }
                //Checking if the people exist in the People map and if the timestamps are the same as the timestamps of when the people joined the network.
                if (People.get(person1_name).getJoinedTheNetwork().equals(person1_timestamp) && People.get(person2_name).getJoinedTheNetwork().equals(person2_timestamp)) {
                    Person person2 = People.get(person2_name);
                    //Getting the friends list of the first person and the second person.
                    List<Person> person1_friends = Graph.get(People.get(person1_name));
                    List<Person> person2_friends = Graph.get(People.get(person2_name));
                    //Checking if the friendship exists between the two people.
                    if (person1_friends.contains(person2) && person2_friends.contains(person1)) {
                        //If the friendship exists removing the friendship between the two people.
                        person1_friends.remove(person2);
                        person2_friends.remove(person1);
                        Graph.put(person1, person1_friends);
                        Graph.put(person2, person2_friends);
                        System.out.println(String.format("Friendship removed between %s and %s succesfully.", person1_name, person2_name));
                        return;
                    } else {
                        //If the friendship does not exist.
                        System.out.println("Friendship does not exist.");
                        return;
                    }
                }
            }
        }
    }

    /**
     * A method to print everyperson in the network.
     */
    public void printPeople(){
        System.out.println("People in the social network : ");
        for(Person p : People.values()){
            System.out.println(String.format("Name : %s\nAge : %d\nHobbies : %s\nJoined the network at : %s",p.getName(),p.getAge(),p.getHobbies(),p.getJoinedTheNetwork()));
        }
    }


    /**
     * For finding the shortest path between two people i am asking the user to give the names of the two people and the timestamps of when they joined the network.
     * I am checking if the people exist in the People map and if the timestamps are the same as the timestamps of when the people joined the network.
     * If the people exist and the timestamps are the same i am finding the shortest path between the two people using the BFS algorithm.
     * I am using a Queue to keep track of the nodes to be visited and a Map to keep track of the parents of the nodes.
     * I am adding the discovered but not visited nodes to the Queue and adding the current node as the parent of these nodes.
     * I am adding the current node to the parentMap as the value for these nodes.
     * I am doing this until i reach the end node and then i am getting the shortest path by going from the end node to the start node.
     * I am adding the current node to the shortest path and getting the parent of the current node.
     */
    public void findShortestPathBetweenTwo(){
        Person startPerson = null,endPerson = null;
        Timestamp startPersonTimestamp = null,endPersonTimestamp = null;

        System.out.println("For finding the shortest path between two people please give the following details.");
        System.out.print("Name of the first person : ");
        String startPersonName = reader.nextLine();
        if(nameExists(startPersonName)) {
            System.out.print("\nTimestamp of when the first person joined the network(format is like this : yyyy-MM-dd HH:mm:ss.SSS) : ");
            String givenTimestamp = reader.nextLine();
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                Date parsedDate = dateFormat.parse(givenTimestamp);
                startPersonTimestamp = new Timestamp(parsedDate.getTime());
            } catch (java.text.ParseException e) {
                System.out.println("\nInvalid timestamp format. Cant find the shortest path. Try again.");
                return;
            }
            if (People.get(startPersonName).getJoinedTheNetwork().equals(startPersonTimestamp)) {
                startPerson = People.get(startPersonName);
            } else {
                System.out.println("No such person exists.");
                return;
            }
        }else{
            System.out.println("No such person exists with given name.");
            return;
        }

        System.out.print("Name of the second person : ");
        String endPersonName = reader.nextLine();
        if(nameExists(endPersonName)) {
            System.out.print("\nTimestamp of when the second person joined the network(format is like this : yyyy-MM-dd HH:mm:ss.SSS) : ");
            String givenTimestamp = reader.nextLine();
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                Date parsedDate = dateFormat.parse(givenTimestamp);
                endPersonTimestamp = new Timestamp(parsedDate.getTime());
            } catch (java.text.ParseException e) {
                System.out.println("\nInvalid timestamp format. Cant find the shortest path. Try again.");
                return;
            }
            if (People.get(endPersonName).getJoinedTheNetwork().equals(endPersonTimestamp)) {
                endPerson = People.get(endPersonName);
            } else {
                System.out.println("No such person exists.");
                return;
            }
        }

        //THE ALGORITHM WORKS LIKE THIS, FIRST WE CHOOSE ARE START NODE AND END NODE,
        // AFTER THAT WE CREATE AN QUEUE TO KEEP TRACK OF THE NODES TO BE VISITED
        // WE ADD DISCOVERED BUT NOT VISITED NODES TO THE QUEUE
        // ALSO BECAUSE WE GO  FROM CURRENT PERSON TO THESE NODES WE CAN ADD THE CURRENT NODE AS THE PARENTS OF THEESE NODES
        // WE ADD THE CURRENT NODE TO THE parentMap AS THE VALUE FOR THESE NODES, LIKE LETS SAY I HAVE AN NODE NAMED YAHYA AND I REACHED IT FROM NODE ALI, THEN ALI WILL BE THE
        // PARENT OF THE NODE YAHYA SO WE ADD IT LIKE parentMap.put(YAHYA,ALI) SO WE CAN GET THE SHORTEST PATH BY GOING FROM THE END NODE TO THE START NODE
        // WE KEEP DOING THIS UNTIL WE REACH THE END NODE, THEN WE CAN GET THE SHORTEST PATH BY GOING FROM THE END NODE TO THE START NODE

        if(startPerson != null && endPerson != null){
            Queue<Person> notvisitedNodes = new LinkedList<>();
            // visited parent
            Map<Person,Person> parentMap = new HashMap<>();

            //first add the startPerson to the queue we will visit there and also add it to the parentMap with null as a parent
            notvisitedNodes.add(startPerson);
            parentMap.put(startPerson,null);

            while(!notvisitedNodes.isEmpty()){
                Person currentPerson = notvisitedNodes.poll();
                if(currentPerson.equals(endPerson)){
                    //We found the end person
                    break;
                }
                List<Person> currentPersonsFriends = Graph.get(currentPerson);

                for(Person friendofCurrent : currentPersonsFriends){
                    if(!parentMap.containsKey(friendofCurrent)){
                        notvisitedNodes.add(friendofCurrent);
                        parentMap.put(friendofCurrent,currentPerson);
                    }
                }

            }

            if(!parentMap.containsKey(endPerson)){
                System.out.println("No path exists between the two people.");
                return;
            }

            List<Person> shortestPath = new ArrayList<>();
            Person current = endPerson;

            while(current != null){
                //ADD THE CURRENT NODE TO THE SHORTEST PATH
                shortestPath.add(current);

                //GET THE PARENT OF THE CURRENT NODE
                current = parentMap.get(current);
            }

            Collections.reverse(shortestPath);

            StringBuilder sb = new StringBuilder();
            for(Person p : shortestPath){
                if(sb.length() > 0){
                    sb.append(" -> ");
                }
                sb.append(p.getName());
            }
            System.out.println(String.format("Shortest path between %s and %s : %s ",startPersonName,endPersonName,sb.toString()));


        }else{
            System.err.print("Not expected error occured. Terminating the program.");
            System.exit(1);
        }



    //END OF BFS
    }

    /**
     * I am using a Suggestion class to keep track of the person to be suggested and the score of the person.
     * This makes it easier to sort the suggestions in descending order.
     * Also it is more readable and understandable.
     * I am using a binary search to insert the suggestions in the correct order.
     * @param list the suggestion list
     * @param personTobeSuggested the person to be suggested
     * @param sug the suggestion
     * @return the index to insert the suggestion
     */
    //buradayim


    /**
     * My score calculation method takes two person objects and checks the similaritys at hobbies and friends
     * for every common hobby total score gets 0.5 points and for every common friends total score gets 1.0 points
     * after checking every hobby and friend for each person we return the total score
     * @param basePerson : base person for checking the similartys between 2
     * @param toBeFriend : the person to be suggested
     * @return double : total score
     */
    private Suggestion returnScore(Person basePerson,Person toBeFriend){
        double score = 0;
        int sameHobbies = 0;
        int sameFriends = 0;
        List<Hobby> basePersonHobbies = basePerson.getHobbies();
        List<Hobby> toBeFriendHobbies = toBeFriend.getHobbies();
        List<Person> basePersonsFriends = Graph.get(basePerson);
        List<Person> toBeFriendFriends = Graph.get(toBeFriend);

        for(Hobby basePersonHobby : basePersonHobbies){
            if(toBeFriendHobbies.contains(basePersonHobby)){
                sameHobbies++;
            }
        }

        for(Person basePersonFriend : basePersonsFriends){
            if(toBeFriendFriends.contains(basePersonFriend)){
                sameFriends++;
            }
        }

        score = (sameFriends) * 1 + (sameHobbies) * 0.5;
        return new Suggestion(toBeFriend,score,sameFriends,sameHobbies);
       // return score;
    }

    private int binarySearch(List<Suggestion> list, Person personToBeSuggested, Suggestion sug) {
        int left = 0; // start index of the list
        int right = list.size() - 1; // end index of the list

        while (left <= right) {
            int mid = left + (right - left) / 2; // get the middle index
            double scoreOfMiddle = returnScore(personToBeSuggested, list.get(mid).getPerson()).getScore();

            if (sug.getScore() > scoreOfMiddle) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private List<Suggestion> createSuggestions(Person personToBeSuggested, int suggestionCount) {
        List<Suggestion> suggestions = new ArrayList<>();
        List<Person> everyPerson = new ArrayList<>(People.values());
        everyPerson.remove(personToBeSuggested);


        // After this, we have a suggestion list sorted in descending order
        for (Person p : everyPerson) {
            Suggestion sug = returnScore(personToBeSuggested, p);


            if (sug.getScore() > 0) {
                int indexToInsert = binarySearch(suggestions, personToBeSuggested, sug);


                suggestions.add(indexToInsert, sug);
            }
        }
        // This part ensures that we are getting the subarray

        List<Suggestion> lastSuggestion = null;
        try {
            lastSuggestion = suggestions.subList(0, Math.min(suggestionCount, suggestions.size()));
        } catch (IndexOutOfBoundsException e) {
            System.err.print("\n An error occurred when creating suggestion sublist: " + e.toString());
        }

        return lastSuggestion;
    }

    public void friendSuggesting() {
        List<Suggestion> suggestions = new ArrayList<>();
        String personName = "";
        int friendsToBeSuggested = 0;
        Person personToBeSuggested = null;
        Timestamp personTimestamp = null;
        System.out.println("For suggesting friends to a person please give the following details.");
        System.out.print("Name of the person : ");
        personName = reader.nextLine();

        if (nameExists(personName)) {
            System.out.print("\nTimestamp of when the second person joined the network(format is like this : yyyy-MM-dd HH:mm:ss.SSS) : ");
            String givenTimestamp = reader.nextLine();
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                Date parsedDate = dateFormat.parse(givenTimestamp);
                personTimestamp = new Timestamp(parsedDate.getTime());
            } catch (java.text.ParseException e) {
                System.out.println("\nInvalid timestamp format. Can't find the shortest path. Try again.");
                return;
            }
            if (People.containsKey(personName)) {
                if (People.get(personName).getJoinedTheNetwork().equals(personTimestamp)) {
                    personToBeSuggested = People.get(personName);
                    System.out.print("How many friend suggestions do you want to get : ");
                    while (true) {
                        try {
                            friendsToBeSuggested = Integer.parseInt(reader.nextLine());
                            if (friendsToBeSuggested < 0) {
                                throw new NumberFormatException();
                            }
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Please give a positive number.");
                        }
                    }

                    suggestions = createSuggestions(personToBeSuggested, friendsToBeSuggested);



                    for (Suggestion suggestion : suggestions) {
                        System.out.printf("%s (Score: %.1f, %d mutual friends, %d common hobbies)%n",
                                suggestion.getPerson().getName(), suggestion.getScore(), suggestion.getMutualfriends(), suggestion.getMutualhobbies());
                    }

                } else {
                    System.out.println("Timestamps don't match.");
                    return;
                }
            }

        } else {
            System.out.println("No such person exists. Please try again.");
            return;
        }
    }

    /**
     * For counting the clusters in the network i am using the BFS algorithm.
     * I am using a Queue to keep track of the nodes to be visited and a List to keep track of the visited nodes.
     * I am adding the discovered but not visited nodes to the Queue and adding the current node to the visited list.
     * I am doing this until the Queue is empty and then i am adding the visited nodes to the clusters list.
     * I am doing this until all the nodes are visited.
     */
    public List<Cluster> countCluster(){
        List<Cluster> clusters = new ArrayList<>();
        List<Person> visited = new ArrayList<>();

        for(Person p : People.values()){
            if(!visited.contains(p)){
                Cluster cluster = new Cluster();
                Queue<Person> notVisitedNodes = new LinkedList<>();
                notVisitedNodes.add(p);
                visited.add(p);
                cluster.addPerson(p);
                while(!notVisitedNodes.isEmpty()){
                    Person currentPerson = notVisitedNodes.poll();
                    List<Person> currentPersonsFriends = Graph.get(currentPerson);
                    for(Person friend : currentPersonsFriends){
                        if(!visited.contains(friend)){
                            notVisitedNodes.add(friend);
                            visited.add(friend);
                            cluster.addPerson(friend);
                        }
                    }
                }
                clusters.add(cluster);

            }
        }

        return clusters;
    }




}
