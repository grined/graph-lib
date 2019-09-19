package com.grined.util.graph;


import com.grined.util.graph.data.Edge;
import com.grined.util.graph.data.Vertex;
import com.grined.util.graph.exception.NoPathException;
import org.junit.Test;
import java.util.List;
import static java.util.Arrays.asList;
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

        assertThat(path,
                anyOf(
                        is(asList(edgeA, edgeC)),
                        is(asList(edgeB, edgeD))
                ));
    }

    @Test
    public void testGetPathForLineOf3Elements(){
        DirectedGraph<Integer> graph = new DirectedGraph<>();
        Vertex<Integer> vertex1 = graph.addVertex(1);
        Vertex<Integer> vertex2 = graph.addVertex(2);
        Vertex<Integer> vertex3 = graph.addVertex(3);
        Edge<Integer> edgeA = graph.addEdge(vertex1, vertex2);
        Edge<Integer> edgeB = graph.addEdge(vertex2, vertex3);
        List<Edge<Integer>> path = graph.getPath(vertex1, vertex3);
        assertThat(path, is(asList(edgeA, edgeB)));
    }

    @Test(expected = NoPathException.class)
    public void testGetPathThrowsExceptionIfVertexIsUnreachable(){
        DirectedGraph<Integer> graph = new DirectedGraph<>();
        Vertex<Integer> vertex1 = graph.addVertex(1);
        Vertex<Integer> vertex2 = graph.addVertex(2);
        Vertex<Integer> vertex3 = graph.addVertex(3);
        Vertex<Integer> vertex4 = graph.addVertex(4);
        Edge<Integer> edgeA = graph.addEdge(vertex1, vertex2);
        Edge<Integer> edgeB = graph.addEdge(vertex1, vertex3);
        Edge<Integer> edgeC = graph.addEdge(vertex2, vertex3);
        graph.getPath(vertex1, vertex4);
    }

    @Test(expected = NoPathException.class)
    public void testGetPathThrowsExceptionIfVertexSequenceIsUnreachable(){
        DirectedGraph<Integer> graph = new DirectedGraph<>();
        Vertex<Integer> vertex1 = graph.addVertex(1);
        Vertex<Integer> vertex2 = graph.addVertex(2);
        Vertex<Integer> vertex3 = graph.addVertex(3);
        Vertex<Integer> vertex4 = graph.addVertex(4);
        Edge<Integer> edgeA = graph.addEdge(vertex1, vertex2);
        Edge<Integer> edgeB = graph.addEdge(vertex3, vertex4);
        graph.getPath(vertex1, vertex4);
    }

    @Test
    public void testGetPathFindPathInGraphsWithLoops(){
        DirectedGraph<Integer> graph = new DirectedGraph<>();
        Vertex<Integer> vertex1 = graph.addVertex(1);
        Vertex<Integer> vertex2 = graph.addVertex(2);
        Vertex<Integer> vertex3 = graph.addVertex(3);
        Vertex<Integer> vertex4 = graph.addVertex(4);
        Vertex<Integer> vertex5 = graph.addVertex(5);
        Edge<Integer> edgeA = graph.addEdge(vertex1, vertex2);
        Edge<Integer> edgeB = graph.addEdge(vertex2, vertex3);
        Edge<Integer> edgeC = graph.addEdge(vertex3, vertex1);
        Edge<Integer> edgeD = graph.addEdge(vertex3, vertex4);
        Edge<Integer> edgeE = graph.addEdge(vertex4, vertex5);
        Edge<Integer> edgeF = graph.addEdge(vertex5, vertex1);
        Edge<Integer> edgeG = graph.addEdge(vertex1, vertex5);
        List<Edge<Integer>> path = graph.getPath(vertex1, vertex5);
        System.out.println(path);
        assertThat(path,
                anyOf(
                        is(asList(edgeA, edgeB, edgeD, edgeE)),
                        is(asList(edgeG))
                ));
    }

    @Test(expected = NoPathException.class)
    public void testGetPathForLineOf3ElementsWrongDirection(){
        DirectedGraph<Integer> graph = new DirectedGraph<>();
        Vertex<Integer> vertex1 = graph.addVertex(1);
        Vertex<Integer> vertex2 = graph.addVertex(2);
        Vertex<Integer> vertex3 = graph.addVertex(3);
        Edge<Integer> edgeA = graph.addEdge(vertex1, vertex2);
        Edge<Integer> edgeB = graph.addEdge(vertex3, vertex1);
        graph.getPath(vertex1, vertex3);
    }

    @Test
    public void testGetPathForLineOf3ElementsWithSelfLinks(){
        DirectedGraph<Integer> graph = new DirectedGraph<>();
        Vertex<Integer> vertex1 = graph.addVertex(1);
        Vertex<Integer> vertex2 = graph.addVertex(2);
        Vertex<Integer> vertex3 = graph.addVertex(3);
        Edge<Integer> edgeA = graph.addEdge(vertex1, vertex2);
        Edge<Integer> edgeB = graph.addEdge(vertex2, vertex3);
        Edge<Integer> edgeC = graph.addEdge(vertex2, vertex2);
        Edge<Integer> edgeD = graph.addEdge(vertex3, vertex3);
        Edge<Integer> edgeE = graph.addEdge(vertex1, vertex1);
        List<Edge<Integer>> path = graph.getPath(vertex1, vertex3);
        assertThat(path, is(asList(edgeA, edgeB)));
    }
}
