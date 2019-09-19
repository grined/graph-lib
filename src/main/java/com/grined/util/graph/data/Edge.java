package com.grined.util.graph.data;

public class Edge<V> {
    private final Vertex<V> from;
    private final Vertex<V> to;

    public Edge(Vertex<V> from, Vertex<V> to) {
        this.from = from;
        this.to = to;
    }

    public Vertex<V> getFrom() {
        return from;
    }

    public Vertex<V> getTo() {
        return to;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "from=" + from +
                ", to=" + to +
                '}';
    }
}
