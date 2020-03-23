package com.ofek.photosexam.objects.uiobjects;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Nullable;

import java.util.UUID;

public class UiPhoto implements Parcelable {
    private String guruPhotoId;
    private final UUID uuid;
    private int views;
    private int likes;
    private long uploadDate;
    private double ratio;
    private int maxHeight;
    private int maxWidth;
    private boolean favorite;

    public UiPhoto(UUID uuid) {
        this.uuid = uuid;
    }


    public UiPhoto() {
        uuid = UUID.randomUUID();
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getGuruPhotoId() {
        return guruPhotoId;
    }

    public void setGuruPhotoId(String guruPhotoId) {
        this.guruPhotoId = guruPhotoId;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public long getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(long uploadDate) {
        this.uploadDate = uploadDate;
    }

    public double getRatio() {
        return ratio;
    }

    public void setRatio(double ratio) {
        this.ratio = ratio;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public void setMaxHeight(int maxHeight) {
        this.maxHeight = maxHeight;
    }

    public int getMaxWidth() {
        return maxWidth;
    }

    public void setMaxWidth(int maxWidth) {
        this.maxWidth = maxWidth;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }


    @Override
    public boolean equals(@Nullable Object obj) {
        return obj instanceof UiPhoto && this.uuid.equals(((UiPhoto) obj).uuid);
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.guruPhotoId);
        dest.writeSerializable(this.uuid);
        dest.writeInt(this.views);
        dest.writeInt(this.likes);
        dest.writeLong(this.uploadDate);
        dest.writeDouble(this.ratio);
        dest.writeInt(this.maxHeight);
        dest.writeInt(this.maxWidth);
        dest.writeByte(this.favorite ? (byte) 1 : (byte) 0);
    }

    protected UiPhoto(Parcel in) {
        this.guruPhotoId = in.readString();
        this.uuid = (UUID) in.readSerializable();
        this.views = in.readInt();
        this.likes = in.readInt();
        this.uploadDate = in.readLong();
        this.ratio = in.readDouble();
        this.maxHeight = in.readInt();
        this.maxWidth = in.readInt();
        this.favorite = in.readByte() != 0;
    }

    public static final Parcelable.Creator<UiPhoto> CREATOR = new Parcelable.Creator<UiPhoto>() {
        @Override
        public UiPhoto createFromParcel(Parcel source) {
            return new UiPhoto(source);
        }

        @Override
        public UiPhoto[] newArray(int size) {
            return new UiPhoto[size];
        }
    };
}
