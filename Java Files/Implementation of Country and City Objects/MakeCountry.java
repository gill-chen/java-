public class MakeCountry 
{
 public static void main(String[] args)
 {
  //Chaning this number will change the number of cities in the country.
  int numCities = 15;
  //You can rename your country to whatever you like.
  String countryName = "Canada";
  
  double maxDist = 50; 
  //creates new country object 
  Country myCountry = new Country (countryName, numCities, maxDist);
  //TODO Create the country
      boolean connected = false;
      //calls setConnectivity method to see whether all the cities in the country are connected 
      //at least once to another city; if it is, the method returns true. 
  if (myCountry.setConnectivity())
  {
    connected = true; 
  }
    
  //TODO Check if this country is connected. 
  
  System.out.println(countryName + " is connected... " + connected);
  
  //Once every thing else is complete, uncomment line below to display the image.
  CountryMap map = new CountryMap(myCountry.getCities(), myCountry.getName());
 }
}
