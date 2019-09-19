package com.grined.util.graph.exception;

import com.grined.util.graph.data.Vertex;

public class NoPathException extends IllegalArgumentException {
    public <V> NoPathException(Vertex<V> from, Vertex<V> to) {
        super("Vertices " + from + " and " + to + " are not connected");
    }
}
