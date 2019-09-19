package com.grined.util.graph;

import com.grined.util.graph.data.Edge;
import com.grined.util.graph.data.Vertex;
import com.grined.util.graph.exception.NoSuchVertexException;

import java.util.*;

public class DirectedGraph<V> implements Graph<V> {
    private Map<Vertex<V>, List<Edge<V>>> vertexEdges = new HashMap<>();

    public DirectedGraph() {
    }

    @Override
    public Vertex<V> addVertex(V value) {
        Vertex<V> newVertex = new Vertex<>(value);
        vertexEdges.put(newVertex, new ArrayList<>());
        return newVertex;
    }

    @Override
    public Edge<V> addEdge(Vertex<V> from, Vertex<V> to) {
        checkIfVertexExist(from);
        checkIfVertexExist(to);
        Edge<V> newEdge = new Edge<>(from, to, true);
        vertexEdges.get(from).add(newEdge);
        return newEdge;
    }


    @Override
    public List<Edge<V>> getPath(Vertex<V> from, Vertex<V> to) {
        checkIfVertexExist(from);
        checkIfVertexExist(to);
        return findPath(from, to, new HashSet<>(), new ArrayList<>());
    }

    private List<Edge<V>> findPath(Vertex<V> current,
                                   Vertex<V> to,
                                   Set<Vertex<V>> visitedVertices,
                                   List<Edge<V>> currentPath) {
        if (current == to) {
            return currentPath;
        }
        List<Edge<V>> edges = vertexEdges.get(current);
        if (edges == null) {
            throw new IllegalStateException("Vertex can't have null as edges list");
        }
        visitedVertices.add(current);
        return edges.stream()
                .filter(Objects::nonNull)
                .filter(edge -> !visitedVertices.contains(getOppositeEdgeSide(edge, current)))
                .map(edge -> {
                    currentPath.add(edge);
                    return findPath(getOppositeEdgeSide(edge, current), to, visitedVertices, currentPath);
                })
                .findAny()
                .orElse(null);
    }

    private Vertex<V> getOppositeEdgeSide(Edge<V> edge, Vertex<V> current) {
        return edge.getFrom() == current ? edge.getTo() : edge.getFrom();
    }

    private void checkIfVertexExist(Vertex<V> vertex) {
        if (!vertexEdges.containsKey(vertex)) {
            throw new NoSuchVertexException(vertex);
        }
    }

}
