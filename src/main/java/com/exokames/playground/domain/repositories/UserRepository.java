package com.exokames.playground.domain.repositories;

import com.exokames.playground.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
