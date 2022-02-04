/*

                       ADMIRE MADYIRA FINAL PROJECT
                       -----------------------------
This project consist of 3 major puzzles
1.Grid Puzzle(2 for variety)
 - 7 by 7 grid
 - 8 by 8 grid
   **both allowing U turns
2. LadyBug(EyeBall Puzzle)

3. Bull's Eye Puzzle
The user can try to solve the puzzles, switch puzzles, and request solutions
The user can also view a live demonstration of how to solve the puzzle

 ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 */



import javax.swing.*;
import java.awt.*;

public class PuzzleFrame extends JFrame {
    /*this setups the default GUI
       /where the user gets instructions
       front page*/
    public void setUpFrontPage() {
        // creates the main container
        Container contentPane = this.getContentPane();
        contentPane.setBackground(new Color(128, 0, 128));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane.setLayout(new GridLayout(0, 1));

        JPanel jPanel0 = new JPanel();
        jPanel0.setLayout(new GridLayout(1, 1));
        jPanel0.setBackground(new Color(128, 0, 128));
        JLabel label0 = new JLabel("MAZE CRAZE", JLabel.CENTER);
        label0.setFont(new Font("Serif", Font.PLAIN, 50));
        jPanel0.add(label0);
        contentPane.add(jPanel0);


        JPanel jPanel1 = new JPanel();
        jPanel1.setLayout(new GridLayout(0, 1));
        jPanel1.setBackground(new Color(128, 0, 128));
        Icon imgIcon = new ImageIcon(("puzzlefront.gif"));
        JLabel label = new JLabel(imgIcon);
        jPanel1.add(label);
        contentPane.add(jPanel1);

        JPanel jPanel2 = new JPanel();
        jPanel2.setLayout(new GridLayout(1, 0));
        jPanel2.setBackground(new Color(128, 0, 128));
        jPanel2.setSize(1000, 20);
        contentPane.add(jPanel2);

        // a panel,add components to the panel, then add the panel to the main container
        JPanel jPanel3 = new JPanel();
        jPanel3.setLayout(new FlowLayout());
        JButton choose_a_puzzle = new JButton("Choose a puzzle");
        choose_a_puzzle.setBackground(new Color(0, 255, 0));
        jPanel3.add(choose_a_puzzle);
        choose_a_puzzle.addActionListener(new ButtonToMenuListener(choose_a_puzzle, this));
        JButton quit = new JButton("Quit          ");
        quit.setBackground(new Color(255, 0, 0));
        quit.addActionListener(new ButtonToMenuListener(quit, this));
        jPanel3.add(quit);
        contentPane.add(jPanel3);

    }

    public void setUpLegalMoves() {
        // defines the legal moves that can be made depending on the starting location of the user.
    }

    public static void main(String[] args) {
        PuzzleFrame puzzleFrame = new PuzzleFrame();
        puzzleFrame.setPreferredSize(new Dimension(1000, 700));
        puzzleFrame.setUpFrontPage();
        puzzleFrame.pack();
        puzzleFrame.setVisible(true);

    }

}
