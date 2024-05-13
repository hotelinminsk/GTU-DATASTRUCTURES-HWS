public class QuickSort extends SortAlgorithm {

	public QuickSort(int input_array[]) {
		super(input_array);
	}
	//in this method we first choose our pivot as the last element of the subarray,
    //then we take 2 iterators one from the startindex and the other from the index before that
    // then we start traversing the subarray as long as step is not equal to end index
    // checking if the element on the step has a lower value than the pivot or not
    // if thats the case we increment the following iterator than swap the values at the traversing iterator and the following iterator
    //after the loop ends, we found the index of pivot at the location one after the following iterator
    //then we swap the values at the following iterator and the pivot, then return the pivot's new locations index
    private int partition(int[] array, int startIndex , int endIndex){
        
        int pivotValue = array[endIndex];
        int step = startIndex;
        int followingStep = step -1;

        while(step != endIndex){
            if(array[step] < pivotValue){
                followingStep++;
                swap(step, followingStep);
            }
            step++;
            comparison_counter++;
        }

        followingStep++;
        swap(followingStep, endIndex);
        return followingStep;
    }
    
    //this is the helper sort method where we make call to partition method,
    // this method is a recursive method that sorts the array's pivots after and after till the sended sub array has 1 element
     
    private void sort(int[] array, int startIndex, int endIndex){
        // fill this method
        if(endIndex <= startIndex) { return;}
        int pivotIndex = partition(array,startIndex,endIndex);
        sort(array,startIndex,pivotIndex-1);
        sort(array,pivotIndex+1,endIndex);

    }

    @Override
    public void sort() {
    	sort(arr,0,arr.length-1);
    }

    @Override
    public void print() {
    	System.out.print("Quick Sort\t=>\t");
    	super.print();
    }
}
