package libraries;

/**
 * package-private Smartphone class
 */
class Smartphone extends AbstractDevice {
    /**
     * Non-parameterized constructor of Smartphone class
     * simply calls non parameterized constructor of its superClass in this case AbstractDevice class.
     */
    public Smartphone(){
        super();
    };
    /**
     * Parameterized constructor of Smartphone class 
     * simply calls  parameterized constructor of its superClass with provided parameters.
     * 
     * @param cString categoryString gets send to the AbstractDevice class's constructor to be set in the category field of the device
     * @param nString name string  gets send to the AbstractDevice class's constructor to be set in the name field of the device
     * @param pDouble price double gets send to the AbstractDevice class's constructor to be set in the price field of the device
     * @param qInteger quantity integer gets send to the AbstractDevice class's constructor to be set in the quantity field of the device
     */
    public Smartphone(String cString,String nString,Double pDouble,Integer qInteger){
        super(cString, nString, pDouble, qInteger);
    };
    
    
}