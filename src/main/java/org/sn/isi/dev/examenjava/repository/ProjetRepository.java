package org.sn.isi.dev.examenjava.repository;

import org.sn.isi.dev.examenjava.entity.Projet;
import org.sn.isi.dev.examenjava.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjetRepository extends JpaRepository<Projet, Long> {
    Projet findProjetByName(String name);
}