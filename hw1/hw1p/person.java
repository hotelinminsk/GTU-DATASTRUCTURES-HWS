package hw1p;

/**
 * Person class is superclass of allof other customer classes and operator class
 */
public class person {

    private String name;
    private String surname;
    private String address;
    private String phone;
    private int ID;

    /**
     * parameterized constructor of person class, every other sub class makes a call to this constructor to initialize same memvber variables
     * 
     * @param _n name parameter
     * @param _sur surname parameter
     * @param _ad address parameter
     * @param _p phone number parameter
     * @param _id id parameter
     */
    public person(String _n,String _sur,String _ad,String _p,int _id){
        name = _n;
        surname = _sur;
        address = _ad;
        phone= _p;
        ID = _id;
    }
    /**
     * a getter method for returning ids of person objects
     * @return
     */
    public int returnId(){
        return ID;
    }
    /***
     * overridden toString method
     * @return string filled with info of person object
     */
    @Override
    public String toString(){
        String base_str = "Name & Surname: "+name+" "+surname+"\n"
        +"Address: "+address+"\nPhone: "+phone+"\nID: "+ID+"\n";
        
        return base_str;
    }

}
