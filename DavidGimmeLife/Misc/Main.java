class Main {
  
    static int a;
    static int b;
    
    public static void main(String[] args) {
      
      a = 5;
      b = 10;
      System.out.printf("Before swap: a=%d, b=%d %n", a, b);
      swapWithTemp();
      System.out.printf("After swap: a=%d, b=%d %n", a, b);
      
      
      System.out.println();
      
      a = 5;
      b = 10;
      System.out.printf("Before swap: a=%d, b=%d %n", a, b);
      swapNoTemp();
      System.out.printf("After swap: a=%d, b=%d %n", a, b);
      
      
    }
    
    public static void swapWithTemp() {
      int temp;
      temp = a;
      a = b;
      b = temp;
      System.out.printf(a+b)
    }
    
    public static void swapNoTemp() {
      a = b + a;
      b = a - b;
      a = a - b;
      System.out.printf(a+b);
    }
  }
    