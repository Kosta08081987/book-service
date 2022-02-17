package telran.java40.book.service;

import telran.java40.book.dto.AuthorDto;
import telran.java40.book.dto.BookDto;

public interface BookService {

	boolean addBook(BookDto bookDto);
	
	BookDto findBookByIsbn(Long isbn);
	
	BookDto removeBook(Long isbn);
	
	BookDto updateBook(Long isbn, String title);
	
	Iterable<BookDto> findBooksByAuthor(String authorName);
	
	Iterable<BookDto> findBooksByPublisher(String publisher);
	
	Iterable<AuthorDto> findBookAuthors(Long isbn);
	
	Iterable<String> findPublishersByAuthor(String authorsName);
	
	AuthorDto removeAuthor(String authorName);
}
