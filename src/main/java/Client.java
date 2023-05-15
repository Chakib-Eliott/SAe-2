import java.io.File;
import java.io.FileNotFoundException;

public class Client {
    public static void main (String [] args) {
        File file = new File("data"+File.separator+"scenario_7.txt");
        try{
            Scenario sTest = Parsing.parsing(file);
            System.out.println(sTest);
        }catch (FileNotFoundException e){
            System.err.println(e);
            System.exit(1);
        }
    }
}
