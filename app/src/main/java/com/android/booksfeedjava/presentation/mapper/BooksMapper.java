package com.android.booksfeedjava.presentation.mapper;

import android.text.TextUtils;

import com.android.booksfeedjava.data.model.Item;
import com.android.booksfeedjava.data.network.response.BooksResponse;
import com.android.booksfeedjava.presentation.modelview.BooksModelView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BooksMapper {

    private static final String EMPTY_STRING_PLACEHOLDER = "-";

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
            StringBuilder authors = new StringBuilder();
            StringBuilder categories = new StringBuilder();

            if (book.getVolumeInfo().getAuthors() != null) {
                for (String author : book.getVolumeInfo().getAuthors())
                    authors.append(author).append(", ");
                if (authors.length() >= 2)
                    booksModelView.setAuthors(authors.substring(0, authors.length() - 2));
            } else {
                authors.append(EMPTY_STRING_PLACEHOLDER);
                booksModelView.setAuthors(authors.toString());
            }

            if (book.getVolumeInfo().getCategories() != null) {
                for (String category : book.getVolumeInfo().getCategories())
                    categories.append(category).append(", ");
                if (categories.length() >= 2)
                    booksModelView.setCategories(categories.substring(0, categories.length() - 2));
            } else {
                categories.append(EMPTY_STRING_PLACEHOLDER);
                booksModelView.setCategories(categories.toString());
            }

            booksModelView.setTitle(book.getVolumeInfo().getTitle());

            if (!TextUtils.isEmpty(book.getVolumeInfo().getDescription()))
                booksModelView.setDescription(book.getVolumeInfo().getDescription());
            else booksModelView.setDescription(EMPTY_STRING_PLACEHOLDER);

            booksModelView.setPublishedDate(book.getVolumeInfo().getPublishedDate());

            if (book.getVolumeInfo().getImageLinks() != null)
                booksModelView.setThumbnailUrl(book.getVolumeInfo().getImageLinks().getSmallThumbnail());

            booksModelView.setPageCount(book.getVolumeInfo().getPageCount());
            booksModelView.setRating(book.getVolumeInfo().getAverageRating());
        }
        return booksModelView;
    }

}
