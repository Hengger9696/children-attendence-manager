//package com.example.entity;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.SequenceGenerator;
//import javax.persistence.Table;
//
//@Entity
//@Table(name = "CLASSES")
//public class Class {
//
//	@Id
//	@SequenceGenerator(name = "CLASSES_ID_GENERATOR", sequenceName = "CLASSES_ID_SEQ", allocationSize = 1)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLASSES_ID_GENERATOR")
//	@Column(name = "ID")
//	private Integer id;
//
//	@Column(name = "NAME")
//	private String name;
//
//	public Integer getId() {
//		return id;
//	}
//
//	public void setId(Integer id) {
//		this.id = id;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//
//}

package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "CLASSES")
public class Class {

	@Id
	@SequenceGenerator(name = "CLASSES_ID_GENERATOR", sequenceName = "CLASSES_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLASSES_ID_GENERATOR")
	@Column(name = "ID")
	private Integer id;

	@Column(name = "NAME")
	private String name;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
	
	
//	//②追記（Userに対して１対多）
//	@OneToMany(mappedBy="class", cascade=CascadeType.ALL)
//    private List<User> users;
//	
//	
//	public List<User> getUsers() {
//        return users;
//    }
//
//    public void setUsers(List<User> users) {
//        this.users = users;
//    }
    
    
    
//	//①追記
//	@OneToMany(mappedBy = "userClass")
//    private List<User> users;
//	
//	public List<User> getUsers() {
//        return users;
//    }
//
//    public void setUsers(List<User> users) {
//        this.users = users;
//    }

    
	


