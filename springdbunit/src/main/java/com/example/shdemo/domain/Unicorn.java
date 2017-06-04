package com.example.shdemo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
		@NamedQuery(name = "unicorn.all", query = "SELECT u FROM Unicorn u"),
		@NamedQuery(name = "unicorn.select", query = "SELECT u FROM Unicorn u WHERE u.id BETWEEN :min AND :max")
})
public class Unicorn {

	private Long id;
	private String name;
	
	public Unicorn() {}
	
	public Unicorn(String name) {
		this.name = name;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
