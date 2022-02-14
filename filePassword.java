import java.util.*;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class encrypt {
    public static void main(String[]args){


Scanner input = new Scanner(System.in);


System.out.print("Enter file name:");
String fileName = input.nextLine();
File file = new File(fileName);
String password = fileName;

// if the password is the file's name, then open the file
if(fileName.equals(password)){
    try{
        Scanner fileInput = new Scanner(file);
        while(fileInput.hasNextLine()){
            System.out.println(fileInput.nextLine());
        }
    }
    catch(FileNotFoundException e){
        System.out.println("File not found");
    }
}

    }
}
