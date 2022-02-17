package telran.java40.book.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class PublisherNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -35417892174419982L;

	public PublisherNotFoundException(String name) {
		super("Publisher " + name + " not found");
		
	}
	
	

}
