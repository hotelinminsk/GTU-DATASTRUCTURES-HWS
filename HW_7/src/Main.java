import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Main class to test the StockDataManager class
 * The main method reads a file with commands to test the StockDataManager class
 * The commands are:
 * ADD <stock_symbol> <price> <volume> <marketcap>
 * REMOVE <stock_symbol>
 * SEARCH <stock_symbol>
 * UPDATE <stock_symbol> <new_price> <new_volume> <new_marketcap>
 */
public class Main {
    private static int addcount = 0;
    private static int removecount = 0;
    private static int searchcount = 0;

    private static int updatecount = 0;


    private static long addtime = 0;
    private static long removetime = 0;
    private static long searchtime = 0;
    private static long updatetime = 0;
    /**
     * Main method to test the StockDataManager class
     * @param args input file with randomly generated commands
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Main <input_file>");
            return;
        }

        String inputFile = args[0];
        StockDataManager manager = new StockDataManager();

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                processCommand(line, manager);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Average time for ADD operation: " + addtime / addcount + " nanoseconds");
        System.out.println("Average time for REMOVE operation: " + removetime / removecount + " nanoseconds");
        System.out.println("Average time for SEARCH operation: " + searchtime / searchcount + " nanoseconds");
        System.out.println("Average time for UPDATE operation: " + updatetime / updatecount + " nanoseconds");

        //AFTER READING THE TREE FROM RANDOMLY GENERATED COMMANDS
        //WE PERFORM SOME TRAVERSALS TO SHOW THE TREE

//        manager.inorderTraversal();
//        manager.postorderTraversal();
//        manager.preorderTraversal();

        //AFTER SHOWING THE TREE WE PERFORM A PERFORMANCE ANALYSIS ON THE AVL TREE





        //This sizes list is used to test the performance of the avltree
        //it contains the data sizes from 10 to 1000 with a step of 10
        List<Integer> sizes = new ArrayList<>();
        for(int i = 10 ; i<1000;i+=10 ){
            sizes.add(i);
        }

        StockDataManager manager2 = new StockDataManager();
        // Perform a simple performance analysis
        performPerformanceAnalysis(manager2, sizes);

    }

    /**
     * Process a command line and call the appropriate method in the StockDataManager class
     * @param line command line to process
     * @param manager StockDataManager instance to call the appropriate method
     */
    private static void processCommand(String line, StockDataManager manager) {
        String[] tokens = line.split(" ");
        String command = tokens[0];

        switch (command) {
            case "ADD":
                addcount++;
                long start = System.nanoTime();
                manager.addOrUpdateStock(tokens[1], Double.parseDouble(tokens[2]), Long.parseLong(tokens[3]), Long.parseLong(tokens[4]));
                long end = System.nanoTime();
                addtime += end - start;
                break;
            case "REMOVE":
                removecount++;
                long start1 = System.nanoTime();
                manager.removeStock(tokens[1]);
                long end1 = System.nanoTime();
                removetime += end1 - start1;
                break;
            case "SEARCH":
                searchcount++;
                long start2 = System.nanoTime();
                Stock stock = manager.searchStock(tokens[1]);
                long end2 = System.nanoTime();
                searchtime += end2 - start2;
                if (stock != null) {

                    System.out.println(stock);
                } else {
                    System.out.println("Stock not found: " + tokens[1]);
                }
                break;
            case "UPDATE":
                updatecount++;
                long start3 = System.nanoTime();
                manager.updateStock(tokens[1], Double.parseDouble(tokens[2]), Long.parseLong(tokens[3]), Long.parseLong(tokens[4]));
                long end3 = System.nanoTime();
                updatetime += end3- start3;
                break;
            default:
                System.out.println("Unknown command: " + command);
                break;
        }
    }
    /**
     * Method to find the threshold value for the data points collected for performance analysis
     * if these data points are above the threshold value, they are replaced with value one before them + threshold value
     *
     * We traverse the data list and calculate the difference between adjacent elements then we take the average of these differences
     * and multiply it by 1.1 to get the threshold value
     *
     *
     * @param data list of data points
     * @return threshold value
     */
    private static  double findthreshold(List<Long> data){
        long avgDifference = 0;

        for(int i=0;i<data.size() - 1;i++){
            avgDifference += data.get(i+1)  - data.get(i);
        }
        avgDifference = avgDifference/data.size();

        return avgDifference * 1;
    }
    /**
     * Method to perform performance analysis on the avltree
     * We measure the time taken for each operation for different data sizes
     * for every data size we first insert the data to the newly created avltree and then measure the time taken for the one individual operation
     * we do this operation on data size number of times and then take the average of these times to get more accurate results
     * We do this for all the data sizes and all operations
     * @param manager StockDataManager instance to test but this is not the same instance as the one in the main method because we create a new instance for each data size
     *                to get more accurate results
     *
     * @param sizes list of data sizes to test
     */
    private static void performPerformanceAnalysis(StockDataManager manager, List<Integer> sizes) {
        // Create lists to store the data points and times for each operation
        List<Integer> dataPointsX = new ArrayList<>();
        List<Long> addTimes = new ArrayList<>();
        List<Long> removeTimes = new ArrayList<>();
        List<Long> searchTimes = new ArrayList<>();
        List<Long> updateTimes = new ArrayList<>();
        long avgForShowStart,avgForShowEnd;
        // Create a RandomStringGenerator instance to generate random strings
        RandomStringGenerator rsg =new RandomStringGenerator();
        //Test for the search operation
        for (int size : sizes) {
            //create a new instance of the avltree for each data size
            manager = new StockDataManager();
            // Add the data size to the data points list to show on the x-axis
            dataPointsX.add(size);
            // Generate random stocks and add them to the avltree
            //after this loop our avl tree is filled with data of size equal to the size variable
            List<String> generatedStrings = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                String randomString = rsg.generateRandomString(4);
                generatedStrings.add(randomString);
                manager.addOrUpdateStock(randomString, Math.random() * 100, (long) (Math.random() * 1000000), (long) (Math.random() * 1000000000));
            }
            //After we filled the tree with data of size equal to the size variable
            // we perform the search operation on the tree for the same data size
            // we do this operation size number of times and then take the average of these times
            // to get more accurate results, then add the average time to the searchTimes list
            long start = System.nanoTime();
            for (int i = 0; i < size; i++) {
                int randomIndex = (int) (Math.random() * (generatedStrings.size()));
                String searchString = generatedStrings.get(randomIndex);
                manager.searchStock(searchString);
            }
            long stop = System.nanoTime();
            searchTimes.add((stop - start)/1000);

            //Print the average time taken for the search operation for the data size
            //System.out.println("For data size : " + size + " Search time is : " + (stop - start)/10 + " nanoseconds");

        }
        //Test for the add operation
        //We do the same thing as we did for the search operation
        //we first fill the tree with data of size equal to the size variable
        //then we perform the add operation on the tree for the same data size
        for(int size:sizes){
            //create a new instance of the avltree for each data size
            manager = new StockDataManager();
            // Add the data size to the data points list to show on the x-axis
            List<String> generatedStrings = new ArrayList<>();
            // Generate random stocks and add them to the avltree
            for(int i=0;i<size ; i++){
                String randomString = rsg.generateRandomString(4);
                generatedStrings.add(randomString);
                manager.addOrUpdateStock(randomString, Math.random() * 100, (long) (Math.random() * 1000000), (long) (Math.random() * 1000000000));
            }

            //We have inserted nodes equal to the amount of size variable
            long addtimes = 0;

            for(int i = 0;i<size;i++){
                String randomString;
                randomString = rsg.generateRandomString(5);
                long start = System.nanoTime();
                //We add a individual node to the tree and calculate the time taken for this operation
                manager.addOrUpdateStock(randomString, Math.random() * 100, (long) (Math.random() * 1000000), (long) (Math.random() * 1000000000));
                long end = System.nanoTime();
                //We remove the node we just added to the tree, so that the tree remains the same as it was before
                manager.removeStock(randomString);
                //We add the time taken for the add operation to the addtimes variable
                addtimes += end - start;
            }
            //We take the average of the times taken for the add operation and add it to the addTimes list
            addTimes.add(addtimes/1000);
            //Print the average time taken for the add operation for the data size
            //System.out.println("For data size : " + size + " avg  Add time is : " + (addtimes/10) + " nanoseconds");

        }
        //Test for the remove operation
        //We do the same thing as we did for the search and add operations
        //we first fill the tree with data of size equal to the size variable
        //then we perform the remove operation on the tree for the same data size
        //we do this operation size number of times and then take the average of these times
        //to get more accurate results, then add the average time to the removeTimes list
        for(int size:sizes){
            //create a new instance of the avltree for each data size
            manager = new StockDataManager();
            List<String> generatedStrings = new ArrayList<>();
            //fill the avl tree with data of size equal to the size variable
            for(int i=0;i<size ; i++){
                String randomString = rsg.generateRandomString(4);
                generatedStrings.add(randomString);
                manager.addOrUpdateStock(randomString, Math.random() * 100, (long) (Math.random() * 1000000), (long) (Math.random() * 1000000000));
            }

            long removetimes =0;


            for(int i=0;i<size;i++){

                int randomIndex = (int) (Math.random() * generatedStrings.size());
                String str = generatedStrings.get(randomIndex);

                long start = System.nanoTime();
                //we remove a individual node from the tree and calculate the time taken for this operation
                manager.removeStock(str);
                long end = System.nanoTime();
                //then we add the node we just removed from the tree so that the tree remains the same as it was before
                manager.addOrUpdateStock(str, Math.random() * 100, (long) (Math.random() * 1000000), (long) (Math.random() * 1000000000));
                //we add the time taken for the remove operation to the removetimes variable
                removetimes += end - start;
            }
            removeTimes.add(removetimes/1000);

           //System.out.println("For data size : " + size + " avg Remove time is : " + (removetimes/10) + " nanoseconds");


        }
        for(int size : sizes) {
            manager = new StockDataManager();
            List<String> generatedStrings = new ArrayList<>();
            //fill the avl tree with data of size equal to the size variable
            for (int i = 0; i < size; i++) {
                String randomString = rsg.generateRandomString(4);
                generatedStrings.add(randomString);
                manager.addOrUpdateStock(randomString, Math.random() * 100, (long) (Math.random() * 1000000), (long) (Math.random() * 1000000000));
            }

            long updatetimes = 0;

            for (int i = 0; i < size; i++) {
                int randomIndex = (int) (Math.random() * generatedStrings.size());
                String str = generatedStrings.get(randomIndex);

                long start = System.nanoTime();
                manager.addOrUpdateStock(str, Math.random() * 100, (long) (Math.random() * 1000000), (long) (Math.random() * 1000000000));
                long end = System.nanoTime();
                updatetimes += end - start;
            }
            updateTimes.add(updatetimes/1000);
        }


