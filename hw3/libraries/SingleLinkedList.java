package libraries;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Scanner;

import javax.annotation.processing.FilerException;
import javax.swing.AbstractAction;
import javax.swing.Action;

import libraries.AbstractDevice;


/**
 * SingleLinked list class is an public class because we wanna be able to acces it outside the package
 * This class has all program loop and every method to manipulate,add the data to inventory
 */
public class SingleLinkedList {
    /**
     * Head node 
     */
    private Node head;
    private static int categoryCount = 1;

    //INNER NODE CLASS
    private class Node{
        private Node next;
        private String nodeCategory;
        /**
         * Data is a array list which holds AbstractDevices because of an polymorphic design
         */
        private ArrayList<AbstractDevice> data;
        /**
         * Default constructor of inner Node class 
         */
        private Node(){
            next = null;
            nodeCategory = "";
            data = new ArrayList<AbstractDevice>();
        }

        /**
         * removeaDevice method inside the node class, gets called from singlelikedlist class's remove method
         * @param index removes the device at the given element from arraylist
         * @return return true if element removed succesfully if not retuns false
         * Complexity O(1) constant time
         */
        private boolean removeADevice(int index){
            try {
                data.remove(index);
                return true;
            } catch (Exception e) {
                // TODO: handle exception
                return false;
            }
        }
        /**
         * private isAddable method used to check if this node  has data inside its ArrayList
         * if arraylist is empty or the category of node is equal to sended device's category returns true in any other case it returns false  
         * @param device takes the device which user wanna add as parameter to check if its addable to arraylist
         * @return boolean true or false 
         * complexity O(1) constant time
         */
        private boolean isAddable(AbstractDevice device){
            
            if(nodeCategory.isEmpty()){
               //the node doesnt contain any category info right now
               //because of that every category type can be added
               this.nodeCategory = device.getCategory(); 
               return true;
            }else{
                
                return (nodeCategory.equals(device.getCategory()));
            }
            
        }
        /**
         * private addDevice method to add given device to the ArrayList
         * @param device the device user wanna add
         * @return boolean true or false 
         * compelxity O(1) constant time
         */
        private boolean addDevice(AbstractDevice device){
            try {
                data.add(device);
                return true;
            } catch (Exception e) {
                return false;
            }
        }

        
    }
    //END NODE CLASS 

    /**
     * default consturctor of singleLinkedList class
     */
    public SingleLinkedList(){
        head = new Node();
    }

    /**
     * Static formatString function to format the userinput to be alllower case and doesnt containt any whitespaces
     * @param input the string user enters
     * @return a string that in wanted format
     */
    public static String formatString(String input){
        return input.replaceAll("\\s", "").toLowerCase();
    }

    /**
     * Static categoryChecker function to chekc if input is in known type ex(smartphone,tv,laptop,smartwatch,headphones) we dont take inputs anyother than theese categorys
     * @param input formatted input from formatString method
     * @return returns true if formatted input is equal to one of known categories if not returns false
     */
    public static boolean categoryChecker(String input){
        for(String s : AbstractDevice.CATEGORIES){
            
            if(input.equals(s)){
                return true;
            }
        }


        return false;
    }

    /***
     * private insertDevice method which gets called from public addNewDevice method, takes an AbstractDevice as an parameter and checks if its addable if thats the case adds it to list and makes the linking inside the nodes
     * @param device an AbstractDevice object created and sended to the method 
     * @throws Exception throws this exception if insertDevice cant find any node(very low potantial)
     */
    private void insertDevice(AbstractDevice device) throws Exception{
        Node tempNode = head;
        boolean is_added =false;
        while(tempNode != null){
            if(tempNode.isAddable(device)){
                tempNode.nodeCategory = device.getCategory();
                is_added = tempNode.addDevice(device);
                break;
            }

            if(tempNode.next == null){
                tempNode.next = new Node();
                tempNode.next.nodeCategory = device.getCategory();
                categoryCount++;
            }

            tempNode = tempNode.next;
        }


        if(!is_added){
            throw new Exception("Can't find node!");
        }

    }

