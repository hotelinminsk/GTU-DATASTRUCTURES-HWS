import java.util.*;

public class Main {
    public static void main(String[] args) {
        SocialNetworkGraph network = new SocialNetworkGraph();
        Scanner scanner = new Scanner(System.in);

        // Adding some people for demonstration
        network.addPerson("John Doe", 25, Arrays.asList(Hobby.ART, Hobby.CINEMA,Hobby.COOKING));
        network.addPerson("Jane Smith", 22, Arrays.asList(Hobby.DANCING, Hobby.FINANCE, Hobby.PHOTOGRAPHY));
        network.addPerson("Alice Johnson", 27, Arrays.asList(Hobby.GAMES, Hobby.MUSIC, Hobby.DANCING));
        network.addPerson("Bob Brown", 30, Arrays.asList(Hobby.COOKING, Hobby.TRAVEL, Hobby.PHOTOGRAPHY));
        network.addPerson("Emily Davis", 28, Arrays.asList(Hobby.MUSIC, Hobby.TRAVEL, Hobby.GAMES));
        network.addPerson("Frank Wilson", 26, Arrays.asList(Hobby.CINEMA, Hobby.FINANCE, Hobby.ART));

        // Adding friendships for demonstration
        network.addFriendship("John Doe", "Jane Smith");
        network.addFriendship("John Doe", "Alice Johnson");
        network.addFriendship("Jane Smith", "Bob Brown");
        network.addFriendship("Emily Davis", "Frank Wilson");

        // Finding shortest path for demonstration
        network.findShortestPath("John Doe", "Bob Brown");

        // Counting clusters for demonstration
        network.countClusters();

        scanner.close();
    }
}
