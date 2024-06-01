import java.util.LinkedList;

import javax.crypto.AEADBadTagException;


public class test{

    public LinkedList<Integer> ints;


    private int[] merge(int[] left,int[] right){
        int leftindex =0,rightindex = 0,ogindex = 0;
        int ogsize = left.length + right.length;
        int[] ogarray = new int[ogsize]; 
        while(leftindex < left.length && rightindex < right.length){
            if(left[leftindex] < right[rightindex]){
                ogarray[ogindex++] = left[leftindex++];
            }else{
                ogarray[ogindex++] = right[rightindex++];
            }
        }

        while(leftindex < left.length){
            ogarray[ogindex++] = left[leftindex++];
        }
        while(rightindex < right.length){
            ogarray[ogindex++] = right[rightindex++];
        }

        return ogarray;
    }


    private int[] mergeSort(int[] array){
        if( !(array.length > 1 ) ){
            return array;
        }
        
        int halv = array.length/2;
        int[] lefta = new int[halv];
        int[] righta = new int[array.length - halv];

        int leftstepper=0,rightstepper=0;

        for(int i=0 ; i<array.length; i++){
            if(i < halv){
                lefta[leftstepper++] = array[i];
            }else{
                righta[rightstepper++] = array[i];
            }
        }

        lefta = mergeSort(lefta);
        righta = mergeSort(righta);
        array = merge(lefta, righta);

        return array;
    }

    public int[] mergeSortk(int[] array){
      return  mergeSort(array);
    }
    public test(){

    }

    public static void main(String[] args) {
        test tk = new test();

        int[] array = new int[30];

        for(int i=0;i<30;i++){
            array[i] = (int)(Math.random() * (i * 10));
        }

        int[] arrayCopy = tk.mergeSortk(array);

        for(var x: arrayCopy){
            System.out.println("The data : "+x);
        }

    }
}