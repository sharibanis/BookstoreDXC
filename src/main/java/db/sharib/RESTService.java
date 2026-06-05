package db.sharib;

import java.io.File;
import java.io.FileReader;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Optional;
import java.util.Properties;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.opencsv.CSVReader;

@Service
public class RESTService {
	  private static final Logger log = LoggerFactory.getLogger(RESTService.class);
		
		@Value("${NACE.csv.filePathAndName}")
		String filePathAndName;
	
		void initDatabase(NACERepository repository) {
    	log.info("initDatabase");
    	log.info("From file: " + filePathAndName);
			  try {
				File file = new File(filePathAndName);
				FileReader filereader = new FileReader(file);
				CSVReader csvReader = new CSVReader(filereader);
				  String[] line;
				  String lineString = "";
				  int insertCount = 0;
	   			  log.info("Uploading file: " + file.getCanonicalPath());
	   			  int maxStringLength = 255;
	   			  NACE nace = new NACE();
	   			  csvReader.readNext(); //skip first line (header)
				    while ((line = csvReader.readNext()) != null) {
				    	for (String cell : line) {
				    		lineString += cell + ", ";
			            }
				    	//log.info("lineString: " + lineString);
				    	if (lineString.trim() != "" && lineString.trim().startsWith("39")) {
				    		nace = new NACE();
				    		long OrderNumber = Long.valueOf(line[0]);
				    		nace.setOrderNumber(OrderNumber);
					    	if (line[1].length() > maxStringLength ) {
					    		nace.setLevel(line[1].substring(0, maxStringLength - 1));
					    	} else {
					    		nace.setLevel(line[1]);
					    	}
					    	if (line[2].length() > maxStringLength ) {
					    		nace.setCode(line[2].substring(0, maxStringLength - 1));
					    	} else {
					    		nace.setCode(line[2]);
					    	}
					    	if (line[3].length() > maxStringLength ) {
					    		nace.setParent(line[3].substring(0, maxStringLength - 1));
					    	} else {
					    		nace.setParent(line[3]);
					    	}
					    	if (line[4].length() > maxStringLength ) {
					    		nace.setDescription(line[4].substring(0, maxStringLength - 1));
					    	} else {
					    		nace.setDescription(line[4]);
					    	}
					    	if (line[5].length() > maxStringLength ) {
					    		nace.setThisItemIncludes(line[5].substring(0, maxStringLength - 1));
					    	} else {
					    		nace.setThisItemIncludes(line[5]);
					    	}
					    	if (line[6].length() > maxStringLength ) {
					    		nace.setThisItemAlsoIncludes(line[6].substring(0, maxStringLength - 1));
					    	} else {
					    		nace.setThisItemAlsoIncludes(line[6]);
					    	}
					    	if (line[7].length() > maxStringLength ) {
					    		nace.setRulings(line[7].substring(0, maxStringLength - 1));
					    	} else {
					    		nace.setRulings(line[7]);
					    	}
					    	if (line[8].length() > maxStringLength ) {
					    		nace.setThisItemExcludes(line[8].substring(0, maxStringLength - 1));
					    	} else {
					    		nace.setThisItemExcludes(line[8]);
					    	}
					    	if (line[9].length() > maxStringLength ) {
					    		nace.setReferenceToISICRev4(line[9].substring(0, maxStringLength - 1));
					    	} else {
					    		nace.setReferenceToISICRev4(line[9]);
					    	}
				    	}
				    	log.debug("nace.toString(): "+nace.toString());
				    	repository.save(nace);
				    	insertCount++;
				    }
			    	log.info("insertCount: " + insertCount);
			    	log.info("Database initialised and loaded!");
			    	//Optional<NACE> optionalNACE = repository.findById(398481L);
			    	//log.info("repository.findById(398481L): " + optionalNACE.get());
			  }	catch(Exception exception){  
				  log.info(exception.toString());
				  exception.printStackTrace();
			  }
	  }
}
