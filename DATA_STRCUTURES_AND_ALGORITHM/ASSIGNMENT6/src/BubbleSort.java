public class BubbleSort extends SortAlgorithm {

	public BubbleSort(int input_array[]) {
		super(input_array);
	}
	
    @Override
    public void sort() {
    	// fill this method

      //swap flag initially false, if swapping occur then it will be true
      boolean swapFlag = false;

      //This outer loops start from the 0 index to arr.length-1 . (arr.length-1 is written becoasue of comparison)
        for (int i = 0; i < arr.length - 1; i++) 
        {
          for (int j = 0; j < arr.length - i - 1; j++) //when this inner loop is done, the largest element is found the correct position, that's why , 
          {                                            //the loop goes until (arr.length - i - 1)
            //If this condition is occured, e.g: arr : 2,1 ...  ->  arr[0] > arr[1] -> then the arr[0] and arr[1] should be swapped 
            if (arr[j] > arr[j + 1]) 
            {
              swap(j, j + 1);   //Calling the swap function from the SortAlgorithm class
              swapFlag=true;   //Updating the flag of the swap (swapFlag)
            }
           //Comparison is occured,so comparison_counter should be inrease by one
            comparison_counter++;
          }
          
          //If swap function is not called in any iteration, it means already sorted
          if(swapFlag == false)
          {
            break;  //Breaking the loop
          }
          //Swap status is assigned to false like in initial
          swapFlag = false;
        }
    }
    
    @Override
    public void print() {
    	System.out.print("Bubble Sort\t=>\t");
    	super.print();
    }
}
