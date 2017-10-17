import java.util.ArrayList;
import java.util.Hashtable;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class SongWriter
{
    private Hashtable<Integer,String> pitchToNote;
    // The constructor of this class
    public SongWriter()
    {
        this.initPitchToNoteDictionary();
    }
    
    // This initialises the pitchToNote dictionary,
    // which will be used by you to convert pitch numbers
    // to note letters
    public void initPitchToNoteDictionary(){
        pitchToNote  = new Hashtable<Integer, String>();
        pitchToNote.put(60, "C");
        pitchToNote.put(61, "C#");
        pitchToNote.put(62, "D");
        pitchToNote.put(63, "D#");
        pitchToNote.put(64, "E");
        pitchToNote.put(65, "F");
        pitchToNote.put(66, "F#");
        pitchToNote.put(67, "G");
        pitchToNote.put(68, "G#");
        pitchToNote.put(69, "A");
        pitchToNote.put(70, "A#");
        pitchToNote.put(71, "B");
    }

    // This method converts a single MidiNote to its notestring representation
    public String noteToString(MidiNote note)
    {
        String result = "";
        String pitch; 
        String duration; 
        //the if and else statements check if the note is a pause or a note. If it is a note, it uses the pitch value
        //as a key to the hashtable containing all the pitch names, and takes the string value out as the pitch name. 
        if (!(note.isSilent()))
        {
          //this takes into account the octave changes (if any). 
          int octave = (note.getOctave()*12); 
          duration = Integer.toString(note.getDuration());
          if (duration.equals("1"))
          {
            duration = ""; 
          }
          //taking away (or adding) the value of the integer variable octave to the pitch value of the MidiNote 
          //ensures that the pitch selected can be found in the hashtable provided. 
          pitch = pitchToNote.get(note.getPitch()-octave);
          result = duration+pitch;
          return result;
        }
        //if the midinote has its silent attribute set to true, it is a pause and the pitch value will then be 
        //set to 'P'. 
        else
        {
          duration = Integer.toString(note.getDuration());
          pitch = "P";
          result = duration+pitch;
          return result;
        }
    }

    // This method converts a MidiTrack to its notestring representation.
    // You should use the noteToString method here
    public String trackToString(MidiTrack track)
    {
        ArrayList<MidiNote> notes = track.getNotes();
        String result = "";
        int previous_octave = 0;
        MidiNote current_note;
        
        //iterates through the arraylist of MidiNotes 
        for (int x = 0; x < notes.size(); x++)
        {
          MidiNote current = notes.get(x); 
          //these if and else statements takes into account octave changes, and adds these octave changes to the 
          //track string. By comparing the previous octave to the current octave register, the code will add either '<'
          //or '>' character depending on whether there has been an octave change. 
          if (current.getOctave() == previous_octave)
          {
            result += noteToString(current); 
          }
          else if (current.getOctave() < previous_octave)
          {
            String octaveString = "<";
            //this adds one or more than one more '<' charater depending on the size of the octave change.  
            for (int i = 0; i < ((previous_octave)-(current.getOctave())); i++)
            {
            result += octaveString; 
            }
            //sets the current octave as the 'previous' octave, for comparison with the next MidiNote.
            previous_octave = current.getOctave(); 
            //adds the note to the string after taking into account any octave changes 
            result += noteToString(current); 
          }
          else if (current.getOctave() > previous_octave)
          {
            String octaveString = ">";
            //this adds one or more than one more '>' charater depending on the size of the octave change. 
            for (int i = 0; i < ((current.getOctave())-(previous_octave)); i++)
            {
            result += octaveString; 
            }
            //sets the current octave as the 'previous' octave, for comparison with the next MidiNote.
            previous_octave = current.getOctave(); 
            //adds the note to the string after taking into account any octave changes
            result += noteToString(current); 
          }
        }
        return result;
    }
    
    // This method writes the properties of the Song s1 object
    // and writes them into a file in the location specified by 
    // file_path. The text file will have the same format as 
    // those provided.
    public void writeToFile (Song s1, String file_path) throws IOException 
    {
      //creates new file, and assigns it a name based on the song chosen and places it in the folder
      //specified by the file path. 
      FileWriter file = new FileWriter(file_path + s1.getName() + "_reverse.txt"); 
      //creates a new writer object. 
      BufferedWriter writer = new BufferedWriter(file);
      
      //writes the first line of the text file, which is always BPM.    
      writer.write("bpm" + " " + "=" + " " +Integer.toString(s1.getBPM()));
      writer.newLine(); 
      //writes the second line of the text file, which is always the name of the song. 
      writer.write("name" + " " + "=" + " " +s1.getName());
      writer.newLine(); 
      //sometimes there is no specific soundbank associated to the song chosen, so the if and else
      //statement writes the third line (or not) depending on whether there is one specified. 
      if (s1.getSoundbank() == "")
      {
      }
      else 
      {
         writer.write("soundbank" + " " + "=" + " " +s1.getSoundbank());
         writer.newLine(); 
      }
      //the for loop handles one or more instrument tracks. There will always be an instrument assigned to the track 
      //(even if it's by default), and so this for loop writes one line indicating the instrument, and the following
      //line indicating the track in 'string' format. 
      for (int x = 0; x < s1.getTracks().size(); x++)
      {
        String instrumentName = Integer.toString(s1.getTracks().get(x).getInstrumentId());
        writer.write("instrument" + " " + "=" + " " + instrumentName);
        writer.newLine(); 
        String track = trackToString(s1.getTracks().get(x)); 
        writer.write("track" + " " + "=" + " " + track);
        writer.newLine(); 
      }      
      writer.close(); 
    }
    
    //this main method creates a new song, one that is reversed from an existing song text file. It loads the existing
    //song file into the new song, reverses the track, and writes out this new song into a text file. It also handles
    //exceptions, e.g. when the chosen existing song text file does not exist, or when the file location in which the
    //user wants to place the new song text file in does not exist. 
    public static void main( String[] args)
    {
      Song reverse = new Song(); 
      String fileName_Here = "./data/02.txt"; 
      
      try 
        {
          reverse.loadFromFile(fileName_Here);
        }
        catch (IOException e)
        {
          System.out.println("The file "+ fileName_Here + " was not found. Please select another file"); 
        }
       
        //calling on this method reverses all the tracks. 
        reverse.revert();
        
        try
        {
          SongWriter reverseSong = new SongWriter(); 
          reverseSong.writeToFile(reverse, "./data/"); 
        }
        catch (IOException e)
        {
          System.out.println("The file location was not found, please use another file location."); 
        }
    }
}