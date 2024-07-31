package franc.book_store.service;

import franc.book_store.model.Book;
import franc.book_store.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService implements IBookService {
    @Autowired
    private BookRepo bookRepo;

    @Override
    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }

    @Override
    public Book getBookById(int id) {
        return bookRepo.findById(id).orElse(null);
    }

    @Override
    public void saveBook(Book book) {
        bookRepo.save(book);
    }
}
