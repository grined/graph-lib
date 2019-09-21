package com.grined.util.graph;

import com.grined.util.graph.data.Edge;
import com.grined.util.graph.data.Vertex;

public class DirectedGraph<V> extends AbstractGraph<V> {
    @Override
    public Edge<V> addEdge(Vertex<V> from, Vertex<V> to) {
        checkIfVertexExist(from);
        checkIfVertexExist(to);
        Edge<V> newEdge = new Edge<>(from, to);
        addEdgeToVertex(from, newEdge);
        return newEdge;
    }
}
