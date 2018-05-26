package com.flickr.assessment.flickrassessment.model;

import android.support.annotation.NonNull;

public class Items implements Comparable<Items> {

    private String tags;
    private String author;
    private String title;
    private String description;
    private String date_taken;
    private String link;
    private String author_id;
    private String published;
    private Media media;

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate_taken() {
        return date_taken;
    }

    public void setDate_taken(String date_taken) {
        this.date_taken = date_taken;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(String author_id) {
        this.author_id = author_id;
    }

    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    /**
     * Sort newest to oldest by the date taken.
     *
     * @param o
     * @return
     */
    @Override
    public int compareTo(@NonNull Items o) {
        return o.getDate_taken().compareTo(getDate_taken());
    }
}
