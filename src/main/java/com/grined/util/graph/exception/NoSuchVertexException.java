package com.grined.util.graph.exception;

import com.grined.util.graph.data.Vertex;

public class NoSuchVertexException extends IllegalArgumentException {
    public <V> NoSuchVertexException(Vertex<V> vertex) {
        super("Vertex:  " + vertex + " hasn't been added to the Graph before operation");
    }
}
