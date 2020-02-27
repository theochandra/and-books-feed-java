package com.android.booksfeedjava.domain.usecase;

import com.android.booksfeedjava.domain.repository.BooksRepository;

import io.reactivex.Observable;

public class RetrieveBooksByQueryUseCase extends UseCase {

    private BooksRepository mBooksRepository;

    private String keyword;

    private String printType;

    private int startIndex;

    private int maxResults;

    public RetrieveBooksByQueryUseCase(BooksRepository booksRepository) {
        mBooksRepository = booksRepository;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void setPrintType(String printType) {
        this.printType = printType;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public void setMaxResults(int maxResults) {
        this.maxResults = maxResults;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return mBooksRepository.retrieveBooksByQuery(keyword, printType, startIndex, maxResults);
    }

}
