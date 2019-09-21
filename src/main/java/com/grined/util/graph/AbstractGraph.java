package com.grined.util.graph;

import com.grined.util.graph.data.Edge;
import com.grined.util.graph.data.Vertex;
import com.grined.util.graph.exception.NoPathException;
import com.grined.util.graph.exception.NoSuchVertexException;

import java.util.*;

public abstract class AbstractGraph<V> implements Graph<V> {
    private Map<Vertex<V>, List<Edge<V>>> vertexEdges = new HashMap<>();

    @Override
    public Vertex<V> addVertex(V value) {
        Vertex<V> newVertex = new Vertex<>(value);
        vertexEdges.put(newVertex, new ArrayList<>());
        return newVertex;
    }

    @Override
    public List<Edge<V>> getPath(Vertex<V> from, Vertex<V> to) {
        checkIfVertexExist(from);
        checkIfVertexExist(to);
        List<Edge<V>> path = findPath(from, to, new HashSet<>(), new ArrayList<>());
        if (path == null) {
            throw new NoPathException(from, to);
        }
        return path;
    }

    void checkIfVertexExist(Vertex<V> vertex) {
        if (!vertexEdges.containsKey(vertex)) {
            throw new NoSuchVertexException(vertex);
        }
    }

    void addEdgeToVertex(Vertex<V> vertex, Edge<V> edge) {
        if (vertex == null || edge == null) {
            throw new IllegalStateException("vertex or edge can't be null");
        }
        List<Edge<V>> edges = vertexEdges.putIfAbsent(vertex, new ArrayList<>());
        edges.add(edge);
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
        Set<Vertex<V>> newVisitedVertices = new HashSet<>(visitedVertices);
        newVisitedVertices.add(current);

        return edges.stream()
                .filter(Objects::nonNull)
                .filter(edge -> !newVisitedVertices.contains(getOppositeVertexOnEdge(edge, current)))
                .map(edge -> {
                    List<Edge<V>> newCurrentPath = new ArrayList<>(currentPath);
                    newCurrentPath.add(edge);
                    return findPath(getOppositeVertexOnEdge(edge, current), to, newVisitedVertices, newCurrentPath);
                })
                .filter(Objects::nonNull)
                .findAny()
                .orElse(null);
    }

    private Vertex<V> getOppositeVertexOnEdge(Edge<V> edge, Vertex<V> current) {
        return edge.getFrom() == current ? edge.getTo() : edge.getFrom();
    }
}
