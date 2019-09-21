package com.grined.util.graph;

import com.grined.util.graph.data.Edge;
import com.grined.util.graph.data.Vertex;

public class UndirectedGraph<V> extends AbstractGraph<V> {
    @Override
    public Edge<V> addEdge(Vertex<V> first, Vertex<V> second) {
        checkIfVertexExist(first);
        checkIfVertexExist(second);
        Edge<V> newEdge = new Edge<>(first, second);
        addEdgeToVertex(first, newEdge);
        addEdgeToVertex(second, newEdge);
        return newEdge;
    }
}
