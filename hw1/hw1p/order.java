package hw1p;

/***
 * ORDER CLASS 
 * 
 */
public class order {
    private String product_name;
    private int count;
    private int total_price;
    private int status;
    private int customer_ID;

    /***
     * Constructor of order class
     * this class only has an parameterized constructor because we will read from a filled dataset 
     * because of this this constructor doesnt do anything more than initialize private members of order objects
     * with data we read from dataset.
     * @param p_n product name parameter
     * @param _c count parameter 
     * @param t_p total price parameter
     * @param s_t status parameter
     * @param c_id customer id parameter
     */
    public order(String p_n,int _c,int t_p,int s_t,int c_id){
        product_name=p_n;
        count = _c;
        total_price = t_p;
        status = s_t;
        customer_ID = c_id;
    }
    /**
     * 
     * @return returns the id of the customer who placed the order
     */
    public int returnId(){
        return customer_ID;
    }

    /***
     * function to print out order objects
     */
    public void print_order(){
        String orderString;
        orderString = "Product name: "+ product_name + " Count: "+ count+ " Total Price : "+ total_price+ " Status : ";
        
        switch (status) {
            case 0:
                orderString = orderString + " Initialized.";
                break;
            case 1:
                orderString = orderString + " Processing.";
                break;
            case 2:
                orderString = orderString + " Completed.";
                break;
            case 3:
                orderString = orderString + " Cancelled.";
                break;
            
            default:
                break;
        }

        

        System.out.println(orderString);
    }
}
