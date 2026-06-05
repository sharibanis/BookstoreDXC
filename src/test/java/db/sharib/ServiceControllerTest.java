package db.sharib;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import com.opencsv.CSVReader;

public class ServiceControllerTest {
	private static final Logger log = LoggerFactory.getLogger(ServiceControllerTest.class);
	// JDBC driver name and database URL 
	static final String JDBC_DRIVER = "org.h2.Driver";
	static final String DB_URL = "jdbc:h2:~/test";
   
	//  Database credentials 
	static final String USER = "sa"; 
	static final String PASS = ""; 
	static Connection conn = null; 
    
	@BeforeAll
	public static void setUp() throws Exception {
		log.info("START UP - creating DB connection");
		Class.forName(JDBC_DRIVER);
		conn = DriverManager.getConnection(DB_URL,USER,PASS);
		Statement stmt = conn.createStatement();
		log.info("creating TABLE nace...");
		String sql = "CREATE TABLE IF NOT EXISTS nace ( "
				+ "	OrderNumber bigint not null, "
				+ "	Code varchar(255), "
				+ "	Description varchar(255), "
				+ "	Level varchar(255), "
				+ "	Parent varchar(255), "
				+ "	ReferenceToISICRev4 varchar(255), " 
				+ "	Rulings varchar(255), "
				+ "	ThisItemAlsoIncludes varchar(255), "
				+ "	ThisItemExcludes varchar(255), "
				+ "	ThisItemIncludes varchar(255), "
				+ "	primary key (OrderNumber)"
				+ ")";
		stmt.executeUpdate(sql);
		log.info("created TABLE nace");
    	sql = "INSERT INTO nace (OrderNumber, Level, Code, Parent, Description, "
    			+ "ThisItemIncludes, ThisItemAlsoIncludes, Rulings, ThisItemExcludes, "
    			+ "ReferenceToISICRev4) VALUES (?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		String filePathAndName = "C:\\git\\dbRESTService3\\src\\main\\resources\\NACE.csv";
		File file = new File(filePathAndName);
		FileReader filereader = new FileReader(file);
		  try(CSVReader csvReader = new CSVReader(filereader)) {
			  String[] line;
			  String lineString = "";
			  int[] insertCount;
   			  log.info("Uploading file: " + file.getCanonicalPath());
   			  int maxStringLength = 255;
   			  csvReader.readNext(); //skip first line (header)
			    while ((line = csvReader.readNext()) != null) {
			    	for (String cell : line) {
			    		lineString += cell + ", ";
		            }
			    	//log.info("lineString: " + lineString);
			    	if (lineString.trim() != "" && lineString.trim().startsWith("39")) {
				    	preparedStatement.setLong(1, Long.valueOf(line[0]));
				    	if (line[1].length() > maxStringLength ) {
					    	preparedStatement.setString(2, line[1].substring(0, maxStringLength - 1));
				    	} else {
					    	preparedStatement.setString(2, line[1]);
				    	}
				    	if (line[2].length() > maxStringLength ) {
					    	preparedStatement.setString(3, line[2].substring(0, maxStringLength - 1));
				    	} else {
					    	preparedStatement.setString(3, line[2]);
				    	}
				    	if (line[3].length() > maxStringLength ) {
					    	preparedStatement.setString(4, line[3].substring(0, maxStringLength - 1));
				    	} else {
					    	preparedStatement.setString(4, line[3]);
				    	}
				    	if (line[4].length() > maxStringLength ) {
					    	preparedStatement.setString(5, line[4].substring(0, maxStringLength - 1));
				    	} else {
					    	preparedStatement.setString(5, line[4]);
				    	}
				    	if (line[5].length() > maxStringLength ) {
					    	preparedStatement.setString(6, line[5].substring(0, maxStringLength - 1));
				    	} else {
					    	preparedStatement.setString(6, line[5]);
				    	}
				    	if (line[6].length() > maxStringLength ) {
					    	preparedStatement.setString(7, line[6].substring(0, maxStringLength - 1));
				    	} else {
					    	preparedStatement.setString(7, line[6]);
				    	}
				    	if (line[7].length() > maxStringLength ) {
					    	preparedStatement.setString(8, line[7].substring(0, maxStringLength - 1));
				    	} else {
					    	preparedStatement.setString(8, line[7]);
				    	}
				    	if (line[8].length() > maxStringLength ) {
					    	preparedStatement.setString(9, line[8].substring(0, maxStringLength - 1));
				    	} else {
					    	preparedStatement.setString(9, line[8]);
				    	}
				    	if (line[9].length() > maxStringLength ) {
					    	preparedStatement.setString(10, line[9].substring(0, maxStringLength - 1));
				    	} else {
					    	preparedStatement.setString(10, line[9]);
				    	}
			    	}
			    	preparedStatement.addBatch();
			    }
		    	insertCount = preparedStatement.executeBatch();
			    conn.commit();
			    preparedStatement.close();
		    	log.info("insertCount : " + insertCount.length);
		    	log.info("Database initialised and loaded!");
		  }	catch(Exception exception){  
			  log.info(exception.toString());
			  exception.printStackTrace();
		  }

	}
	
