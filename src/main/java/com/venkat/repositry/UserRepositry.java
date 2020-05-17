package com.venkat.repositry;

import com.venkat.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositry extends JpaRepository<User,Integer> {
}
