package com.grined.util.graph;

import com.grined.util.graph.data.Edge;
import com.grined.util.graph.data.Vertex;

public class UndirectedGraph<V> extends DirectedGraph<V> {
    @Override
    public Edge<V> addEdge(Vertex<V> from, Vertex<V> to) {
        Edge<V> addedEdge = super.addEdge(from, to);
        getVertexEdges().get(to).add(addedEdge);
        return addedEdge;
    }
}
