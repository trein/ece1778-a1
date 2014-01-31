package com.ackbox.a1;

public class Counter {

    private int internalCounter = 0;

    public int increment() {
        return ++this.internalCounter;
    }

    public boolean reset() {
        this.internalCounter = 0;
        return true;
    }

}
