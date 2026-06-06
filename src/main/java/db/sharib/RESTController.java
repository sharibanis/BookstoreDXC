package db.sharib;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Optional;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController("/bookstore")
public class RESTController {
	private final AuthorsRepository authRepository;
	private final BooksRepository booksRepository;
	//@Autowired
	//private RESTService restService;
	private static final Logger log = LoggerFactory.getLogger(RESTController.class);
	
	public RESTController(AuthorsRepository authRepository, BooksRepository booksRepository)  {
		this.authRepository = authRepository;
		this.booksRepository = booksRepository;
	}
	
	//Add a new book
	@PostMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	Books addBook(@RequestBody Books newBook) {
		return booksRepository.save(newBook);
	}

	//Update an existing book
	@PutMapping("/{ISBN}")
	@PreAuthorize("hasAuthority('ADMIN')")
	Books updateBook(@RequestBody Books newBook, @PathVariable Long ISBN) {
		return booksRepository.findById(ISBN)
			.map(book -> {
				book.setTitle(newBook.getTitle());
				book.setAuthors(newBook.getAuthors());
				book.setYearPublished(newBook.getYearPublished());
				book.setPrice(newBook.getPrice());
				book.setGenre(newBook.getGenre());
				return booksRepository.save(book);
			})
			.orElseGet(() -> {
				return booksRepository.save(newBook);
			});
	}

	//Find books either by title or author name or both 
	@GetMapping("/find")
	Books findBook(@RequestParam(required = false) String title, 
					@RequestParam(required = false) String author) {
		
		return booksRepository.findByTitleOrAuthor(title, author);
	}

	//Delete a book by ISBN
	@DeleteMapping("/{ISBN}")
	@PreAuthorize("hasAuthority('ADMIN')")
    public void delete(@PathVariable Long ISBN) {
        booksRepository.deleteById(ISBN);
    }
}
