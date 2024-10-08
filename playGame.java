
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class playGame {
    public static void main(String[] args){ 
        // this script is executed by emulation station, when a game is selected.
        // parameters specified in es_systems.config
        
        String pathToCore = args[0];
        String pathToRom = args[1];

        File pauseFile = new File("pause.txt");
        //contains a single boolean value
        // is changed to true when the arcade pause button is pressed
        File timeFile = new File("time.txt");
        //contains a single int 0 or higher
        // is increased by the coin reader script when a coin is inputed

        try {
            // read current time 
            Scanner timeFileReader = new Scanner(timeFile);
            int time = 0;
            if(timeFileReader.hasNextLine()){
                time = Integer.parseInt(timeFileReader.nextLine());
            }
            timeFileReader.close();
            System.out.println(time);
            
            Boolean pause = false;
            
            if(time > 0) {
                //time remains to play. Open game.
                // Runtime.getRuntime().exec("retroarch --fullscreen -L " + pathToCore + " " + pathToRom);

                while(time > 0 && pause == false){
                    try {
                        Scanner pauseFileReader = new Scanner(pauseFile);
    
                        if(pauseFileReader.hasNextLine()) {
                            pause = Boolean.parseBoolean(pauseFileReader.nextLine());
                        }
                        pauseFileReader.close();
    
                    } catch (FileNotFoundException e) {
                        System.out.println(e);
                    }

                    try{
                        try{
                            Thread.sleep(1000);
                        } catch (InterruptedException e){
                            System.out.println(e);
                        }
                        FileWriter writer = new FileWriter(timeFile);
                        time--;
                        writer.write(Integer.toString(time));
                        writer.close();
                        System.out.println(Integer.toString(time));
                        
                    } catch (IOException e){
                        System.out.println(e);
                    }

                }
            }

            if(pause == false){
                // no time left to play:
                // simulate pause key press
                // show coin prompt
            } else {
                // player exited or paused the game, reset varaibles and end script
                try {
                    FileWriter writer = new FileWriter(pauseFile);
                    writer.write("false");
                    writer.close();
                } catch (IOException e){
                    System.out.println(e);
                }
            }

        } catch (FileNotFoundException e){ System.out.println(e); }
    }
}
