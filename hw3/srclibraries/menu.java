package srclibraries;

import java.io.File;
import java.util.Scanner;

public class menu extends Inventory {
    private Inventory inventory;
    private Scanner sc;
    

    public menu(){
        sc = new Scanner(System.in);
        inventory = new Inventory(sc);
        
    }

    public void mainMenu(){
    
        String menuString = "Welcome to the Electronics Inventory Management System!\n " +
        "Please select an option:\n" +
        "1. Add a new device\n"+
        "2. Remove a device\n" +
        "3. Update device details\n" +
        "4. List all devices\n" +
        "5. Find the cheapest device\n" +
        "6. Sort devices by price\n" +
        "7. Calculate total inventory value\n" +
        "8. Restock a device\n" + 
        "9. Export inventory report\n" + 
        "0. Exit \n"
        ;
        boolean exitFlag = false;
        boolean errorFlag = false;
        Integer choiceInteger = -99;
        String input = "";
        do {
            do {
                if(!errorFlag){
                    System.out.print(menuString);
                }else{
                    System.out.println("Input not correct. Check your input and try again.");
                    System.out.print(menuString);
                }
                input = sc.nextLine();
                try {
                    choiceInteger = Integer.parseInt(input);
                    if(choiceInteger < 0 || choiceInteger > 9){
                        errorFlag = true;
                    }else if(choiceInteger == 0){
                        exitFlag = true;
                        break;
                    }
                    else{
                        errorFlag = false;
                    }
                } catch (Exception e) {
                    errorFlag = true;
                }

            } while (errorFlag);

            switch(choiceInteger){
                case 0:
                    exit();
                    break;
                case 1:
                    inventory.addDevice();
                    break;
                case 2:
                    inventory.removeADevice();
                    break;
                case 3:
                    inventory.updateAnItem();
                    break;
                case 4:
                    inventory.displayAllitems();
                    break;
                case 5:
                    inventory.cheapestDevice();
                    break;
                case 6:
                    inventory.sortDevices();
                    break;
                case 7:
                    inventory.calculateTotalValue();
                    break;
                case 8:
                    inventory.restockADevice();
                    break;
                case 9:
                String currentDirectory = System.getProperty("user.dir");
                File outputsDirectory = new File(currentDirectory + "/outputs");
                outputsDirectory.mkdirs();
                String path = currentDirectory + "/outputs/report.txt";
                inventory.generateReport(path);
                break;
                default:
                    break;

            }

        } while (!exitFlag);

    }

    private void exit(){
        sc.close();
    }
}
