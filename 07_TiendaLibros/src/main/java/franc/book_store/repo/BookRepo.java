package franc.book_store.repo;

import franc.book_store.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepo extends JpaRepository<Book, Integer> {
}
