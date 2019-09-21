package com.grined.util.graph;

import com.grined.util.graph.data.Edge;
import com.grined.util.graph.data.Vertex;

import java.util.List;

/**
 * An object stores Graph
 * Graph is math model that contains Vertices and Edges
 * @param <V> - a type of data which is stored in each Vertex of the Graph
 */
public interface Graph<V> {
    /**
     * Creates a new Vertex in current Graph object
     * @param value data which is saved in Vertex
     * @return new created {@link Vertex} object
     */
    Vertex<V> addVertex(V value);

    /**
     * Added a new Edge connecting 2 {@link Vertex} in the Graph
     * @param from the 1st Vertex of the Edge
     * @param to the 2nd Vertex of the Edge
     * @return new created {@link Edge} object
     * @throws com.grined.util.graph.exception.NoSuchVertexException if Vertex is null or not in a Graph
     */
    Edge<V> addEdge(Vertex<V> from, Vertex<V> to);

    /**
     * Find a path between 2 passed Vertices
     * @param from start Vertex of the path
     * @param to end Vertex of tha path
     * @return {@link List} of {@link Edge Edges} in the path. If from and to are the same Vertex, returns an empty list.
     * @throws com.grined.util.graph.exception.NoPathException if 2 Vertices are not connected
     */
    List<Edge<V>> getPath(Vertex<V> from, Vertex<V> to);
}
