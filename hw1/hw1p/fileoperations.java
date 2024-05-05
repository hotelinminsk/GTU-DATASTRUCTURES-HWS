package hw1p;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/***
 * FILEOPERATIONS CLASS
 * where all the methods about file reading and data storing is defined
 * basically the main class of system
 */
public class fileoperations {
    private person[] holderPersons;
    private order[] holderOrders;
    private int indexerOrders;
    private int indexerPersons;
    
    private int isInteger(String line){
        try {
            int intd = Integer.parseInt(line);
            if(intd > 0 && intd < Integer.MAX_VALUE){
                return 1;
            }
            return 0;
        } catch (NumberFormatException e) {
            return 0;
        }
    }
    public fileoperations(){
        holderOrders = new order[100];
        holderPersons = new person[100];
        indexerOrders = 0;
        indexerPersons = 0;
        read();
        connectData();
    }

    public void search_byId(){
        Scanner sc = new Scanner(System.in);
        int flag = 0;
        do {
            System.out.println("Please enter your id: ");
            String line =sc.nextLine();
            int in_integer;
            if(isInteger(line) > 0){
                in_integer = Integer.parseInt(line);
                int found = 0;
                person tempP = null ;
                for(int i=0;i<indexerPersons;i++){

                    if(holderPersons[i].returnId() == in_integer){
                        tempP = holderPersons[i];
                        found = 1;
                        break;
                    }
                }

                if(found>0){
                    if(tempP instanceof operator){
                        System.out.println(
                            "***Operator Screen***"
                        );
                        ((operator)tempP).print_operator();
                    }else if(tempP instanceof retail_customer){
                        System.out.println("***Customer Screen***");
                        ((retail_customer)tempP).print_customer();
                        ((retail_customer)tempP).print_orders();
                    }else if(tempP instanceof corporate_customer){
                        System.out.println("***Customer Screen***");
                        ((corporate_customer)tempP).print_customer();
                        ((corporate_customer)tempP).print_orders();
                    }
                }else{
                    System.err.println("No operator/customer found with given ID "+in_integer+" please try again.");

                    flag = 1;
                }

            }else{
                flag = 1;
            }
        } while (flag > 0);
        
    }

    public void printEveryConsumer(){
        for(int i=0;i<indexerPersons;i++){
            if(holderPersons[i] instanceof customer){
                ((customer)holderPersons[i]).print_customer();

            }else{
                ((operator)holderPersons[i]).print_operator();
            }
        }

        for(int i=0;i<indexerOrders;i++){
            holderOrders[i].print_order();
        }
    }
    public void connectData(){
        //CONNECTING ORDERS TO CUSTOMERS
        for(int i=0;i<indexerPersons;i++){
            if(holderPersons[i] instanceof customer){
                ((customer)holderPersons[i]).define_orders(holderOrders);

            }
        }

        //Connecting customers to operators
        customer[] temp_customer = new customer[100];
        int j_counter = 0;
        for(int i=0;i<indexerPersons;i++){
            if(holderPersons[i] instanceof customer){
                temp_customer[j_counter++] = (customer)holderPersons[i];
            }
        }

        for(int i=0;i<indexerPersons;i++){
            if(holderPersons[i] instanceof operator){
                ((operator)holderPersons[i]).define_customers(temp_customer);
            }
        }
        
    }

    private int check_validityId(int readedId,int[] ids,int index){
        int itExists = 0;
        for(int i=0;i<index; i++){
            if(ids[i] == readedId){
                itExists = 1;
                break;
            }
        }


        return itExists;
    }
    

