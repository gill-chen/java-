import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.util.ArrayList;

public class Song
{
    String myName;
    int myBeatsPerMinute;
    String mySoundbank;
    ArrayList<MidiTrack> myTracks;
    
    // The constructor of this class
    public Song()
    {
        myTracks = new ArrayList<MidiTrack>();
        myBeatsPerMinute = 200;
        mySoundbank = "";
        myName = "Default_Name";
    }

    // GETTER METHODS

    public String getName(){
       return myName;
    }

    public String getSoundbank(){
       return mySoundbank;
    }
    
    public int getBPM(){
        return myBeatsPerMinute;
    }

    public ArrayList<MidiTrack> getTracks()
    {
        return myTracks;
    }
    // This method loads the properties and build the tracks of this
    // song object from a file in the location specified by 
    // file
    public void loadFromFile (String file_path) throws IOException 
    {
      BufferedReader reader = new BufferedReader(new FileReader(file_path));
      String currentLine = reader.readLine();  
      boolean instrumentCheck = false;
      int instrumentSpecified = 0; 
      
      while ((currentLine != null))
      {
        String[] resultLine = currentLine.split("\\s+"); 
        int x = 0; 
        
        if (resultLine[x].equals("bpm"))
        {
          this.myBeatsPerMinute = Integer.valueOf(resultLine[2]); 
        }
        else if (resultLine[x].equals("name"))
        {
          this.myName = resultLine[2]; 
        }
        else if (resultLine[x].equals("soundbank"))
        {
          this.mySoundbank = resultLine[2]; 
        }
        else if (resultLine[x].equals("instrument"))
        {
          instrumentSpecified = Integer.valueOf(resultLine[2]); 
          instrumentCheck = true; 
        }
        else if ((resultLine[x].equals("track")) && (instrumentCheck))
        {
          MidiTrack instrument = new MidiTrack(instrumentSpecified); 
          instrument.loadNoteString(resultLine[2]);
          this.myTracks.add(instrument); 
          instrumentCheck = false; 
        }
        else if (resultLine[x].equals("track") && !instrumentCheck)
        {
          MidiTrack noInstrument = new MidiTrack(instrumentSpecified);
          noInstrument.loadNoteString(resultLine[2]);
          this.myTracks.add(noInstrument); 
        }    
        currentLine = reader.readLine(); 
      }  
    }
    
    public void revert()
    {
      for (int i = 0; i<myTracks.size(); i++)
      {
        myTracks.get(i).revert();
      }
    }
    /*
    public static void main(String[] args)
    {
      Song song = new Song ();
      try 
      {
        song.loadFromFile("./data/07.txt"); 
      }
      catch (IOException e)
      {
        System.out.println("Song file not found, please try another file"); 
      }
    }
    */
}