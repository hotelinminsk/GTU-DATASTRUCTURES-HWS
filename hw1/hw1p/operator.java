package hw1p;

import java.util.ArrayList;
import java.util.Vector;
/***
 * operator class which extends person class
 * 
 */
public class operator extends person {
    private Integer wage;
    private person[] customers;
    //private person[] customers;
    //public person(String _n,String _sur,String _ad,String _p,Integer _id){
    public operator(String _n,String sur, String ad,String _p,Integer _id,Integer wk){
        super(_n,sur,ad,_p,_id);
        wage = wk;
        customers = new person[100];
    }
    /***
     * method to print out operator with its customers(if exists)
     */
    public void print_operator(){
        // String result_string =  "*** Operator Screen ***\n----------------------------\n"+toString();
        System.out.println("---------------------------\n"+toString());

        if(customers[0] == null){
            System.out.print("This operator doesn't have any customer.\n----------------------------\n");
        }else{
            for(int a=0;customers[a] != null;a++){
                System.out.print("Customers #"+(a+1));
                if(customers[a] instanceof retail_customer){
                    System.out.print("(a retail customer)\n");
                    ((retail_customer)customers[a]).print_customer();
                    ((retail_customer)customers[a]).print_orders();
                }else if(customers[a] instanceof corporate_customer){
                    System.out.print("(a corporate customer)\n");
                    ((corporate_customer)customers[a]).print_customer();
                    ((corporate_customer)customers[a]).print_orders();
                }
            }
        }
    }
    /***
     * overriden toString method which is using superclasses toString too, used to return a string with details of operator object
     */
    @Override
    public String toString(){
        String n_s = super.toString() + "Wage: "+wage+"\n----------------------------\n";
        return n_s;
    }
    /**
     * public define_customers method for adding new  customers to operator object 
     * 
     * @param c_array customer objects sended by the caller 
     * taken customer references are passed to inner customer array of operator object
     */
    public void define_customers(customer[] c_array){
        int j_counter = 0;
        for(int i=0;c_array[i] != null; i++){
            if(this.returnId() == c_array[i].returnOperatorId()){
                customers[j_counter++] = c_array[i];
            }
        }
    }
    
}