    /**
     * public addNewDevice method this method gets called from inventory object,it uses private methods to check inputs, and send the device to private insertDevice method if there is none 
     * problems with inputs exetra.
     * @param sc an open scanner object to read data from System.in
     * Time complexity is in the best case is O(1) constant time , but in worst case we can say unlimited because user can give unlimited amount of incorrect inputs and we are inside the loop if user doesnt give correct inputs. 
     *
     */
    public void addNewDevice(Scanner sc){
        
        boolean errorFlag = false;
    
        String categoryInputOriginal = "";
        do {
            if(!errorFlag){
                System.out.print("Give the category : ");
            }else{
                System.out.println("Give valid category please(smartphone,smartwatch,tv,laptop,headphones)");
                System.out.print("Give the category: ");
            }
            categoryInputOriginal = sc.nextLine();

            if(categoryChecker(formatString(categoryInputOriginal))){
                errorFlag = false;
            }else{
                errorFlag = true;
            }

            System.out.println();
        } while (errorFlag);

        errorFlag =false;
        String name = "";
        do {
            if(!errorFlag){
                System.out.print("Give the name: ");

            }else{
                System.out.print("Please give an valid input: ");
            }
            name = sc.nextLine();
            if(name.equals("")){
                errorFlag = true;
            }else{
                if(itemExist(name) != null){
                    errorFlag = true;
                    System.out.println("Please give an unique name for every device.");
                }else{
                    errorFlag = false;
                }
                
            }
            System.out.println();

        } while (errorFlag);

        Double price = 0.0;
        errorFlag = false;
        String input = " ";
        do {
            if(!errorFlag){
                System.out.print("Give the price : ");
            }else{
                System.out.print("Please check your input you cant enter values lower than 0 : ");
            }
            try {
                input = sc.nextLine();
                price = Double.parseDouble(input);
                if(price < 0.1){
                    throw new Exception();
                }
                errorFlag = false;
            } catch (Exception e) {
                errorFlag = true;
            }
            System.out.println();
        } while (errorFlag);



        errorFlag= false;
        Integer quantity= 0;
        input = "";
        do {
            if(!errorFlag){
                System.out.print("Enter quantity: ");
            }else{
                System.out.print("Please check your input and try again: ");
            }

            try {
                input = sc.nextLine();
                quantity = Integer.parseInt(input);
                if(quantity < 0 ){
                    throw new Exception();
                }
                errorFlag = false;
            } catch (Exception e) {
                errorFlag = true;
            }
            System.out.println();
        } while (errorFlag);

        switch(formatString(categoryInputOriginal)){
            case "headphones":
                AbstractDevice newDevice = new Headphones("Headphones",name,price,quantity);
                try {
                    insertDevice(newDevice);
                } catch (Exception e) {
                    System.err.println(e.toString());
                }
                break;
            case "smartphone":
                newDevice = new Smartphone("Smartphone", name, price, quantity);
                try {
                    insertDevice(newDevice);
                } catch (Exception e) {
                    System.err.println(e.toString());
                }
                break;
            case "smartwatch":
                newDevice = new SmartWatch("Smart Watch", name,price,quantity);
                try {
                    insertDevice(newDevice);
                } catch (Exception e) {
                    System.err.println(e.toString());
                }
                break;
            case "tv":
                newDevice = new TV("TV",name,price,quantity);
                try {
                    insertDevice(newDevice);
                } catch (Exception e) {
                    System.err.println(e.toString());
                }
                break;
            case "laptop":
                newDevice = new Laptop("Laptop",name,price,quantity);
                try{
                    insertDevice(newDevice);
                }catch(Exception e){
                    System.err.println(e.toString());
                }
                break;
            default:
                break;
        }
        



    }
    /**
     * overloaded displayItems with parameter item_category, instead of displaying all the items in the list it filters the category user entered and displays that devices.
     * @param item_category category string user provides to filter the devices and output the devices inside that category.
     * Time complexity of this method changes is O(n) because we are only go in an second loop if the category is equals to the searched category
     * 
     * Time Complexity O(n) linear complexity
     */
    public void displayItems(String item_category){
        if(item_category.equals("")){
            displayItems();
        }else{
            if(categoryChecker(formatString(item_category))){
                Node tempNode = head;
                while(tempNode != null ){
                    if(formatString(tempNode.nodeCategory).equals(formatString(item_category))){
                        System.out.println("Category : "+ item_category+" ");
                        for(AbstractDevice a : tempNode.data){
                            System.out.println("Name: "+a.getName()+",Price: "+a.getPrice()+", Quantity: "+a.getQuantity());
                        }
                    }
                    tempNode = tempNode.next;
                }
            }else{
                System.out.println("There isn't a category named : "+item_category+" try again.");
            }
        }

    }
    /**
     * Non parameterized displayItems method, display all the elements in the inventory
     * The time complexity of this method changes based on the program's design, in this program i limited the category section to have atmost 5 elements so with this we could have atmost 5 nodes in our linked list
     * this makes the method to have time function of T(n) = 5n  at the worst case, and this time function makes the method has time complexity of O(n) at worst case.
     * In the bset case scenario this method takes constant time.
     * 
     * If programs design is changed and we can give the user the freedom of creating more than 5 categories this means user can create more than 5 nodes aswell, with this freedom we can assume that user created N nodes.
     * and this makes our method to have complexity of n square. But this is not the case in this design.
     */

