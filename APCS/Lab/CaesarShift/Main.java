import java.util.*;
import java.lang.*;
class Hanoi {
  
  static Scanner s = new Scanner(System.in);
  
  public static void main(String[] args) {
        
    System.out.println("Would you like to (E)ncrypt or (D)ecrypt a massage?");
  	boolean doEncrypt = getEorD().equals("E");
    
    System.out.println("Enter your shift value: ");
    int shift = getShift();

    System.out.println("Enter your massage: ");
    String massage = s.nextLine();
    
    System.out.printf("Your %s massage is: %n%s%n", 
                      doEncrypt ? "encrypted" : "decrypted", 
                      shiftMessage(massage, shift, doEncrypt));
                      
  }
  
  public static String shiftMessage(String massage, int shift, boolean doEncrypt) {
    StringBuilder sb = new StringBuilder();
    if (shift == 0) { return sb.toString();}
    for (int i = 0; i < massage.length(); i++) {
      char c = massage.charAt(i);

      if (isLetterOrDigit(c)) {
        if (doEncrypt) {
          c = (char) (c + shift);
          if ((Character.isLowerCase(massage.charAt(i)) && c > 'z') || (Character.isUpperCase(massage.charAt(i)) && c > 'Z')) {
            c = (char) (c - 26);
          }
        } else {
            c = (char) (c - shift);
            if ((Character.isLowerCase(massage.charAt(i)) && c < 'a') || (Character.isUpperCase(massage.charAt(i)) && c < 'A')) { c = (char) (c + 26);}
        }
      }
      sb.append(c);
    }


    // This method takes the <massage> parameter you input and shifts it according to the <shift> parameter. 

    // If the program is meant to encrypt, then <doEncrypt> is true, 
    //   and you should shift every letter in <massage> FORWARDS <shift> letters.

    // If the program is meant to decrypt, then <doEncrypt> is false, 
    //   and you should shift every letter in <massage> BACKWARDS <shift> letters. 
    
    
    return sb.toString();
  }
  private static boolean isLetterOrDigit(char c) {
    return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
  }
  
  public static int getShift() {
    // Data validation: 
    //   this method should return an integer between -25 and 25, inclusive. 
    int n = 0;
    boolean validInput = false;

    while (!validInput) {
        if (s.hasNextInt()) {
            n = s.nextInt();
                validInput = true;
        } else {
            System.out.println("Invalid input. Please enter a positive integer.");
        }
        s.nextLine(); // Consume invalid input
    }
    return n % 26;
}
  
  public static String getEorD() {
    // Data Validation:
    // this method should return either "E" or "D". 
    // The main method asks whether they want to (e)ncrypt or (d)ecrypt, 
    //   and this method validates that they input either "E" or "D" properly. 
    // You should accept either uppercase or lowercase e/d, 
    //   but they should enter only one letter, otherwise have them input again. 
    String Char = "";
    boolean bool = false;
    while (!bool) {
      Char = s.nextLine().toUpperCase();
      if (Char.toLowerCase().equals("e") || Char.toLowerCase().equals("d")) {
        bool = true;
      } else {
        System.out.println("Your letter should be e or d");
      }
    }

    return Char;
  }
}
