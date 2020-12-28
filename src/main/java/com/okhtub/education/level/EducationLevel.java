package com.okhtub.education.level;

import javax.persistence.*;


@Entity
public class EducationLevel {
	@Id
	@GeneratedValue(generator="increment")
	private int ID;
	private int sorting , available;
	private String englishLevel , arabicLevel;
	

	
	public int getId() {
		return ID;
	}
	/*public void setId(int id) {
		this.ID = id;
	}*/
	public int getSorting() {
		return sorting;
	}
	public void setSorting(int sorting) {
		this.sorting = sorting;
	}
	public int getAvailable() {
		return available;
	}
	public void setAvailable(int available) {
		this.available = available;
	}
	public String getEnglishLevel() {
		return englishLevel;
	}
	public void setEnglishLevel(String englishLevel) {
		this.englishLevel = englishLevel;
	}
	public String getArabicLevel() {
		return arabicLevel;
	}
	public void setArabicLevel(String arabicLevel) {
		this.arabicLevel = arabicLevel;
	}

	@Override
	public String toString() {
		
		return "{'ID':"+ID+" , 'sorting'="+sorting+",'available'="+available
				+",'englishLevel'= '"+englishLevel +"' ,'arabicLevel'='"+arabicLevel+"'}";
		
	}
	
	
	

}
