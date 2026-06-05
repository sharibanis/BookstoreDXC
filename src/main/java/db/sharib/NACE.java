package db.sharib;

import java.io.File;
import java.io.Serializable;
import java.util.Objects;
import java.util.Scanner;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@Table(name = "nace")
public class NACE {
	  /*@Id
	  @GeneratedValue(strategy = GenerationType.SEQUENCE)
	  private long id;*/
	  @Id
	  @NotNull
	  @Column(name="OrderNumber")
	  private Long OrderNumber;
	  @Column(name="Level") 	
	  private String Level;
	  @Column(name="Code") 	
	  private String Code;
	  @Column(name="Parent") 	
	  private String Parent;
	  @Column(name="Description") 	
	  private String Description;
	  @Column(name="ThisItemIncludes") 	
	  private String ThisItemIncludes;
	  @Column(name="ThisItemAlsoIncludes") 	
	  private String ThisItemAlsoIncludes;
	  @Column(name="Rulings") 	
	  private String Rulings;
	  @Column(name="ThisItemExcludes") 	
	  private String ThisItemExcludes;
	  @Column(name="ReferenceToISICRev4") 	
	  private String ReferenceToISICRev4;

	  private static final Logger log = LoggerFactory.getLogger(NACE.class);

	  NACE() {
	  }

	  NACE(Long OrderNumber, String Level, String Code, String Parent, String Description, String ThisItemInclude,
			  String ThisItemAlsoIncludes, String Rulings, String ThisItemExcludes, String ReferenceToISICRev4) {
	    this.OrderNumber = OrderNumber;
	    this.Level = Level;
	    this.Code = Code;
	    this.Parent = Parent;
	    this.Description = Description;
	    this.ThisItemIncludes = ThisItemIncludes;
	    this.ThisItemAlsoIncludes = ThisItemAlsoIncludes;
	    this.Rulings = Rulings;
	    this.ThisItemExcludes = ThisItemExcludes;
	    this.ReferenceToISICRev4 = ReferenceToISICRev4;
	  }

	  public Long getOrderNumber() {
		return this.OrderNumber;
	}

	public void setOrderNumber(Long OrderNumber) {
	    this.OrderNumber = OrderNumber;
	}

	public String getLevel() {
		return this.Level;
	}

	public void setLevel(String level) {
		this.Level = level;
	}

	public String getCode() {
		return this.Code;
	}

	public void setCode(String code) {
		this.Code = code;
	}

	public String getParent() {
		return this.Parent;
	}

	public void setParent(String parent) {
		this.Parent = parent;
	}

	public String getDescription() {
		return this.Description;
	}

	public void setDescription(String description) {
		this.Description = description;
	}

	public String getThisItemIncludes() {
		return this.ThisItemIncludes;
	}

	public void setThisItemIncludes(String thisItemIncludes) {
		this.ThisItemIncludes = thisItemIncludes;
	}

	public String getThisItemAlsoIncludes() {
		return this.ThisItemAlsoIncludes;
	}

	public void setThisItemAlsoIncludes(String thisItemAlsoIncludes) {
		this.ThisItemAlsoIncludes = thisItemAlsoIncludes;
	}

	public String getRulings() {
		return this.Rulings;
	}

	public void setRulings(String rulings) {
		this.Rulings = rulings;
	}

	public String getThisItemExcludes() {
		return this.ThisItemExcludes;
	}

	public void setThisItemExcludes(String thisItemExcludes) {
		this.ThisItemExcludes = thisItemExcludes;
	}

	public String getReferenceToISICRev4() {
		return this.ReferenceToISICRev4;
	}

	public void setReferenceToISICRev4(String referenceToISICRev4) {
		this.ReferenceToISICRev4 = referenceToISICRev4;
	}

	@Override
	public String toString() {
		return "NACE [OrderNumber=" + OrderNumber + ", Level=" + Level + ", Code=" + Code + ", Parent=" + Parent + ", Description="
				+ Description + ", ThisItemIncludes=" + ThisItemIncludes + ", ThisItemAlsoIncludes="
				+ ThisItemAlsoIncludes + ", Rulings=" + Rulings + ", ThisItemExcludes=" + ThisItemExcludes
				+ ", ReferenceToISICRev4=" + ReferenceToISICRev4 + "]";
	}
}