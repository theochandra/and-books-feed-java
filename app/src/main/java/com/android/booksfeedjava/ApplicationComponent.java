package com.android.booksfeedjava;

import com.android.booksfeedjava.data.source.factory.BooksDataSourceFactory;
import com.android.booksfeedjava.domain.repository.BooksDataRepository;
import com.android.booksfeedjava.domain.repository.BooksRepository;
import com.android.booksfeedjava.domain.usecase.RetrieveBooksByQueryUseCase;

public class ApplicationComponent {

    private ApplicationComponent() { }

    private static BooksRepository provideBooksRepository() {
        BooksDataSourceFactory booksDataSourceFactory = new BooksDataSourceFactory();
        return new BooksDataRepository(booksDataSourceFactory.createCloudDataSource());
    }

    public static RetrieveBooksByQueryUseCase provideRetrieveBooksByQuery() {
        return new RetrieveBooksByQueryUseCase(provideBooksRepository());
    }

}
