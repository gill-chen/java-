import java.util.Arrays;
public class MergeSort 
{
 
 //Here is a test array. You may use it or change it to test with other examples.
 public static void main(String[] args)
 {
  String[] a = {"apple", "orange", "banana", "pear", "grapefruit"};
  System.out.println(Arrays.toString(a));
  a = mergeSort(a);
  System.out.println(Arrays.toString(a));
 }
 
 /*
  * This is the recursive sorting method, mergeSort.
  * Your task is to implement the merge method described below.
  */
 public static String[] mergeSort(String[] a)
 {
  if(a.length<2)
  {
    return a;
  }
  else
  { 
    int middle = a.length/2;
    String[] left = new String[middle];
    String[] right = new String[a.length - middle];
    int i=0;
    for(i=0; i<middle; i++)
    {
      left[i] = a[i];
    }
    for(int j=0; j < right.length; j++)
    {
      right[j] = a[i];
      i++;
    }
    
    left = mergeSort(left);
    right = mergeSort(right);
    
    String[] result = merge(left,right);
    
    return result;
  }
 }
 
 /*
  * This method merges two sorted arrays. They might be of slightly different lengths.
  * The resulting array should be sorted and should contain all values (including duplicates)
  * from the original two input arrays.
  */
 public static String[] merge(String[] l, String[] r)
 {
   // creates a new string array that will be the resulting sorted array of the two input arrays merged together
  String[] result = new String[l.length + r.length];
  //declares and initializes (so that each 'pointer' at each array starts at position 0)
  int i = 0; int x= 0; int y = 0;
  
  //this while loop iterates until one of the left or right array pointers reaches the end of the 
  //corresponding array. For each iteration, contents of
  //either the left or right array is put into an element of the 'resulting' array. The 'pointer' of 
  //the resulting array always moves by one position each iteration.(May not be the case
  //for the left and right arrays.
  while (x < l.length && y < r.length)
  {
    //if element of left array comes before element of the right array, it puts the contents of the left array
    //into the corresponding 
    if ((l[x].compareTo(r[y])) < 0)  
    {
      result[i] = l[x];
      //'resulting' array pointer shifts to next position
      i++;
      //left array pointer shifts to next position
      x++;
    }//vice versa of the comments for above if statement. 
    else 
    {
      result[i] = r[y];    
      //'resulting' array pointer shifts to next position
      i++;
      //right array pointer shifts to next position
      y++;
    }
  }
  // At this point, if there are still remaining elements in the left or right array, it will run 
  //either while loops below and put the contents of the remaining elements into the 'resulting'
  //array.
  
  while (x < l.length)  
  {
    result[i] = l[x];
    i++;
    x++;
  }
  
  while (y < r.length)   
  {
    result[i] = r[y];
    i++;
    y++;
  }
  
  return result;
 }
}

