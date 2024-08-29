package com.sp.multitenant.repository.dao.user;

import com.sp.multitenant.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}