	@Test
	public void checkDBTest() {
    	log.info("checkDBTest()");
    	int count = 0;
    	try {
    		Statement stmt = conn.createStatement();
    		String query = "SELECT count(*) FROM nace";
    		ResultSet  resultSet  = stmt.executeQuery(query);
    		resultSet.first();
            count = resultSet.getInt(1);
        	log.info("checkDBTest() count: " + count);
    	}	catch(Exception exception){  
			  log.info(exception.toString());
		}
        assertEquals(996, count);
	}

	@Test
	public void getNACEPassTest() {
    	log.info("getNACEPassTest()");
    	String result = "";
    	try {
    		String query = "SELECT * FROM nace WHERE OrderNumber = ?";
    		PreparedStatement pstmt = conn.prepareStatement(query);
    		pstmt.setLong(1, 398481);
    		ResultSet  resultSet  = pstmt.executeQuery();
    		resultSet.first();
  			result = resultSet.getString("OrderNumber") + ", "
  					+ resultSet.getString("Level") + ", "
  					+ resultSet.getString("Code") + ", "
 					+ resultSet.getString("Parent") + ", "
  					+ resultSet.getString("Description") + ", "
  					+ resultSet.getString("ThisItemIncludes") + ", "
  					+ resultSet.getString("ThisItemAlsoIncludes") + ", "
 					+ resultSet.getString("Rulings") + ", "
  					+ resultSet.getString("ThisItemExcludes") + ", "
  					+ resultSet.getString("ReferenceToISICRev4");
    	}	catch(Exception exception){  
			  log.info(exception.toString());
		}
    	log.info("result: " + result);
		assertEquals(result, "398481, 1, A, , AGRICULTURE, FORESTRY AND FISHING, This section includes the "
				+ "exploitation of vegetal and animal natural resources, comprising the activities of growing of "
				+ "crops, raising and breeding of animals, harvesting of timber and other plants, "
				+ "animals or animal products from a farm or their natural, , , , A");
	}
	
	@Test
	public void getNACEFailTest() {
    	log.info("getNACEFailTest()");
    	String result = "";
    	try {
    		String query = "SELECT * FROM nace WHERE OrderNumber = ?";
    		PreparedStatement pstmt = conn.prepareStatement(query);
    		pstmt.setLong(1, 398481);
    		ResultSet  resultSet  = pstmt.executeQuery();
    		resultSet.first();
  			result = resultSet.getString("OrderNumber") + ", "
  					+ resultSet.getString("Level") + ", "
  					+ resultSet.getString("Code") + ", "
 					+ resultSet.getString("Parent") + ", "
  					+ resultSet.getString("Description") + ", "
  					+ resultSet.getString("ThisItemIncludes") + ", "
  					+ resultSet.getString("ThisItemAlsoIncludes") + ", "
 					+ resultSet.getString("Rulings") + ", "
  					+ resultSet.getString("ThisItemExcludes") + ", "
  					+ resultSet.getString("ReferenceToISICRev4");
    	}	catch(Exception exception){  
			  log.info(exception.toString());
		}
    	log.info("result: " + result);
    	assertNotSame(result, "398481,1, A, , AGRICULTURE, FORESTRY AND FISHING, This section includes the "
				+ "exploitation of vegetal and animal natural resources, comprising the activities of growing of "
				+ "crops, raising and breeding of animals, harvesting of timber and other plants, "
				+ "animals or animal products from a farm or their natural, , , , A");
	}
	
	@AfterAll
	public static void cleanUp(){
		log.info("@AfterAll cleanUp() method called");
    	try {
    		Statement stmt = conn.createStatement();
    		String sql = "DROP TABLE nace";
    		int count = stmt.executeUpdate(sql);
        	log.info("cleanUp() count: " + count);
		    conn.commit();
        	conn.close();
    	}	catch(Exception exception){  
			  log.info(exception.toString());
		}
	}
}
