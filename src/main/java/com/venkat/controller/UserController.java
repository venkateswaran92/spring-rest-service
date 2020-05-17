package com.venkat.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.hateoas.server.mvc.ControllerLinkBuilder;
//import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
//import org.springframework.hateoas.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.venkat.exception.UserNotFounfException;
import com.venkat.model.Post;
import com.venkat.model.User;
import com.venkat.repositry.PostRepositry;
import com.venkat.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userPrepositry;

	@Autowired
	private PostRepositry postRepositry;

	@PostMapping("/")
	public ResponseEntity<Object> User(@Valid @RequestBody User user) {
		User saveUser = userPrepositry.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(saveUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@GetMapping("/get/{id}")
	public User getUser(@PathVariable Integer id) {
		Optional<com.venkat.model.User> user = userPrepositry.getUSerById(id);
		if (user.isPresent())
			return user.get();
		throw new UserNotFounfException("id " + id);
	}

	/*
	 * @GetMapping("/get/{id}") public EntityModel<User> getUser(@PathVariable
	 * Integer id) { User user=service.getUSerById(id); //Implementation of HATEOS
	 * EntityModel<User> model =new EntityModel<>(user); WebMvcLinkBuilder innkTo=
	 * WebMvcLinkBuilder .linkTo(ControllerLinkBuilder.methodOn(this.getClass())
	 * .getAllUser()); model.add(innkTo.withRel("all-users")); return model; }
	 */

	@GetMapping("all")
	public List<User> getAllUser() {
		return userPrepositry.GetAllUser();
	}

	@DeleteMapping("delete/{id}")
	public void deleteUSer(@PathVariable int id) {
		userPrepositry.DeleteUser(id);
	}

	@GetMapping("/users/{id}/posts")
	public List<Post> retriveUsers(@PathVariable int id) {
		Optional<com.venkat.model.User> user = userPrepositry.getUSerById(id);
		if (user.isPresent())
			return user.get().getPost();
		else
			throw new UserNotFounfException("id " + id);
	}

	@PostMapping("/users/{id}/posts")
	public Post createPost(@PathVariable int id,@Valid @RequestBody Post post) {
		Optional<com.venkat.model.User> user = userPrepositry.getUSerById(id);
		if (user.isPresent()) {
			User u = user.get();
			post.setUser(u);
			return postRepositry.save(post);
		} else
			throw new UserNotFounfException("id " + id);
	}
}
