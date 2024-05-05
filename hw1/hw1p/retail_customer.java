package hw1p;

public class retail_customer extends customer{
    /**
     * Parameterized constructor of retail_customer class
     * this constructor doesnt do anything more than a call to its super constructor because retail customer class doesnt have any unique private members 
     * 
     * 
     * 
     * @param n  name parameter to send super consturactor
     * @param l lastname parameter to send super consturactor
     * @param ad address parameter to send super constructor
     * @param p phone number parameter to send super constructor
     * @param id id parameter to send super constructor
     * @param op_id operator id parameter to send super constructor
     */

    
    public retail_customer(String n,String l,String ad,String p,int id,int op_id){
        super(n, l, ad, p, id, op_id);
    }

    /***
     * overriden print customer class
     * does nothing other than call to superclasses printcustomer method
     */
    @Override
    public void print_customer(){
        super.print_customer();
        System.out.println();

    }
}
     