package com.grined.util.graph;


import com.grined.util.graph.data.Edge;
import com.grined.util.graph.data.Vertex;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DirectedGraphTest {
    @Test
    public void testGetPathForDiamondStructureWith4Elements(){
        DirectedGraph<Integer> graph = new DirectedGraph<>();
        Vertex<Integer> vertex1 = graph.addVertex(1);
        Vertex<Integer> vertex2 = graph.addVertex(2);
        Vertex<Integer> vertex3 = graph.addVertex(3);
        Vertex<Integer> vertex4 = graph.addVertex(4);
        Edge<Integer> edgeA = graph.addEdge(vertex1, vertex2);
        Edge<Integer> edgeB = graph.addEdge(vertex1, vertex3);
        Edge<Integer> edgeC = graph.addEdge(vertex2, vertex4);
        Edge<Integer> edgeD = graph.addEdge(vertex3, vertex4);
        List<Edge<Integer>> path = graph.getPath(vertex1, vertex4);

        assertThat(true,
                anyOf(
                        is(path.get(0) == edgeA && path.get(1) == edgeC),
                        is(path.get(0) == edgeB && path.get(0) == edgeD)
                ));
    }
}
