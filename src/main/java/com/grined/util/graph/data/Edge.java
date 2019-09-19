package com.grined.util.graph.data;

public class Edge<V> {
    private final Vertex<V> from;
    private final Vertex<V> to;
    private final boolean directed;

    public Edge(Vertex<V> from, Vertex<V> to) {
        this(from, to, false);
    }
    public Edge(Vertex<V> from, Vertex<V> to, boolean directed) {
        this.from = from;
        this.to = to;
        this.directed = directed;
    }

    public Vertex<V> getFrom() {
        return from;
    }

    public Vertex<V> getTo() {
        return to;
    }

    public boolean isDirected() {
        return directed;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "from=" + from +
                ", to=" + to +
                ", directed=" + directed +
                '}';
    }
}
