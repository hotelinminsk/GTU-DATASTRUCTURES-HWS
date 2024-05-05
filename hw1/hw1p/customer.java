package hw1p;

public class customer extends person {
    private order[] orders;
    private int operator_id;

    /**
     * CONSTRUCTOR OF CUSTOMER CLASS WHICH EXTENDS PERSON CLASS
     * @param n_ name parameter to send super constructor
     * @param s_ surname parameter to send super constructor
     * @param ad_ address parameter to send super constructor
     * @param _p phone number parameter to send super constructor
     * @param id id parameter to send super constructor
     * @param op_id operator id parameter to initialize our private member
     */
    public customer(String n_,String s_,String ad_,String _p,int id,int op_id){
        super(n_,s_,ad_,_p,id);
        operator_id = op_id;
        orders = new order[100];
    }
    public int returnOperatorId(){
        return operator_id;
    }
    /**
     * Non parametirized print_customer method to 
     * print out essential properties of customer object
     * returns null
     * @return void
     * 
     */
    public void print_customer(){
        String customer_string = "\n"+super.toString()+"Operator ID: " +operator_id;

        
        System.out.println(customer_string);
    }
    public void print_orders(){
        if(orders[0] == null){
            System.out.println("There 's none orders exists for this customer!\n---------------------------\n");
        }else{
            System.out.println("---------------------------");
            for(int i=0;orders[i] != null;i++){
                System.out.print("Order #"+(i+1)+" => ");
                orders[i].print_order();
                System.out.println("---------------------------");
                // order_string = "Order #"+i+1+" => ";orders[i].print_order();
            }
            
        }

    }

    /**
     * This function initializes the inner order array named orders with sended _orders array
     * 
     * @param _orders initialized order array sended from program init
     * 
     */
    public void define_orders(order[] _orders){
        
        int j_counter = 0;
        for(int i=0;_orders[i] != null;i++){
            if(this.returnId() == _orders[i].returnId()){
                orders[j_counter++] = _orders[i];
            }
        }
    }
}
