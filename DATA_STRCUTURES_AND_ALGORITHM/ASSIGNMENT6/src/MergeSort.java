public class MergeSort extends SortAlgorithm {
	
	public MergeSort(int input_array[]) {
		super(input_array);
	}
	
	private void merge(int left, int middle, int right){
        // fill this method
        //System.out.println("\nleft: " +left);
        //System.out.println("middle: " +middle);
        //System.out.println("right: " +right);


        //Finding the size of the subarray which is from middle to right
        int sizeSubArrayRight = right - middle;

        //Finding the size of the subarray which is from left to middle
        int sizeSubArrayLeft = middle - left + 1;
        
        //Temp array for right side is created
        int tempArrForRight[] = new int[sizeSubArrayRight];
        //Temp array for left side is created
        int tempArrForLeft[] = new int[sizeSubArrayLeft];
       
        //Right part from the middle is assigned to tempArrForRight
        for (int i = 0; i < sizeSubArrayRight; i++)
        {
            tempArrForRight[i] = arr[middle + i + 1];
        }
            
        //Left part from the left to middle is assigned to tempArrForLeft
        for (int j = 0; j < sizeSubArrayLeft; j++)
        {
            tempArrForLeft[j] = arr[left + j];
        }        

       

       //i for first subarray and j for second subarray
        int i = 0, j = 0;

        //Initiliazing index for merged array
        int k = left;
        
        while (i < sizeSubArrayLeft && j < sizeSubArrayRight)
         {
            //If this condition will be occured
            if (tempArrForLeft[i] <= tempArrForRight[j]) {
                arr[k] = tempArrForLeft[i]; //tempArrForLeft[i]assign to the arr[k]
                i++;    //i will be increment because  tempArrForLeft[i] is assigned to the arr[k]
                //System.out.print(arr[k]);
            }
           
            //Other condition
            else {
                arr[k] = tempArrForRight[j];    //tempArrForRight[j]assign to the arr[k]
                j++;    //j will be increment because  tempArrForRight[j] is assigned to the arr[k]
                //System.out.print(arr[k]);
            }
            k++; //İncrementing the index of the merged array

             //After Comparison was occured , incrementing the comparison_counter
            comparison_counter++;
        }
        //System.out.println();

        //Remaining values in the tempArrForLeft array is assigned to the arr[k]
        for(;i < sizeSubArrayLeft;i++,k++)
        {
            arr[k] = tempArrForLeft[i];
            //System.out.print(arr[k]);
        }

         //Remaining values in the tempArrForRight array is assigned to the arr[k]
        for(; j < sizeSubArrayRight;j++,k++ )
        {
              arr[k] = tempArrForRight[j];
              //System.out.print(arr[k]);
        }
  
    }

    private void sort(int left, int right){
        // fill this method

        //Recurcion will be occured until this condion is not supported
        if (right > left) {

            //Finding the middle
            int middle = left + (right - left) / 2;
            //System.out.println(middle);

           //Sorting from left to middle
            sort(left, middle);

            //Soring from the middle ro right
            sort( middle + 1, right);

           //Then do merging according to left, middle, right parameter
            merge(left, middle, right);
        }
    }
    
    @Override
    public void sort() {
        //Call the sort function 0 index to last index arr.lenght-1 -> we decrement by one 
    	sort(0, arr.length - 1);    //Eg: 2,1,3,4 total elemrent is 4 , arr.length is retruning 4
    }                               //but last element index is 3 
    
    @Override
    public void print() {
    	System.out.print("Merge Sort\t=>\t");
    	super.print();
    }
}
