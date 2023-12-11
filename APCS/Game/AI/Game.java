import java.util.*;
public class Game {

    public static void main(String[] args) {



        
        // Create instances
        SpawnRoom spawnRoom = new SpawnRoom("SpawnRoom", true, new String[]{"forward"});
        PuzzleRoom puzzleRoom1 = new PuzzleRoom("PuzzleRoom1", "4+26-8", 22, false, new String[]{"22", "left"});
        PuzzleRoom puzzleRoom2 = new PuzzleRoom("PuzzleRoom2", "5*6/2", 15, false, new String[]{"15"});
        SideRoom sideRoom = new SideRoom("SideRoom", true, new String[]{"key"});
        GoalRoom goalRoom = new GoalRoom("GoalRoom", false, new String[]{"exit"});

        // Connect the rooms.
        spawnRoom.setNextRoom(puzzleRoom1);
        puzzleRoom1.setNextRoom(puzzleRoom2);
        puzzleRoom1.setLeftRoom(sideRoom);
        puzzleRoom2.setNextRoom(goalRoom);

        puzzleRoom1.setPreviousRoom(spawnRoom);
        puzzleRoom2.setPreviousRoom(puzzleRoom1);
        sideRoom.setPreviousRoom(puzzleRoom1);
        goalRoom.setPreviousRoom(puzzleRoom2);

        // Start the game from the spawn room
        Room currentRoom = spawnRoom;

        boolean hasKey = false;

        Scanner scanner = new Scanner(System.in);

        while (currentRoom != null) {
            currentRoom.displayRoom();
            String[] validActions = currentRoom.getValidActions();
            //heehe
            System.out.println("Possible actions: " + String.join(", ", validActions));

            if (currentRoom instanceof PuzzleRoom) {
                PuzzleRoom puzzleRoom = (PuzzleRoom) currentRoom;
                    System.out.print("Enter your answer to the puzzle. If you want to go back a level or end the game, type 'pass' ");

                    String userInput = scanner.nextLine();

                    if (!userInput.equals("pass")) {
                        try {
                            int userAnswer = Integer.parseInt(userInput);
                            if (puzzleRoom.solvePuzzle(userAnswer)) {
                                if (currentRoom.getNextRoom() == puzzleRoom1.getNextRoom() && hasKey) {
                                    puzzleRoom.setUnlocked(true);
                                    currentRoom = currentRoom.getNextRoom();
                                    System.out.println("Correct! The room is now unlocked, go forward to win!");
                                } else if(currentRoom.getNextRoom() == puzzleRoom1.getNextRoom() && !hasKey) {
                                    System.out.println("HAHA! You will have yet to complete my puzzle!");
                                } else {
                                    puzzleRoom.setUnlocked(true);
                                    currentRoom = currentRoom.getNextRoom();
                                    System.out.println("Correct! The room is now unlocked.");
                                }
                                
                            } else {
                                System.out.println("Incorrect answer. Try again.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a valid number.");
                        }
                    } else {
                        System.out.print("Enter your action: ");
                        String action = scanner.nextLine();

                        if (action.equals("exit")) {
                            currentRoom = null;
                        } else if (currentRoom.getPreviousRoom() != null && action.equals("prev")) {
                            currentRoom = currentRoom.getPreviousRoom();
                        } else if (currentRoom instanceof PuzzleRoom && puzzleRoom.getLeftRoom() != null && action.equals("left")) {
                            currentRoom = puzzleRoom.getLeftRoom();
                        } else {
                            System.out.println("Invalid action. Try again.");
                        }
                    }
            } else if (currentRoom instanceof SideRoom) {
                if (!hasKey) {
                    System.out.println("You need a key to enter the GoalRoom. You can find the key in this room.");
                } else {
                    System.out.println("You already have the key. Proceed to the next room.");
                }
                System.out.print("Enter your action: ");
                String action = scanner.nextLine();
                if (action.equals("key")) {
                    hasKey = true;
                    System.out.println("You have obtained the key. You can now enter the Goal Room.");
                } else if (action.equals("prev")) {
                    currentRoom = currentRoom.getPreviousRoom();
                } else {
                    System.out.println("Invalid action. Try again.");
                }
            } else {
                System.out.print("Enter your action: ");
                String action = scanner.nextLine();

                if (action.equals("exit")) {
                    currentRoom = null;
                } else if (currentRoom.getNextRoom() != null && action.equals("forward")) {
                    currentRoom = currentRoom.getNextRoom();
                } else if (currentRoom.getPreviousRoom() != null && action.equals("prev")) {
                    currentRoom = currentRoom.getPreviousRoom();
                } else {
                    System.out.println("Invalid action. Try again.");
                }
            }
            System.out.println();
            }
        System.out.println("Game Over");
        scanner.close();
    }
}

// curious if you're still reading :^)
abstract class Room {
    private String name;
    private Room nextRoom;
    private Room previousRoom;
    private boolean unlocked;
    private String[] validActions;

