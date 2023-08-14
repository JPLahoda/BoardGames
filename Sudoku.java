
public class Sudoku {

    public static boolean isInRow(int[][] board, int row, int x) {
        for (int i = 0; i < 9; i++)
            if (board[row][i] == x)
                return true;
        return false;
    }

    public static boolean isInCol(int[][] board, int col, int x) {
        for (int i = 0; i < 9; i++)
            if (board[i][col] == x)
                return true;
        return false;
    }

    public static boolean isInBox(int[][] board, int row, int col, int x) {
        row -= row % 3;
        col -= col % 3;
        for (int i = row; i < row + 3; i++) {
            for (int j = col; j < col + 3; j++) {
                if (board[i][j] == x) {
                    return true;
                }
            }
        }
        return false;
    }

    private static void printBoard(int[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
    }
    public static boolean algorithm(int[][] board){
        //i is row
        //j is column
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0) {
                    //Testing "number" in the box
                    for (int number = 1; number <= 9; number++) {
                        if (!isInRow(board, i, number) && !isInCol(board, j, number) && !isInBox(board, i, j, number)) {
                            board[i][j] = number;
                            if (algorithm(board)) {
                                return true;
                            }
                            board[i][j] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }


    public static void main(String args[]) {
        //The world's hardest Sudoku puzzle
        int[][] board = {
                { 8, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 3, 6, 0, 0, 0, 0, 0 },
                { 0, 7, 0, 0, 9, 0, 2, 0, 0 },
                { 0, 5, 0, 0, 0, 7, 0, 0, 0 },
                { 0, 0, 0, 0, 4, 5, 7, 0, 0 },
                { 0, 0, 0, 1, 0, 0, 0, 3, 0 },
                { 0, 0, 1, 0, 0, 0, 0, 6, 8 },
                { 0, 0, 8, 5, 0, 0, 0, 1, 0 },
                { 0, 9, 0, 0, 0, 0, 4, 0, 0 }
        };
        //The solved board from online
        int[][] solvedBoard = {
                { 8, 1, 2, 7, 5, 3, 6, 4, 9 },
                { 9, 4, 3, 6, 8, 2, 1, 7, 5 },
                { 6, 7, 5, 4, 9, 1, 2, 8, 3 },
                { 1, 5, 4, 2, 3, 7, 8, 9, 6 },
                { 3, 6, 9, 8, 4, 5, 7, 2, 1 },
                { 2, 8, 7, 1, 6, 9, 5, 3, 4 },
                { 5, 2, 1, 9, 7, 4, 3, 6, 8 },
                { 4, 3, 8, 5, 2, 6, 9, 1, 7 },
                { 7, 9, 6, 3, 1, 8, 4, 5, 2 }
        };
        boolean correct = true;
        if (algorithm(board)){
            printBoard(board);
            for (int row = 0; row < 9; row++){
                for (int col = 0; col < 9; col++){
                    if (board[row][col] != solvedBoard[row][col]){
                        correct = false;
                        break;
                    }
                }
                if (correct == false){
                    System.out.println("The puzzle has been solved incorrectly.");
                    break;
                }
            }
            if (correct == true){
                System.out.println("The puzzle has been solved correctly.");
            }
        }
    }
}
