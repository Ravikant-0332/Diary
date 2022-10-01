package com.r135714160332.diary.Models;

public class Entry {
    String timeStamp;
    String content;

    public Entry() {
    }

    public Entry(String timeStamp, String content) {
        this.timeStamp = timeStamp;
        this.content = content;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
