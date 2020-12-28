import java.util.List;

public interface GraphADT {

    public List<NodeADT> bfSearch(NodeADT start, NodeADT end);

    public void addEdge(NodeADT startNode, NodeADT endNode, String label);

    public void addNode(int id, String label);

    public NodeADT getNode(int id);

    public int numNodes();
}
