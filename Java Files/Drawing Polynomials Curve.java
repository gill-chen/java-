import java.util.InputMismatchException;
import java.util.Scanner;
  
public class Question2
{  
  public static void main (String [] args)
  {
    //have to declare and initialize variables, or else code does not compile.
    Double a= 0.0; Double b= 0.0; Double c=0.0; Double aa=0.0; Double bb=0.0;
    
    //creating boolean variable for while loop to work properly, and to ask user for input again 
    //if the input is not a number/digit. --When the user puts in the right input for all variables, 
    //the boolean variable rightinput is given value true, and the while loop stops. 
    boolean rightinput= false;
    
    while (!rightinput)
    {
      Scanner inputKeyboard = new Scanner(System.in);
      
      //not covered in class, but I looked up how to use a try statement to catch exceptions 
      //when the program executes, especially if the user puts in a non-digit character.
      try
      { 
        System.out.println ("Please enter value for variable a (For y= ax + b)");
        a = inputKeyboard.nextDouble();
        System.out.println ("Please enter value for variable b (For y= ax + b)");
        b = inputKeyboard.nextDouble();        
        System.out.println ("Please enter value for variable aa (For y = aa(x^2)+bb(x)+c)");
        aa = inputKeyboard.nextDouble();
        System.out.println ("Please enter value for variable bb (For y = aa(x^2)+bb(x)+c)");
        bb = inputKeyboard.nextDouble();
        System.out.println ("Please enter value for variable c (For y = aa(x^2)+bb(x)+c)");
        c = inputKeyboard.nextDouble();
        rightinput=true;
      }
      catch (InputMismatchException e)
      {
        System.out.println("Please re-enter the value in digits.");
      }
    }
    //Here are my 2 for loops to print out my axis, lines and parabola. For each position, it goes through
    //the if, else-if statements to determine what to print. If none of the conditions are satisfied in any
    //of the if, else-if statements, a " " will be printed. 
    for (int y= 10; y>=-10; y--)
    {
      for (int x = -10; x<= 10; x++)
      {
        if (!(DrawAxis(x,y)==""))
        {
          System.out.print(DrawAxis(x,y));
        }
        //If this else if condition below was set as just DrawAxis (x,y)=="", and if the position (x,y) is so that
        //DrawLine method returns an empty string as well, the else if statement will just print out an empty
        //string, instead of the space that we want--resulting in a strange looking graph. Same idea applies for 
        //other else if statements. 
        else if (DrawAxis(x,y)=="" && !(DrawLine (x,y,a,b)=="")) 
        {
          System.out.print(DrawLine (x,y,a,b));
        }
        else if (DrawAxis(x,y)=="" && DrawLine(x,y,a,b)=="" && !(DrawParabola (x,y,aa,bb,c)==""))
        {
          System.out.print(DrawParabola (x,y,aa,bb,c));
        }
        else 
        {
          System.out.print(" ");
        }
      }                  
      System.out.print("\n");
    }
  }
  //This is my DrawAxis method; using if, else-if statements, it determines what character
  //is to be returned for each position. If the position (x.y) does not satisfy any condition,
  //an empty string "" is returned.
  public static String DrawAxis (int x, int y)
  {     
    if (y==0 && x==0)
    {
      return ".";  
    }
    else if (y==10 && x==0)
    {
      return "^";
    }  
    else if (y==0 && x==10)
    {
      return ">";
    }
    else if (y==0)
    {
      return "-";
    }
    else if (x==0)
    {
      return "|";
    }
    else 
    {
    return "";
    }
  }
  //This is my DrawLine method; using if, else-if statements, it determines what character
  //is to be returned for each position. I set my e value to 0.5 because I felt that it is
  //a good in-between number-enough to make some positions satisfy the condition, but not 
  //too relaxed that results in a very thick line. 
  public static String DrawLine (int x, int y, double a, double b)
  {
    double e= 0.5;    
    if (a*x + b <= y + e && a*x + b>= y - e)
    {
      return "*";
    }
    else
    {
      return "";
    }
  }
  //This is my DrawParabola method; using if, else-if statements, it determines what character
  //is to be returned for each position. I set my e value to 2 because I felt that it is
  //a good in-between number-enough to make some positions satisfy the condition, but not 
  //too relaxed that results in a very thick parabola curve. Again, like the DrawAxis and DrawLine
  //methods, if the position does not satisfy the conditions, it returns an empty string "". 
  public static String DrawParabola (int x, int y, double aa, double bb, double c)
  {
    double e = 2;
    if (aa*x*x+bb*x+c<= y+e && aa*x*x+bb*x+c>= y-e )
    {
      return "#";
    }
    else 
    {
      return "";
    }
  }  
}  

