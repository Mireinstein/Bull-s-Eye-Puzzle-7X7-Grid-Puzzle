import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PuzzleSolver implements ActionListener {
    ArrayList<Location> dynamicArraylist = new ArrayList<>();
    ArrayList<Location> dumpster = new ArrayList<>();
    ArrayList<Location> solution = new ArrayList<>();
    ArrayList<Location> realTransitions = new ArrayList<>();
    Location  previousLocation;
    private int realTransitionCount=0;

    private Location startingState;
    private Location goal;
    private Boolean goalFound = false;
    private boolean allowUTurn;
    private Location[][] locations;
    JButton restart;


    public PuzzleSolver(Location startingState, Location goal, Boolean allowUTurn, Location[][] locations, JButton restart) {
        this.startingState = startingState;
        this.goal = goal;
        this.allowUTurn = allowUTurn;
        this.locations = locations;
        this.restart = restart;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (allowUTurn) {
            search();

        } else {
            specialSearch();

        }
    }

    public void search() {
        //Breadth First search
        revert(true);
        dynamicArraylist.add(startingState);
        while ((!goalFound) && (!dynamicArraylist.isEmpty())) {
            System.out.println("searching............");
            if ((dynamicArraylist.get(0).getRow() == goal.getRow()) && (dynamicArraylist.get(0).getColumn() == goal.getColumn())) {
                goalFound = true;
               System.out.println("Solution Found...");
                buildSolution(dynamicArraylist.remove(0));//then some other way to print the solution follows

            }
            if (!dynamicArraylist.isEmpty()) {
                for (Location location : dynamicArraylist.get(0).getTransitions()) {
                    if ((!dynamicArraylist.contains(location)) && (!dumpster.contains(location))) {
                        location.setParentNode(dynamicArraylist.get(0));
                        dynamicArraylist.add(location);
                    }
                }
            }
            if (!dynamicArraylist.isEmpty()) {
                dumpster.add(dynamicArraylist.remove(0));
            }

        }

        // printing the solution----works

    }

    public void buildSolution(Location location) {
        System.out.println("Building solution...");
        solution.add(location);
        while (true) {
            solution.add(location.getParentNode());
            location = location.getParentNode();
            if ((location.getRow() == startingState.getRow()) && (location.getColumn() == startingState.getColumn())) {
                System.out.println(solution);
                break;

            }

        }

        computerGivesCommands();

    }

    public void revert(Boolean emptyAll) {
        for (Location pseudoLocation[] : locations) {
            for (Location actualLocation : pseudoLocation) {
                actualLocation.setEnabled(false);
                actualLocation.setWasPassedBy(false);
                actualLocation.setContainsLadyBug(false);
                actualLocation.setIsClickableLocation(false);


                if (emptyAll) {
                    while (!dynamicArraylist.isEmpty()) {
                        dynamicArraylist.remove(0);
                    }
                    while (!solution.isEmpty()) {
                        solution.remove(0);
                    }
                    while (!dumpster.isEmpty()) {
                        dumpster.remove(0);
                    }
                    goalFound = false;
                    actualLocation.setWasVisited(false);
                    actualLocation.setParentNode(null);
                    startingState.setIsClickableLocation(true);
                    startingState.setEnabled(true);

                }

            }
        }
        startingState.setParentNode(startingState);

    }

    public void computerGivesCommands() {
        String toSet = " start at " + startingState.getRow() + "," + startingState.getColumn();
        for (int i = solution.size() - 2; i >= 0; i--) {
            toSet = toSet + " then  " + solution.get(i);

        }
        JFrame jFrame = new JFrame();
        JOptionPane.showMessageDialog(jFrame, toSet);
        computerShowsLiveDemo();
    }

    public void computerShowsLiveDemo() {
        ArrayList<Location> newSolution = new ArrayList<>();
        for (int i = solution.size() - 1; i >= 0; i--) {
            newSolution.add(solution.remove(i));
        }
        revert(true);
        startingState.setEnabled(true);
        for (Location location : newSolution) {
            location.doClick(1700);
        }

    }

    //start testing
    public void specialSearch() {
        dynamicArraylist.add(startingState);
        startingState.setParentNode(startingState);
        while((!goalFound)&&(!dynamicArraylist.isEmpty())){
            if ((dynamicArraylist.get(0).getRow() == goal.getRow()) && (dynamicArraylist.get(0).getColumn() == goal.getColumn())) {
                goalFound = true;
                 System.out.println("Solution Exists...");
               buildSolution(dynamicArraylist.remove(0));//then some other way to print the solution follows
            }

            if (!dynamicArraylist.isEmpty()) {

                if(dumpster.isEmpty()){
                    for (Location location :dynamicArraylist.get(0).getTransitions()) {
                        if ((!dynamicArraylist.contains(location)) && (!dumpster.contains(location))) {
                            location.setParentNode(dynamicArraylist.get(0));
                            dynamicArraylist.add(location);
                        }
                    }
                }
               else if(!dumpster.isEmpty()){
                    for (Location location :realTransitions(dynamicArraylist.get(0), dumpster.get(dumpster.size()-1), dynamicArraylist.get(0).getTransitions())) {
                        if ((!dynamicArraylist.contains(location)) && (!dumpster.contains(location))) {
                            location.setParentNode(dynamicArraylist.get(0));
                            dynamicArraylist.add(location);
                        }
                    }
                }

            }
            if (!dynamicArraylist.isEmpty()) {
                dumpster.add(dynamicArraylist.remove(0));
            }

        }
    }
public ArrayList<Location> realTransitions(Location clickedLocation,Location previousLocation ,ArrayList<Location> transitions){
        while(!realTransitions.isEmpty()){
            realTransitions.remove(0);
        }
        realTransitionCount=0;
        ////////////////////// get transitions which are actually allowed

    for(Location transition: transitions){

        if((transition.getRow()==previousLocation.getRow())&&((transition.getColumn()<clickedLocation.getColumn())&&
                (previousLocation.getColumn()<clickedLocation.getColumn()))){
            //previous location to my left (my column > previous column)


        }

        else if((transition.getRow()==previousLocation.getRow())&&((transition.getColumn()>clickedLocation.getColumn())&&
                (previousLocation.getColumn()>clickedLocation.getColumn()))){
            //previous location to my right (my column < previous column)

        }
        else if((transition.getColumn()==previousLocation.getColumn())&&
                ((previousLocation.getRow()<clickedLocation.getRow())&&(transition.getRow()<clickedLocation.getRow()))){
            //previous location above me my row> previous row


        }
        else if((transition.getColumn()==previousLocation.getColumn())&&
                ((previousLocation.getRow()>clickedLocation.getRow())&&(transition.getRow()>clickedLocation.getRow()))){


        }
        else {

            realTransitions.add(transition);
        }


    }

        return realTransitions;
}



}
