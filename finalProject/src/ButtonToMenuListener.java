import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class ButtonToMenuListener implements ActionListener {

    private JButton clicker;
    JPopupMenu gameMenu;
    PuzzleFrame frame;


    public ButtonToMenuListener(JButton clicker, PuzzleFrame frame) {
        this.clicker = clicker;
        this.frame = frame;


        // create a popup menu
        gameMenu = new JPopupMenu("Menu");


        // create a menu item
        JMenuItem smallerGridPuzzle = new JMenuItem("7 X 7 Grid Puzzle");
        // add the menu item to the menu
        gameMenu.add(smallerGridPuzzle);
        // add a listener to the menu item
        smallerGridPuzzle.addActionListener(this);

        // create a menu item
        JMenuItem bullEyePuzzle = new JMenuItem("Bull's Eye Puzzle");
        // add the menu item to the menu
        gameMenu.add(bullEyePuzzle);
        // add a listener to the menu item
        bullEyePuzzle.addActionListener(this);

        // create a menu item
        JMenuItem ladyBugPuzzle = new JMenuItem("Lady Bug Puzzle");
        // add the menu item to the menu
        gameMenu.add(ladyBugPuzzle);
        // add a listener to the menu item
        ladyBugPuzzle.addActionListener(this);


        // create a menu item
        JMenuItem largerGridPuzzle = new JMenuItem("8 X 8 Grid Puzzle");
        // add the menu item to the menu
        gameMenu.add(largerGridPuzzle);
        // add a listener to the menu item
        largerGridPuzzle.addActionListener(this);


    }

    public void actionPerformed(ActionEvent e) {
        if ((e.getActionCommand().equals("Quit          ")) || (e.getActionCommand().equals("Choose a puzzle")) || ((e.getActionCommand().equals("Switch puzzle")))) {
            if ((clicker.getText().toLowerCase().equals("choose a puzzle")) || ((e.getActionCommand().equals("Switch puzzle")))) {
                gameMenu.show(clicker, clicker.getWidth() / 2, clicker.getHeight() / 2);
            } else if (clicker.getText().toLowerCase().equals("quit          ")) {
                System.exit(3);
            }
        } else if (!((e.getActionCommand().equals("Quit          ")) || (e.getActionCommand().equals("Choose a puzzle")))) {
            if ((e.getActionCommand().equals("7 X 7 Grid Puzzle"))) {
                //	//clearing the frame
                frame.getContentPane().removeAll();
                frame.validate();
                frame.repaint();
                MinorGridPuzzle minorGridPuzzle = new MinorGridPuzzle();
                minorGridPuzzle.launch(frame, minorGridPuzzle);

            } else if ((e.getActionCommand().equals("Lady Bug Puzzle"))) {
                //clearing the frame
                frame.getContentPane().removeAll();
                frame.validate();
                frame.repaint();
                LadyBugPuzzle ladyBugPuzzle = new LadyBugPuzzle();
                ladyBugPuzzle.launch(frame, ladyBugPuzzle);
            } else if ((e.getActionCommand().equals("8 X 8 Grid Puzzle"))) {
                //clearing the frame
                frame.getContentPane().removeAll();
                frame.validate();
                frame.repaint();
                MajorGridPuzzle majorGridPuzzle = new MajorGridPuzzle();
                majorGridPuzzle.launch(frame, majorGridPuzzle);
            } else if ((e.getActionCommand().equals("Bull's Eye Puzzle"))) {
                //clearing the frame
                frame.getContentPane().removeAll();
                frame.validate();
                frame.repaint();
                BullEyePuzzle bullEyePuzzle = new BullEyePuzzle();
                bullEyePuzzle.launch(frame, bullEyePuzzle);
            }


        }


    }


}