        //DATA ELIMINATION
        //We eliminate the data points that are above the threshold value

        //we call the findthreshold method to get the threshold value for the data points
        //then we traverse the data points and if the difference between adjacent elements is greater than the threshold value
        //if its greater than we replace the data point with the previous data point + threshold value
        double adddataTreshold,removeDataTreshold,searchDataTreshold,updateDataTreshold;
        adddataTreshold = findthreshold(addTimes);
        removeDataTreshold = findthreshold(removeTimes);
        searchDataTreshold = findthreshold(searchTimes);
        updateDataTreshold = findthreshold(updateTimes);

        //loop where we make the replacing of the data points
        for(int i=0; i<dataPointsX.size() - 1;i++){
            if( (addTimes.get(i+1) - addTimes.get(i)) > adddataTreshold){
                addTimes.set(i+1, (long) (addTimes.get(i) + adddataTreshold));
            }
            if( (removeTimes.get(i+1) - removeTimes.get(i)) > removeDataTreshold){
                removeTimes.set(i+1, (long) (removeTimes.get(i) + removeDataTreshold));
            }
            if( (searchTimes.get(i+1) - searchTimes.get(i)) > searchDataTreshold){
                searchTimes.set(i+1, (long) (searchTimes.get(i) + searchDataTreshold));
            }

            if( ((updateTimes.get(i+1)) - updateTimes.get(i))  > updateDataTreshold){
                updateTimes.set(i+1,(long) (updateTimes.get(i) + updateDataTreshold));
            }

        }



        
        // Create a GUIVisualization instance for each operation and make them visible
        GUIVisualization addFrame = new GUIVisualization("Add","line", dataPointsX, addTimes);
        addFrame.setTitle("ADD Operation Performance");
        addFrame.setVisible(true);

        GUIVisualization searchFrame = new GUIVisualization("Search","line", dataPointsX, searchTimes);
        searchFrame.setTitle("SEARCH Operation Performance");
        searchFrame.setVisible(true);

        GUIVisualization removeFrame = new GUIVisualization("remove","line", dataPointsX, removeTimes);
        removeFrame.setTitle("REMOVE Operation Performance");
        removeFrame.setVisible(true);

        GUIVisualization updateFrame = new GUIVisualization("update","line", dataPointsX, updateTimes);
        updateFrame.setTitle("UPDATE Operation Performance");
        updateFrame.setVisible(true);


}

}


