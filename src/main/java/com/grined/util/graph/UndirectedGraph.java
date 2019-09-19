package com.grined.util.graph;

import com.grined.util.graph.data.Edge;
import com.grined.util.graph.data.Vertex;

import java.util.List;

public class UndirectedGraph<V> extends DirectedGraph<V> {

    @Override
    public Vertex<V> addVertex(Vertex<V> vertex) {
        return null;
    }

    @Override
    public Vertex<V> addVertex(V value) {
        return null;
    }

    @Override
    public Edge<V> addEdge(Vertex<V> from, Vertex<V> to) {
        return null;
    }

    @Override
    public Edge<V> addEdge(Edge<V> edge) {
        return null;
    }

    @Override
    public List<Edge<V>> getPath(Vertex<V> from, Vertex<V> to) {
        return null;
    }
}
