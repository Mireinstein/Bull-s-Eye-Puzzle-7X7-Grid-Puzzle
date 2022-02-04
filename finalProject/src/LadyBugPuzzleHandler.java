import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LadyBugPuzzleHandler extends LadyBugPuzzle implements ActionListener {
    private Location clickedLocation;

    public LadyBugPuzzleHandler(Location location) {
        this.clickedLocation = location;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        deActivateAll();
        if (e.getActionCommand().equals("Goal")) {
            updateLabel("SUCCESS!");
        } else {
            //activating the transitions as long as they are not on the same side as the previous location

            for (Location transition : clickedLocation.getTransitions()) {
                if ((clickedLocation.equals(previousLocation)) && (!((clickedLocation.getRow() == 5) && (clickedLocation.getColumn() == 1)))) {
                    //nothing happens, this prevents cheating by pressing the same button twice
                } else if ((transition.getRow() == previousLocation.getRow()) && ((transition.getColumn() < clickedLocation.getColumn()) &&
                        (previousLocation.getColumn() < clickedLocation.getColumn()))) {
                    //previous location to my left (my column > previous column)
                    transition.setIsClickableLocation(false);

                } else if ((transition.getRow() == previousLocation.getRow()) && ((transition.getColumn() > clickedLocation.getColumn()) &&
                        (previousLocation.getColumn() > clickedLocation.getColumn()))) {
                    //previous location to my right (my column < previous column)
                    transition.setIsClickableLocation(false);
                } else if ((transition.getColumn() == previousLocation.getColumn()) &&
                        ((previousLocation.getRow() < clickedLocation.getRow()) && (transition.getRow() < clickedLocation.getRow()))) {
                    //previous location above me my row> previous row
                    transition.setIsClickableLocation(false);

                } else if ((transition.getColumn() == previousLocation.getColumn()) &&
                        ((previousLocation.getRow() > clickedLocation.getRow()) && (transition.getRow() > clickedLocation.getRow()))) {
                    transition.setIsClickableLocation(false);

                } else {

                    transition.setIsClickableLocation(true);
                }

                //then, after making transitions clickable
                setUpLegalMoves();
                clickedLocation.setEnabled(false);
            }
        }

        //last line of code changes clicked location to be previous
        previousLocation = clickedLocation;
    }


}



