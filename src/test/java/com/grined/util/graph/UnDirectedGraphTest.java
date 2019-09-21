package com.grined.util.graph;


import com.grined.util.graph.data.Edge;
import com.grined.util.graph.data.Vertex;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class UnDirectedGraphTest extends AbstractGraphTest {
    @Override
    Graph<Integer> buildGraph() {
        return new UndirectedGraph<>();
    }

    @Test
    public void testGetPathForLineOf3ElementsWrongDirectionDoesntMatter(){
        Graph<Integer> graph = buildGraph();
        Vertex<Integer> vertex1 = graph.addVertex(1);
        Vertex<Integer> vertex2 = graph.addVertex(2);
        Vertex<Integer> vertex3 = graph.addVertex(3);
        Edge<Integer> edgeA = graph.addEdge(vertex1, vertex2);
        Edge<Integer> edgeB = graph.addEdge(vertex3, vertex1);
        List<Edge<Integer>> path = graph.getPath(vertex1, vertex3);
        assertThat(path, is(asList(edgeB)));
    }

    @Test
    public void testGetPathForLineOf3ElementsWrongDirectionDoesntMatter2steps(){
        Graph<Integer> graph = buildGraph();
        Vertex<Integer> vertex1 = graph.addVertex(1);
        Vertex<Integer> vertex2 = graph.addVertex(2);
        Vertex<Integer> vertex3 = graph.addVertex(3);
        Edge<Integer> edgeA = graph.addEdge(vertex1, vertex2);
        Edge<Integer> edgeB = graph.addEdge(vertex3, vertex1);
        List<Edge<Integer>> path = graph.getPath(vertex2, vertex3);
        assertThat(path, is(asList(edgeA, edgeB)));
    }

    @Test
    public void testGetPathLoopWithNoMatterDirection(){
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
        assertThat(path, is(asList(edgeD)));
    }
}
