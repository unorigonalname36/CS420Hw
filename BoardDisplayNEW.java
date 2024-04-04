import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BoardDisplayNEW extends JFrame {
    private final char[][] board;
    private final JButton[][] buttons;

    public BoardDisplayNEW(char[][] board) {
        this.board = board;
        this.buttons = new JButton[board.length][board[0].length];

        setTitle("Minesweeper");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(board.length, board[0].length));

        initializeButtons();
        setSize(400, 400);
        setLocationRelativeTo(null); // Center the window
        setVisible(true);
    }

    private void initializeButtons() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(50, 50));
                button.setFont(new Font("Arial", Font.PLAIN, 20));
                button.setFocusPainted(false);
                button.addActionListener(new ButtonClickListener(i, j));
                buttons[i][j] = button;
                add(button);
            }
        }
    }

    public void updateButton(int row, int col, char value) {
        buttons[row][col].setText(String.valueOf(value));
    }

    private class ButtonClickListener implements ActionListener {
        private final int row;
        private final int col;

        public ButtonClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // Handle button click event
            // You can put your logic here
            // For example, call revealCell(row, col) method from Minesweeper class
        }
    }
}
