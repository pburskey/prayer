package com.burskey.prayer.schedule;

public class  ConfigurableItem <T> extends AbstractConfigurable implements Configurable {

    private T value = null;


    public ConfigurableItem(String name, T value) {
        super(name);
        this.value = value;
    }

    @Override
    public T value() {
        return this.value;
    }


    @Override
    public void value(Object aValue) {
        this.value = value;
    }
}