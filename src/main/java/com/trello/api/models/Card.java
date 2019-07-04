package com.trello.api.models;

public class Card {

    public String id;
    public Badges badges;
    public Object[] checkItemStates;
    public boolean closed;
    public boolean dueComplete;
    public String dateLastActivity;
    public String desc;
    public Object descData;
    public Object due;
    public Object email;
    public String idBoard;
    public Object[] idChecklists;
    public String idList;
    public String[] idMembers;
    public Object[] idMembersVoted;
    public int idShort;
    public String idAttachmentCover;
    public boolean manualCoverAttachment;
    public Label[] labels;
    public String[] idLabels;
    public String name;
    public int pos;
    public String shortLink;
    public String shortUrl;
    public boolean subscribed;
    public String url;
    public String address;
    public String locationName;
    public Coordinates coordinates;

/*
    public String id;
    public String desc;
    public String idBoard;
    public String idList;
    public String name;
    public String url;*/

    public Card() {
    }

    public Card(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "Card{" +
                "id='" + id + '\'' +
                ", desc='" + desc + '\'' +
                ", idBoard='" + idBoard + '\'' +
                ", idList='" + idList + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

}