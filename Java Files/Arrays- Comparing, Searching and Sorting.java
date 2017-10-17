public class Sorting 
{
 public static void main (String[] args)
 {

   plotCombBubble(1000);
 } 
 
 public static int Compare (String s1, String s2)
 {
   int length = s1.length();
   boolean checked = true;
   //sets the smaller string as the length, as to avoid out of range errors.
   if (s1.length()>s2.length())
   {
     length = s2.length();
   }
   //scans through each element of both strings.
   for (int i = 0; 0 < length && checked; i++)
   {
     int ascii1 = s1.charAt(i);
     int ascii2 = s2.charAt(i);
     //the following two if-statements simplifies the process by converting all lower case letter to uppercase
     //letters. 
     if (ascii1 > 90)
     {
       ascii1 -= 32;
     }
     if (ascii2 > 90)
     {
       ascii2 -= 32;
     } 
     //if the letter in s1 comes alphabetically after the corresponding letter in s2, the method returns 1. 
     //it also stops the for loop since there is no need to continue the loop.
     if (ascii1 > ascii2)
     {
       checked = false; 
       return 1;
     }
     //if the letter in s1 comes alphabetically before the corresponding letter in s2, the method returns -1.
     //it also stops the for loop since there is no need to continue the loop.
     else if (ascii1 < ascii2)
     {
       checked = false;
       return -1;
     }
     //if the loop is at the last element of the shorter string, and the last letter of both s1 and s2 are
     //the same, then they are determined 
     else if ((ascii1 == ascii2) && i == length-1 && (s1.length() == s2.length()))
     {
       return 0; 
     } 
     //if the loop is at the last element of the shorter string, but the lengths of the two strings are
     //not the same.
     else if ((ascii1 == ascii2) && i == length-1 && !(s1.length() == s2.length()))
     {
       //if s1 is shorter than s2, then it means it comes before s2 alphabetically, thus the method returns -1. 
       if (s1.length() < s2.length())
       {
         return -1;
       }
       //if s1 is shorter than s2, then it means it comes after s2 alphabetically, thus the method returns 1. 
       else
       {
         return 1;
       }
     } 
   }
   return 20; 
  }
  
 public static int linearSearch (String [] a, String t)
  {
    boolean checked = true;
    //going through the array
    for (int i = 0; i < a.length && checked; i++)
    {
      //if an element of the array and t are the same, compare method returns zero,
      //and the for loop stops since the condition is no longer satisfied due to
      //the change in boolean value from true to false. 
      if (Compare(a[i],t) == 0)
      {
        checked = false; 
        return i;
      }
      //if the loop is at the end of the array, and the last element still does not match
      //the target variable, the method returns -1 since the target is not found in the array.
      else if ((Compare(a[i],t) == 1 || (Compare(a[i],t)) == -1) && (i == (a.length-1)))
      {
        return -1;
      }
    }
    return 20;
  }
  
  public static int binarySearch (String [] a , String t)
  {
    int min = 0;
    int max = a.length - 1;
    int mid;
  
    while (max >= min)
    {
      //changes the mid value every while iteration, to 'shorten the array size' and 
      //increase efficiency in searching
      mid = min + (max-min)/2;
      //if the string in the chosen 'middle index' is the target, then method returns the position 
      //of the string in the array. 
      if (Compare(a[mid],t) == 0)
      {
        return mid;
      }
      //if the string in the chosen 'middle index' comes alphabetically before the target,
      //the minimum of the array is switched, so that we now only focus on the array to the 
      //right of the 'middle index'. 
      else if (Compare(a[mid], t) == -1)
      {
        min = mid + 1;
      }
      //if the string i the chosen 'middle index' comes alphabetically after the target, 
      //the maximum is switched so that we only focus on the array to the left of the
      //middle index. 
      else
      {
        max = mid - 1; 
      }
    }
    //if the target is still not found in the array, the method returns -1. 
    return -1; 
  }
  
  public static int bubbleSort (String[] a)
  {
    boolean swapped = true;
    int n = a.length;
    //declaring and initializing integer that holds all the comparisons made. 
    int bubbleCounter = 0;
    //runs the while loop while the array is not sorted. 
    while (swapped)
    {
      swapped = false;
      //goes through the array for each for loop iteration 
      for (int i = 1; i <= (n - 1);i++)
      {
        //if the element a[i-1] before the currently tested element a[i] is alphabetically after a[i]
        //then we swap the two elements. 
        //the bubbleCounter increments each time 
        bubbleCounter ++; 
        if (Compare (a[i-1], a[i]) == 1)
        {
          String swap = a[i-1];
          a[i-1] = a[i];
          a[i] = swap;
          swapped = true;  
        }       
      }
    }
    //returns the number of comparisons made. 
    return bubbleCounter;
  }
  
  public static int combSort (String [] a)
  {
    int gap = a.length;
    double shrink = 1.3;
    int combCounter = 0; 
    boolean swapped = true;
    //the while loop will keep going as long as it still is not sorted
    while (swapped || !(gap == 1))
    {
      //for each iteration (going through of the array), the gap will become smaller
      //by factor of shrink
      gap = (int) (gap / shrink);
      //if array is still not sorted and gap is less than 1, it will change it to 1-since
      //it is not possible to check with gaps less than 1. 
      if (gap < 1)
      {
        gap = 1;
      }
      int i = 0;
      swapped = false;
   
      while ((i + gap) < a.length)
      { //combCounter increments each time Compare method is called. 
        combCounter ++; 
        //the if statement swaps two elements, if the element in the larger position is alphabetically before
        //the element in smaller position of the array. 
        if (Compare (a[i], a[i + gap]) == 1)
        {
          String swap = a[i];
          a[i] = a[i + gap];
          a[i + gap] = swap;
          swapped = true;  
        }
        i++;
      }    
    }
    return combCounter; 
  }
  
  public static void plotBubbleSortTest(int N_MAX) 
  {
    /* 
     * bubble_sort_results[N] = the number of comparisons made
     * when sorting an array of size N.
     */
    int[] bubble_sort_results = new int[N_MAX];
    
    /*
     * For each array size between 1 (the smallest array size)
     * and N_MAX (an upper limit passed to this method), we will:
     * 1) create a random test array
     * 2) sort it, and store the number of comparisons in bubble_sort_results
     * MAKE SURE THAT YOUR METHOD IS ACTUALLY SORTING THE TEST ARRAY!!!!!!
     */
    for (int i = 1; i < N_MAX; i++) {
      String[] test_array = ArrayUtilities.getRandomNamesArray(i);
      bubble_sort_results[i] = bubbleSort(test_array);
    }
    
    // create a plot window
    // The argument passed to  PlotWindow is the title of the window
    PlotWindow pw = new PlotWindow("Bubble Sort!");
    
    // add a plot to the window using our results array
    /*
     *  The first argument for addPlot is a name for your data set
     *  The second argument for addPlot is an array of integers,
     *  In position "N" in the array, you should put the number of
     *  comparisons that your algorithm made, when sorting an array
     *  of size N. For example, bubble_sort_results[100] will contain
     *  the number of comparisons made for sorting an array of 100 elements
     */
    pw.addPlot("BubbleSort", bubble_sort_results);
  }
  
  public static void plotCombSortTest(int N_MAX) 
  {
    // the results array
    int[] comb_sort_results = new int[N_MAX];
    
    // test sorting for arrays from size 1 to N_MAX
    // MAKE SURE THAT YOUR METHOD IS ACTUALLY SORTING THE TEST ARRAY!!!!!!
    for (int i = 1; i < N_MAX; i++) {
      String[] test_array = ArrayUtilities.getRandomNamesArray(i);
      comb_sort_results[i] = combSort(test_array);
    }
    // create a plot window
    PlotWindow pw = new PlotWindow("Comb Sort!");
    // add a plot to the window using our results array
    pw.addPlot("CombSort", comb_sort_results);
  }   
  
  public static void plotCombBubble (int N_MAX)
  {
    int [] bubble_sort_results = new int[N_MAX];
    int [] comb_sort_results = new int[N_MAX];
    
    for (int i = 1; i < N_MAX; i++)
    {
      String[] test_array = ArrayUtilities.getRandomNamesArray(i);
      bubble_sort_results[i] = bubbleSort(test_array);
    }
    for (int i = 1; i < N_MAX; i++) 
    {
      String[] test_array = ArrayUtilities.getRandomNamesArray(i);
      comb_sort_results[i] = combSort(test_array);
    }
    
    PlotWindow pw = new PlotWindow("Comb-Bubble Comparison!");
    pw.addPlot("CombSort", comb_sort_results);
    pw.addPlot("BubbleSort", bubble_sort_results);
    //results show that comb sort is much faster than bubble sort as it performs a significantly smaller
    //number of comparisons. 
  }
}