package com.insnergy.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.insnergy.domain.User;

public interface UserRepo extends JpaRepository<User, Long> {
}
