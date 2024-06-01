public class SelectionSort extends SortAlgorithm {

	public SelectionSort(int input_array[]) {
		super(input_array);
	}

    @Override
    public void sort() {
        // fill this method
  
        // Starting from the 0 index to arr.length - 1. 
        for (int i = 0; i < (arr.length - 1); i++) 
        { 
            // Assign i to indexOfMinValue initially, to compare with other elements
            int indexOfMinValue = i; 
        
            //Starting from the i+1 to last element of the arr
            for (int j = i + 1; j < arr.length; j++)
            {
                //If arr[j] value is smaller than arr[indexOfMinValue]
                if (arr[indexOfMinValue] > arr[j]) 
                    indexOfMinValue = j; //Then assign j to indexOfMinValue
                
                comparison_counter++; //Increment this varible to show how many comparison is occured
            } 

            //Calling the swap function from the SortAlgorithm class
            swap(i,indexOfMinValue);

        } 
    }

    @Override
    public void print() {
    	System.out.print("Selection Sort\t=>\t");
    	super.print();
    }
}
