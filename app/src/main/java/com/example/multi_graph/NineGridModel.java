package com.example.multi_graph;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fei .
 * Created by Date 2019/7/23 16:07
 */

public class NineGridModel implements Serializable {
    private static final long serialVersionUID = 2189052605715370758L;

    public List<String> urlList = new ArrayList<>();

    public boolean isShowAll = false;

    private String name;
    private String time;
    private String content;

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public String getContent() {
        return content;
    }

    public void setName(String name) {

        this.name = name;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
