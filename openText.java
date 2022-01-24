import java.util.*;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
public class encrypt {
    public static void main(String[]args){
 
// User types in their password
Scanner input = new Scanner(System.in);
System.out.print("Enter password:");
String password = input.nextLine();
String realPassword = "!!!";


if(password.equals(realPassword)){
// open passwords.txt if password is correct
System.out.print("Enter file name:");
String fileName = input.nextLine();
File file = new File(fileName);
try{
// read the file
Scanner fileInput = new Scanner(file);
while(fileInput.hasNextLine()){
    String line = fileInput.nextLine();
    System.out.println(line);

}
}
catch(FileNotFoundException e){
    System.out.println("File not found");
}
}
else
{
    System.out.println("Invalid password");
        }

    }



}
