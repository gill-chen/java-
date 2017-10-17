public class Country 
{
 // declares private attributes of the country class
 private String name;
 private City[] cities;
 private boolean connected;
 
 //initiailizes the country class by giving it a name, the number of cities, and the maximum distance
 //between neighbours
 public Country (String name, int numCities, double maxDist)
 {
   this.name = name;
   this.cities = new City [numCities];
   
   //gives a new 'random' city for every iteration (the length of this for loop is the number of cities
   //this country reference will have. 
   for (int i = 0; i < this.cities.length ; i++)
   {
     cities[i] =  new City(); 
   }
   //sets neighbours for each city created in this country reference.
   for (int i = 0; i < this.cities.length; i++)
   {
     cities[i].setNeighbours(maxDist, this.cities); 
   }
 }
 
 //this method checks if all the cities are connected to at least 1 other city. 
 public boolean setConnectivity()
 {
   cities[0].explore();
   this.connected = true; 
   for (int i = 0; i < cities.length; i++)
   {
     if (cities[i].getExplored() == false)
     {
      this.connected = false;  
     }
   }
   return this.connected; 
 }
 
 //the following methods returns private attributes of this country reference. 
 
 public City[] getCities ()
 {
   return cities;
 }
 
 public String getName ()
 {
   return name; 
 }
}
