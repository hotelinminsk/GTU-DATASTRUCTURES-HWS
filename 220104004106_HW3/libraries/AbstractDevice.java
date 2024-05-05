package libraries;
/***
 * Package-private
 * AbstractDevice class is a abstract class which implements Device interface
 * this created because there is so many common variables,methods for all devices and we cant implement these in device interface.
 */
abstract class AbstractDevice implements Device {
    /**
     * Member variables of AbstractDevice class
     * these members are common in every class which is a device so 
     * we implement these here
     * 
     */
    private static int itemCount = 0 ;
    private String category;
    private String name;
    private Double price;
    private Integer quantity;
    private int id;
    /***
     * a final static category array to check inputs given by user
     */
    final static String[] CATEGORIES = {"smartphone","smartwatch","laptop","headphones","tv"};
    
    //CONSTRUCTORS

    /***
     * non parameterized constructor of AbstractDevice class
     */
    public AbstractDevice(){
        category = " null ";
        name = " null ";
        price = null;
        quantity = null;
        id = itemCount++;
    }
    /**
     * 
     * @param cString  category string
     * @param nString name of device
     * @param pDouble price of the device in double type
     * @param qInteger quantity of device in  integer type
     */
    public AbstractDevice(String cString,String nString,Double pDouble,Integer qInteger){
        category = cString;
        name = nString;
        price = pDouble;
        quantity = qInteger;
        id = itemCount++;
    }


    //GETTERS  these getters are defined in Device interface and implemented in AbstractDevice abstract class

    /**
     * A static method 
     * @return  returns  created item count 
     * time complexity O(1) constant time
     */
    public static int getDeviceCount(){
        return itemCount;
    }
   
    /*** 
     * An getter which defined in Device interface and implemented in AbstractDevice class
     * 
     * @return the category string of the device 
     * time complexity O(1) constant time 
     */
    @Override
    public String getCategory(){
        return category;
    }

    /***
     * A getter method which defined in device interface and implemented in AbstractDevice class
     * @return the name string of the device
     * time complexity O(1) constant time
     */
    @Override
    public String getName(){
        return name;
    }

    /***
     * A getter method which defined in device interface and implemented in AbstractDevice class
     * @return the price double of the device
     * time complexity O(1) constant time
     */
    @Override
    public Double getPrice(){
        return price;
    }
    /***
     * A getter method which defined in device interface and implemented in AbstractDevice class
     * @return the quantity integer of the Device
     * time complexity O(1) constant time
     */
    @Override
    public Integer getQuantity(){
        return quantity;
    }

    /***
     * A getter method which defined in device interface and implemented in AbstractDevice class
     * @return the id integer of the device
     * time complexity O(1) constant time
     */
    public int getId(){
        return id;
    }
    


    //SETTERS these setters are defined in Device interface and implemented in AbstractDevice abstract class
    

    /**
     * A setter method which defined in Device interface and implemented in AbstractDevice class 
     * @param nameString, the name field of a Device gets assigned to this value
     * time complexity O(1) constant time
     */
    @Override    
    public void setName(String nameString){
        name = nameString;
    }

    /**
     * A setter method which defined in Device interface and implemented in AbstractDevice class 
     * @param categoryString, the category field of a Device gets assigned to this value
     * time complexity O(1) constant time
     */
    @Override
    public void setCategory(String categoryString){
        category = categoryString;
    }
    /**
     * A setter method which defined in Device interface and implemented in AbstractDevice class 
     * @param priceDouble, the price field of a Device gets assigned to this value
     * time complexity O(1) constant time
     */
    @Override
    public void setPrice(Double priceDouble){
        price = priceDouble;
    }
    /**
     * A setter method which defined in Device interface and implemented in AbstractDevice class 
     * @param quantityInteger, the quantity field of a Device gets assigned to this value
     * time complexity O(1) constant time
     */
    @Override
    public void setQuantity(Integer quantityInteger){
        quantity = quantityInteger;
    }

    /***
     * Overriden toString method of Object class
     * creates an string which has every property of a device in a formatted form
     * @return lastString, the string which has every property of a device in formatted string 
     * time complexity O(1) constant time  
     */
    @Override
    public String toString(){
        String lastString = "Category : " + category + ", Name: "+name + ",Price: "+price+"$, Quantity: "+quantity+ " ";
        return lastString;
    }
}
