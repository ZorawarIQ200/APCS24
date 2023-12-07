import java.util.*;

class EC {
    static Scanner s = new Scanner(System.in);
    public static void main(String [] args) {
        
        int [][] A;
        // ask for size of array A
        System.out.print("Please input length of the first array: ");
        int aLength = s.nextInt();
        s.nextLine();
        // ask for values of array A
        System.out.print("Please input length of array: ");
        int aValues = s.nextInt();
        s.nextLine();
        int [][] B;
        // ask for size of array B
        System.out.print("Please input length of the second array: ");
        System.out.println("");
        int bLength = s.nextInt();
        s.nextLine();
        // ask for values of array B
        System.out.print("Please input length of array: ");
        int bValues = s.nextInt();
        s.nextLine();

        // print out both arrays after the user creates the arrays

        // these lines test if it's possible to do matrix addition or multiplication
        boolean canAdd = A.length == B.length && A[0].length == B[0].length;
        boolean canMult = A[0].length == B.length;        
        if (!(canAdd || canMult)) {
            System.out.println("You cannot find (A + B) or AB with these matrices.");
            System.exit(0);
        }

        // display which operations are available for the arrays
        // ask for an operation (if applicable)
        // otherwise do the only valid operation

        // do the operation
        // print the result

    }

    public static int [][] makeMatrix() {
     //   int [][] matrix = new int [][]; // you have to change this line
        // do something here
        return matrix;
    }

    public static int [][] matAdddition(int [][] A, int [][] B) {
    /    int [][] result = new int[][]; // you have to change this line
        // do something here
        return result;
    }

    public static int [][] matMultiplication(int [][] A, int [][] B) {
    //    int [][] result = new int[][]; // you have to change this line
        // do something here
        return result;
    }

    public static void printMatrix(int [][] matrix) {
        printMatrix(matrix, matrix.length, matrix[0].length);
    }
    
    /*
     * This method is overloaded, but the functionality in my solution is:
     * I ask for the user to input the element for arr[ii][jj]
     * This method will print out the preexisting array (values already filled in)
     *   as well as the position of the value that they're currently about to input. 
     * If you don't want to use this method, you can just use printMatrix(int[][] matrix)
     *   and it prints out the whole matrix no problem. 
     * Try to understand what this method is doing though! 
     *   i spent a long time on it
     *   if it goes to waste i will cry
     */
    public static void printMatrix(int [][] matrix, int ii, int jj) {
        int maxLen = matrix[0][0];
        for (int i = 0; i < matrix.length; i++) {
        for (int j = 0; j < matrix[i].length; j++)
            if (matrix[i][j] > maxLen) maxLen = matrix[i][j];
        }
        maxLen = Integer.toString(maxLen).length();
        
        for (int i = 0; i < matrix.length; i++) {
            System.out.print(i == 0 ? "[[" : " [");
            for (int j = 0; j < matrix[i].length; j++){
                if (i < ii || (i == ii && j < jj)) {
                    System.out.printf("%" + maxLen + "d", matrix[i][j]);
                } else {
                    System.out.printf("%" + maxLen + "s", i == ii && j == jj ? "X" : "");
                }
                    System.out.print((i == matrix.length - 1 && j == matrix[i].length - 1) ? "]]\n" : 
                                        (j == matrix[i].length - 1 ? "],\n" : ", "));
            }
        } // end printing array
    }
}