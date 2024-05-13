public class MergeSort extends SortAlgorithm {
	
	public MergeSort(int input_array[]) {
		super(input_array);
	}
	
    //the private helper merge method where we compare the values at the two subarrays then push the smaller one to the original array.
    //the merge method calls made at the return time so we are sure that coming subarrays are sorted in ascending order themselves
    
	private void merge(int[] leftArray,int[] rightArray,int[] ogArray){
        int rightSize = rightArray.length;
        int leftSize = leftArray.length;
        int leftIndex = 0,rightIndex = 0,ogIndex = 0;

        while(leftIndex < leftSize && rightIndex < rightSize){
            if(leftArray[leftIndex] < rightArray[rightIndex]){
                ogArray[ogIndex++] = leftArray[leftIndex++];
            }else{
                ogArray[ogIndex++] = rightArray[rightIndex++];
            }

            comparison_counter++;
        }

        while(leftIndex < leftSize){
            ogArray[ogIndex++] = leftArray[leftIndex++];
        }

        while(rightIndex < rightSize){
            ogArray[ogIndex++] = rightArray[rightIndex++];
        }
    }

    //this is the recursive private helper method of mergesort class,
    //we divide the array to two subarrays from the middle recursively till there is 1 or less element on the subarray,
    //after the dividing, in the return time of the sort methods, we call merge function to merge two subarrays to the original one
    //after merging is done we return to the caller method then it merges the two subarrays to original array.
    private void sort(int[] array){
        // fill this method
        if(array.length <= 1){return;}

        int middle = array.length/2;
        int[] leftArray = new int[middle];
        int[] rightArray = new int[array.length - middle];


        int leftIndex=0,rightIndex = 0;
        for(int i=0; i<array.length; i++){
            if(i<middle){
                leftArray[leftIndex++] = array[i];
            }else{
                rightArray[rightIndex++] = array[i];
            }
        }

        sort(leftArray);
        sort(rightArray);
        merge(leftArray,rightArray,array);
    }
    
    @Override
    public void sort() {
    	sort(arr);
    }
    
    @Override
    public void print() {
    	System.out.print("Merge Sort\t=>\t");
    	super.print();
    }
}
