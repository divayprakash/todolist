package io.github.todolist.todolist;

import java.io.Serializable;

/**
 * Created by Divay Prakash on 09-Nov-16.
 */
public class TodoItem implements Serializable{
    private String title;
    private String desc;

    public TodoItem() {}

    public TodoItem(String title, String desc) {
        this.title = title;
        this.desc = desc;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
