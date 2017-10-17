import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class PlaySong{
    public static void main( String[] args)
    {
      //creates a new 'music player'.
      MusicInterpreter myMusicPlayer = new MusicInterpreter();
      //creates a new song. 
      Song mySong = new Song(); 
      //specifiy the file path of the song to be uploaded 
      String song_file_path = "./data/bonusQuestion.txt"; 
      
      try 
      {
        //loads specified song text file into mySong. 
        mySong.loadFromFile(song_file_path);
        // uncomment this line to print the available instruments
        System.out.println(myMusicPlayer.availableInstruments());
      }
      //if chosen song text file is not found, the message prompts the user to try another one. 
      catch (IOException e)
      {
        System.out.println("Song file not found, please try another file"); 
      }
      
      //the following loads the song into the player, plays the song, and finally closes the song when it is done. 
      myMusicPlayer.loadSong(mySong); 
      myMusicPlayer.play();
      myMusicPlayer.close(); 
    }
}