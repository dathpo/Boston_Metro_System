public class Node implements NodeADT {

    private int id;
    private String label;

    Node(int nodeID, String nLabel) {
        id = nodeID;
        label = nLabel;
    }

    public int getId() {
        return this.id;
    }

    public String getLabel() {
        return this.label;
    }

    public boolean equals(Node obj) {
        boolean result = false;
        if (obj instanceof Node)
            result = (this.getId() == obj.getId());
        return result;
    }
}
