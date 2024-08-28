import java.util.Scanner;
import java.util.Random;

public class FifteenPuzzle {
    private static final int SIZE = 4;
    private static int[][] board = new int[SIZE][SIZE];
    private static int emptyX, emptyY; 

    public static void main(String[] args) {
        initializeBoard();
        shuffleBoard();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            printBoard();
            if (isSolved()) {
                System.out.println("Congratulations! You solved the puzzle.");
                break;
            }
            System.out.print("Enter your move (W=Up, S=Down, A=Left, D=Right): ");
            String move = scanner.nextLine().toUpperCase();

            switch (move) {
                case "W":
                    moveUp();
                    break;
                case "S":
                    moveDown();
                    break;
                case "A":
                    moveLeft();
                    break;
                case "D":
                    moveRight();
                    break;
                default:
                    System.out.println("Invalid move. Please use W, A, S, or D.");
            }
        }
        scanner.close();
    }

    private static void initializeBoard() {
        int num = 1;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = num;
                num++;
            }
        }
        board[SIZE - 1][SIZE - 1] = 0; 
        emptyX = SIZE - 1;
        emptyY = SIZE - 1;
    }

    private static void shuffleBoard() {
        Random rand = new Random();
        for (int i = 0; i < 1000; i++) {
            int move = rand.nextInt(4);
            switch (move) {
                case 0:
                    moveUp();
                    break;
                case 1:
                    moveDown();
                    break;
                case 2:
                    moveLeft();
                    break;
                case 3:
                    moveRight();
                    break;
            }
        }
    }

    private static void printBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == 0) {
                    System.out.print(" \t");
                } else {
                    System.out.print(board[i][j] + "\t");
                }
            }
            System.out.println();
        }
    }

    private static boolean isSolved() {
        int num = 1;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (i == SIZE - 1 && j == SIZE - 1) {
                    return board[i][j] == 0;
                }
                if (board[i][j] != num) {
                    return false;
                }
                num++;
            }
        }
        return true;
    }

    private static void moveUp() {
        if (emptyX > 0) {
            board[emptyX][emptyY] = board[emptyX - 1][emptyY];
            board[emptyX - 1][emptyY] = 0;
            emptyX--;
        }
    }

    private static void moveDown() {
        if (emptyX < SIZE - 1) {
            board[emptyX][emptyY] = board[emptyX + 1][emptyY];
            board[emptyX + 1][emptyY] = 0;
            emptyX++;
        }
    }

    private static void moveLeft() {
        if (emptyY > 0) {
            board[emptyX][emptyY] = board[emptyX][emptyY - 1];
            board[emptyX][emptyY - 1] = 0;
            emptyY--;
        }
    }

    private static void moveRight() {
        if (emptyY < SIZE - 1) {
            board[emptyX][emptyY] = board[emptyX][emptyY + 1];
            board[emptyX][emptyY + 1] = 0;
            emptyY++;
        }
    }
}