    public void displayItems(){
        Node tempNode = head;
        if(tempNode.data.isEmpty()){
            System.out.println("List is empty!");
        }else{
            System.out.println("Displaying all the inventory: ");
            while(tempNode != null){
                System.out.println("Category: "+tempNode.nodeCategory+" ");
                for(AbstractDevice a:tempNode.data){
                    System.out.println("Name: "+a.getName()+",Price: "+a.getPrice()+"$,Quantity: "+a.getQuantity());
                }
                tempNode = tempNode.next;
            }
        }
        
    }
    /**
     * Private itemExist method to check if searched item is in any arraylist or not
     * @param item takes an string item name to check if it is in our inventory or not
     * @return if item is finded returns that AbstractDevice if not returns null
     * 
     * In the best case it has time complexity of O(1), in the worst case this method also has O(n) linear time complexity because the Node(category) can be created is limited to 5 categories.
     * 
     */
    private AbstractDevice itemExist(String item){
        Node tempnode = head;
        while(tempnode != null){
            if(tempnode.data != null){
                for(AbstractDevice a: tempnode.data){
                    if(a.getName().equals(item)){
                        return a;
                    }

                }
            }
            tempnode = tempnode.next;
        }

        return null;
    }

    /**
     * Private remove method which gets called from public removeADevice method
     * @param nameofDevice name string to find wanted element in the inventory
     * @throws Exception throws this if there is an exception when trying to remove the device form ArrayList. Very low possibility. 
        This method has time complexity of O(n) linear time complexity in worst case, because of design.
        in best case scenario it takes constant time. 
     */
    private void remove(String nameofDevice) throws Exception{
        Node tempNode = head;
        int index = 0 ;
        while(tempNode != null){
            for(AbstractDevice a : tempNode.data){
                if(a.getName().equals(nameofDevice)){
                    if(tempNode.removeADevice(index)){
                        break;
                    }else{
                        throw new Exception("Exception occured when removing a device.Check line : 312");
                    }
                }
                index++;
            }
            index = 0;
            tempNode = tempNode.next;
        }
    }

