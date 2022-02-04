import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class LadyBugPuzzle extends PuzzleFrame {
    LadyBugPuzzle ladyBugPuzzle;
    static ImageIcon blank = new ImageIcon("blank.png");
    static ImageIcon greenPlus = new ImageIcon("greenPlus.png");
    static ImageIcon redPlus = new ImageIcon("redPlus.png");
    static ImageIcon redStar = new ImageIcon("redStar.png");
    static ImageIcon yellowDiamond = new ImageIcon("yellowDiamond.png");
    static ImageIcon blueDiamond = new ImageIcon("blueDiamond.png");
    static ImageIcon redDiamond = new ImageIcon("redDiamond.png");
    static ImageIcon yellowStar = new ImageIcon("yellowStar.png");
    static ImageIcon greenStar = new ImageIcon("greenStar.png");
    static ImageIcon greenFlower = new ImageIcon("greenFlower.png");
    static ImageIcon redFlower = new ImageIcon("redFlower.png");
    static ImageIcon blueFlower = new ImageIcon("blueFlower.png");
    static ImageIcon yellowPlus = new ImageIcon("yellowPlus.png");
    static ImageIcon yellowFlower = new ImageIcon("yellowFlower.png");
    static ImageIcon goal = new ImageIcon("goal.png");
    protected ImageIcon[][] locationIcons = {{blank, blank, goal, blank, blank, blank},
            {redPlus, redStar, greenStar, blueDiamond, redDiamond, yellowStar},
            {yellowStar, yellowDiamond, redStar, greenPlus, greenStar, greenFlower},
            {redFlower, redPlus, blueDiamond, blueFlower, yellowDiamond, yellowPlus},
            {greenStar, redDiamond, yellowPlus, yellowDiamond, greenFlower, blueFlower},
            {blank, yellowFlower, blank, blank, blank, blank}};
    public static Location[][] locations = new Location[6][6];
    //note that each location has transitions that it can move to

    protected static JLabel success = new JLabel("RUNNING!...", JLabel.CENTER);

  protected static Location previousLocation;

    public void launch(PuzzleFrame frame, LadyBugPuzzle ladyBugPuzzle) {
        success.setFont(new Font("Serif", Font.PLAIN, 50));
        this.ladyBugPuzzle = ladyBugPuzzle;
        this.ladyBugPuzzle.setPreferredSize(new Dimension(1249, 992));
        this.ladyBugPuzzle.setContentPane(frame.getContentPane());
        this.ladyBugPuzzle.getContentPane().setLayout(new FlowLayout());
        frame.setVisible(false);
        setUpFrontPage();

    }

    @Override
    public void setUpFrontPage() {

        Container contentPane = this.ladyBugPuzzle.getContentPane();

        JLabel label0 = new JLabel("LADY BUG PUZZLE", JLabel.CENTER);
        label0.setFont(new Font("Serif", Font.PLAIN, 50));
        label0.setPreferredSize(new Dimension(1245, 60));
        label0.setBackground(new Color(255, 255, 255));
        contentPane.add(label0);

        JPanel jPanel1 = new JPanel();
        // jPanel1.setLayout(new GridLayout(6,6));
        jPanel1.setLayout(new GridLayout(6, 6));
        jPanel1.setPreferredSize(new Dimension(600, 600));

        jPanel1.setBackground(new Color(128, 0, 128));
//adding buttons(locations to the location array)
        for (int row = 0; row < 6; row++) {
            for (int column = 0; column < 6; column++) {
                Location toBeAdded;
                if ((row == 0) && (column == 2)) {
                    toBeAdded = new Location(locationIcons[row][column],row,column);
                    toBeAdded.addActionListener(new LadyBugPuzzleHandler(toBeAdded));
                    toBeAdded.setText("Goal");


                } else {
                    toBeAdded = new Location(locationIcons[row][column],row,column);
                    toBeAdded.addActionListener(new LadyBugPuzzleHandler(toBeAdded));
                    toBeAdded.setContentAreaFilled(true);


                }
                locations[row][column] = toBeAdded;

                // adding those locations to the actual panel
                jPanel1.add(locations[row][column]);

            }

        }
       locations[5][1].setIsClickableLocation(true);
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
        seeSolution.addActionListener(new PuzzleSolver(locations[5][1],locations[0][2],false,locations,seeSolution));
        jPanel3.add(seeSolution);
        //
        JButton quit = new JButton("Quit          ");
        quit.setBackground(new Color(255, 0, 0));
        quit.addActionListener(new ButtonToMenuListener(quit, this));
        jPanel3.add(quit);
        contentPane.add(jPanel3);

        ladyBugPuzzle.pack();
        ladyBugPuzzle.setVisible(true);

    }

    @Override
    public void setUpLegalMoves() {
        //enabling clickable locations
        for(int row=0; row<6;row++){
            for (int column=0; column<6;column++){
                if((locations[row][column].getIsClickableLocation())){
                    locations[row][column].setEnabled(true);
                }
                else{
                    locations[row][column].setEnabled(false);
                    //testing
                    locations[row][column].setDisabledIcon(locations[row][column].getIcon());
                }
            }
        }


    }


        public void deActivateAll(){
        //disable all the buttons
            for(int row=0; row<6;row++){
                for (int column=0; column<6;column++){
                    locations[row][column].setIsClickableLocation(false);
                    locations[row][column].setEnabled(false);
                }
            }

        }

    public void updateLabel(String update) {
        //updates the lable, to  give instructions or to give the winning message
        success.setText(update);

    }

    public void setTransitions(){
        //set the possible locations the ladybug can move to from that locations
        //testing.......
        //do this for every single location
        for (Location pseudoLocation[]:locations) {
            for(Location actualLocation:pseudoLocation){
                //loop over all the locations to see if they are possible transitions
                for(int row=0;row<6;row++){
                    for(int column=0;column<6;column++ ){
                        /*if a location is in my row
                          --do stuff, {test to see if its same color,or symbol then set it as a possible transition if yes, and not already contained}
                          */
                        if(actualLocation.getRow()==row){
                            if((locations[row][column].getColor().equals(actualLocation.getColor()))||(locations[row][column].getSymbol().equals(actualLocation.getSymbol()))){
                                if(!actualLocation.getTransitions().contains(locations[row][column])){
                                    actualLocation.setTransitions(locations[row][column]);
                                }
                            }

                        }


                        /*if a location is in my column
                        --do stuff, {test to see if its same symbol,or symbol, then set it as a possible transition if yes
                        ..if its not already contained}
                         */
                        if(actualLocation.getColumn()==column){
                            if((locations[row][column].getSymbol().equals(actualLocation.getSymbol()))||(locations[row][column].getColor().equals(actualLocation.getColor()))){
                                if(!actualLocation.getTransitions().contains(locations[row][column])){
                                    actualLocation.setTransitions(locations[row][column]);
                                }
                            }
                        }


                    }
                }
            }

        }
        //stop testing.....
        //to avoid previous location being set to null
        previousLocation=locations[5][1];
    }
}