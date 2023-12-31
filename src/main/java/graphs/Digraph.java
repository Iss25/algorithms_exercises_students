package graphs;
import java.util.ArrayList;


/**
 * Implement the Digraph.java interface in
 * the Digraph.java class using an adjacency list
 * data structure to represent directed graphs.
 */
public class Digraph {

    private int V;
    private int E;
    private ArrayList<ArrayList> graph;

    public Digraph(int V) {
        this.V = V;
        this.E = E;
        graph = new ArrayList<ArrayList>();
        for (int i = 0; i < V(); i++) {
            graph.add(new ArrayList<>());
        }
    }

    /**
     * The number of vertices
     */
    public int V() {
        return V;
    }

    /**
     * The number of edges
     */
    public int E() {
        return E;
    }

    /**
     * Add the edge v->w
     */
    public void addEdge(int v, int w) {
        this.graph.get(v).add(w);
        E++;
    }

    /**
     * The nodes adjacent to node v
     * that is the nodes w such that there is an edge v->w
     */
    public Iterable<Integer> adj(int v) {
        return graph.get(v);
    }

    /**
     * A copy of the digraph with all edges reversed
     */
    public Digraph reverse() {
        Digraph reversed = new Digraph(V);
        for (int i = 0; i < this.V; i++) {
            for (int w: adj(i)) {
                reversed.addEdge(w,i);
            }
        }
        return reversed;
    }

}
