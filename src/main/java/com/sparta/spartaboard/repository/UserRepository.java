package com.sparta.spartaboard.repository;

import com.sparta.spartaboard.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

}
