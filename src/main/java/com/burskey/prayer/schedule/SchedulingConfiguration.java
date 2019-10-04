package com.burskey.prayer.schedule;

import java.util.HashMap;
import java.util.Map;

public class SchedulingConfiguration {


    private Map<String, Configurable> map = null;

    public Map<String, Configurable> configuration() {
        if (this.map == null) {
            this.map = new HashMap<String, Configurable>();
        }

        return map;

    }


}
