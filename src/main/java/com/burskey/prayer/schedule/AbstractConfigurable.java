package com.burskey.prayer.schedule;

public abstract class AbstractConfigurable implements Configurable {

    private String name = null;

    @Override
    public String name() {
        return this.name;
    }


    public AbstractConfigurable(String name) {
        this.name = name;
    }
}
