




class Abstract {
  public static void main(String [] args) {
    Human baoBao = new Child("baoBao");
    Human zhongBao = new YoungAdult("zhongBao");
    Human GaoBao = new Adult("GaoBao");
    
    CanWork y1 = new YoungAdult("YoungAdult");
    CanWork a1 = new Adult("Adult");
    Legal a2 = new Adult("Adult");
    
    System.out.print(baoBao.getName()+": "); baoBao.haveParty();
    System.out.println();
    System.out.print(zhongBao.getName()+": "); zhongBao.haveParty();
    System.out.print(zhongBao.getName()+": "); y1.goToWork();
    System.out.println();
    System.out.print(GaoBao.getName()+": "); GaoBao.haveParty();
    System.out.print(GaoBao.getName()+": "); a1.goToWork();
    System.out.print(GaoBao.getName()+": "); a2.drinkAlcohol();
    System.out.println();
  }
}

interface CanWork {
  public abstract void goToWork();
}
interface Legal {
  public abstract void drinkAlcohol();
}

abstract class Human {
  private String name;
  
  Human(String name) { this.name = name; }
  
  public String getName() { return this.name; }
  public void setName(String name) { this.name = name; }
  
  public abstract void haveParty();
}

class Child extends Human {
  private String name;
  
  Child(String name) { super(name); this.name = name; }
  
  public void setName(String name) { this.name = name; }
  public void haveParty() {System.out.println("I never had a party.");}
}
class YoungAdult extends Human implements CanWork {
  private String name;
  
  YoungAdult(String name) { super(name); this.name = name; }
  
  public void setName(String name) { this.name = name; }
  public void haveParty() {System.out.println("Dancin' till the break of dawn...");}
  public void goToWork() {System.out.println("Ew Responsibilities. Rather waste away my life playing Valo");}
}

class Adult extends Human implements CanWork, Legal {
  private String name;
  
  Adult(String name) { super(name); this.name = name; }
  
  public void setName(String name) { this.name = name; }
  public void haveParty() {System.out.println("Party's are boring. Rather sleep.");}
  public void goToWork() {System.out.println("Spend all day playing mine sweeper");}
  public void drinkAlcohol() {System.out.println("Me waiting for the stop sign to turn green: \"Officer, I am not as high as you think I am\"");}
} 