package com.android.booksfeedjava.domain.usecase;

import com.android.booksfeedjava.domain.repository.BooksRepository;

import io.reactivex.Observable;

public class RetrieveBooksByQueryUseCase extends UseCase {

    private BooksRepository mBooksRepository;

    private String keyword;

    public RetrieveBooksByQueryUseCase(BooksRepository booksRepository) {
        mBooksRepository = booksRepository;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return mBooksRepository.retrieveBooksByQuery(keyword);
    }

}
