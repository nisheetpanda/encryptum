import java.util.*;
import java.io.FileWriter;
import java.io.FileReader;

public class encryptum {
   public static void main(String[] args) throws Exception{
        try {
            FileWriter fw = new FileWriter("testout.txt");
            fw.write("this is a test");
            fw.close();
        }
        catch(Exception e){System.out.println(e);} 
                System.out.println("it work");

        FileReader fr = new FileReader("testout.txt");
        int i = 0;
        while(i != -1) {
            i = fr.read();
            if(i != -1)
                System.out.print((char)i);
        }
    }
}
           
 ////////////////////////////////////////////////////////
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class notes {
  public static void main(String[] args) {
    try {
      File readFile = new File("read.txt");
      Scanner myReader = new Scanner(readFile);
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        System.out.println(data);
      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
}
      
