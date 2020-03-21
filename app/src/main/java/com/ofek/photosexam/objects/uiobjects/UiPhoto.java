package com.ofek.photosexam.objects.uiobjects;

import androidx.annotation.Nullable;

public class UiPhoto {
    private String id;
    private int views;
    private int likes;
    private long uploadDate;
    private double ratio;
    private int maxHeight;
    private int maxWidth;

    public String getId() {
        return id;
    }

    public UiPhoto setId(String id) {
        this.id = id;
        return this;
    }

    public int getViews() {
        return views;
    }

    public UiPhoto setViews(int views) {
        this.views = views;
        return this;
    }

    public int getLikes() {
        return likes;
    }

    public UiPhoto setLikes(int likes) {
        this.likes = likes;
        return this;
    }

    public long getUploadDate() {
        return uploadDate;
    }

    public UiPhoto setUploadDate(long uploadDate) {
        this.uploadDate = uploadDate;
        return this;
    }

    public double getRatio() {
        return ratio;
    }

    public UiPhoto setRatio(double ratio) {
        this.ratio = ratio;
        return this;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public UiPhoto setMaxHeight(int maxHeight) {
        this.maxHeight = maxHeight;
        return this;
    }

    public int getMaxWidth() {
        return maxWidth;
    }

    public UiPhoto setMaxWidth(int maxWidth) {
        this.maxWidth = maxWidth;
        return this;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return obj instanceof UiPhoto && this.id.equals(((UiPhoto) obj).id);
    }
}
