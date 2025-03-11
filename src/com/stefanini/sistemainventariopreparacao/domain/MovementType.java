package com.stefanini.sistemainventariopreparacao.domain;

public enum MovementType {
    IN(1),
    OUT(0),
    ;

    private final int value;
    MovementType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
