package com.burskey.prayer.schedule;

public interface Configurable <T>
{
    public String name() ;
    public <T> Object  value() ;
    public void value(Object value);
}
