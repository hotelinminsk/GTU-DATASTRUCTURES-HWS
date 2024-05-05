package libraries;
/**
 * Device interface is a package-private interface
 */
interface Device {
    

    //Getters 
    /**
     * 
     * @return category string of the device
     */
    public String getCategory();
    /**
     * 
     * @return name string of the device
     */
    public String getName();
    /**
     * 
     * @return price double of the device
     */
    public Double getPrice();
    /**
     * 
     * @return quantity integer of the device
     */
    public Integer getQuantity();

    //Setters
    /**
     * 
     * @param p a double value to set the price field of the device
     */
    public void setPrice(Double p);
    /**
     * 
     * @param s a string value to set the name field of the device
     */
    public void setName(String s);
    /**
     * 
     * @param c a string value to set the category field of the device
     */
    public void setCategory(String c);
    /**
     * 
     * @param pr a integer value to set the quantity field of the device
     */
    public void setQuantity(Integer pr);
}

