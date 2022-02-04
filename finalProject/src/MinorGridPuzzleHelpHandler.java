import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MinorGridPuzzleHelpHandler extends MinorGridPuzzle implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Help")) {
            updateLabel("Click active buttons to get to the goal");
        }
    }
}
