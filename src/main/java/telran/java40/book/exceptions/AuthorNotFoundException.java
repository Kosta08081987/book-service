package telran.java40.book.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class AuthorNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5883074161089097217L;

	public AuthorNotFoundException(String name) {
		super("Author " + name +" not found");
	}
	
	

}
