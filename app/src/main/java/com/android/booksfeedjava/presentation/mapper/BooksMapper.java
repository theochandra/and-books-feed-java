package com.android.booksfeedjava.presentation.mapper;

import com.android.booksfeedjava.data.model.Item;
import com.android.booksfeedjava.data.network.response.BooksResponse;
import com.android.booksfeedjava.presentation.modelview.BooksModelView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BooksMapper {

    public static List<BooksModelView> transform(BooksResponse response) {
        List<BooksModelView> booksModelViewList;
        if (response != null) {
            booksModelViewList = new ArrayList<>();
            for (Item book : response.getItems()) {
                booksModelViewList.add(transform(book));
            }
        } else {
            booksModelViewList = Collections.emptyList();
        }
        return booksModelViewList;
    }

    private static BooksModelView transform(Item book) {
        BooksModelView booksModelView = null;
        if (book != null) {
            booksModelView = new BooksModelView();
            booksModelView.setCategories(book.getVolumeInfo().getCategories());
            booksModelView.setTitle(book.getVolumeInfo().getTitle());
            if (book.getVolumeInfo().getImageLinks() != null)
                booksModelView.setThumbnailUrl(book.getVolumeInfo().getImageLinks().getSmallThumbnail());
            booksModelView.setDescription(book.getVolumeInfo().getDescription());
            booksModelView.setPublishedDate(book.getVolumeInfo().getPublishedDate());
            booksModelView.setPageCount(book.getVolumeInfo().getPageCount());
            booksModelView.setRating(book.getVolumeInfo().getAverageRating());
            booksModelView.setAuthors(book.getVolumeInfo().getAuthors());
        }
        return booksModelView;
    }

}
