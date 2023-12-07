import java.util.*;

class fib {

  static String[] action;
  static String[] Room;

  public static void main(String[] args) {
  }
}

abstract class Room {

    private String name;
    private boolean unlocked;
    private ArrayList<String> validActions;
    private ArrayList<Room> allRooms;

    public Room(String name, boolean unlocked) {
        this.name = name;
        this.unlocked = unlocked;
        this.validActions = new ArrayList<>();
        this.allRooms = new ArrayList<>();
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

    public ArrayList<String> getValidActions() {
        return validActions;
    }

    public void addValidAction(String action) {
        validActions.add(action);
    }

    public ArrayList<Room> getAllRooms() {
        return allRooms;
    }

    public void addRoom(Room room) {
        allRooms.add(room);
    }

    public abstract void displayRoom();
}


abstract class PuzzleRoom extends Room {

  PuzzleRoom(String name, boolean unlocked) {
    super();
  }
}