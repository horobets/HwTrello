package com.trello.api.models;

import com.trello.api.services.LabelColor;

public class Label {

    public String id;
    public String idBoard;
    public String name;
    public LabelColor color;

    public Label() {
    }

    public Label(LabelColor color) {
        this.color = color;
    }

}