        /**
         * Public method to remove a device from the inventory.
        * 
        * @param sc An open Scanner object to take input from System.in.
        * 
        * This method has an average-case time complexity of O(n) because it calls the private remove method,
        * which takes linear time to find the searched device. However, the worst-case time complexity cannot be determined
        * due to the presence of a do-while loop. If the user provides incorrect input, this loop may not terminate.
        */
    public void removeADevice(Scanner sc){
        boolean flag = false;
        String input = "";
        do {
            
            if(!flag){
                System.out.print("Give the name of device to remove directly: ");
            }else{
                System.out.print("Please check your input and try again:");
            }

            try {
                input = sc.nextLine();
                if(itemExist(input) != null){
                    flag =false;
                }else{
                    flag=true;
                }    
            } catch (Exception e) {
                flag = true;
            }
            System.out.println();

        } while (flag);

        try {
            remove(input);
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    /***
     * Public method the change device properties.
     * @param sc an open Scanner object to take inputs from System.in
     * This method has O(n) time complexity in average-case because of itemExist() method which is called to see if device with given name is exists on the invetory or not.
     * In the worst case this method may never be terminate because of do while loop where we take input from user.If user keeps providing incorrect inputs method's time complexity cant be determineted.
     */
    public void changeDeviceProperties(Scanner sc){
        String name = "";
        Double new_price = -99.99;
        Integer new_quantity = -99;
        AbstractDevice deviceToUpdate = null;
        boolean errorFlag = false;
        
        do {
            if(!errorFlag){
                System.out.print("Enter the name of the device to update:  ");
            }else{
                System.out.print("Please check your input,give the exact name of the device: ");
            }
            name = sc.nextLine();
            if( (deviceToUpdate = itemExist(name)) != null){
                errorFlag =false;
            }else{
                errorFlag = true;
            }
            
            System.out.println();
        } while (errorFlag);
        String input = "";
        if(!errorFlag){
            System.out.print("Enter new price(Leave blank to keep current price): ");
            input = sc.nextLine();
            if(input.equals("")){
                //in this case we aint  gonna update the price
            }else{
                // we gonna update the price value
                try {
                    new_price = Double.parseDouble(input);
                    if(new_price < 0){
                        throw new Exception();
                    }

                } catch (Exception e) {
                    System.out.println("Cant parse the price data,  not gonna change the data.");
                }
            }
        }

        input = "";
        if(!errorFlag){
            System.out.print("Enter new quantity(Leave blank to keep current quantity): ");
            input = sc.nextLine();
            if(input.equals("")){
                //in this case we aint  gonna update the price
            }else{
                // we gonna update the price value
                try {
                    new_quantity = Integer.parseInt(input);
                    if(new_quantity < 0 ){
                        throw  new Exception();
                    }

                } catch (Exception e) {
                    System.out.println("Cant parse the quantity data,  not gonna change the data.");
                }
            }
        }

        if(new_price > -1 && new_quantity > -1){
            deviceToUpdate.setPrice(new_price);
            deviceToUpdate.setQuantity(new_quantity);
        }else if(new_price > -1){
            deviceToUpdate.setPrice(new_price);
        }else if(new_quantity > -1){
            deviceToUpdate.setQuantity(new_quantity);
        }

        System.out.println(name + " details updated: Price -"+deviceToUpdate.getPrice() + "$,Quantity - "+ deviceToUpdate.getQuantity()+ " ");
    }


   /**
     * Method to find the cheapest device.
     * 
     * In the average-case scenario, this method has a time complexity of O(n) due to the limited number of categories.
     * If the program were designed to allow the creation of n categories, this method could potentially have a time complexity of O(n^2).
     * However, in this program's design, that is not the case.
     */
    public void findTheCheapestDevice(){
        Node tempNode = head;
        Double min_price = Double.MAX_VALUE;
        AbstractDevice cheapestDevice = null;
        while(tempNode != null){
            if(tempNode.data != null){
                for(AbstractDevice a : tempNode.data){
                    if(a.getPrice() < min_price){
                        min_price = a.getPrice();
                        cheapestDevice = a;
                    }
                }
            }
            tempNode = tempNode.next;
        }

        System.out.println("Cheapest device :");
        System.out.println(cheapestDevice.toString());
    }


    /**
     * Public method to sort devices in ascending order and print them.
     * 
     * Has the time complexity of O(N^2) because of the second nested loop.
     * 
     */
    public void sortDevicesByPrice(){
        AbstractDevice[] tempArray = new AbstractDevice[100];
        int index = 0;
        Node tempNode = head;
        //THIS NESTED LOOP HAS TIME COMPLEXITY OF O(N) BECAUSE OF THE PROGRAMS DESIGN
        while(tempNode != null){
            for(AbstractDevice a : tempNode.data){
                tempArray[index++] = a;
            }
            tempNode = tempNode.next;
        }

        //BECAUSE OF THIS LOOP METHOD HAS TIME COMPLEXITY OF O(N^2)
        for(int i=0; i < index ; i++){
            for(int j = i+1; j<index;j++){
                if(tempArray[i].getPrice() > tempArray[j].getPrice()){
                    AbstractDevice temp = tempArray[j];
                    tempArray[j] = tempArray[i];
                    tempArray[i] = temp; 
                }
            }
        }
        System.out.println("Devices sorted by price:");
        // TIME COMPLEXITY OF THIS LOOP IS O(N)
        for(int i=0;i<index;i++){
            System.out.println((i+1)+  tempArray[i].toString());
        }

    }
    /**
     * public method to calculate total inventory value
     * @return the Double total inventory value 
     * 
     * This method has time complexity of O(n) in average case,
     * in best case it has time complexity of O(1);
     * 
     */
    public Double calculateTotalInventoryValue(){
        Double total_price = 0.0;
        Node tempNode = head;
        while(tempNode != null){
            for(AbstractDevice a : tempNode.data){
                total_price += a.getPrice() * a.getQuantity();
            }

            tempNode = tempNode.next;
        }


        return total_price;
    }
    
    /**
     * a public method to restockADevice 
     * 
     * @param sc  a open Scanner object to take inputs form System.in
     * this method has time complexity of O(n) in average-case because of the to call to itemExist method 
     * in worst case this method's time complexity cant be determined because of do while loops 
     * 
     */
    public void restockADevice(Scanner sc){
        boolean errorFlag = false;
        boolean actionFlag = false;
        AbstractDevice foundDevice = null;
        String input = "";
        //LOOP TO BE SURE THAT ITEM EXISTS IN THE INVENTORY 
        //TIME COMPLEXITY O(N) IN THE BEST CASE, 
        //IN THE WORST CASE CANT BE DETERMINED
        do {
            if(!errorFlag){
                System.out.print("Enter the name of the device to restock:");
            }else{
                System.out.print("None device found with this name, please check your input: ");
            }

            input = sc.nextLine();
            //  BECAUSE OF THIS LINE THE METHOD HAS O(N) TIME COMPLEXITY
            if((foundDevice = itemExist(input)) != null){
                errorFlag = false;
            }else{
                errorFlag = true;
            }

            System.out.println();

        } while (errorFlag);

        input = "";
        errorFlag = false;
        //LOOP TO BE SURE THAT GIVEN INPUT IS ADD OR REMOVE
        do {
            if(!errorFlag){
                System.out.print("Do you want to add or remove stock? (Add/Remove):");
            }else{
                System.out.print("Input is not correct,try again: (Add/Remove):");
            }
            input = sc.nextLine();
            if(input.equals("Add") || input.equals("Remove")){
                errorFlag = false;
                if(input.equals("Add")){
                    actionFlag = true;
                }else{
                    actionFlag = false;
                }
            }else{
                errorFlag = true;
            }
        } while (errorFlag);

        errorFlag = false;
        input = "";
        Integer value = -99;


        String question = "Enter the quantity to " + (errorFlag ? "Add: " : "Remove: ");
        //Loop to be sure the given input is can be parsed to an Integer, and bigger than 0.
        do{
            if(!errorFlag){
                System.out.print(question);
            }else{
                System.out.println("Please check your input!");
                System.out.print(question);
            }

            input = sc.nextLine();
            try {
                value = Integer.parseInt(input);
                if(value < 0){
                    errorFlag = true;
                }

            } catch (Exception e) {
                errorFlag = true;
            }
        }while(errorFlag);

        value = value * (actionFlag ? 1 : -1 );

        foundDevice.setQuantity(foundDevice.getQuantity() + value);

        String lastString = foundDevice.getName() + " stock " + (actionFlag ? "restocked." : "reduced.") + "New quantity: " + foundDevice.getQuantity() + " \n"; 
        
        System.out.print(lastString);
    }


    /**
     * Method to create a formatted report in desired path.
     * Has time complexity of O(n) 
     * @param filename
     */
    public void generateReport(String filename){

        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("Electronics Shop Inventory Report\n");
            writer.write("Generated on: " + LocalDate.now() + "\n");
            writer.write("--------------------------------------------------------------------\n");
            writer.write(String.format("| %-4s | %-10s | %-40s | %-10s | %-8s |\n", "No", "Category", "Name", "Price", "Quantity"));    writer.write("--------------------------------------------------------------------\n");

            Double totalValue = calculateTotalInventoryValue();
            Node tempNode = head;
            int indexer = 1;
            //Loop where we check every device for its property
            //has time complexity of O(n) because of the limited node's 
            while (tempNode != null) {

                for (AbstractDevice device : tempNode.data) {
                    writer.write(String.format("| %-4d | %-10s | %-30s | $%-8.2f | %-8d |\n",
                            indexer++, tempNode.nodeCategory, device.getName(), device.getPrice(), device.getQuantity()));
                }
                tempNode = tempNode.next;
            }
            writer.write("--------------------------------------------------------------------\n");


            writer.write("Summary: \n");
            writer.write(String.format("- Total number of devices: %-5d\n", AbstractDevice.getDeviceCount()));
            writer.write(String.format("- Total inventory value: $%-8.2f\n", totalValue));
            writer.write("End of report.\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

