package org.example.multitenant.repository.dao.user;

import org.example.multitenant.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}