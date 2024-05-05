package libraries;

/**
 * A packaage-private headphones class which extends the AbstractDevice class and implemets the Device interface
 */
class Headphones extends AbstractDevice {
    /**
     * Non-parameterized constructor of Headphones class
     * simply calls non parameterized constructor of its superClass in this case AbstractDevice class.
     */
    public Headphones(){
        super();
    }

    /**
     * Parameterized constructor of Headphones class 
     * simply calls  parameterized constructor of its superClass with provided parameters.
     * 
     * @param cString categoryString gets send to the AbstractDevice class's constructor to be set in the category field of the device
     * @param nString name string  gets send to the AbstractDevice class's constructor to be set in the name field of the device
     * @param pDouble price double gets send to the AbstractDevice class's constructor to be set in the price field of the device
     * @param qInteger quantity integer gets send to the AbstractDevice class's constructor to be set in the quantity field of the device
     */
    public Headphones(String cString,String nString,Double pDouble,Integer qInteger){
        super(cString, nString, pDouble, qInteger);
    }
    
}
