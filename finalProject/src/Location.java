import javax.swing.*;
import java.util.ArrayList;

public class Location extends JButton {


    //for all
    private ArrayList<Location> transitions = new ArrayList<>();
    private boolean isClickableLocation = false;

    private int row;
    private int column;
    private Location parentNode;
    private Boolean wasVisited = false;
    private Boolean wasPassedBy = false;

    private Boolean moveTopRightCorner = false;
    private Boolean moveTopLeftCorner = false;
    private Boolean moveBottomRightCorner = false;
    private Boolean getMoveBottomLeftCorner = false;


    //for ladybug
    private String color;
    private String symbol;
    boolean containsLadyBug = false;

    //for grid and
    public Location(String buttonLabel, int row, int column) {
        super((buttonLabel));
        this.isClickableLocation = false;
        this.row = row;
        this.column = column;

    }


    //for ladyBug and Bull eye
    public Location(ImageIcon buttonIcon, int row, int column) {
        super((buttonIcon));
        if (buttonIcon.equals(LadyBugPuzzle.blank)) {
            this.color = "blank";
            this.symbol = "blank";
        } else if (buttonIcon.equals(LadyBugPuzzle.greenPlus)) {
            this.color = "green";
            this.symbol = "plus";
        } else if (buttonIcon.equals(LadyBugPuzzle.redPlus)) {
            this.color = "red";
            this.symbol = "plus";
        } else if (buttonIcon.equals(LadyBugPuzzle.redStar)) {
            this.color = "red";
            this.symbol = "star";
        } else if (buttonIcon.equals(LadyBugPuzzle.yellowDiamond)) {
            this.color = "yellow";
            this.symbol = "diamond";
        } else if (buttonIcon.equals(LadyBugPuzzle.blueDiamond)) {
            this.color = "blue";
            this.symbol = "diamond";
        } else if (buttonIcon.equals(LadyBugPuzzle.redDiamond)) {
            this.color = "red";
            this.symbol = "diamond";
        } else if (buttonIcon.equals(LadyBugPuzzle.yellowStar)) {
            this.color = "yellow";
            this.symbol = "star";
        } else if (buttonIcon.equals(LadyBugPuzzle.greenStar)) {
            this.color = "green";
            this.symbol = "star";
        } else if (buttonIcon.equals(LadyBugPuzzle.greenFlower)) {
            this.color = "green";
            this.symbol = "flower";
        } else if (buttonIcon.equals(LadyBugPuzzle.redFlower)) {
            this.color = "red";
            this.symbol = "flower";
        } else if (buttonIcon.equals(LadyBugPuzzle.blueFlower)) {
            this.color = "blue";
            this.symbol = "flower";
        } else if (buttonIcon.equals(LadyBugPuzzle.yellowPlus)) {
            this.color = "yellow";
            this.symbol = "plus";
        } else if (buttonIcon.equals(LadyBugPuzzle.yellowFlower)) {
            this.color = "yellow";
            this.symbol = "flower";
        } else if (buttonIcon.equals(LadyBugPuzzle.goal)) {
            this.color = "green";
            this.symbol = "plus";
        }
        ////
        else if ((buttonIcon.equals(BullEyePuzzle.redUp))) {
            this.color = "red";

        } else if ((buttonIcon.equals(BullEyePuzzle.redTopRightCorner))) {
            this.color = "red";
        } else if ((buttonIcon.equals(BullEyePuzzle.redRight))) {
            this.color = "red";
        } else if ((buttonIcon.equals(BullEyePuzzle.redBottomRightCorner))) {
            this.color = "red";
        } else if ((buttonIcon.equals(BullEyePuzzle.redDown))) {
            this.color = "red";
        } else if ((buttonIcon.equals(BullEyePuzzle.redBottomLeftCorner))) {
            this.color = "red";
        } else if ((buttonIcon.equals(BullEyePuzzle.redLeft))) {
            this.color = "red";
        } else if ((buttonIcon.equals(BullEyePuzzle.redTopLeftCorner))) {
            this.color = "red";
        }
        //////
        else if ((buttonIcon.equals(BullEyePuzzle.blueUp))) {
            this.color = "blue";
        } else if ((buttonIcon.equals(BullEyePuzzle.blueTopRightCorner))) {
            this.color = "blue";
        } else if ((buttonIcon.equals(BullEyePuzzle.blueRight))) {
            this.color = "blue";
        } else if ((buttonIcon.equals(BullEyePuzzle.blueBottomRightCorner))) {
            this.color = "blue";
        } else if ((buttonIcon.equals(BullEyePuzzle.blueDown))) {
            this.color = "blue";
        } else if ((buttonIcon.equals(BullEyePuzzle.blueBottomLeftCorner))) {
            this.color = "blue";
        } else if ((buttonIcon.equals(BullEyePuzzle.blueLeft))) {
            this.color = "blue";
        } else if ((buttonIcon.equals(BullEyePuzzle.blueTopLeftCorner))) {
            this.color = "blue";
        } else if (buttonIcon.equals(BullEyePuzzle.target)) {
            this.color = "gold";
        }

        this.row = row;
        this.column = column;

    }


    public boolean getIsClickableLocation() {
        return isClickableLocation;
    }

    public void setIsClickableLocation(boolean isClickableLocation) {
        this.isClickableLocation = isClickableLocation;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public boolean getContainsLadyBug() {
        return containsLadyBug;
    }

    public void setContainsLadyBug(boolean containsLadyBug) {
        this.containsLadyBug = containsLadyBug;
    }

    public void setTransitions(Location location) {
        transitions.add(location);
    }

    public ArrayList<Location> getTransitions() {
        return this.transitions;
    }

    public void setLocation(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }


    public String toString() {
        return (this.row + "," + this.column);
    }

    public void setParentNode(Location parentNode) {
        this.parentNode = parentNode;
    }

    public Location getParentNode() {
        return parentNode;
    }

    public void setWasVisited(Boolean wasVisited) {
        this.wasVisited = wasVisited;
    }

    public Boolean getWasVisited() {
        return wasVisited;
    }

    public Boolean getWasPassedBy() {
        return wasPassedBy;
    }

    public void setWasPassedBy(Boolean wasPassedBy) {
        this.wasPassedBy = wasPassedBy;
    }
}
