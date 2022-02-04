import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MajorGridHelpHandler extends MajorGridPuzzle implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Help")) {
            updateLabel("Click active buttons to get to the goal");
        } else if (e.getActionCommand().equals("See Solution")) {
            updateLabel("Solution not yet available!...");
        }
    }
}

