package com.idogs.budejie.budejie.model;

/**
 * Created by idogs
 * 事件
 */
public class Notice {
    public int type;
    public Object content;

    public Notice(int type) {
        this.type = type;
    }

    public Notice(int type, Object content) {
        this.type = type;
        this.content = content;
    }

    public Notice() {
    }
}
