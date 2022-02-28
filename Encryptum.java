import java.util.*;
import java.io.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;  
public class Encryptum {

   //    MAIN METHOD ----------------------------------------------------------------------------------------------------------------------------------------------------------
   public static void main(String args[])throws Exception {
      Scanner scanner = new Scanner(System.in);
      String[] letters = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W"
      ,"X","Y","Z","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x"
      ,"y","z","1","2","3","4","5","6","7","8","9","0","[","]",";","'",",",".","/","`","~","!","@","#","$","%",
      "^","&","*","(",")","_","+","-","=","{","}","|",":","<",">","?"," "};
      String[] inputKey = new String[letters.length];
      clearScreen();
      System.out.println("Welcome to the Encryptum® Encryption Service!");
      System.out.println();
      System.out.println("Encryptum is a simple and easy encryption tool that allows you to set a certain key (ex. a=g, b=3, c=erg) which can be used to write messages to friends."); 
      System.out.println("What makes this unique is that your key is accessed threw a password which can be downloaded by a friend.");
      System.out.println("This allows for multiple keys on you password, for different groups, while blocking others from decrypting the code.");
      System.out.println();
      boolean validResponse = false;
      boolean validResponse2 = false;
      String response = " ";
      String action = " ";
      while(!validResponse) {
         System.out.println("Would you like to:\n1. Create A New Key \n2. Use An Existing Key");
         response = scanner.nextLine();
         clearScreen();
         if(!response.equals("1") && !response.equals("2")){ 
             System.out.println("That is not a valid response.");
         }
         else{
                validResponse = true;
         }
      }
      // IF THE USER WANT TO MAKE A NEW PASSWORD AND KEY
      if(response.equals("1")){
         System.out.println("What is your new password");
         String password = scanner.nextLine();
         for(int i = 0; i < inputKey.length; i++) {
            boolean valid = false;
            while(!valid) {
                System.out.println("What would you like your " + (i+1) + "[]" + " string to be?");
                String next = scanner.nextLine();
                if(!checkUsed(inputKey,next)) {
                    inputKey[i] = next;
                    valid = true;
                } else 
                    System.out.println("That string has already been used, has the same beginning as another string, or is blank.");
            }
         } 
         writeConvFile(inputKey, password);
         System.out.println("The File is written");
      }
      // IF THE USER WANTS TO USE AN OLD PASSWORD AND KEY
      else if(response.equals("2")){
         System.out.println("What is the password (This will fail later on if you enter wrong password)");
         String password = scanner.nextLine();
         while(!validResponse2) {
            System.out.println("Would you like to:\n1. Encrypt \n2. Decrypt");
            action = scanner.nextLine();
            clearScreen();
            if(!action.equals("1") && !action.equals("2")){ 
                System.out.println("That is not a valid response.");
            }
            else{
                   validResponse2 = true;
            }
         }
         if(action.equals("1")){
            System.out.println("What is your message that you want to encrypt");
            String message = scanner.nextLine();
            String Encmessage =  encryptMessage(letters, readingFileStrings(password), message);
            System.out.println("Here is your encrypted message:");
            System.out.println(Encmessage);
         }
         else if(action.equals("2")){
            System.out.println("What is your message that you want to decrypt");
            String Encmessage = scanner.nextLine();
            System.out.println("Here is your decrypted message:\n"+decryptMessage(letters, readingFileStrings(password), Encmessage));
         }        
      }
   }
   //    -----------------------------------------------------------------------------------------------------------------------------------------------------------------------

   //    READ AND WRITE A FILE -------------------------------------------------------------------------------------------------------------------------------------------------

   public static void writeConvFile(String[] inputKey, String password) throws Exception{
      FileWriter writer = new FileWriter("./"+password+".txt");
      int len = inputKey.length;
      for (int i = 0; i < len; i++) {
         writer.write(inputKey[i] + "\n");
      }
      writer.close();
   }

   public static String[] readingFileStrings(String password)throws IOException{
      // Creates a FileReader Object
      BufferedReader br = new BufferedReader(new FileReader(password+".txt")); 
      ArrayList<String> a = new ArrayList<String>();
      String line;
      while((line = br.readLine())!=null) {
          a.add(line);
      }   // reads the content to the array
      String[] x = new String[a.size()];
      for(int i = 0; i<a.size(); i++) x[i] = a.get(i);
      br.close();
      return x;
   }
   //  -------------------------------------------------------------------------------------------------------------------------------------------------------------------------

   //  ENCRYPT AND DECRYPT -----------------------------------------------------------------------------------------------------------------------------------------------------

   public static String encryptMessage(String[] letters, String[] inputKey, String message) {
      String encryptedMessage = "";
      int temp = 0;
      for(int i = 1; i <= message.length(); i++) {
          String letter = message.substring(temp,i);
          for(int j = 0; j < letters.length; j++) {
              if(letters[j].equals(letter)) 
                  encryptedMessage += inputKey[j];
          }
          temp++;
      }
      return encryptedMessage;
  }

   public static String decryptMessage(String[] letters, String[] inputKey, String message) {
      String decryptedMessage = "";
      int position = 0;
      for(int i = 0; i <= message.length(); i++) {
         String match = message.substring(position,i);
         for(int j = 0; j < letters.length; j++) {
            if(match.equals(inputKey[j])) {
                  decryptedMessage+=letters[j];
                  j = letters.length;
                  position = i;
            }
         }
      }
      return decryptedMessage;
   }
   
   //  ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

   //  CHECK USED WHILE WRITING FILE AND CLEAR SCREEN ----------------------------------------------------------------------------------------------------------------------------
   public static boolean checkUsed(String[] symbols, String line) {
      clearScreen();
      if(line.length() == 0)
          return true;
      int existingPhrases = 0;
      for(int i = 0; i < symbols.length; i++) {
          if(symbols[i] != null) 
              existingPhrases++;
          else 
              i = symbols.length;
      }
     for(int i = 0; i < existingPhrases; i++) {
          for(int j = 1; j <= symbols[i].length(); j++) {
              if(symbols[i].substring(0,j).equals(line)) 
                  return true;
          }  
      } 

      for(int i = 0; i < existingPhrases; i++) {
          for(int j = 1; j <= line.length(); j++) {
              if(line.substring(0,j).equals(symbols[i]))
                  return true;
          }
      }
      //Need to account for blank enters
      return false;
  }

   public static void clearScreen() {
      System.out.print("\033[H\033[2J");  
      System.out.flush();  
  }

  //    -------------------------------------------------------------------------------------------------------------------------------------------------------------------------
}

