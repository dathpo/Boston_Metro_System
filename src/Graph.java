import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Graph implements GraphADT {

    private ArrayList<EdgeADT> edges = new ArrayList<EdgeADT>();
    private ArrayList<NodeADT> nodes = new ArrayList<NodeADT>();

    public void addEdge(NodeADT startNode, NodeADT endNode, String label) {
        assert startNode != null : "Start node is null";
        assert endNode != null : "End node is null";
        assert label != null : "Label is null";
        EdgeADT addedEdge = new Edge(startNode, endNode, label);
        edges.add(addedEdge);
    }

    public List<NodeADT> bfSearch(NodeADT startNode, NodeADT endNode) {
        List<NodeADT> currentPath = new ArrayList<NodeADT>();
        Queue<List<NodeADT>> searchQueue = new LinkedList<List<NodeADT>>();
        List<NodeADT> startPath = new ArrayList<>();
        startPath.add(startNode);
        assert startPath.contains(startNode) : "node not added to path";
        searchQueue.add(startPath);
        assert searchQueue.contains(startPath) : "search queue doesn't contain start path";
        while (!searchQueue.isEmpty()) {
            currentPath = searchQueue.poll();
            if (currentPath.get(currentPath.size() - 1).equals(endNode))
                return shortenPath(currentPath);
            List<List<NodeADT>> extendedPath = extendPath(currentPath);
            for (List<NodeADT> list : extendedPath)
                searchQueue.add(list);
        }
        assert !searchQueue.isEmpty() : "SearchQueue isn't empty at the end of the search";
        return null;
    }

    public List<List<NodeADT>> extendPath(List<NodeADT> currentPath) {
        List<NodeADT> children = getNeighbours(currentPath.get(currentPath.size() - 1));
        List<List<NodeADT>> extendedPath = new ArrayList<List<NodeADT>>();
        for (NodeADT child : children) {
            List<NodeADT> temp = new ArrayList<>(currentPath);
            if (!currentPath.contains(child)) {
                temp.add(child);
                extendedPath.add(temp);
            }
        }
        return extendedPath;
    }

    public List<NodeADT> getNeighbours(NodeADT start) {
        List<NodeADT> neighbours = new ArrayList<NodeADT>();
        for (EdgeADT e : edges) {
            if (e.getStart().equals(start) && !(e.getEnd().equals(start))) {
                if (!neighbours.contains(e.getEnd()))
                    neighbours.add(e.getEnd());
            }
        }
        return neighbours;
    }

    public List<NodeADT> shortenPath(List<NodeADT> longPath) {
        List<NodeADT> path = new ArrayList<>(longPath);
        List<NodeADT> shortPath = new ArrayList<>();
        String line = "";
        for (int i = 0; i < path.size() - 1; i++) {
            for (EdgeADT e : edges) {
                if (e.getStart().equals(path.get(i)) && e.getEnd().equals(path.get(i + 1))) {
                    if (!e.getLabel().equals(line)) {
                        if (!(e.getStart().getLabel().equals("NorthStation") && e.getEnd().getLabel().equals("Haymarket")) && !(e.getStart().getLabel().equals("Haymarket") && e.getEnd().getLabel().equals("NorthStation"))) {
                            line = e.getLabel();
                            if (!shortPath.contains(e.getStart()))
                                shortPath.add(e.getStart());
                        }
                    }
                }
            }
        }
        shortPath.add(path.get(path.size() - 1));
        return shortPath;
    }

    public void addNode(int id, String label) {
        Node addedNode = new Node(id, label);
        nodes.add(addedNode);
        assert !nodes.isEmpty() : "node not added";
    }

    public NodeADT getNode(int id) {
        return nodes.get(id);
    }

    public int numNodes() {
        return nodes.size();
    }
}