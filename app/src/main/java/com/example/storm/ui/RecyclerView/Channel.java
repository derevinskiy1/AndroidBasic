package com.example.storm.ui.RecyclerView;

/**
 * Created by zhiwenyan on 12/17/16.
 */

public class Channel {
    private String name;

    public Channel() {

    }

    public Channel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "name='" + name + '\'' +
                '}';
    }
}
