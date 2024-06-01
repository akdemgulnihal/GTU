public class QuickSort extends SortAlgorithm {

	public QuickSort(int input_array[]) {
		super(input_array);
	}
	
   /**
    * Last element in the arr is assigned as pivot elemenet
    * Then ,finding the position of pivot element and place it.
    * Elements whose are smaller than pivot replace to the lest side of pivot
    * and the other ones replace to right side of the pivot
    */
    private int partition(int k, int l){
        // fill this method

        //Choosing the last element of the array as pivot
        int pivotLatElement = arr[l];

        //Initilation of index k
        int i = (k - 1);

        //Traversing from initial index to higher index
        for (int j = k; j < l ; j++)
        {
            comparison_counter++;   //Incrementing the comparison counter
            
            //If arr[j] is bigger than pivot, then ignore this and incrementing the j
            if(arr[j] > pivotLatElement)
            {/*pass*/}

            //Then checking the new value arr[j] ,if it is now smaller than pivot ,increment the i
            //After that ,swapping the arr[i] and arr[j]

            //If arr[j] value is smaller than the pivotLastElement values
            if (arr[j] < pivotLatElement) {

                //Smaller element's index will be incresed by one
                i++;
                swap(i, j);    //Swapping index of k and index of j
            }
        }//When the loop is done,j reaches the pivot
       
        //Now pivot should be placed after i 
        swap( i + 1, l); //Swaping the pivot and index of i+1
        return (i + 1); //Returning the i+1, it is partitioning index.
    }
    
    //Recursive function
    //startIndex --> starting index of th arr
    //ending index of the arr
    private void sort(int startIndex, int endIndex){
        // fill this method

        //recursive calls run until this condition is not true
        if (startIndex < endIndex) {

            //It is also position of the pivot 
           //Call the partion function and it returning the partition index, it stores in indexOfPartition
            int indexOfPartition = partition(startIndex, endIndex);

            //This function call is sorting elements according to the left of pivot
            sort(startIndex, indexOfPartition - 1);
            //This function call is sorting elements according to the rigth of pivot
            sort( indexOfPartition + 1, endIndex);
        }
    }

    @Override
    public void sort() {
    	sort(0,arr.length-1);//Call the sort function,end element is arr.length-1 because the index starts from the 0
    }

    @Override
    public void print() {
    	System.out.print("Quick Sort\t=>\t");
    	super.print();
    }
}
