package kuljeet.panesar.songFinder.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import kuljeet.panesar.songFinder.exception.ResourceNotFoundException;
import kuljeet.panesar.songFinder.exception.SongAlreadyExistsException;

@RestControllerAdvice
public class SongControllerAdvice {

	private final static Logger log = LoggerFactory.getLogger(SongControllerAdvice.class);

	@ExceptionHandler(value = ResourceNotFoundException.class)
	public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
		log.info("Entering handleResourceNotFoundException");
		log.error("ResourceNotFoundException occurred: {}", ex.getMessage());
		log.info("Exiting handleResourceNotFoundException");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}

	@ExceptionHandler(value = SongAlreadyExistsException.class)
	public ResponseEntity<String> handleUserAlreadyExistsException(SongAlreadyExistsException ex) {
		log.info("Entering handleSongAlreadyExistsException");
		log.error("SongAlreadyExistsException occurred: {}", ex.getMessage());
		log.info("Exiting handleSongAlreadyExistsException");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}
}
