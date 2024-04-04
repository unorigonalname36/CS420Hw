import java.util.Random;
import java.util.Scanner;

public class Minesweeper {
    private final int rows;
    private final int cols;
    private final int minesCount;
    private final char[][] board;
    private final boolean[][] mineLocations;
    private final boolean[][] revealed;
    private final BoardDisplay boardDisplay;
    private int uncoveredCount;

    public Minesweeper(int rows, int cols, int minesCount) {
        this.rows = rows;
        this.cols = cols;
        this.minesCount = minesCount;
        this.board = new char[rows][cols];
        this.mineLocations = new boolean[rows][cols];
        this.revealed = new boolean[rows][cols];
        this.uncoveredCount = 0;
        this.boardDisplay = new BoardDisplay(this.board);
        initializeBoard();
        placeMines();
    }

    private void initializeBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = '-';
                mineLocations[i][j] = false;
                revealed[i][j] = false;
            }
        }
    }

    private void placeMines() {
        Random random = new Random();
        int minesPlaced = 0;
        while (minesPlaced < minesCount) {
            int row = random.nextInt(rows);
            int col = random.nextInt(cols);
            if (!mineLocations[row][col]) {
                mineLocations[row][col] = true;
                minesPlaced++;
            }
        }
    }

    private boolean isValidCoordinate(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    private int countAdjacentMines(int row, int col) {
        int count = 0;
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (isValidCoordinate(i, j) && mineLocations[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }

    private void revealCell(int row, int col) {
        if (isValidCoordinate(row, col) && !revealed[row][col]) {
            revealed[row][col] = true;
            if (mineLocations[row][col]) {
                System.out.println("Game Over! You hit a mine!");
                boardDisplay.updateButton(row, col, 'X');
                System.exit(0);
            } else {
                int adjacentMines = countAdjacentMines(row, col);
                board[row][col] = (char) (adjacentMines + '0');
                boardDisplay.updateButton(row, col, board[row][col]);
                uncoveredCount++;
                if (adjacentMines == 0) {
                    for (int i = row - 1; i <= row + 1; i++) {
                        for (int j = col - 1; j <= col + 1; j++) {
                            revealCell(i, j);
                        }
                    }
                }
            }
        }
    }

    private boolean isGameWon() {
        return (rows * cols - uncoveredCount) == minesCount;
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter row and column (e.g., 1 2): ");
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            revealCell(row, col);
            if (isGameWon()) {
                System.out.println("Congratulations! You've cleared all non-mine cells.");
                break;
            }
        }
    }
}