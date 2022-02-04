import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BullEyePuzzle extends PuzzleFrame {
    BullEyePuzzle bullEyePuzzle;
    static ImageIcon redUp = new ImageIcon("redUp.png");
    static ImageIcon redTopRightCorner = new ImageIcon("redTopRightCorner.png");
    static ImageIcon redLeft = new ImageIcon("redLeft.png");
    static ImageIcon redBottomRightCorner = new ImageIcon("redBottomRightCorner.png");
    static ImageIcon redDown = new ImageIcon("redDown.png");
    static ImageIcon redBottomLeftCorner = new ImageIcon("redBottomLeftCorner.png");
    static ImageIcon redRight = new ImageIcon("redRight.png");
    static ImageIcon redTopLeftCorner = new ImageIcon("redTopLeftCorner.png");
    static ImageIcon blueUp = new ImageIcon("blueUp.png");
    static ImageIcon blueTopRightCorner = new ImageIcon("blueTopRightCorner.png");
    static ImageIcon blueLeft = new ImageIcon("blueLeft.png");
    static ImageIcon blueBottomRightCorner = new ImageIcon("blueBottomRightCorner.png");
    static ImageIcon blueDown = new ImageIcon("blueDown.png");
    static ImageIcon blueBottomLeftCorner = new ImageIcon("blueBottomLeftCorner.png");
    static ImageIcon blueRight = new ImageIcon("blueRight.png");
    static ImageIcon blueTopLeftCorner = new ImageIcon("blueTopLeftCorner.png");
    static ImageIcon target = new ImageIcon("target.png");

    protected ImageIcon[][] locationIcons = {
            {redRight, redBottomRightCorner, blueDown, blueBottomLeftCorner, redDown, redBottomLeftCorner, redDown, redDown},
            {blueRight, redDown, blueBottomRightCorner, redRight, blueBottomRightCorner, blueDown, blueLeft, redBottomLeftCorner},
            {redUp, blueLeft, blueBottomLeftCorner, redBottomRightCorner, redTopRightCorner, blueBottomLeftCorner, blueLeft, redLeft},
            {redBottomRightCorner, redBottomRightCorner, blueBottomLeftCorner, redBottomRightCorner, redDown, blueTopLeftCorner, redRight, blueTopLeftCorner},
            {blueTopRightCorner, redLeft, redDown, blueDown, blueRight, blueTopRightCorner, blueTopLeftCorner, redTopLeftCorner},
            {redDown, blueBottomRightCorner, redBottomRightCorner, redBottomRightCorner, redTopLeftCorner, redTopRightCorner, blueRight, redLeft},
            {redTopRightCorner, blueLeft, blueBottomRightCorner, redRight, redRight, blueRight, blueTopLeftCorner, redBottomLeftCorner},
            {blueTopRightCorner, redRight, blueUp, redTopRightCorner, blueTopRightCorner, blueUp, blueTopLeftCorner, target}
    };
    protected static Location[][] locations = new Location[8][8];
    //note that each location has transitions that it can move to

    protected static JLabel success = new JLabel("RUNNING!...", JLabel.CENTER);

    protected static Location previousLocation;

    public void launch(PuzzleFrame frame, BullEyePuzzle bullEyePuzzle) {
        success.setFont(new Font("Serif", Font.PLAIN, 50));
        this.bullEyePuzzle = bullEyePuzzle;
        this.bullEyePuzzle.setPreferredSize(new Dimension(1249, 992));
        this.bullEyePuzzle.setContentPane(frame.getContentPane());
        this.bullEyePuzzle.getContentPane().setLayout(new FlowLayout());
        frame.setVisible(false);
        setUpFrontPage();

    }

    @Override
    public void setUpFrontPage() {

        Container contentPane = this.bullEyePuzzle.getContentPane();

        JLabel label0 = new JLabel("LADY BUG PUZZLE", JLabel.CENTER);
        label0.setFont(new Font("Serif", Font.PLAIN, 50));
        label0.setPreferredSize(new Dimension(1245, 60));
        label0.setBackground(new Color(255, 255, 255));
        contentPane.add(label0);

        JPanel jPanel1 = new JPanel();
        // jPanel1.setLayout(new GridLayout(6,6));
        jPanel1.setLayout(new GridLayout(8, 8));
        jPanel1.setPreferredSize(new Dimension(600, 600));

        jPanel1.setBackground(new Color(128, 0, 128));
//adding buttons(locations to the location array)
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                Location toBeAdded;
                if ((row == 7) && (column == 7)) {
                    toBeAdded = new Location(locationIcons[row][column], row, column);
                    toBeAdded.addActionListener(new BullEyePuzzleHandler(toBeAdded));
                    toBeAdded.setText("Goal");


                } else {
                    toBeAdded = new Location(locationIcons[row][column], row, column);
                    toBeAdded.addActionListener(new BullEyePuzzleHandler(toBeAdded));
                    toBeAdded.setContentAreaFilled(true);


                }
                locations[row][column] = toBeAdded;

                // adding those locations to the actual panel
                jPanel1.add(locations[row][column]);

            }

        }
        locations[0][0].setIsClickableLocation(true);
        setTransitions();
        setUpLegalMoves();
        //adding the panel to contentpane
        contentPane.add(jPanel1);

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
        help.addActionListener(new LadyBugHelpHandler());
        jPanel3.add(help);
        //
        JButton seeSolution = new JButton("See Solution");
        seeSolution.setBackground(new Color(0, 255, 0));
        seeSolution.addActionListener(new PuzzleSolver(locations[0][0], locations[7][7],true, locations,seeSolution));
        jPanel3.add(seeSolution);
        //
        JButton quit = new JButton("Quit          ");
        quit.setBackground(new Color(255, 0, 0));
        quit.addActionListener(new ButtonToMenuListener(quit, this));
        jPanel3.add(quit);
        contentPane.add(jPanel3);

        bullEyePuzzle.pack();
        bullEyePuzzle.setVisible(true);

    }

    @Override
    public void setUpLegalMoves() {
        //enabling clickable locations
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
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
        //disable all the buttons
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                locations[row][column].setIsClickableLocation(false);
                locations[row][column].setEnabled(false);
            }
        }

    }

    public void updateLabel(String update) {
        //updates the lable, to  give instructions or to give the winning message
        success.setText(update);

    }

    public void setTransitions() {
        //set the possible locations the ladybug can move to from that locations
         ////2D MOVEMENT
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                if ((locations[row][column].getIcon().equals(redUp))||(locations[row][column].getIcon().equals(blueUp))) {
                    //test location in my column up
                    for (int i = row; i >= 0; i--) {
                        if (!(locations[i][column].getColor().equals(locations[row][column].getColor())) || (locations[i][column].getIcon().equals(target))) {
                            if(!locations[row][column].getTransitions().contains(locations[i][column])){
                                locations[row][column].setTransitions(locations[i][column]);
                            }

                        }
                    }
                }
                if ((locations[row][column].getIcon().equals(redRight))||(locations[row][column].getIcon().equals(blueRight))) {
                    //test everything in my row to the right
                    for (int i = row; i < 8; i++) {
                        for (int j = column; j < 8; j++) {
                            if (!(locations[i][j].getColor().equals(locations[row][column].getColor())) || (locations[i][j].getIcon().equals(target))) {

                                if(!locations[row][column].getTransitions().contains(locations[i][column])){
                                    locations[row][column].setTransitions(locations[i][j]);
                                }

                            }
                        }
                        break;
                    }
                }

                if ((locations[row][column].getIcon().equals(redDown))||(locations[row][column].getIcon().equals(blueDown))) {
                    //test everything in my column down
                    for (int i = row; i < 8; i++) {
                        if (!(locations[i][column].getColor().equals(locations[row][column].getColor())) || (locations[i][column].getIcon().equals(target))) {

                            if(!locations[row][column].getTransitions().contains(locations[i][column])){
                                locations[row][column].setTransitions(locations[i][column]);
                            }

                        }
                    }
                }

                if ((locations[row][column].getIcon().equals(redLeft))|| (locations[row][column].getIcon().equals(blueLeft))) {
                    //testing everything in my row going to the left
                    for (int i = row; i < 8; i++) {
                        for (int j = column; j >= 0; j--) {
                            if (!(locations[i][j].getColor().equals(locations[row][column].getColor())) || (locations[i][j].getIcon().equals(target))) {
                                if(!locations[row][column].getTransitions().contains(locations[i][column])){
                                    locations[row][column].setTransitions(locations[i][j]);
                                }
                            }
                        }
                       break;
                    }
                }

            }
        }
        ///////3D MOVEMENT
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {


                if ((locations[row][column].getIcon().equals(redTopRightCorner))||(locations[row][column].getIcon().equals(blueTopRightCorner))) {
                    //test location in my diagonal up
                    boolean bumped=false;
                    int myRow=row-1;
                    int myColumn=column+1;
                    while(!bumped){
                        //set bumped to true if column becomes 7 or row becomes zero
                        if(!locations[row][column].getColor().equals(locations[myRow][myColumn].getColor())){
                            if(!locations[row][column].getTransitions().contains(locations[myRow][myColumn])){
                                locations[row][column].setTransitions(locations[myRow][myColumn]);
                            }
                        }
                        myRow--;
                        myColumn++;
                        if((myRow==-1)||(myColumn==8)){
                            bumped=true;
                        }

                    }

                }
                if ((locations[row][column].getIcon().equals(redBottomRightCorner))||(locations[row][column].getIcon().equals(blueBottomRightCorner))) {
                    boolean bumped=false;
                    int myRow=row+1;
                    int myColumn=column+1;
                    while(!bumped){
                        //set bumped to true if column becomes 7 or row becomes zero
                        if(!locations[row][column].getColor().equals(locations[myRow][myColumn].getColor())){
                            if(!locations[row][column].getTransitions().contains(locations[myRow][myColumn])){
                                locations[row][column].setTransitions(locations[myRow][myColumn]);
                            }
                        }
                        myRow++;
                        myColumn++;
                        if((myRow==8)||(myColumn==8)){
                            bumped=true;
                        }

                    }
                }

                if ((locations[row][column].getIcon().equals(redBottomLeftCorner))||(locations[row][column].getIcon().equals(blueBottomLeftCorner))) {

                    boolean bumped=false;
                    int myRow=row+1;
                    int myColumn=column-1;
                    while(!bumped){
                        //set bumped to true if column becomes 7 or row becomes zero
                        if(!locations[row][column].getColor().equals(locations[myRow][myColumn].getColor())){
                            if(!locations[row][column].getTransitions().contains(locations[myRow][myColumn])){
                                locations[row][column].setTransitions(locations[myRow][myColumn]);
                            }
                        }
                        myRow++;
                        myColumn--;
                        if((myRow==8)||(myColumn==-1)){
                            bumped=true;
                        }

                    }
                }

                if ((locations[row][column].getIcon().equals(redTopLeftCorner))||((locations[row][column].getIcon().equals(blueTopLeftCorner)))) {

                    boolean bumped=false;
                    int myRow=row-1;
                    int myColumn=column-1;
                    while(!bumped){
                        //set bumped to true if column becomes 7 or row becomes zero
                        if(!locations[row][column].getColor().equals(locations[myRow][myColumn].getColor())){
                            if(!locations[row][column].getTransitions().contains(locations[myRow][myColumn])){
                                locations[row][column].setTransitions(locations[myRow][myColumn]);
                            }
                        }
                        myRow--;
                        myColumn--;
                        if((myRow==-1)||(myColumn==-1)){
                            bumped=true;
                        }

                    }

                }


            }

            ////////////////////////////////////////////////stop testing.....
            //to avoid previous location being set to null
            previousLocation = locations[0][0];
        }
    }
}