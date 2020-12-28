import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Metro {

    GraphADT graphInterface = new Graph();
    public ArrayList<String> stationNames = new ArrayList<String>();

    public String findRoute(int startID, int endID) {
        NodeADT start = graphInterface.getNode(startID);
        NodeADT end = graphInterface.getNode(endID);
        List<NodeADT> route = new ArrayList<NodeADT>();
        route = graphInterface.bfSearch(start, end);
        for (NodeADT n : route) {
            if (n.getId() == startID) {
                System.out.println("Board at " + n.getLabel());
            }
            if (n.getId() == endID) {
                System.out.println("Then get off at " + n.getLabel());
            } else if (n.getId() != startID && n.getId() != endID) {
                System.out.println("Then change line at " + n.getLabel());
            }
        }
        return "";
    }

    public NodeADT getStation(int ID) {
        return graphInterface.getNode(ID);
    }

    public void addStationInfo(int ID, String stationName) {
        graphInterface.addNode(ID, stationName);
    }

    public int getStationID(String name) {
        int iD = stationNames.indexOf(name);
        return iD;
    }

    public void addLine(int startNode, int endNode, String lineName) {
        if (startNode == 0) {
            startNode = endNode;
        }
        if (endNode == 0) {
            endNode = startNode;
        }
        NodeADT start = graphInterface.getNode(startNode);
        NodeADT end = graphInterface.getNode(endNode);
        graphInterface.addEdge(start, end, lineName);
    }

    public int checkDouble() {
        Scanner doubleInput = new Scanner(System.in);
        System.out.println("Do you mean St.PaulStreet on GreenB or GreenC?");
        String whichLine = doubleInput.nextLine();
        if (whichLine.equals("GreenB")) {
            return 38;
        } else if (whichLine.equals("GreenC")) {
            return 61;
        } else checkDouble();
        return 0;
    }
}
