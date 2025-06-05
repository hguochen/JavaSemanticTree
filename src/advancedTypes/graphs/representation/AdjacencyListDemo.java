package advancedTypes.graphs.representation;

import java.util.*;

public class AdjacencyListDemo {
    public static void main(String[] args) {
        AdjacencyList graph = new AdjacencyList(5);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.print();
        System.out.println("------------");
        graph.removeEdge(0, 1);
        graph.print();
        System.out.println(graph.hasEdge(0, 1)); // true
        System.out.println(graph.hasEdge(1, 2)); // false
    }
}
