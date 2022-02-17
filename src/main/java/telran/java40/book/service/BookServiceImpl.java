package telran.java40.book.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telran.java40.book.dao.AuthorRepository;
import telran.java40.book.dao.BookRepository;
import telran.java40.book.dao.PublisherRepository;
import telran.java40.book.dto.AuthorDto;
import telran.java40.book.dto.BookDto;
import telran.java40.book.exceptions.AuthorNotFoundException;
import telran.java40.book.exceptions.BookNotFoundException;
import telran.java40.book.exceptions.PublisherNotFoundException;
import telran.java40.book.model.Author;
import telran.java40.book.model.Book;
import telran.java40.book.model.Publisher;


@Service
public class BookServiceImpl implements BookService {

	BookRepository bookRepository;
	AuthorRepository authorRepository;
	PublisherRepository publisherRepository;
	ModelMapper modelMapper;
	
	@Autowired
	public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository,
			PublisherRepository publisherRepository, ModelMapper modelMapper) {
		this.bookRepository = bookRepository;
		this.authorRepository = authorRepository;
		this.publisherRepository = publisherRepository;
		this.modelMapper = modelMapper;
	}


	@Override
	public boolean addBook(BookDto bookDto) {
		if(bookRepository.existsById(bookDto.getIsbn())) {
			return false;
		}
		//Publishers
		Publisher publisher = publisherRepository.findById(bookDto.getPublisher())
													.orElse(publisherRepository.save(new Publisher(bookDto.getPublisher())));
		//Authors
		Set<Author> authors = bookDto.getAuthors().stream().map(ad -> authorRepository.findById(ad.getName()).orElse(authorRepository.save(new Author(ad.getName(),ad.getBirthDate())))).collect(Collectors.toSet());
		Book book = new Book(bookDto.getIsbn(),bookDto.getTitle(),authors,publisher );
		bookRepository.save(book);
		return true;
	}


	@Override
	public BookDto findBookByIsbn(Long isbn) {
		Book book = bookRepository.findById(isbn).orElse(null);
		if(book == null) {
			throw new BookNotFoundException(isbn);
		}
		
		return modelMapper.map(book,BookDto.class);
	}


	@Override
	public BookDto removeBook(Long isbn) {
		Book book = bookRepository.findById(isbn).orElse(null);
		if(book == null) {
			throw new BookNotFoundException(isbn);
		}
		bookRepository.deleteById(isbn);
		return bookToBookDto(book);
	}


	@Override
	public BookDto updateBook(Long isbn, String title) {
		Book book = bookRepository.findById(isbn).orElse(null);
		if(book == null) {
			throw new BookNotFoundException(isbn);
		}
		book.setTitle(title);
		return modelMapper.map(bookRepository.save(book),BookDto.class);
	}


	@Override
	public Iterable<BookDto> findBooksByAuthor(String authorName) {
		Author author = authorRepository.findById(authorName).orElse(null);
		if(author == null) {
			throw new AuthorNotFoundException(authorName);
		}
		return bookRepository.findByAuthorsName(authorName).stream().map(b -> modelMapper.map(b,BookDto.class)).collect(Collectors.toList());
	}


	@Override
	public Iterable<BookDto> findBooksByPublisher(String publisher) {
		Publisher publisher1 = publisherRepository.findById(publisher).orElse(null);
		if(publisher1 == null) {
			throw new PublisherNotFoundException(publisher);
		}
		return bookRepository.findByPublisherPublisherName(publisher).stream().map(b -> modelMapper.map(b,BookDto.class)).collect(Collectors.toList());
	}


	@Override
	public Iterable<AuthorDto> findBookAuthors(Long isbn) {
		Book book = bookRepository.findById(isbn).orElse(null);
		if(book == null) {
			throw new BookNotFoundException(isbn);
		}
		
		return book.getAuthors().stream().map(a -> modelMapper.map(a, AuthorDto.class)).collect(Collectors.toList());
	}


	@Override
	public List<String> findPublishersByAuthor(String authorsName) {
		Author author = authorRepository.findById(authorsName).orElse(null);
		if(author == null) {
			throw new AuthorNotFoundException(authorsName);
		}
		
		return bookRepository.findByAuthorsName(authorsName).stream().map(b -> b.getPublisher().getPublisherName()).distinct().collect(Collectors.toList());
	}


	@Override
	public AuthorDto removeAuthor(String authorName) {
		Author author = authorRepository.findById(authorName).orElse(null);
		if(author == null) {
			throw new AuthorNotFoundException(authorName);
		}
		authorRepository.delete(author);
		return modelMapper.map(author, AuthorDto.class);
	}
	
	
	
	
	
	
	private BookDto bookToBookDto(Book book) {
		Set<AuthorDto> authors = book.getAuthors().stream()
				.map(this::authorToAuthorDto)
				.collect(Collectors.toSet());
		return new BookDto(book.getIsbn(), book.getTitle(), authors, book.getPublisher().getPublisherName());
	}
	
	private AuthorDto authorToAuthorDto(Author author) {
		return new AuthorDto(author.getName(), author.getBirthDate());
	}

}
