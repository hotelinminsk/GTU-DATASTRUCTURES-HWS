import java.util.List;
import java.util.Scanner;

/**
 * This class represents the Command Line Interface for the Social Network Analysis.
 * It has a menu with options to add a person, remove a person, add a friendship, remove a friendship,
 * find the shortest path between two people, suggest friends for a person and count the clusters in the network.
 */
public class CLI {
    //VARIABLES
    private SocialNetwork theNetwork;
    private Scanner sc;
    //CONSTRUCTOR
    public CLI(Scanner sc){
        this.sc = sc;
        theNetwork = new SocialNetwork(sc);
    }
    //METHODS
    private int  menu(){
        int choice;
        System.out.println("===== Social Network Analysis Menu =====");
        System.out.println("1. Add person");
        System.out.println("2. Remove person");
        System.out.println("3. Add friendship");
        System.out.println("4. Remove friendship");
        System.out.println("5. Find shortest path");
        System.out.println("6. Suggest friends");
        System.out.println("7. Count clusters");
        System.out.println("8. Exit");
        System.out.print("Please select an option: ");

        try{
            choice = sc.nextInt();
            return choice;
        }catch (NumberFormatException e){
            System.out.println("Invalid input please give an number.");
            return 9;
        }
    }
    //Prints the clusters
    private void printCluster(List<Cluster> clusters){
        System.out.println("Number of clusters: " + clusters.size());

        for(int i = 0; i < clusters.size(); i++){
            System.out.println("Cluster " + (i+1) + ":");
            for(Person p : clusters.get(i).getPeople()){
                System.out.println(p.getName());
            }
        }
    }
    //Main method that calls the menu and call the wanted operation's method till the user wants to exit
    public void caller(){
        boolean loop = true;
        do {
            int choice = menu();
            if(choice < 1 || choice > 8 ){
                System.err.println("Invalid input try again.");
            }else{
                sc.nextLine();
                switch (choice){
                    case 1:
                        theNetwork.addPerson();
                        break;
                    case 2:
                        theNetwork.removePerson();
                        break;
                    case 3:
                        theNetwork.addFriendShip();
                        break;
                    case 4:
                        theNetwork.removeFriendShip();
                        break;
                    case 5:
                        theNetwork.findShortestPathBetweenTwo();
                        break;
                    case 6:
                        theNetwork.friendSuggesting();
                        break;
                    case 7:
                        printCluster(theNetwork.countCluster());
                        break;
                    case 8:
                        loop = false;
                        break;
                    default:
                        break;
                }

            }
        }while(loop);

    };

}
