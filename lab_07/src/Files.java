import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Files {
    public Files(){

    }

    public ArrayList<String> readFiles(String pathname){

        ArrayList<String> readString = new ArrayList<>();

        try {
            File myObj = new File(pathname);
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                readString.add(data);
//                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return readString;
    }

    public void writeFiles(String pathname, String writedata){
        try {
            FileWriter myWriter = new FileWriter(pathname);
            myWriter.write(writedata);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
