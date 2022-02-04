import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MinorGridPuzzleHandler extends MinorGridPuzzle implements ActionListener {
 Location clickedLocation;

    public MinorGridPuzzleHandler(Location location){
       clickedLocation=location;

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        deActivateAll();
        if(e.getActionCommand().equals("GOAL")){
           updateLabel("SUCCESS");
        }
        else{
         //set transitions to light up
            for(Location location: clickedLocation.getTransitions()){
               location.setIsClickableLocation(true);

            }
            setUpLegalMoves();
        }


    }
//

//

}
