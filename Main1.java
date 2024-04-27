import java.util.Scanner;

public class Main1 {
    private static char[][] board = new char[3][3];
    private static char currentPlayer = 'X';
    private static boolean gameEnded = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (!gameEnded) {
            displayBoard();
            System.out.println("Player " + currentPlayer + "'s turn. Enter row and column (e.g., 1 1): ");

            int row = scanner.nextInt() - 1;
            int col = scanner.nextInt() - 1;

            if (isValidMove(row, col)) {
                board[row][col] = currentPlayer;

                if (checkWin()) {
                    displayBoard();
                    System.out.println("Player " + currentPlayer + " wins!");
                    gameEnded = true;
                } else if (checkDraw()) {
                    displayBoard();
                    System.out.println("It's a draw!");
                    gameEnded = true;
                } else {
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                }
            } else {
                System.out.println("Invalid move. Please try again.");
            }
        }

        System.out.println("Do you want to play again? (yes/no)");
        String playAgain = scanner.next();
        if (playAgain.equalsIgnoreCase("yes")) {
            resetGame();
        } else {
            System.out.println("Thanks for playing!");
        }

        scanner.close();
    }

    private static void displayBoard() {
        System.out.println("  1 2 3");
        for (int i = 0; i < 3; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static boolean isValidMove(int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == '\0';
    }

    private static boolean checkWin() {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
                return true;
            }
        }
        // Check columns
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == currentPlayer && board[1][j] == currentPlayer && board[2][j] == currentPlayer) {
                return true;
            }
        }
        // Check diagonals
        if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) {
            return true;
        }
        if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) {
            return true;
        }
        return false;
    }

    private static boolean checkDraw() {
        // Check if the board is full
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '\0') {
                    return false; // Found an empty cell, game not over
                }
            }
        }
        return true; // All cells filled, game is a draw
    }

    private static void resetGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '\0';
            }
        }
        currentPlayer = 'X';
        gameEnded = false;
    }
}
