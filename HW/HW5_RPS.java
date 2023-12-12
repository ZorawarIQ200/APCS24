package HW;
//COUNTER SUBTRACTS SCORE -- MAKE SEPERASTE CORES

import java.util.*;
// import java.lang.*;

public class HW5_RPS {
    public static Scanner s = new Scanner(System.in);
    public static void main(String[] args) {
        // Rock == 1
        // Paper == 2
        // Scissors == 3
        System.out.println("hello loser. Today you will be playing Rock Paper Scissor with a robot because you have nothing better to do. \n");
        int bestOf = DataValaBo(s);

        int temp = -1;
        if (bestOf == 1) {
            temp = CompareVals(DataValainput(s), RoboGuess());
            while (temp == 0) {
                temp = CompareVals(DataValainput(s), RoboGuess());
            }
            if (temp == 1) {
                System.out.println("you win!");
            } else {
                System.out.println("you lose!");
            }
        } else if (bestOf == 3) {
            int winCounter = 0;
            int loseCounter = 0;
          	int temp1 = 0, temp2 = 0;
            while (winCounter < 2 && loseCounter > -2) {
				temp1 = DataValainput(s);
              	temp2 = RoboGuess();
                System.out.println(winCounter +" "+ loseCounter);
                temp = CompareVals(temp1, temp2);
                while (temp == 0) {
                  	temp1 = DataValainput(s);
                  	temp2 = RoboGuess();
                  	System.out.println(temp1 +" "+ temp2);
                    temp = CompareVals(temp1, temp2);
                }
                if (temp == 1) {
                    winCounter++;
                } else {
                    loseCounter--;
                }
              System.out.println();
            }
            if(winCounter > 1) {
                System.out.println("you win!");
            } else if (loseCounter < -1){ 
                System.out.println("you lose!");
            }   
        } else {
            int winCounter = 0;
            int loseCounter = 0;
          	int temp1 = 0, temp2 = 0;
            while (winCounter < 3 && loseCounter > -3) {
				temp1 = DataValainput(s);
              	temp2 = RoboGuess();
                System.out.println(winCounter +" "+ loseCounter);
                temp = CompareVals(temp1, temp2);
                while (temp == 0) {
                  	temp1 = DataValainput(s);
                  	temp2 = RoboGuess();
                  	System.out.println(temp1 +" "+ temp2);
                    temp = CompareVals(temp1, temp2);
                }
                if (temp == 1) {
                    winCounter++;
                } else {
                    loseCounter--;
                }
              System.out.println();
            }
            if(winCounter > 2) {
                System.out.println("you win!");
            } else if (loseCounter < -2){ 
                System.out.println("you lose!");
            }   
        }
    }
    public static int CompareVals(int hum, int robo) {
        if (hum == robo) {
            return 0;
        }
        if (robo == 1 && hum == 2) {
            return 1;
        } else if (robo == 1 && hum == 3) {
            return -1;
        } else if (robo == 2 && hum == 3) {
            return 1;
        } else if (robo == 2 && hum == 1) {
            return -1;
        }
        return 1;
    }    

    public static int RoboGuess() {
        int guess = (int) (Math.random() * 3 + 1);
        return guess;
    }
    public static int DataValainput(Scanner s) {

      String str = "";
      boolean validInput = false;

      while (!validInput) {
          System.out.print("Pick 1-3 for Rock(1), Paper(2), or Scissors(3): ");
          str = s.nextLine();

          if (str.equalsIgnoreCase("r") || str.equalsIgnoreCase("p") || str.equalsIgnoreCase("s")) {
              validInput = true;
          } else {
              System.out.println("Make it r, p, or s, please.");
          }
      }

      if (str.equalsIgnoreCase("r")) {
          return 1;
      } else if (str.equalsIgnoreCase("p")) {
          return 2;
      } else {
          return 3;
      }
	}	

    public static int DataValaBo(Scanner s) {
      int n = 0;
      boolean validInput = false;

      while (!validInput) {
          System.out.print("Decide whether you want to play Bo1, Bo3, or Bo5: ");
          if (s.hasNextInt()) {
              n = s.nextInt();
              if (n == 1 || n == 3 || n == 5) {
                  validInput = true;
              } else {
                  System.out.println("Make it 1, 3, or 5, please.");
              }
          } else {
              System.out.println("Just give me the number of rounds you want to play.");
          }
          s.nextLine(); // Consume invalid input
      }

      return n;
	}


     
    }
