import java.util.*;

class Lab7_PuzzleRoom {

    static Scanner s = new Scanner(System.in);

    public static void main(String[] args) {

      Room spawnRoom = new SpawnRoom("Spawn Room", true);
      Room puzzleRoom1 = new PuzzleRoom("Puzzle Room 1", false, "2+3", 5);
      Room puzzleRoom2 = new PuzzleRoom("Puzzle Room 2", false, "2+2", 4);
      Room goalRoom = new GoalRoom("Goal Room", true);

      spawnRoom.setNextRoom(puzzleRoom1);
      puzzleRoom1.setNextRoom(puzzleRoom2);
      puzzleRoom1.setPreviousRoom(spawnRoom);
      puzzleRoom2.setNextRoom(goalRoom);
      puzzleRoom2.setPreviousRoom(puzzleRoom1);
      goalRoom.setPreviousRoom(puzzleRoom2);//if(getNextRoom()==null)

      Room currentRoom = spawnRoom;

      System.out.println("Welcome! Your current location is in " + currentRoom.getName());

      String inputAction;
      int userAnswer;
      boolean test = false;
      while(!test) {
        while (currentRoom != goalRoom) {
        currentRoom.displayRoom();
        
        if (!currentRoom.isUnlocked()) {
          System.out.println("Please unlock the room");
          if (currentRoom.equals(puzzleRoom1)) {
            userAnswer = dataValidation();
            // puzzleRoom1.tryUnlockRoom(userAnswer);
            currentRoom.setUnlocked(true);
          } else if (currentRoom.equals(puzzleRoom2)) {
            userAnswer = dataValidation();
            // puzzleRoom2.tryUnlockRoom(userAnswer);
            // puzzleRoom1.tryUnlockRoom(userAnswer);
            currentRoom.setUnlocked(true);
          } else if (currentRoom.equals(spawnRoom)) {
            System.out.println("Please give a valid action");
            inputAction = s.nextLine();
            if (isValidAction(inputAction, currentRoom.getValidActions())){
              if (inputAction.toLowerCase().equals("next")) {
                currentRoom = currentRoom.getNextRoom();
              }
            }
          }
        } else if (currentRoom.isUnlocked()) {
          System.out.println("Please give a valid action " + Arrays.toString(currentRoom.getValidActions()));
          inputAction = s.nextLine();
          if (isValidAction(inputAction.toLowerCase(), currentRoom.getValidActions())) {
            if (inputAction.toLowerCase().equals("next")) {
              currentRoom = currentRoom.getNextRoom();
              System.out.println("You are currently in " + currentRoom.getName());
            } else if (inputAction.toLowerCase().equals("prev")) {
              currentRoom = currentRoom.getPreviousRoom();
              System.out.println("You are currently in " + currentRoom.getName());
            } 
          } else if (!isValidAction(inputAction.toLowerCase(), currentRoom.getValidActions())) {
            System.out.println("Invalid action. Please try again.");
            inputAction = s.nextLine();
          }
        }
        else {
          System.out.println("Invalid action. Try again.");
          inputAction = s.nextLine();
        }
      }
      
      // When currentRoom equals goalRoom
     while (currentRoom.equals(goalRoom) && !test) {
        currentRoom.displayRoom();
        System.out.println("Please give a valid action");
        inputAction = s.nextLine();
        if (isValidAction(inputAction, currentRoom.getValidActions())) {
          if (inputAction.toLowerCase().equals("prev")) {
            currentRoom = currentRoom.getPreviousRoom();
            currentRoom.setUnlocked(true);
            System.out.println("You are currently in " + currentRoom.getName());
            System.out.println("Please give a valid action " + Arrays.toString(currentRoom.getValidActions()));
            inputAction = s.nextLine();
            if (isValidAction(inputAction.toLowerCase(), currentRoom.getValidActions())) {
              if (inputAction.toLowerCase().equals("next")) {
                currentRoom = currentRoom.getNextRoom();
                System.out.println("You are currently in " + currentRoom.getName());
              } else if (inputAction.toLowerCase().equals("prev")) {
                currentRoom = currentRoom.getPreviousRoom();
                currentRoom.setUnlocked(true);
                System.out.println("You are currently in " + currentRoom.getName());

              } 
            } 
          }
          if(inputAction.toLowerCase().equals("exit")) {
            test = true;
          }
         }
      }
    }
    
    System.out.println("you win");

    }
  public static int dataValidation() {
    int userAnswer = 0;
    boolean validInput = false;
    while (!validInput) {
      System.out.print("Answer for this puzzle: ");
      try {
        userAnswer = Integer.parseInt(s.nextLine());
        validInput = true;
      } catch (NumberFormatException e) {
        System.out.println("Your answer should be an integer.");
      }
    }
    while (!validInput) {
      System.out.print("Answer for this puzzle: ");
      try {
        userAnswer = Integer.parseInt(s.nextLine());
        validInput = true;
      } catch (NumberFormatException e) {
        System.out.println("Your answer should be an integer.");
      }
    }
    return userAnswer;
  }

