package com.okhtub.user;

import com.okhtub.education.level.*;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="my_user")
public class MyUser {
	@Id
	@GeneratedValue(generator = "increment")
	private int userId;
	private String userName;
	private Date myData;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pk_education_level" )
	private EducationLevel educationLevel;

	public int getUserId() {
		return userId;
	}

/*	public void setUserId(int userId) {
		this.userId = userId;
	}*/

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getMyData() {
		return myData;
	}

	public void setMyData(Date myData) {
		this.myData = myData;
	}


	public EducationLevel getEducationLevel() {
		return educationLevel;
	}

	public void setEducationLevel(EducationLevel educationLevel) {
		this.educationLevel = educationLevel;
	}

}
