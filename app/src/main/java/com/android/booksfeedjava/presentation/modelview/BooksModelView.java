package com.android.booksfeedjava.presentation.modelview;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class BooksModelView implements Parcelable {

    public static final Creator<BooksModelView> CREATOR = new Creator<BooksModelView>() {
        @Override
        public BooksModelView createFromParcel(Parcel parcel) {
            return new BooksModelView(parcel);
        }

        @Override
        public BooksModelView[] newArray(int i) {
            return new BooksModelView[i];
        }
    };

    private String categories;

    private String title;

    private String authors;

    private String thumbnailUrl;

    private String description;

    private float rating;

    private String publishedDate;

    private int pageCount;

    public BooksModelView() { }

    protected BooksModelView(Parcel source) {
        title           = source.readString();
        thumbnailUrl    = source.readString();
        description     = source.readString();
        rating          = source.readFloat();
        publishedDate   = source.readString();
        pageCount       = source.readInt();
        authors         = source.readString();
        categories      = source.readString();
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(thumbnailUrl);
        parcel.writeString(description);
        parcel.writeFloat(rating);
        parcel.writeString(publishedDate);
        parcel.writeInt(pageCount);
        parcel.writeString(authors);
        parcel.writeString(categories);
    }

}
