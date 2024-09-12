
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class startGame {
    public static void main(String[] args){      
        try {
            File timefile = new File("time.txt");
            String timedata = "";
            Scanner reader = new Scanner(timefile);

            while(reader.hasNextLine()){
                timedata += reader.nextLine();
            }

            reader.close();
            System.out.println(timedata);
            int time = Integer.parseInt(timedata);
            
            if(time <= 0){
                
            } else {
                // retroarch --fullscreen -L /path/to/core.so /path/to/rom
                // Runtime.getRuntime().exec("retroarch --fullscreen -L " + args[0] + " " + args[1]);
                while(time > 0){
                        try{
                            try{
                                Thread.sleep(1000);
                            } catch (InterruptedException e){
                                System.out.println(e);
                            }
                            FileWriter writer = new FileWriter(timefile);
                            time--;
                            writer.write(Integer.toString(time));
                            writer.close();
                            System.out.println(Integer.toString(time));
                        } catch (IOException e){
                            System.out.println(e);
                        }
                        //show coin prompt
                    }
            }
        } catch (FileNotFoundException e){ System.out.println(e); }
    }
}
