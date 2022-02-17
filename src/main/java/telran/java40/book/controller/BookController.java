package telran.java40.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import telran.java40.book.dto.AuthorDto;
import telran.java40.book.dto.BookDto;
import telran.java40.book.service.BookService;

@RestController
public class BookController {

	BookService bookService;
	
	@Autowired
	public BookController(BookService bookService) {
		this.bookService = bookService;
	}
	
	
	@PostMapping("/book")
	public  boolean addBook(@RequestBody BookDto bookDto) {
		return bookService.addBook(bookDto);
	}
	
	@GetMapping("/book/{isbn}")
	public BookDto findBookByIsbn(@PathVariable long isbn) {
		return bookService.findBookByIsbn(isbn);
	}
	
	@DeleteMapping("/book/{isbn}")
	public BookDto RemoveBook(@PathVariable long isbn) {
		return bookService.removeBook(isbn);
	}
	
	@PutMapping("/book/{isbn}/title/{title}")
	public BookDto updateBook(@PathVariable Long isbn, @PathVariable String title) {
		return bookService.updateBook(isbn, title);
	}
	
	@GetMapping("/books/author/{authorName}")
	public Iterable<BookDto> findBooksByAuthor(@PathVariable String authorName){
		return bookService.findBooksByAuthor(authorName);
	}
	
	@GetMapping("/books/publisher/{publisher}")
	public Iterable<BookDto> findBooksByPublisher(@PathVariable String publisher){
		return bookService.findBooksByPublisher(publisher);
	}
	
	@GetMapping("/authors/book/{isbn}")
	public Iterable<AuthorDto> findBookAuthors(@PathVariable Long isbn){
		return bookService.findBookAuthors(isbn);
	}
	
	@GetMapping("/publishers/author/{authorsName}")
	public Iterable<String> findPublishersByAuthor(@PathVariable String authorsName){
		return bookService.findPublishersByAuthor(authorsName);
	}
	
	
	@DeleteMapping("/author/{authorName}")
	public AuthorDto removeAuthor(@PathVariable String authorName) {
		return bookService.removeAuthor(authorName);
	}
	
	
}
