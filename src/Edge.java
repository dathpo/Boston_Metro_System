public class Edge implements EdgeADT {

    private int edgeID;
    private String label;
    private NodeADT startNode, destNode;

    Edge(NodeADT start, NodeADT end, String edgeLabel) {
        startNode = start;
        destNode = end;
        label = edgeLabel;
    }

    public void setEdgeID(int value) {
        this.edgeID = value;
    }

    public int getEdgeID() {
        return this.edgeID;
    }

    public String getLabel() {
        return label;
    }

    public NodeADT getStart() {
        return startNode;
    }

    public NodeADT getEnd() {
        return destNode;
    }
}
