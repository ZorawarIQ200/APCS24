class test {
  public static void main(String[] args) {
    String test = "12345";
    int len = test.length() - 1;
    
    for (int i = 0; i<len; i++) {
      System.out.println(test.substring(i) + " ");

    }
  }
}