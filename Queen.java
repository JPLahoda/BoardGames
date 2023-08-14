package edu.temple.datastructures;

import java.util.Scanner;
import static java.lang.System.exit;


public class Queen {
    public static void printArray(char[][] board) {
        for (int i = 0; i < 8; i++) {
            System.out.println();
            for (int j = 0; j < 8; j++) {
                System.out.print(board[i][j] + " ");
            }
        }
    }

    public static boolean inBounds(int column, int row){
        return (column >= 0 && row >= 0 && column <= 7 && row <= 7);
    }

    public static char[][] removeTheXs(char[][] board){
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                if (board[i][j] != 'Q'){
                    board[i][j] = 'o';
                }
            }
        }
        return board;
    }
    public static char[][] markTheXs(char[][] board){
        int rowNumber;
        int colNumber;

        //Replace the X's properly
        for (int column = 0; column < 8; column++) {
            for (int row = 0; row < 8; row++) {
                if (board[column][row] == 'Q') {
                    rowNumber = row;
                    colNumber = column;
                    //Setting horizontal and verticals to char 'x'
                    for (row = 0; row < 8; row++) {
                        if (board[column][row] != 'Q') {
                            board[column][row] = 'x';
                        }
                    }
                    row = rowNumber;
                    for (column = 0; column < 8; column++) {
                        if (board[column][row] != 'Q') {
                            board[column][row] = 'x';
                        }
                    }
                    column = colNumber;
                    //Now, we do the same for the diagonals:
                    //Northwest
                    for (int a = 0; a < 8; a++) {
                        if (inBounds(column - 1, row - 1)) {
                            column--;
                            row--;
                            board[column][row] = 'x';
                        }
                    }
                    column = colNumber;
                    row = rowNumber;
                    //Northeast
                    for (int b = 0; b < 8; b++) {
                        if (inBounds(column - 1, row + 1)) {
                            column--;
                            row++;
                            board[column][row] = 'x';
                        }
                    }
                    column = colNumber;
                    row = rowNumber;
                    //Southwest
                    for (int c = 0; c < 8; c++) {
                        if (inBounds(column + 1, row - 1)) {
                            column++;
                            row--;
                            board[column][row] = 'x';
                        }
                    }
                    column = colNumber;
                    row = rowNumber;
                    //Southeast
                    for (int d = 0; d < 8; d++) {
                        if (inBounds(column + 1, row + 1)) {
                            column++;
                            row++;
                            board[column][row] = 'x';
                        }
                    }
                    column = colNumber;
                    row = rowNumber;

                }
            }
        }
        return board;
    }


    public static boolean placeRemainingQueens(char board[][], int queens) {
        board = removeTheXs(board);
        printArray(board);
        System.out.println();
        board = markTheXs(board);
        printArray(board);
        System.out.println();

        //Base case
        if (queens == 0){
            return true;
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.println("we're at i = " + i);

                if (board[i][j] == 'o') {
                    board[i][j] = 'Q';
                    queens--;

                    //Recursion
                    if (placeRemainingQueens(board, queens) == true) {
                        return true;
                    }

                    // Else backtrack
                    else {
                        board[i][j] = 'o';
                        board = removeTheXs(board);
                        printArray(board);
                        System.out.println();
                        board = markTheXs(board);
                        printArray(board);
                        System.out.println();
                        queens++;
                    }
                }
            }
        }
        return false;
    }



    public static void main(String[] args) {
        char[][] board = new char[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = 'o';
            }
        }

        printArray(board);
        System.out.println();

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the starting queen position X. (between 1-8)");
        int x = sc.nextInt();
        if (x > 8 || x < 1) {
            System.out.println("Invalid number. Only enter numbers between 1-8. Closing the program.");
            exit(0);
        }


        System.out.println("Now, enter the starting queen position Y. (between 1-8)");
        int y = sc.nextInt();
        if (y > 8 || y < 1) {
            System.out.println("Invalid number. Only enter numbers between 1-8. Closing the program.");
            exit(0);
        }

        board[8 - y][x - 1] = 'Q';
        printArray(board);
        System.out.println();

        char[] alphabet = new char[8];
        alphabet[0] = 'a';
        alphabet[1] = 'b';
        alphabet[2] = 'c';
        alphabet[3] = 'd';
        alphabet[4] = 'e';
        alphabet[5] = 'f';
        alphabet[6] = 'g';
        alphabet[7] = 'h';

        System.out.println("You have placed queen 1 at: " + alphabet[x - 1] + (y));


        //Try placing all 8 queens using backtracking
        //The parameter "queens" represents the number of queens that still need to be placed.
        if (placeRemainingQueens(board, 7)){
            System.out.println();
            System.out.println("There are 8 queens.");
        }
    }
}