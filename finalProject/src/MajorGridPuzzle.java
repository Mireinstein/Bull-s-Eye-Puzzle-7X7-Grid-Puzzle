import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MajorGridPuzzle extends PuzzleFrame {
    MajorGridPuzzle majorGridPuzzle;
    protected static int[][] locationValues;
    //note that each location has transitions that it can move to
    protected static Location[][] locations;

    protected static JLabel success = new JLabel("RUNNING!...", JLabel.CENTER);

    protected static Location previousLocation;
    protected static ArrayList<ArrayList<Integer>> valuesFromFile;

    public void launch(PuzzleFrame frame, MajorGridPuzzle majorGridPuzzle) {
        success.setFont(new Font("Serif", Font.PLAIN, 50));
        valuesFromFile = readValueFromFile("noUTurnPuzzleInput");
        locationValues = new int[valuesFromFile.size()][valuesFromFile.get(0).size()];
        locations = new Location[locationValues.length][locationValues[0].length];
        setLocationValues();
        this.majorGridPuzzle = majorGridPuzzle;
        this.majorGridPuzzle.setPreferredSize(new Dimension(1249, 992));
        this.majorGridPuzzle.setContentPane(frame.getContentPane());
        this.majorGridPuzzle.setLayout(new FlowLayout());
        frame.setVisible(false);
        setUpFrontPage();

    }

    @Override
    public void setUpFrontPage() {

        Container contentPane = this.majorGridPuzzle.getContentPane();

        JLabel label0 = new JLabel("8 BY 8 GRID", JLabel.CENTER);
        label0.setFont(new Font("Serif", Font.PLAIN, 50));
        label0.setBackground(new Color(255, 255, 255));
        label0.setPreferredSize(new Dimension(1245, 60));
        contentPane.add(label0);

        JPanel jPanel1 = new JPanel();
        jPanel1.setLayout(new GridLayout(8, 8));
        jPanel1.setBackground(new Color(128, 0, 128));
        jPanel1.setPreferredSize(new Dimension(600, 600));
//adding buttons(locations to the location array)
        for (int row = 0; row < locationValues.length; row++) {
            for (int column = 0; column < locationValues[0].length; column++) {
                Location toBeAdded;
                if ((row == locationValues.length - 1) && (column == locationValues[0].length - 1)) {
                    toBeAdded = new Location("GOAL", row, column);
                    toBeAdded.addActionListener(new MajorGridPuzzleHandler(toBeAdded));
                } else {
                    toBeAdded = new Location("" + locationValues[row][column], row, column);
                    toBeAdded.addActionListener(new MajorGridPuzzleHandler(toBeAdded));
                }
                locations[row][column] = toBeAdded;
                //testing
                //adding those locations to the actual panel
                jPanel1.add(locations[row][column]);

            }

        }
        locations[0][0].setIsClickableLocation(true);// making sure the start state will be enabled
        setUpLegalMoves();
        setTransitions();
        setColors();
        //adding the panel to contentpane
        contentPane.add(jPanel1);

        //start
        JPanel jPanel2 = new JPanel();
        jPanel2.setLayout(new GridLayout(1, 1));
        jPanel2.setBackground(new Color(128, 0, 128));
        jPanel2.setPreferredSize(new Dimension(1246, 70));
        jPanel2.add(success);
        contentPane.add(jPanel2);

        // a panel,add components to the panel, then add the panel to the main container
        JPanel jPanel3 = new JPanel();
        jPanel3.setLayout(new FlowLayout());
        JButton switch_puzzle = new JButton("Switch puzzle");
        switch_puzzle.setBackground(new Color(0, 255, 0));
        switch_puzzle.addActionListener(new ButtonToMenuListener(switch_puzzle, this));
        jPanel3.add(switch_puzzle);
        //
        JButton help = new JButton("Help");
        help.setBackground(new Color(0, 255, 0));
        help.addActionListener(new MajorGridHelpHandler());
        jPanel3.add(help);
        //
        JButton seeSolution = new JButton("See Solution");
        seeSolution.setBackground(new Color(0, 255, 0));
        seeSolution.addActionListener((new PuzzleSolver(locations[0][0], locations[locationValues.length - 1][locationValues[0].length - 1],true,locations,seeSolution)));
        jPanel3.add(seeSolution);
        //
        JButton quit = new JButton("Quit          ");
        quit.setBackground(new Color(255, 0, 0));
        quit.addActionListener(new ButtonToMenuListener(quit, this));
        jPanel3.add(quit);
        contentPane.add(jPanel3);
        //stop
        majorGridPuzzle.pack();
        majorGridPuzzle.setVisible(true);

    }

    @Override
    public void setUpLegalMoves() {
        ////enabling clickable locations
        for (int row = 0; row < locationValues.length; row++) {
            for (int column = 0; column < locationValues[0].length; column++) {
                if ((locations[row][column].getIsClickableLocation())) {
                    locations[row][column].setEnabled(true);

                } else {
                    locations[row][column].setEnabled(false);
                    //testing
                    locations[row][column].setDisabledIcon(locations[row][column].getIcon());
                }
            }
        }


    }

    public void deActivateAll() {
        for (int row = 0; row < locationValues.length; row++) {
            for (int column = 0; column < locationValues[0].length; column++) {
                locations[row][column].setIsClickableLocation(false);
                locations[row][column].setEnabled(false);
            }
        }

    }

    public void updateLabel(String update) {
        success.setText(update);

    }

    public void setTransitions() {
        for (int row = 0; row < locationValues.length; row++) {
            for (int column = 0; column < locationValues[0].length; column++) {
                if ((column + (locationValues[row][column])) < locationValues[0].length) {
                    locations[row][column].setTransitions(locations[row][(column + locationValues[row][column])]);
                }

                if ((column - (locationValues[row][column])) >= 0) {
                    locations[row][column].setTransitions(locations[row][(column - locationValues[row][column])]);
                }

                if ((row - ((locationValues[row][column])) >= 0)) {
                    locations[row][column].setTransitions(locations[row - ((locationValues[row][column]))][column]);
                }

                if (((row + (locationValues[row][column]))) < locationValues.length) {
                    locations[row][column].setTransitions(locations[row + ((locationValues[row][column]))][column]);
                }


            }

        }
        previousLocation = locations[0][0];
    }

    public void setColors() {
        int count;
        for (int i = 0; i < locationValues.length; i++) {
            count = i;
            for (int j = 0; j < locationValues[0].length; j++) {

                if (count == 0) {
                    locations[i][j].setBackground(Color.RED);
                    count++;
                } else if ((count % 2 == 0)) {

                    locations[i][j].setBackground(new Color(0, 0, 255));
                    count++;
                } else if ((count % 2 == 1)) {
                    locations[i][j].setBackground(Color.GREEN);
                    count++;

                }
                locations[i][j].setOpaque(true);
                locations[i][j].setBorderPainted(false);
                locations[i][j].setForeground(Color.BLACK);
                locations[i][j].setFont(new Font("Serif", Font.BOLD, 50));
            }
        }
        locations[locationValues.length - 1][locationValues[0].length - 1].setFont(new Font("Serif", Font.BOLD, 12));
    }

    public static ArrayList<ArrayList<Integer>> readValueFromFile(String fileName) {
        ArrayList<ArrayList<Integer>> value = new ArrayList<>();
        ArrayList<Integer> listN = new ArrayList<>();
        File f = new File(fileName);


        try {
            Scanner sc = new Scanner(f);

            while (sc.hasNext()) {
                if (sc.hasNextInt()) {
                    listN.add(sc.nextInt());
                } else if (sc.next().equals("b")) {
                    value.add(listN);
                    listN = new ArrayList<>();
                }

            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }
        return value;
    }

    public void setLocationValues() {
        for (int i = 0; i < valuesFromFile.size(); i++) {
            for (int j = 0; j < valuesFromFile.get(0).size(); j++) {
                locationValues[i][j] = valuesFromFile.get(i).get(j);
            }
        }
    }
}

