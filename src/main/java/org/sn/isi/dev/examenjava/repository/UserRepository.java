package org.sn.isi.dev.examenjava.repository;

import org.sn.isi.dev.examenjava.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByEmail(String email);
}
