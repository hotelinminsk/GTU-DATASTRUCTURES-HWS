import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

/**
 * This class generates a random input file for the StockDataManager program.
 * The input file contains a sequence of commands to be executed by the StockDataManager.
 * The commands are randomly generated and include ADD, REMOVE, SEARCH, and UPDATE operations.
 * The number of nodes, add, remove, search, and update operations are specified by the user.
 * The generated input file is saved to a file named "randominput.txt".

 */
public class randomInputGenerator {
    // LinkedList to store the operations
    private LinkedList<String> operations = new LinkedList<>();
    // Variables to store the user input
    private int nodeCountInstruction;
    private int addOperationCountInstruction;
    private int removeOperationCountInstruction;
    private int searchOperationCountInstruction;
    private int updateOperationCountInstruction;
    private int instructionLineCount = 0;
    private int nodeCount = 0;
   // Constants for the maximum values of price, volume, and market cap
    private static final long maxprice = 2000L;
    private static final long maxvolume = 100000000L;
    private static final long maxmarketcap = 1000000000L;
    private final String filename = "randominput.txt";
    private LinkedList<String> usedSymbols;
    private static final Random rand = new Random();

    /**
     * Displays the menu to get the user input for the number of nodes and operations.
     * The user input is stored in the corresponding variables.
     * The total number of instructions is calculated based on the user input.

     */
    private void menu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the random input generator");
        System.out.println("To generate a random input file give:");
        try {
            System.out.print("1) Node Count = ");
            nodeCountInstruction = sc.nextInt();
            System.out.print("2) Add Operation Count = ");
            addOperationCountInstruction = sc.nextInt();
            System.out.print("3) Remove Operation Count = ");
            removeOperationCountInstruction = sc.nextInt();
            System.out.print("4) Search Operation Count = ");
            searchOperationCountInstruction = sc.nextInt();
            System.out.print("5) Update Operation Count = ");
            updateOperationCountInstruction = sc.nextInt();
        } catch (Exception e) {
            System.err.println("Invalid input");
            e.printStackTrace();
            System.exit(1);
        }
    }
    // Main method to create the random input file
    public static void main(String[] args) {
        randomInputGenerator rig = new randomInputGenerator();
    }

    /**
     * Constructor for the randomInputGenerator class.
     * Initializes the menu to get the user input for the number of nodes and operations.
     * Calculates the total number of instructions based on the user input.
     * Initializes the usedSymbols LinkedList to store the symbols used in the input file.
     * Initializes the operations LinkedList to store the ADD, REMOVE, SEARCH, and UPDATE operations.
     * Calls the createFile method to generate the random input file.
     * The generated input file is saved to a file named "randominput.txt".
     */
    public randomInputGenerator() {
        menu();
        instructionLineCount = nodeCountInstruction + addOperationCountInstruction + removeOperationCountInstruction + searchOperationCountInstruction + updateOperationCountInstruction;
        usedSymbols = new LinkedList<>();
        operations.add("ADD");
        operations.add("REMOVE");
        operations.add("UPDATE");
        operations.add("SEARCH");
        createFile(filename, instructionLineCount);
    }

    /**
     * Randomly selects an operation from the operations LinkedList based on the remaining operations to be performed.
     * The ADD operation is selected if the number of nodes is less than the nodeCountInstruction.
     * If selected operations instructions are completed, the corresponding operation is removed from the operations LinkedList.
     * This removing operation ensures that the remaining operations are selected randomly.
     * The selected operation is returned as a String.
     * @return String - The selected operation (ADD, REMOVE, SEARCH, or UPDATE).
     */
    public String operationPicker() {
        if (operations.isEmpty()) {
            return null;
        }

        if (nodeCount < nodeCountInstruction) {
            return "ADD";
        }

        if (addOperationCountInstruction == 0 && operations.contains("ADD")) {
            operations.remove("ADD");
        } else if (removeOperationCountInstruction == 0 && operations.contains("REMOVE")) {
            operations.remove("REMOVE");
        } else if (searchOperationCountInstruction == 0 && operations.contains("SEARCH")) {
            operations.remove("SEARCH");
        } else if (updateOperationCountInstruction == 0 && operations.contains("UPDATE")) {
            operations.remove("UPDATE");
        }

        int index = rand.nextInt(operations.size());
        return operations.get(index);
    }

    /**
     * Generates a random symbol for the ADD operation.
     * The symbol is generated based on the instructionLineCount and the usedSymbols LinkedList.
     * The generated symbol is added to the usedSymbols LinkedList to avoid duplication.
     * @return String - The randomly generated symbol for the ADD operation.
     */
    private String getRandomSymbol() {
        int symbolInt;
        do {
            symbolInt = Math.abs(rand.nextInt()) % instructionLineCount;
        } while (usedSymbols.contains("SYM" + symbolInt));
        usedSymbols.add("SYM" + symbolInt);
        return "SYM" + symbolInt;
    }

    /**
     *  Creates the ADD command with a random symbol, price, volume, and market cap.
     *  The price, volume, and market cap are generated randomly within the maximum values.
     * @return String - The ADD command with a random symbol, price, volume, and market cap.
     */
    private String createAddCommand() {
        return "ADD " + getRandomSymbol() + " " + Math.abs(rand.nextLong()) % maxprice + " " + Math.abs(rand.nextLong()) % maxvolume + " " + Math.abs(rand.nextLong()) % maxmarketcap;
    }

    /**
     * Creates the REMOVE command with a random symbol.
     * The symbol is randomly selected from the usedSymbols LinkedList.
     * The selected symbol is removed from the usedSymbols LinkedList to avoid duplication.
     * @return String - The REMOVE command with a random symbol.
     */
    private String createRemoveCommand() {
        return "REMOVE " + getRandomSymbolForRemoving();
    }

    /***
     * Creates the SEARCH command with a random symbol.
     * The symbol is randomly selected from the usedSymbols LinkedList.
     * @return String - The SEARCH command with a random symbol.
     */

    private String createSearchCommand() {
        return "SEARCH " + getRandomSymbolForSearching();
    }

    /**
     * Creates the UPDATE command with a random symbol, price, volume, and market cap.
     * The symbol is randomly selected from the usedSymbols LinkedList.
     * The price, volume, and market cap are generated randomly within the maximum values.
     * @return String - The UPDATE command with a random symbol, price, volume, and market cap.
     */
    private String createUpdateCommand() {
        String symbolFirst = getRandomSymbolForSearching();
        long priceNew = Math.abs(rand.nextLong()) % maxprice;
        long volumeNew = Math.abs(rand.nextLong()) % maxvolume;
        long marketCapNew = Math.abs(rand.nextLong()) % maxmarketcap;
        return String.format("UPDATE %s %d %d %d", symbolFirst, priceNew, volumeNew, marketCapNew);
    }
    /**
     * Randomly selects a symbol from the usedSymbols LinkedList for the SEARCH operation.
     * The selected symbol is returned as a String.
     * @return String - The randomly selected symbol for the SEARCH operation.
     */
    private String getRandomSymbolForSearching() {
        int randomIndex = Math.abs(rand.nextInt()) % usedSymbols.size();
        return usedSymbols.get(randomIndex);
    }
    /**
     * Randomly selects a symbol from the usedSymbols LinkedList for the REMOVE operation.
     * The selected symbol is removed from the usedSymbols LinkedList to avoid duplication.
     * The selected symbol is returned as a String.
     * @return String - The randomly selected symbol for the REMOVE operation.
     */
    private String getRandomSymbolForRemoving() {
        if (usedSymbols.isEmpty()) {
            return null; // No symbols to remove
        }
        int randomIndex = Math.abs(rand.nextInt()) % usedSymbols.size();
        String usedSymbol = usedSymbols.get(randomIndex);
        usedSymbols.remove(randomIndex);
        return usedSymbol;
    }
    /**
     * Creates a command based on the randomly selected operation.
     * The command is created for the ADD, REMOVE, SEARCH, and UPDATE operations.
     * The command is returned as a String.
     * @return String - The command created based on the randomly selected operation.
     */
    private String commandCreator() {
        String command = operationPicker();
        if (command == null) {
            return null;
        } else if (nodeCount < nodeCountInstruction) {
            nodeCount++;
            return createAddCommand();
        }
        String commandLine;
        switch (command) {
            case "ADD":
                addOperationCountInstruction--;
                commandLine = createAddCommand();
                break;
            case "REMOVE":
                removeOperationCountInstruction--;
                commandLine = createRemoveCommand();
                break;
            case "SEARCH":
                searchOperationCountInstruction--;
                commandLine = createSearchCommand();
                break;
            case "UPDATE":
                updateOperationCountInstruction--;
                commandLine = createUpdateCommand();
                break;
            default:
                System.out.println("Unknown command: " + command);
                commandLine = null;
                break;
        }
        return commandLine;
    }
    /**
     * Creates the random input file with the specified filename and number of commands.
     * The commands are generated using the commandCreator method.
     * The commands are written to the file using a PrintWriter.
     * @param filename - The name of the file to create.
     * @param commandNum - The number of commands to generate.
     */
    private void createFile(String filename, int commandNum) {
        try (PrintWriter file = new PrintWriter(new FileWriter(filename))) {
            for (int i = 0; i < commandNum; i++) {
                String commandLine = commandCreator();
                if (commandLine == null) {
                    throw new Exception("No more operations to perform");
                }
                file.println(commandLine);
            }
        } catch (Exception e) {
            System.err.println("Error when creating random input file.");
            e.printStackTrace();
        }
    }
}
