public class BubbleSort extends SortAlgorithm {

	public BubbleSort(int input_array[]) {
		super(input_array);
	}
	
    @Override
    //the bubblesort algorithm which takes O(n^2) time based on the input size.
    //is a linear search algorithm where we check the every following element for every element
    // we do the comparison n^2 times for n input.
    public void sort() {
    	// fill this method
        for(int i=0;i<arr.length-1;i++){
            for(int j=0;j<arr.length-i-1;j++){
                if(arr[j] > arr[j+1]){
                    swap(j, j+1);
                }
                comparison_counter++;
            }
            
            
        }
    }
    
    @Override
    public void print() {
    	System.out.print("Bubble Sort\t=>\t");
    	super.print();
    }
}
