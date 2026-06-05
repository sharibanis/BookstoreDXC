package db.sharib;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController("/naceservice")
public class RESTController {
	private final NACERepository repository;
	@Autowired
	private RESTService restService;
	private static final Logger log = LoggerFactory.getLogger(RESTController.class);
	
	public RESTController(NACERepository repository)  {
		this.repository = repository;
	}
	
	@GetMapping("/putnace")
	public ResponseEntity<String> putNaceDetails() {
		log.info("initDatabase");
		restService.initDatabase(repository);
		String msg = "Database initialised and loaded!";
		log.info(msg);
		return new ResponseEntity<String>(msg, HttpStatus.OK);
		
	}

	@GetMapping("/getnace/{orderNumber}")
	public ResponseEntity<String> getNACE(@RequestParam(value = "orderNumber") String orderNumber) {
		log.info("Getting NACE @orderNumber: " + orderNumber);
		Optional<NACE> optionalNACE = repository.findById(Long.valueOf(orderNumber));
		if (optionalNACE != null) {
			if (optionalNACE.isPresent()) {
				log.info("Received NACE @orderNumber: " + optionalNACE.get().toString());
				JSONObject jo = new JSONObject(optionalNACE.get());
				return new ResponseEntity<String>(jo.toString(), HttpStatus.OK);
			} else {
				String msg = "NACE @orderNumber: " + orderNumber + " NOT FOUND";
				log.info(msg);
				return new ResponseEntity<String>(msg, HttpStatus.NOT_FOUND);
			}
		} else {
			String msg = "NACE @orderNumber: " + orderNumber + " NOT FOUND";
			log.info(msg);
			return new ResponseEntity<String>(msg, HttpStatus.NOT_FOUND);
		}
	}
}
