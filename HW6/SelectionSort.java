public class SelectionSort extends SortAlgorithm {

	public SelectionSort(int input_array[]) {
		super(input_array);
	}

    @Override
    //the selection sort algorithm is another linear search algorithm which takes O(n^2) time based on the input size
    //in this method we choose the value of the element on the firstiter as minimum then compare it to the  every other element in the array
    //if the compared element has a smaller value than our minvalue then that element is choosen as the minvalue,
    //this comparison continiues till the second iter reaches the end of the array,
    //after that inner traverse ends we swap the values at the firstiter and minindex  
    //because of this comparisons the algorithm takes n^2 times when supplied with n input size. 
    //
    public void sort() {
        // fill this method

        int firstIter, secondIter, min_index;

        for(firstIter=0;firstIter<arr.length-1;firstIter++){
            min_index = firstIter;
            for(secondIter=firstIter+1; secondIter < arr.length;secondIter++){
                if(arr[secondIter] < arr[min_index]){
                    min_index = secondIter;
                }
                comparison_counter++ ;
            }

            
            if(min_index != firstIter){
                swap(min_index,firstIter);
            }
        }
    }

    @Override
    public void print() {
    	System.out.print("Selection Sort\t=>\t");
    	super.print();
    }
}
