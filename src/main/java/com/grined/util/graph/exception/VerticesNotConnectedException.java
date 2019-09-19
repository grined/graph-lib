package com.grined.util.graph.exception;

import com.grined.util.graph.data.Vertex;

public class VerticesNotConnectedException extends IllegalArgumentException {
    public <V> VerticesNotConnectedException(Vertex<V> from, Vertex<V> to) {
        super("Vertices " + from + " and " + to + " are not connected");
    }
}
