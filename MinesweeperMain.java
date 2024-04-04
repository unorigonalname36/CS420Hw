import java.util.Scanner;

class MinesweeperMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of rows: ");
        int rows = scanner.nextInt();
        System.out.print("Enter number of columns: ");
        int cols = scanner.nextInt();
        System.out.print("Enter number of mines: ");
        int minesCount = scanner.nextInt();

        Minesweeper game = new Minesweeper(rows, cols, minesCount);
        game.play();
    }
}