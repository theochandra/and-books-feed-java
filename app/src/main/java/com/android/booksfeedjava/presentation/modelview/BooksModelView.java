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

    private String title;

    private List<String> authors;

    private String thumbnailUrl;

    private String description;

    private float rating;

    public BooksModelView() { }

    protected BooksModelView(Parcel source) {
        title           = source.readString();
        thumbnailUrl    = source.readString();
        description     = source.readString();
        rating          = source.readFloat();
        authors         = new ArrayList<>();
        source.readList(authors, null);
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

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
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
        parcel.writeList(authors);
    }

}
