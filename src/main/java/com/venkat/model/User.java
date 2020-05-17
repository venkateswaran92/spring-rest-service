package com.venkat.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Table(name = "USER")
@Entity
@ApiModel
public class User {
	@Id
	@GeneratedValue
	private int id;

	@ApiModelProperty(notes = "name atleast two char")
	@Size(min = 2, message = "name not less then 2 chr")
	private String name;
	@ApiModelProperty(notes = "Age of the user")
	private int age;
	@OneToMany(mappedBy = "user")
	private List<Post> post;

	public User() {
	};

	public User(int id, String name, int age) {
		this.id = id;
		this.name = name;
		this.age = age;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public List<Post> getPost() {
		return post;
	}

	public void setPost(List<Post> post) {
		this.post = post;
	}

}
