import java.util.List;
import java.util.ArrayList;
/**
 * Different from the demo code provided from the instructor i am creating a new class called Cluster
 * This class represents a cluster of people in the social network.
 * A cluster has a list of people in it.
 */
public class Cluster {
    //VARIABLES

    //List of people in the cluster
    private List<Person> peopleincluster;
    //CONSTRUCTOR
    public Cluster(){
        peopleincluster = new ArrayList<>();
    }
    //Setter
    public void addPerson(Person p){
        peopleincluster.add(p);
    }
    //Getter
    public List<Person> getPeople(){
        return peopleincluster;
    }
}
