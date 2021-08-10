import java.util.Scanner;

public class Main {
    Scanner sc = new Scanner(System.in);
   
    public static void main(String[] args) {
        Main m = new Main();
        m.run();
    }
    void menu() {
        System.out.println("========= Calculator Program =========");
        System.out.println("1. Addition Matrix");
        System.out.println("2. Subtraction Matrix");
        System.out.println("3. Multiplication Matrix");
        System.out.println("4. Quit");
    }
    void run() {
        while (true) {
            menu();
            int choice = getInputInt("Your choice: ", "Choose only 1-4!", 1, 4);
            if (choice == 4) {
               // System.exit(0);
               return;
            }
            matrixHandling(choice);
            System.out.println();
        }
    }   
    int getInputInt(String message, String error, int min, int max) {
        int number;
        while (true) {
            try {
                System.out.print(message);
                number = Integer.parseInt(sc.nextLine());    
                if (number < min || number > max) {
                    System.out.println(error);
                } else {
                    return number;
                }
            } catch (NumberFormatException e) {
                System.out.println("Values of matrix must be the number!");
            }
        }       
    }

    public int[][] additionMatrix(int[][] matrix1, int[][] matrix2) {
        int[][] newMat = new int[matrix1.length][matrix1[0].length];
        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix1[0].length; j++) {
                newMat[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }
        return newMat;
    }
    
    public int[][] subtractionMatrix(int[][] matrix1, int[][] matrix2) {
        int[][] newMat = new int[matrix1.length][matrix1[0].length];
        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix1[0].length; j++) {
                newMat[i][j] = matrix1[i][j] - matrix2[i][j];
            }
        }
        return newMat;
    }
    
    public int[][] multiplicationMatrix(int[][] matrix1, int[][] matrix2) {
        int[][] newMat = new int[matrix1.length][matrix2[0].length];
        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix2[0].length; j++) {
               for (int k = 0; k < matrix1[0].length; k++) {
                    newMat[i][j] = newMat[i][j] + matrix1[i][k] * matrix2[k][j];
                }
            }
        }
        return newMat;
    }
    
     void displayMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print("[" + matrix[i][j] + "]");
            }
            System.out.println();
        }
    }

    int[][] newMatrix(int row, int col) {
        int[][] newMat = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                newMat[i][j] = getInputInt(String.format("Enter Matrix[%d][%d]: ", i+1, j+1), "Out of range!", Integer.MIN_VALUE, Integer.MAX_VALUE);
            }
        }
        return newMat;
    }
    
    void displayResult(int[][] matrix1, int[][] matrix2, int[][] result, int option) {
        System.out.println("--------- Result ---------");
        displayMatrix(matrix1);
        switch (option) {
            case 1:
                System.out.println("+");
                break;
            case 2:
                System.out.println("-");
                break;
            case 3:
                System.out.println("*");
                break;
        }
        displayMatrix(matrix2);
        System.out.println("=");
        displayMatrix(result);
    }
    
    void matrixHandling(int option) {
        int row1 = getInputInt("Enter Row Matrix1: ", "Out of range!", 1, Integer.MAX_VALUE);
        int col1 = getInputInt("Enter Column Matrix1: ", "Out of range!", 1, Integer.MAX_VALUE);
        int[][] mat1 = newMatrix(row1, col1);
        
        int row2, col2;
        while (true) {
            row2 = getInputInt("Enter Row Matrix2: ", "Out of range!", 1, Integer.MAX_VALUE);
            if (option != 3) {
                if (row2 != row1) {
                    System.out.println("Two matrix must be same size!");
                } else {
                    break;
                }
            } else {
                if (row2 != col1) {
                    System.out.println("Matrix2 rows number must equal Matrix1 cols number!");
                } else {
                    break;
                }
            }
        }
       
        while (true) {
            col2 = getInputInt("Enter Column Matrix2: ", "Out of range!", 1, Integer.MAX_VALUE);
            if (option != 3) {
                if (col2 != col1) {
                    System.out.println("Two matrix must be same size!");
                } else {
                    break;
                }
            } else {
                break;
            }
        }
       
        int[][] mat2 = newMatrix(row2, col2);
        int[][] result;
        switch (option) {
            case 1: //add matrix1 and matrix2
                result = new int[row1][col1];
                result = additionMatrix(mat1, mat2);
                displayResult(mat1, mat2, result, option);
                break;
            case 2: //sub matrix1 and matrix2
                result = new int[row1][col1];
                result = subtractionMatrix(mat1, mat2);
                displayResult(mat1, mat2, result, option);
                break;
            case 3: // mul matrix1 and matrix2
                result = new int[row1][col2];
                result = multiplicationMatrix(mat1, mat2);
                displayResult(mat1, mat2, result, option);
                break;
        }
    /*    
       1 2
       1 2 
        * 3 4
          3 4
          = 9 12
            9 12
      */  
    
    }
}