  private static boolean isValidAction(String action, String[] validActions) {
    for (String validAction : validActions) {
      if (validAction.equals(action.toLowerCase())) {
        return true;
      }
    }
    return false;
  }
}


abstract class Room {
  
  private String name;
  private boolean unlocked;
  private String[] validActions;
  
  private Room nextRoom = null;
  private Room previousRoom = null;

  public Room(String name, boolean unlocked, String[] validActions) {
    this.name = name;
    this.unlocked = false;
    this.validActions = validActions;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isUnlocked() {
    return unlocked;
  }

  public void setUnlocked(boolean unlocked) {
    this.unlocked = unlocked;
  }

  public String[] getValidActions() {
    return validActions;
  }
  
  public Room getNextRoom() {
    return this.nextRoom;
  }

  
  public void setNextRoom(Room nextRoom) {
    this.nextRoom = nextRoom;

  }

  public Room getPreviousRoom() {
    return previousRoom;
  }

  public void setPreviousRoom(Room previousRoom) {
    this.previousRoom = previousRoom;
  }
  
  

  public abstract void displayRoom();
}

class PuzzleRoom extends Room {
  
  private String question;
  private int answer;
  
  PuzzleRoom(String name, boolean unlocked, String question, int answer) {
    super(name, unlocked, new String[]{"next", "prev"});
    this.question = question;
    this.answer = answer;
  }
 
  private String getQuestion() {
    return this.question;
  }
  
  public int getAnswer() {
    return answer;
  }
  
  public boolean tryUnlockRoom(int userAnswer) {
    
    if(userAnswer == answer){
      this.setUnlocked(true);
      // System.out.println("Congraduation! You solved the puzzle and unlocked the room!");
      return true;
    }
    return false;
  }

  
  
  public void displayRoom() {
    
    System.out.println("You are in a room: " + getName());
    System.out.println("Valid Actions are " + Arrays.toString(getValidActions()));
    System.out.println("Please solve the puzzle: " + this.getQuestion() + " to unlock the room");

    
  }
}

class SpawnRoom extends Room {
  SpawnRoom(String name, boolean unlocked) {
    super(name, unlocked, new String[]{"next"});
  }
  
  public void displayRoom() {
    System.out.println("You are in a room: " + getName());
    System.out.println("Valid Actions are " + Arrays.toString(getValidActions()));
  }  
}

class GoalRoom extends Room {
  
  GoalRoom(String name, boolean unlocked) {
    super(name,unlocked,new String[]{"prev", "exit"});
  }
  
  public void displayRoom() {
    System.out.println("You are in a room: " + getName());
    System.out.println("Valid Actions are " + Arrays.toString(getValidActions()));
  }
  
  
}