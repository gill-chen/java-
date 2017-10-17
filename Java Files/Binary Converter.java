import java.util.Scanner; 
  
public class Question1
{
  public static void main (String[] args)
  { 
    System.out.println("Marvin: Please enter a binary number");
    
    Scanner inputKeyboard = new Scanner(System.in);
    
    String input=inputKeyboard.nextLine();    
    
    //As long as CheckInputCorrect returns a false value, the while loop will keep
    //asking the user to enter a number. I declared another string variable inputUser2
    //to store the input, and then put the input into String variable input so it can be into the
    //CheckInputCorrect as an argument. 
    while (!CheckInputCorrect (input))
    {
      System.out.println("Marvin: This will all end in tears. Can you just please enter a number?"); 
      
      String inputUser2 =inputKeyboard.nextLine();
      
      input= inputUser2;
    }
    
    //If CheckInputCorrect returns true, the if condition is satisfied and will run the code inside of it.
    //Here, the BinaryToNumber takes the argument (which is the user's input/binary number), and returns integers; 
    //in other words, it returns the base 10 number converted from the binary number. 
    if (CheckInputCorrect (input))
    {
      String numberInput= input;
      
      BinaryToNumber (numberInput);
      
      System.out.println("Marvin: I've calculated your binary number, but I don't think you'll like it.");
      
      System.out.println("Marvin: The binary number " + numberInput + " is " + BinaryToNumber(numberInput)+ " in base 10.");
    } 
  }
 
  public static boolean CheckInputCorrect (String input)
  { 
    //The for loop scans through each position of the string array/input.
    for(int i = 0; i< input.length();i++)
    {
      //if at a certain position in the string array/input has a '0' character, it returns true. 
      if (input.charAt(i)== '0') 
      {
        return true;
      }   
      //if the position does not have a character '0', and has the character '1' instead, it returns true. 
      else if (input.charAt(i) == '1')
      {
        return true;
      }
    }
    //If character at the position does not satisfy any of the conditions above, it returns false. 
    return false;
  }
  
  //This is my method that takes a string input aka my binary number, and converts it into a base 10 number. 
  public static int BinaryToNumber (String numberInput)
  {
    //creating an array named binaryarray, with the length of numberInput.
    int[] binaryarray= new int[numberInput.length()];
    
    //declaring and initializing the int variable sum, to store the sum AKA the base 10 number. 
    int sum=0;
    
    //for loop to go through each position in String numberInput
    for (int i=0; i< numberInput.length();i++)
    {
      if (numberInput.charAt(i)=='0')
      {
      }
      else if (numberInput.charAt(i)=='1')
      {
        int a= 2;
        
        //the value inside int b represents the exponent of 2, which depends on the position in numberInput. 
        int b= numberInput.length()-1-i; 
        binaryarray [i]= 1;
        
        //this for loop calcualtes the value of 2^n (depending on the position in numberInput--if the 
        //position is larger/higher, then the for loop repeats more times, adding a *2 each time it loops)
        //It then stores the value into the corresponding position of binaryarray. 
        for (int x= b; x> 0; x--)
        {
          binaryarray [i]= binaryarray[i]*a;
        }
      }     
      //this sums at all the values in binaryarray, and stores it in int variable sum. 
      sum += binaryarray [i];
    }
    return sum; 
  }
}
  