    public int valid_semicolon(String line){
        int valid = 0;
        int semicolon_nums = 0;
        int semicolon_counter = 0;
        if(line.startsWith("order")){
            semicolon_nums = 5;
            for(int i=0;i<line.length();i++){
                if(line.charAt(i) == ';'){
                    semicolon_counter++;
                }
            }
            if(semicolon_counter == semicolon_nums){
                valid = 1;
            }

        }else if(line.startsWith("retail")){
            semicolon_nums = 6;
            for(int i=0;i<line.length();i++){
                if(line.charAt(i) == ';'){
                    semicolon_counter++;
                }
            }
            if(semicolon_counter == semicolon_nums){
                valid = 1;
            }
        }else if(line.startsWith("corporate")){
            semicolon_nums = 7;

            for(int i=0;i<line.length();i++){
                if(line.charAt(i) == ';'){
                    semicolon_counter++;
                }
            }
            if(semicolon_counter == semicolon_nums){
                valid = 1;
            }
        }else if(line.startsWith("operator")){

            semicolon_nums = 6;
            for(int i=0;i<line.length();i++){
                if(line.charAt(i) == ';'){
                    semicolon_counter++;
                }
            }

            if(semicolon_counter == semicolon_nums){
                valid = 1;
            }
        }else{
            System.exit(-1);
        }


        return valid;
    }
    public void read(){
        try {
            File inputfile = new File("hw1p/content.txt");

            Scanner scan = new Scanner(inputfile);
            int[] takenIds = new int[100];
            int id_indexer = 0;

            int line_c = 0;
            while(scan.hasNextLine()){
                String line = scan.nextLine();
                String[] tokens;
                if(valid_semicolon(line)<1){
                    continue;
                }else{
                    tokens = line.split(";");
                }
               

                switch (tokens[0]) {
                    case "order":
                        if(tokens.length != 6 ){
                            // tokenler bozuk atla;
                            // System.err.println("Error when reading line : "+line_c+" : can't find every variable.");
                            break;
                        }else{
                            int flag = 0; 
                            for(int i=1;i<tokens.length;i++){
                                if(tokens[i].length() < 1){
                                    flag = 1;
                                    break;
                                }
                            }

                            if(flag<1){
                                order tempOrder = null;
                                //order;computer;4;8000;1;1500
                                int item_count = Integer.parseInt(tokens[2]);
                                int total_price = Integer.parseInt(tokens[3]);
                                int status_ = Integer.parseInt(tokens[4]);
                                int customer_id = Integer.parseInt(tokens[5]);
                                //stay here!!!!!
                                if(item_count>0 && item_count< Integer.MAX_VALUE){
                                    if(total_price > 0 && total_price < Integer.MAX_VALUE){
                                        if(status_ < 0 && status_ > 3){
                                   //         System.err.println("Order status is invalid..");
                                            continue;
                                        
                                        }else{
                                            if(indexerOrders < 100){
                                                tempOrder = new order(tokens[1], item_count, total_price, status_, customer_id);
                                                holderOrders[indexerOrders++] = tempOrder;
                                                
                                            }
                                            else{
                                                throw new ArrayIndexOutOfBoundsException("Orders are full! Cant read new orders.");
                                            }
                                        }
                                    }
                                }else{
                                 //   System.err.println("Integer is out of bounds in line : "+line_c+".");          
                                    continue;
                                }
                            }
                            else{
                                continue;
                               // System.err.println("Variable error on line : "+line_c);    
                            }
                        }
                        break;
                    case "retail_customer":
                        if(tokens.length != 7){
                           // System.err.println("Error when reading line : "+line_c+" : can't find every variable.");
                            break;
                        }else{
                            int flag = 0;
                            for(int i= 1;i<tokens.length;i++){
                                if(tokens[i].length() < 1){
                                    flag = 1;
                                    break;
                                }
                            }

                            if(flag < 1){
                                retail_customer r_c = null;
                                String name = tokens[1];
                                String last_name = tokens[2];
                                String address = tokens[3];
                                String phone_ = tokens[4];
                                int id = Integer.parseInt(tokens[5]);
                                if(check_validityId(id, takenIds,id_indexer) > 0){
                                    continue;
                                }
                                else{
                                    takenIds[id_indexer++] = id;
                                    int op_id = Integer.parseInt(tokens[6]);
                                    if( id > 0 && id<Integer.MAX_VALUE){
                                        if(op_id>0 && op_id<Integer.MAX_VALUE){
                                            if(indexerPersons < 100){
                                                r_c = new retail_customer(name, last_name, address, phone_, id, op_id);
                                                holderPersons[indexerPersons++] = r_c;

                                            }else{
                                                throw new ArrayIndexOutOfBoundsException("Person's array is full! Can't read anymore person data.");
                                            }

                                        }{
                                            //System.err.println("Variable error on line : "+line_c);
                                            continue;
                                        }
                                    }else{
                                        //System.err.println("Variable error on line : "+line_c);
                                        continue;
                                    }
                                }
                            }else{
                                continue;
                            }
                        }
                        
                    case "corporate_customer":
                        if(tokens.length == 8){
                            int flag = 0;
                            for(int i=1;i<tokens.length;i++){
                                if(tokens[i].length() < 1){
                                    flag = 1;
                                    break;
                                }
                            }
                            if(flag<1){
                                corporate_customer tempCorpo = null;
                                String name_String = tokens[1];
                                String l_name_string = tokens[2];
                                String address = tokens[3];
                                String p_number = tokens[4];
                                int id = Integer.parseInt(tokens[5]);
                                if(check_validityId(id, takenIds, id_indexer)>0){
                                    continue;
                                }else{

                                    takenIds[id_indexer++] = id; 
                                
                                    int op_id = Integer.parseInt(tokens[6]);
                                    String corporate_String = tokens[7];

                                    if(id > 0 && id < Integer.MAX_VALUE){
                                        if(op_id > 0 && op_id < Integer.MAX_VALUE){
                                            if(indexerPersons < 100){
                                                tempCorpo = new corporate_customer(name_String, l_name_string, address, p_number, id, op_id, corporate_String);
                                                holderPersons[indexerPersons++] = tempCorpo;
                                            }else{
                                                continue;
                                            }
                                        }
                                    }else{
                                        continue;
                                    }
                                }
                            }else{
                                continue;
                            }
                        }else{
                            //System.err.println("Error reading line : "+ line_c + " not exact number of variables.");
                            continue;
                        }
                        break;
                    case "operator":
                        if(tokens.length == 7){
                            int flag = 0;
                            for(int i=1;i<tokens.length;i++){
                                if(tokens[i].length() < 1){
                                    flag = 1;
                                    break;
                                }
                            
                            }
                            //operator;didem;gozupek kocaman;istanbul;+902626050006;502;2100
                            if(flag<1){
                                operator tempOperator = null;
                                String name = tokens[1];
                                String surname = tokens[2];
                                String address = tokens[3];
                                String phoneNumber = tokens[4];
                                int id_ = Integer.parseInt(tokens[5]);
                                if(check_validityId(id_, takenIds, id_indexer) > 0){
                                    continue;
                                }else{
                                    takenIds[id_indexer++] = id_;
                                    int wage = Integer.parseInt(tokens[6]);
                                    if(id_ > 0 && id_ < Integer.MAX_VALUE){
                                        if(wage > 0 && wage < Integer.MAX_VALUE ){
                                            if(indexerPersons < 100){
                                                tempOperator = new operator(name, surname, address, phoneNumber, id_, wage);
                                                holderPersons[indexerPersons++]=tempOperator;
                                            }else{
                                                continue;
                                            }
                                        }else{
                                            continue;
                                        }
                                    }else{
                                        continue;
                                    }
                                }
                            }else{
                                continue;
                            }

                        }else{
                            continue;
                        }
                        break;        
                    default:
                        break;
                }
                line_c++;
            }
        } catch (Exception e) {
            System.err.println("Can't find content.txt. Terminating.");
            System.exit(-2);
            // TODO: handle exception
        }
    }

}
