package srclibraries;

import java.util.Scanner;

import libraries.SingleLinkedList;

/**
 * Packge-private Inventory class 
 */
class Inventory {
    private SingleLinkedList data;
    private Scanner sc;
    /**
     * Parameterized consturctor of Inventory class
     * @param s_c an open Scanner object send by menu.java class used for taking input from System.in
     */
    public Inventory(Scanner s_c){
        data = new SingleLinkedList();
        sc  = s_c;
    }
    /**
     * public addDevice method calls the single linked list objects addnewdevice method
     */
    public void addDevice(){
        data.addNewDevice(sc);
    }

    public void displayAllitems(){
        data.displayItems();
    }

    public void removeADevice(){
        data.removeADevice(sc);
    }

    public void updateAnItem(){
        data.changeDeviceProperties(sc);
    }

    public void cheapestDevice(){
        data.findTheCheapestDevice();
    }

    public void sortDevices(){
        data.sortDevicesByPrice();
    }

    /**
     * public calculateTotalValue method that prints total inventory value returned by calculateTtaolInventoryValue method of single linked list 
     */
    public void calculateTotalValue(){
        Double total_val = data.calculateTotalInventoryValue();

        System.out.println("Total inventory value: "+total_val+"$");

    }

    public void restockADevice(){
        data.restockADevice(sc);
    }


    public void generateReport(String filePath){
        
        data.generateReport(filePath);
        
        
    }



}