    public Room(String name, boolean unlocked, String[] validActions) {
        this.name = name;
        this.unlocked = unlocked;
        this.validActions = validActions;
    }

    public String getName() {
        return name;
    }
    // public String getAns() {
    //     return name;
    // }

    public Room getNextRoom() {
        return nextRoom;
    }

    public void setNextRoom(Room nextRoom) {
        // ArrayList nextRoom <
        this.nextRoom = nextRoom;

    }

    public Room getPreviousRoom() {
        return previousRoom;
    }

    public void setPreviousRoom(Room previousRoom) {
        this.previousRoom = previousRoom;
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

    public abstract void displayRoom();
}

class PuzzleRoom extends Room {
    private Room leftRoom;
    private String puzzle;
    private int answer;

    public PuzzleRoom(String name, String puzzle, int answer, boolean unlocked, String[] validActions) {
        super(name, unlocked, validActions);
        this.puzzle = puzzle;
        this.answer = answer;
    }

    public Room getLeftRoom() {
        return leftRoom;
    }

    public void setLeftRoom(Room leftRoom) {
        this.leftRoom = leftRoom;
    }

    public String getPuzzle() {
        return puzzle;
    }

    public int getAnswer() {
        return answer;
    }

    public boolean solvePuzzle(int playerAnswer) {
        return playerAnswer == answer;
    }

    @Override
    public void displayRoom() {
        System.out.println("You are in " + getName() + " room.");
if (isUnlocked()) {
    System.out.println("The room is unlocked.");
} else {
    System.out.println("Solve the puzzle to unlock the room.");
    System.out.println("Puzzle: " + getPuzzle());
}
if (getNextRoom() != null) {
    System.out.println("In front of you is " + getNextRoom().getName() + " room.");
}
if (getPreviousRoom() != null) {
    System.out.println("Behind you is " + getPreviousRoom().getName() + " room.");
}

    }
}

class SpawnRoom extends Room {
    public SpawnRoom(String name, boolean unlocked, String[] validActions) {
        super(name, unlocked, validActions);
    }

    @Override
    public void displayRoom() {
        System.out.println("Welcome to the game! You are in the " + getName() + " room.");
    }
}

class GoalRoom extends Room {
    public GoalRoom(String name, boolean unlocked, String[] validActions) {
        super(name, unlocked, validActions);
    }

    @Override
    public void displayRoom() {
        System.out.println("Congratulations! You have reached the goal in the " + getName() + " Goom.");
    }
}

class SideRoom extends Room {
    public SideRoom(String name, boolean unlocked, String[] validActions) {
        super(name, unlocked, validActions);
    }

    @Override
    public void displayRoom() {
        System.out.println("You are in " + getName() + " room.");
        if (isUnlocked()) {
            System.out.println("The room is unlocked.");
        } else {
            System.out.println("This room has a key that you need to enter the GoalRoom.");
        }
        if (getNextRoom() != null) {
            System.out.println("In front of you is " + getNextRoom().getName() + " room.");
        }
        if (getPreviousRoom() != null) {
            System.out.println("Behind you is " + getPreviousRoom().getName() + " room.");
        }
    }
}



















