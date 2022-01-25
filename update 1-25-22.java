
import java.util.*;

public class encryptum {
    public static void main(String[] args) {
        String[] key1 = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W"
        ,"X","Y","Z","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x"
        ,"y","z","1","2","3","4","5","6","7","8","9","0","[","]","\\",";","'",",",".","/","`","~","!","@","#","$","%",
        "^","&","*","(",")","_","+","-","=","{","}","|",":","\"","<",">","?"," "}; 
        String[] key2 = new String[95];
        Scanner prompt = new Scanner(System.in);
        
        clearScreen();
        System.out.println("Welcome to the Encryptum® Encryption Service!");
        boolean validResponse = false;
        String response = " ";
        while(!validResponse) {
            System.out.println("Which would you like to do:\n1. Encrypt\n2. Decrypt");
            response = prompt.nextLine();
            clearScreen();
            if(!response.equals("1") && !response.equals("2")) 
                System.out.println("That is not a valid response.");
            else
                validResponse = true;
        }

        if(response.equals("1")) {
            System.out.println("Would you like to use a pre-existing key or make a new one?");
            
            for(int i = 0; i < 95; i++) {
                boolean valid = false;
                while(!valid) {
                    System.out.println("What would you like your " + (i+1) + "[]" + " string to be?");
                    String next = prompt.nextLine();
                    if(!checkUsed(key2,next)) {
                        key2[i] = next;
                        valid = true;
                    } else 
                        System.out.println("That string has already been used, has the same beginning as another string, or is blank.");
                }
            }
            for(int i = 0; i < (int)(Math.random() * 10000); i++)
                scrambleKey(key2);
            boolean confirmed = false;
            String message = "";
            while(!confirmed) {
                validResponse = false;
                System.out.println("Please enter the message you want to encrypt.");
                message = prompt.nextLine();
                while(!validResponse) {
                    System.out.println("Message:\n" + message + "\nAre you sure this is the message you want to encrypt?\n1. Yes\n2. No");
                    response = prompt.nextLine();
                    clearScreen();
                    if(!response.equals("1") && !response.equals("2")) 
                        System.out.println("That is not a valid response.");
                    else if(response.equals("1")) {
                        validResponse = true;
                        confirmed = true;
                    } else 
                        validResponse = true;
                }
            }

            message = encryptMessage(key1,key2, message);
        
            System.out.println(message);
            String descs = decryptMessage(key1, key2, message);
            System.out.println(descs);

        } else if(response.equals("2")) {

        }
    }

    public static void scrambleKey(String[] symbols) {
        for(int i = 0; i < (int)(Math.random() * 1000); i++) {
            int firstSlot = (int)(Math.random() * 95);
            int secondSlot = (int)(Math.random() * 95);
            String temp = symbols[firstSlot];
            symbols[firstSlot] = symbols[secondSlot];
            symbols[secondSlot] = temp;
        }
    }

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

    public static String encryptMessage(String[] letters, String[] key, String message) {
        String encryptedMessage = "";
        int temp = 0;
        for(int i = 1; i <= message.length(); i++) {
            String letter = message.substring(temp,i);
            for(int j = 0; j < letters.length; j++) {
                if(letters[j].equals(letter)) 
                    encryptedMessage += key[j];
            }
            temp++;
        }
        
        return encryptedMessage;
    }

    public static String decryptMessage(String[] letters, String[] key, String message) {
        String decryptedMessage = "";
        int position = 0;
        for(int i = 0; i <= message.length(); i++) {
            String match = message.substring(position,i);
            for(int j = 0; j < 95; j++) {
                if(match.equals(key[j])) {
                    decryptedMessage+=letters[j];
                    j = 95;
                    position = i;
                }
            }
        }
        return decryptedMessage;
    }
}
