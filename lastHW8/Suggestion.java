/**
 * Also different from the demo code provided from instructor i am creating a new class called Suggestion for the friend suggestion
 * This class is used to store the friend suggestion of an person and it contains the suggested friend and also similarty score
 * this class makes it easier for me to store the suggestion and also to sort the suggestion based on the score
 *
 * Suggestion class
 *
 */
public class Suggestion {
    //Variables
    private Person person;
    private double score;
    private int mutualfriends;
    private int mutualhobbies;


    //Constructor

    public Suggestion(Person p,double score,int mf , int mh){
        person = p;
        this.score = score;
        mutualfriends = mf;
        mutualhobbies = mh;
    }
    //getters

    public Person getPerson(){return person;}

    public double getScore() {
        return score;
    }

    public int getMutualfriends() {
        return mutualfriends;
    }

    public int getMutualhobbies(){return mutualhobbies;}
}
