package com.xshaffter.marymod.updater;

import java.util.HashMap;

public class VersionHashMap extends HashMap<String, String> {

    public boolean equals(VersionHashMap otherMap) {
        if (otherMap == null) {
            return false;
        }
        if (otherMap.size() != size()) {
            return false;
        }
        for (Entry<String, String> e : entrySet()) {
            String key = e.getKey();
            String value = e.getValue();
            if (value == null) {
                if (!(otherMap.get(key) == null && otherMap.containsKey(key)))
                    return false;
            } else {
                if (!value.equals(otherMap.get(key)))
                    return false;
            }
        }
        return true;
    }
}
