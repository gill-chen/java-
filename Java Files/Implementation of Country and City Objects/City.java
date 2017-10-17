import java.util.Random;

public class City 
{
 private static String[] namePrefixes = { "Chiguiro", "Maracas", "Raccoon",
  "Glass", "Iron", "Spring", "Winter", "Autumn", "Summer", "Godel",
  "Recursion", "Escher", "PowPow", "LOL", "Cheese", "MasterRoshi", "Pants", "Dork",
  "Cat", "Liszt", "Hysteria", "Cool", "Ennui", "Tortoise", "Mudkip", "Tonkatsu", 
  "Rainy", "Slump", "Tortilla", "Rodizio", "Ajiaco", "Sunny" };
 
 private static String[] nameSuffixes = { "ville", "vale", "_City", "town", "ton",
   "hill", "field", "land", "ia", "furt", "grad", "lia", "stadt", "stan" };
 
 //declares private attributes of city class object- name, x and y position in the map, and its neighbours
 private String name;
 private Vector2D pos;
 private City[] neighbours; 
 // it has not been explored at this point, so its set as 'false'. 
 private boolean explored = false;
 
//this city constructor initializes the name and position. 
 public City() 
 {
   this.name = (namePrefixes[new Random().nextInt(namePrefixes.length)]) + " " + (nameSuffixes[new Random().nextInt(nameSuffixes.length)]);
   this.pos = new Vector2D (new Random().nextInt(150), new Random().nextInt(150)); 
  //TODO complete constructor.
  //Generate a random name that is combination of a random prefix and suffix.
  //Initialize the initial position as a vector.
  //Max x and max y value should be 150.
 }
 
 //maxDist is the further distance apart two cities can be and still be connected.
 //Return the number of neighbours found
 //If there are j neighbours, then the last length-j spots of neighbours should be null
 //And the first j elements should be city objects
 
 //this method sets its neighbour according to the given input argument for maximum Distance. 
 public void setNeighbours(double maxDist, City[] cities)
 {
   this.neighbours = new City[cities.length-1]; 
   int numberOfNeighbours = 0; 
   for (int i = 0; i<cities.length ; i++) 
  {
     if(this.pos.distance(cities[i].pos) <= maxDist)
     {
       this.neighbours[numberOfNeighbours] = cities[i]; 
       numberOfNeighbours++; 
     }
  }
 }
 
 //Searches to see which cities are connected to the current city.
 //If a city can be reached, its boolean 'explore' value will be true after this method is called
 //Otherwise, it will be false.
 public void explore() 
 {
   //this.explored = true; 
 
   for (int i = 0; i < neighbours.length && !(neighbours[i] == null); i++)
   {
     this.explored = true; 
     if (neighbours[i].explored == false)
     {
       neighbours[i].explore(); 
     }
   }
 }
 // the following gives returns the attribute of this city object reference when 'called'. 
 
 public Vector2D getPos()
 {
  return pos;
 }

 public String getName() 
 {
  return name;
 }
 
 public City[] getNeighbours()
 {
  return neighbours;
 }
 
 public boolean getExplored()
 {
  return explored; 
 }
}

