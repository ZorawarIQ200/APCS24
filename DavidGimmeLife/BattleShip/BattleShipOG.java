import java.util.*;

public class BattleShipOG {
  public static Scanner s = new Scanner(System.in); 
  private static char[][] board; 
  private static int shipSize; 
  private static int AREA; 
  private static int shipRow;
  private static int shipCol;
  private static int fireCount;
  private static int hitCounter;
  private static int guess;
  private static boolean hit;
  private static int ShipHitCounter = 0;
  private static int shipCounter = 4; 
  private static int[] shipColArray; 
  private static int[] shipRowArray; 
  private static int[] shipSizeArray;

  public static void main(String[] args) {
    System.out.println("Computer SETUP...DONE...");
    
    System.out.println("Welcome to Battleship Game!");
    System.out.println("Sink the hidden ship on the board.");
    System.out.println("Enter coordinates in the format <Letter><Number> (e.g., A1, G3).");
    System.out.println("Let's start!\n");

    initializeGame();
    // Main game loop
    while (true) {
      displayBoard();
      String userInput = getUserInput();
      if (isValidInput(userInput)) {
        
        int row = convertRow(userInput.charAt(0));
        int col = Integer.parseInt(userInput.substring(1)) - 1;
        processUserGuess(row, col);
        
        if (isGameOver()) {
          displayBoard(); 
          System.out.println("Congratulations! You destroyed all the ships in " + fireCount + " tries.");
          break;
        } 
        } else {
          System.out.println("DEBUG 4");
        System.out.println("Invalid input. Please enter a valid coordinate.");
        }
      }
    }

  // Initialize the game (setup the board, ship, etc.)
  private static void initializeGame() {
    AREA = DataValaNum(s);
    board = new char[AREA][AREA];
    for (int i = 0; i < AREA; i++) {
      Arrays.fill(board[i], ' ');
    }

    //initialize Size of Ships

    if (AREA > 10) {
      shipCounter = 4;  // change this to the number of ships
      shipColArray = new int[shipCounter];
      shipRowArray = new int[shipCounter];
      shipSizeArray = new int[shipCounter];
      for (int i = 0; i < shipCounter; i++) { // runs 4 not 5 hehehehaw
        shipSize = (int) ((Math.random() * 3) + 2); // Random size between 2 and 4

        guess = (int) (Math.random() * 2);

        //int shipRow, shipCol;
        if (guess == 0) {
          shipRow = (int) (Math.random() * AREA);
          shipCol = (int) (Math.random() * (AREA - shipSize + 1)); // goes right
        } else {
          shipRow = (int) (Math.random() * (AREA - shipSize + 1)); // goes down
          shipCol = (int) (Math.random() * AREA);
        }
        // You can store ship attributes in arrays or collections for later use.
 
        shipColArray[i] = shipCol;
        shipRowArray[i] = shipRow;
        shipSizeArray[i] = shipSize;

        fireCount = 0;
        //   for (i = 0; i < shipCounter; i++) {
        //     System.out.println(shipCol[i]+ " " + shipRow[i] + " " + shipSizeArray[i]);
        //   }
        System.out.println(shipColArray[i] + " " + shipRowArray[i] + " " + shipSizeArray[i]);
      } 

    }else {
      shipSize = (int) ((Math.random() * 3) + 2);
      // Set the size of the ship (you can change this)
      guess = (int) (Math.random() * 2);
      //System.out.println(guess);
      //System.out.println(shipSize);
      if (guess == 0) {
        shipRow = (int) (Math.random() * AREA);
        shipCol = (int) (Math.random() * (AREA - shipSize+1)); // goes  right
      } else {
        shipRow = (int) (Math.random() * (AREA - shipSize+1)); // goes down
        shipCol = (int) (Math.random() * AREA);
      }
      //System.out.println((shipCol+1) + ", " +  (shipRow+1));

      fireCount = 0;
    }
  }



  // Display the current state of the game board
  private static void displayBoard() {
    System.out.println();
    System.out.print("  ");
    for (int i = 0; i < AREA; i++) {
      System.out.print((i + 1) + " ");  // Print column numbers
    }
    System.out.println();

    for (int i = 0; i < AREA; i++) {
      System.out.print((char) ('A' + i) + " ");  // Print row letter
      for (int j = 0; j < AREA; j++) {
        System.out.print(board[i][j] + " ");
      }
      System.out.println();
    }
  }

  public static int DataValaNum(Scanner s) {
    int n = 0;
    boolean validInput = false;

    while (!validInput) {
      System.out.print("Enter the Battlefield size (4-20): ");
      if (s.hasNextInt()) {
        n = s.nextInt();
        if (n >= 4 && n <= 20) {
          validInput = true;
        } else {
          System.out.print("Please enter a valid size between 4 and 20.");
        }
      } else {
        System.out.println("Invalid input. Please enter a positive integer.");
        s.nextLine(); // Consume invalid input
      }
    }
    return n;
  }

  // Get user input for their next guess
  private static String getUserInput() {
    System.out.print("Enter your guess: ");
    return s.next().toUpperCase();
  }


  private static boolean isValidInput(String input) {
    if (input.length() < 2 || input.length() > 3) {
      return false;
    }

    char rowChar = input.charAt(0);
    int col;

    if (rowChar < 'A' || rowChar >= 'A' + AREA) {
      return false;
    }

    try {
      col = Integer.parseInt(input.substring(1));
      if (col < 1 || col > AREA) {
      return false;
    }
    } catch (NumberFormatException e) {
      return false;
    }

    return true;
  }

  // Process the user's guess and update the board
  private static void processUserGuess(int row, int col) {
    if (board[row][col] == 'O' || board[row][col] == 'X') {
      System.out.println("You already hit here!");
    } else {
      boolean hit = false;
  
      for (int i = 0; i < shipCounter; i++) {
        for (int j = 0; j < shipSizeArray[i]; j++) {
          if ((col == shipColArray[i] && row == shipRowArray[i] + j) ||
              (row == shipRowArray[i] && col == shipColArray[i] + j)) {
            hit = true;
            System.out.println("Hit!");
            hitCounter++;
            board[row][col] = 'X'; // Mark the ship as sunk
  
            if (hitCounter == shipSizeArray[i]) {
              System.out.println("You destroyed a ship!");
            }
          }
        }
      }
  
      if (!hit) {
        System.out.println("Missed!");
        board[row][col] = 'O'; // Mark the missed shot
      }
    }
  }
  


  // add check for ArrayLen
  // Check if the game is over (ship destroyed)
  private static boolean isGameOver() {
    if (AREA > 10) {
      // for (int i = 0; i < shipCounter; i++) { 
        
        // shipColArray[i] = shipCol;
        // shipRowArray[i] = shipRow;
        // shipSizeArray[i] = shipSize;
        // if (hitCounter >= shipSizeArray[i]) {
      //  /   hitCounter -= shipSizeArray[i];
        // /  ShipHitCounter++; 
        // /} else return false;
        
        // if (ShipHitCounter == (shipSizeArray[0] + shipSizeArray[i+1] + shipSizeArray[i+2] + shipSizeArray[i+3])) {
          // tempBool =  true;
      //   }
      // return tempBool;
      // }
    // for 
      return (ShipHitCounter == (shipSizeArray[0] + shipSizeArray[1] + shipSizeArray[2] + shipSizeArray[3]));
    } else {
      return hitCounter == shipSize;
    }
  }

  // Convert row coordinate (A-J) to an array index (0-9)
  private static int convertRow(char rowChar) {
    return rowChar - 'A'; 
  }
}