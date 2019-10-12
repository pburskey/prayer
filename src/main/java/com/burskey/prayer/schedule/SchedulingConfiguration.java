package com.burskey.prayer.schedule;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SchedulingConfiguration {


    private Map<String, Configurable> map = null;

    public Map<String, Configurable> configuration() {
        if (this.map == null) {
            this.map = new HashMap<String, Configurable>();
        }

        return map;
    }


    public ConfigurableItem<Integer> getInteger(String name)
    {
        ConfigurableItem item = (ConfigurableItem) this.configuration().get(name);
        return item;
    }

    public ConfigurableItem<Boolean> getBoolean(String name)
    {
        ConfigurableItem item = (ConfigurableItem) this.configuration().get(name);
        return item;
    }

    public ConfigurableItem<String> getString(String name)
    {
        ConfigurableItem item = (ConfigurableItem) this.configuration().get(name);
        return item;
    }

    public ConfigurableItem get(String name)
    {
        ConfigurableItem item = (ConfigurableItem) this.configuration().get(name);
        return item;
    }

    public SchedulingConfiguration add(Configurable aConfigurable)
    {
        if (aConfigurable != null)
        {
            this.configuration().put(aConfigurable.name(), aConfigurable);
        }
        return this;
    }

    public Iterator<Configurable> iterator()
    {
        return this.configuration().values().iterator();
    }

    public SchedulingConfiguration merge(Configurable aConfigurable)
    {
        if (aConfigurable != null)
        {
            Configurable present = this.get(aConfigurable.name());
            if (present == null)
            {
                this.add(aConfigurable);
            }
            else
            {
                present.value(aConfigurable.value());
            }
        }
        return this;
    }




}
