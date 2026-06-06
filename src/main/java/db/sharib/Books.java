package db.sharib;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@Table(name = "books")
public class Books {
	  @Id
	  @NotNull
	  @Column(name="ISBN")
	  private long isbn;
	  @Column(name="title") 	
	  private String title;
	  /*@OneToMany(cascade = CascadeType.ALL)
	  @JoinColumn(name = "book_isbn")  // creates foreign key in Authors table
	  private List<Authors> authors = new ArrayList<>();*/
	  @Column(name="authors")
	  private String authors;
	  @Column(name="yearPublished") 	
	  private int yearPublished;
	  @Column(name="price") 	
	  private double price;
	  @Column(name="genre")
	  private String genre;

	  private static final Logger log = LoggerFactory.getLogger(Books.class);

	  Books() {
	  }

	  Books(long isbn, String title, String authors, int yearPublished, double price, String genre) {
	    this.isbn = isbn;
	    this.title = title;
	    this.authors = authors;
	    this.yearPublished = yearPublished;
	    this.price = price;
	    this.genre = genre;
	  }

	  
	  public long getIsbn() {
		return isbn;
	}

	  public void setIsbn(long isbn) {
		  this.isbn = isbn;
	  }

	public String getTitle() {
		return title;
	}

	  public void setTitle(String title) {
		  this.title = title;
	  }

	  public String getAuthors() {
		  return authors;
	  }

	  public void setAuthors(String authors) {
		  this.authors = authors;
	  }

	  public int getYearPublished() {
		  return yearPublished;
	  }

	  public void setYearPublished(int yearPublished) {
		  this.yearPublished = yearPublished;
	  }

	  public double getPrice() {
		  return price;
	  }

	  public void setPrice(double price) {
		  this.price = price;
	  }

	  public String getGenre() {
		  return genre;
	  }

	  public void setGenre(String genre) {
		  this.genre = genre;
	  }

	  public static Logger getLog() {
		  return log;
	  }

	@Override
	public String toString() {
		return "Books [isbn=" + isbn + ", title=" + title + ", authors=" + 	authors.toString()
			 + ", yearPublished=" + yearPublished + ", price="+ price + ", genre=" + genre + "]";
	}
}