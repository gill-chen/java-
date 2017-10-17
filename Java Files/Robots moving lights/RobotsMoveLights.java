import java.awt.Color;
import becker.robots.*; 

public class RobotsMoveLights 
{

 public static void moveToDiagonal(Robot robot) 
 {
  int moves = 1;  
  //Assuming that the robot is always right next to the first flasher on the same street, and the flashers are
  //always in a row with no gaps between each other, the robot moves one step towards east. 
  robot.move();
  
  //as long as the robot identifies the object at the current location is 'pickable', the robot will pick it up
  //and move on to the next intersection on the same street. (keeps moving east)
  while (robot.canPickThing())
  {
    robot.pickThing(); 
    robot.move(); 
    moves++; 
  }
  //changing the direction so that robot now faces west to return back to the 1st flasher's original position
  robot.turnLeft();
  robot.turnLeft(); 
  //Since the number of moves made by the robot was stored in the type int 'moves' variable, we can now use 
  //this number in a for loop to return back to the first flasher's original position. 
  for (int i = 0; i < moves - 1; i++)
  {
    robot.move(); 
  }
  //Once it has reached the original position of the 1st flasher, it will begin to place the flashers 
  //diagonally from the original position. 
  for (int i = 0; i < moves; i++)
  {  
    //it puts one flasher in the corresponding position each time and moves to the next position at each 
    //iteration. 
    robot.putThing();
    //at this point the robot is only facing west or east at the end each iteration. 
    if (robot.getDirection() == Direction.WEST)
    {
      robot.turnLeft();
      robot.move();
      robot.turnLeft();
      robot.move();
    }
    else if (robot.getDirection() == Direction.EAST)
    {
      turnRight(robot); 
      robot.move();
      robot.turnLeft();
      robot.move(); 
    }
    
  }
 }
 //created method to make the robot turn right. 
 public static void turnRight(Robot robot)
 {
   for (int i = 1; i <= 3; i++)
   {
     robot.turnLeft(); 
   }
 }
   
 public static void main(String[] args) 
 {
  final int LIGHT_STREET = 1;
  final int LIGHT_AVENUE = 1;
  final int NUMBER_FLASHERS = 6;
  
  City montreal = new City(12,12);

  Robot asimo = new Robot(montreal, LIGHT_STREET,
   LIGHT_AVENUE - 1, Direction.EAST);

  for (int i = 0; i < NUMBER_FLASHERS; i++) 
  {
   new Flasher(montreal, LIGHT_STREET, LIGHT_AVENUE + i,
    true);
  }   
   
  moveToDiagonal(asimo);  
 }

}
