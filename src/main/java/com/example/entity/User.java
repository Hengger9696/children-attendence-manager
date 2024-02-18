package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
public class User {
  @Id
  @SequenceGenerator(name = "USER_ID_GENERATOR", sequenceName = "USER_ID_SED", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_ID_GENERATOR")
  @Column(name = "ID")
  private Integer id;

  @Column(name = "MAIL_ADDRESS")
  private String mailAddress;

  @Column(name = "PASSWORD")
  private String password;

  @Column(name = "CLASS_ID")
  private Integer classId;

public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}

public String getMailAddress() {
	return mailAddress;
}

public void setMailAddress(String mailAddress) {
	this.mailAddress = mailAddress;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public Integer getClassId() {
	return classId;
}

public void setClassId(Integer classId) {
	this.classId = classId;
}


}
