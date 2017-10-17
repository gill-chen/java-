//Complete this class.
public class Vector2D 
{//declare private attributes x and y positions of class object vector 2d
  private double xVal;
  private double yVal; 
  
  //assigns the input arguments as the x and y values for this particular instance of the vector 2d object 
  //when it is initliazed 
  public Vector2D (double xVal, double yVal)
  {
    this.xVal = xVal;
    this.yVal = yVal; 
  }
  //this distance method takes in another vector 2d object, and calculates the distance between the argument
  //vector 2d and this particular instance of the vector 2d object. 
  public double distance (Vector2D v)
  {
    double distanceSquared = Math.pow(v.xVal - this.xVal, 2) + Math.pow (v.yVal - this.yVal, 2);
    double distance = Math.sqrt(distanceSquared); 
    return distance; 
  }
  //returns the x position value of this vector 2d reference when called.
  public double getX ()
  {
    return this.xVal; 
  }
  // returns the y position value of this vector 2d reference when called.
  public double getY ()
  {
    return this.yVal; 
  }
 
}
