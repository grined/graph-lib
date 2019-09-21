package com.grined.util.graph;


import com.grined.util.graph.data.Edge;
import com.grined.util.graph.data.Vertex;
import com.grined.util.graph.exception.NoPathException;
import org.junit.Test;

import java.util.List;

public class DirectedGraphTest extends AbstractGraphTest {

    @Override
    Graph<Integer> buildGraph() {
        return new DirectedGraph<>();
    }

    @Test(expected = NoPathException.class)
    public void testGetPathForLineOf3ElementsWrongDirection(){
        Graph<Integer> graph = buildGraph();
        Vertex<Integer> vertex1 = graph.addVertex(1);
        Vertex<Integer> vertex2 = graph.addVertex(2);
        Vertex<Integer> vertex3 = graph.addVertex(3);
        Edge<Integer> edgeA = graph.addEdge(vertex1, vertex2);
        Edge<Integer> edgeB = graph.addEdge(vertex3, vertex1);
        graph.getPath(vertex1, vertex3);
    }

    @Test(expected = NoPathException.class)
    public void testGetPathLoopWithDirectionWrongWay(){
        Graph<Integer> graph = buildGraph();
        Vertex<Integer> vertex1 = graph.addVertex(1);
        Vertex<Integer> vertex2 = graph.addVertex(2);
        Vertex<Integer> vertex3 = graph.addVertex(3);
        Vertex<Integer> vertex4 = graph.addVertex(4);
        Edge<Integer> edgeA = graph.addEdge(vertex1, vertex2);
        Edge<Integer> edgeB = graph.addEdge(vertex2, vertex3);
        Edge<Integer> edgeC = graph.addEdge(vertex3, vertex1);
        Edge<Integer> edgeD = graph.addEdge(vertex4, vertex1);
        List<Edge<Integer>> path = graph.getPath(vertex1, vertex4);
    }
}
