
import java.sql.Timestamp;
import java.util.List;

/**
 * This class represents a person in the social network.
 * A person has an age, a name, a list of hobbies and a timestamp of when they joined the network.
 */
public class Person {
    //VARIABLES
    private int age;
    private String name;
    private List<Hobby> hobbies;
    private Timestamp joinedTheNetwork;

    //CONSTRUCTOR

    /**
     * Constructor for the Person class.
     * @param age age of the person
     * @param name name of the person
     * @param hobbies hobbies ArrayList of the person
     * @param joinedTheNetwork timestamp of when the person joined the network
     */
    public Person(int age, String name, List<Hobby> hobbies, Timestamp joinedTheNetwork) {
        this.age = age;
        this.name = name;
        this.hobbies = hobbies;
        this.joinedTheNetwork = joinedTheNetwork;
    }

    //GETTERS
    //age getter

    /**
     * Getter for the age of the person.
     * @return int: age of the person
     */
    public int getAge(){return age;}

    //name getter

    /**
     * Getter for the name of the person.
     * @return String: name of the person
     */
    public String getName(){return name;}

    //hobbies getter

    /**
     * Getter for the hobbies of the person.
     * @return List: hobbies of the person
     */
    public List<Hobby> getHobbies(){return hobbies;}

    //joinedTheNetwork getter

    /**
     * Getter for the timestamp of when the person joined the network.
     * @return Timestamp: timestamp of when the person joined the network
     */
    public Timestamp getJoinedTheNetwork(){return joinedTheNetwork;}


    //SETTERS

    //age setter

    /**
     * Setter for the age of the person.
     * @param age: age of the person
     */
    public void setAge(int age){this.age = age;}
    /**
     * Setter for the name of the person.
     * @param name: name of the person
     */
    public void setName(String name){this.name = name;}

    //hobbies setter

    /**
     * Setter for the hobbies of the person.
     * @param hobbies: hobbies of the person
     */
    public void setHobbies(List<Hobby> hobbies){this.hobbies = hobbies;}

    /**
     * Setter for the timestamp of when the person joined the network.
     * @param joinedTheNetwork: timestamp of when the person joined the network
     */
    public void setJoinedTheNetwork(Timestamp joinedTheNetwork) {
        this.joinedTheNetwork = joinedTheNetwork;
    }
}
