package io.kowsu.algos;

/*
    @created January/24/2024 - 2:40 PM
    @project spring-boot-web
    @author k.ramanjineyulu
*/


import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Graph contains vertices
 * Vertices contains edges
 */
public class Graph {

    Map<String, List<Node>> graph;
    private boolean isBiDirectional;

    public Graph(boolean isBiDirectional) {
        this.isBiDirectional = isBiDirectional;
        graph = new LinkedHashMap<>();
    }

    public void addVertex(String vetxLabel) {
        if (!graph.containsKey(vetxLabel)) {
            graph.put(vetxLabel, new LinkedList<>());
        }
    }

    public void addEdge(String srcVtx, String destVtx) {
        assert graph.containsKey(srcVtx);
        assert graph.containsKey(destVtx);

        if(graph.containsKey(srcVtx) && !graph.get(srcVtx).contains(destVtx)) {
            graph.get(srcVtx).add(new Node(destVtx));
        }
        if(isBiDirectional) {
            if(graph.containsKey(destVtx) && !graph.get(destVtx).contains(srcVtx)) {
                graph.get(destVtx).add(new Node(srcVtx));
            }
        }
    }

    public void display() {
        graph.forEach((node, edges) -> {
            System.out.println(node + " -> " + edges);
        });
    }

    public static void main(String[] args) {
        Graph graph = new Graph(true);
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B");
        graph.display();
    }
}

class Node {
    private String label;

    public Node(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(label, node.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(label);
    }

    @Override
    public String toString() {
        return label;
    }
}
