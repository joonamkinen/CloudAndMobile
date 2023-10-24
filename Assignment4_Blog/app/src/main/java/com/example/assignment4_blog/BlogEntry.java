package com.example.assignment4_blog;

import java.util.Date;

public class BlogEntry {
    private String username;
    private String comment;
    private Date timestamp;

    public BlogEntry(String username, String comment) {
        this.username = username;
        this.comment = comment;
        this.timestamp = new Date(); // Current timestamp when the entry is created.
    }

    public String getUsername() {
        return username;
    }

    public String getComment() {
        return comment;
    }

    public Date getTimestamp() {
        return timestamp;
    }
}
