package franc.book_store.service;

import franc.book_store.model.Book;

import java.util.List;

public interface IBookService {
    public List<Book> getAllBooks();
    public Book getBookById(int id);
    public void saveBook(Book book);
}
