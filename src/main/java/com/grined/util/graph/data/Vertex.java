package com.grined.util.graph.data;

public class Vertex<V> {
    private final V value;

    public Vertex(V value) {
        this.value = value;
    }

    public V getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "value=" + value +
                '}';
    }
}
