package org.sn.isi.dev.examenjava.repository;

import org.sn.isi.dev.examenjava.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}