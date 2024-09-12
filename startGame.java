
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
            
            try {
                FileWriter writer = new FileWriter(timefile);
                writer.write(Integer.toString(time));

                if(time <= 0){

                } else {
                    try{
                        // retroarch --fullscreen -L /path/to/core.so /path/to/rom
                        // Runtime.getRuntime().exec("retroarch --fullscreen -L " + args[0] + " " + args[1]);
                        
                        while(time > 0){
                            try{
                                time--;
                                writer.write(Integer.toString(time));
                                System.out.println(Integer.toString(time));
                                Thread.sleep(1000);
                            } catch (InterruptedException e){
                                System.out.println(e);
                            }
                            //show coin prompt
                        }

                    } catch (IOException e){
                        System.out.println(e);
                    }
                }

                writer.close();

            } catch (IOException e){ System.out.println(e); }
        } catch (FileNotFoundException e){ System.out.println(e); }
    }
}
