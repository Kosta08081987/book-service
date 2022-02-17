package telran.java40.book.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.java40.book.model.Book;

public interface BookRepository extends JpaRepository<Book,Long> {
	List<Book> findByAuthorsName(String name);
	
	List<Book> findByPublisherPublisherName(String name);
}
