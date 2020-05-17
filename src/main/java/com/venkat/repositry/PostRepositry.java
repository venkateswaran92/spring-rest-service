package com.venkat.repositry;

import com.venkat.model.Post;
import com.venkat.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepositry extends JpaRepository<Post,Integer> {
}
