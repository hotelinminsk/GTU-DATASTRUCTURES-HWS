package hw1p;

public class corporate_customer extends customer {
    private String company_name;
    /**
     * Takes essential parameters for a call to its superclass customer and takes an additional data which is corporate name
     * @param n name parameter
     * @param s surname parameter
     * @param ad address parameter
     * @param p phone number parameter
     * @param id id parameter
     * @param op_id operator id parameter
     * @param co_n corporate name parameter
     */
    public corporate_customer(String n,String s,String ad,String p,int id,int op_id,String co_n){
        super(n, s, ad, p, id, op_id);
        company_name = co_n;
    }
    
    /***
     * Overriden print_customer method which makes a call to its superclass's print_customer method and prints out the one
     * additional data 'company_name' aswell.
     */
    @Override
    public void print_customer() {
        super.print_customer();
        System.out.println("Company name: "+company_name+'\n');
    }
}
     