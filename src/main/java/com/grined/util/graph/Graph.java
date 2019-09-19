package com.grined.util.graph;

import com.grined.util.graph.data.Edge;
import com.grined.util.graph.data.Vertex;

import java.util.List;

public interface Graph<V> {
    Vertex<V> addVertex(V value);

    Edge<V> addEdge(Vertex<V> from, Vertex<V> to);

    List<Edge<V>> getPath(Vertex<V> from, Vertex<V> to);
}
