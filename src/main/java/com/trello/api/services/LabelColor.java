package com.trello.api.services;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by horobets on Jul 04, 2019
 */
public enum LabelColor {
    NULL("null"),
    YELLOW("yellow"),
    PURPLE("purple"),
    BLUE("blue"),
    RED("red"),
    GREEN("green"),
    ORANGE("orange"),
    BLACK("black"),
    SKY("sky"),
    PINK("pink"),
    LIME("lime");

    private static final Map<String, LabelColor> lookup = new HashMap<String, LabelColor>();

    static {
        for (LabelColor labelColor : LabelColor.values()) {
            lookup.put(labelColor.toString(), labelColor);
        }
    }

    private String description;

    LabelColor(String description) {
        this.description = description;
    }

    //This method can be used for reverse lookup purpose
    public static LabelColor get(String description) {
        return lookup.get(description);
    }

    @Override
    public String toString() {
        return description;
    }

}
