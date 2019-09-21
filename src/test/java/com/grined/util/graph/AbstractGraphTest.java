package com.grined.util.graph;


import com.grined.util.graph.data.Edge;
import com.grined.util.graph.data.Vertex;
import com.grined.util.graph.exception.NoPathException;
import com.grined.util.graph.exception.NoSuchVertexException;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public abstract class AbstractGraphTest {
    abstract Graph<Integer> buildGraph();

    @Test(expected = NoSuchVertexException.class)
    public void testThatNewEdgeCantBeAddedIfBothVertexIsNull() {
        Graph<Integer> graph = buildGraph();
        graph.addEdge(null, null);
    }

    @Test(expected = NoSuchVertexException.class)
    public void testThatNewEdgeCantBeAddedIfFromVertexIsNull() {
        Graph<Integer> graph = buildGraph();
        Vertex<Integer> vertex = graph.addVertex(1);
        graph.addEdge(null, vertex);
    }

    @Test(expected = NoSuchVertexException.class)
    public void testThatNewEdgeCantBeAddedIfAToVertexIsNull() {
        Graph<Integer> graph = buildGraph();
        Vertex<Integer> vertex = graph.addVertex(1);
        graph.addEdge(vertex, null);
    }
    @Test(expected = NoSuchVertexException.class)
    public void testThatNewEdgeCantBeAddedIfBothVertexIsNotInGraph() {
        Graph<Integer> graph = buildGraph();
        Vertex<Integer> outFromGraph1 = new Vertex<>(1);
        Vertex<Integer> outFromGraph2 = new Vertex<>(2);
        graph.addEdge(outFromGraph1, outFromGraph2);
    }

    @Test(expected = NoSuchVertexException.class)
    public void testThatNewEdgeCantBeAddedIfFromVertexIsNotInGraph() {
        Graph<Integer> graph = buildGraph();
        Vertex<Integer> vertex = graph.addVertex(1);
        Vertex<Integer> outFromGraph = new Vertex<>(2);
        graph.addEdge(outFromGraph, vertex);
    }

    @Test(expected = NoSuchVertexException.class)
    public void testThatNewEdgeCantBeAddedIfToVertexIsNotInGraph() {
        Graph<Integer> graph = buildGraph();
        Vertex<Integer> vertex = graph.addVertex(1);
        Vertex<Integer> outFromGraph = new Vertex<>(2);
        graph.addEdge(vertex, outFromGraph);
    }

    @Test
    public void testGetPathForDiamondStructureWith4Elements(){
        Graph<Integer> graph = buildGraph();
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
    public void testGetPathEmptyOnTheSameVertex(){
        Graph<Integer> graph = buildGraph();
        Vertex<Integer> vertex1 = graph.addVertex(1);
        Vertex<Integer> vertex2 = graph.addVertex(2);
        Edge<Integer> edgeA = graph.addEdge(vertex1, vertex2);

        List<Edge<Integer>> path = graph.getPath(vertex1, vertex1);
        assertTrue(path.isEmpty());
    }

    @Test
    public void testGetPathForLineOf3Elements(){
        Graph<Integer> graph = buildGraph();
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
        Graph<Integer> graph = buildGraph();
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
        Graph<Integer> graph = buildGraph();
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
        Graph<Integer> graph = buildGraph();
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
        assertThat(path,
                anyOf(
                        is(asList(edgeA, edgeB, edgeD, edgeE)),
                        is(asList(edgeG))
                ));
    }

    @Test
    public void testGetPathForLineOf3ElementsWithSelfLinks(){
        Graph<Integer> graph = buildGraph();
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

    @Test
    public void testGetPathLoopWithDirection(){
        Graph<Integer> graph = buildGraph();
        Vertex<Integer> vertex1 = graph.addVertex(1);
        Vertex<Integer> vertex2 = graph.addVertex(2);
        Vertex<Integer> vertex3 = graph.addVertex(3);
        Vertex<Integer> vertex4 = graph.addVertex(4);
        Edge<Integer> edgeA = graph.addEdge(vertex1, vertex2);
        Edge<Integer> edgeB = graph.addEdge(vertex2, vertex3);
        Edge<Integer> edgeC = graph.addEdge(vertex3, vertex1);
        Edge<Integer> edgeD = graph.addEdge(vertex4, vertex1);
        List<Edge<Integer>> path = graph.getPath(vertex4, vertex3);
        assertThat(path, is(asList(edgeD, edgeA, edgeB)));
    }
}
