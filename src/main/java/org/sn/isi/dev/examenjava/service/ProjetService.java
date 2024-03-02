package org.sn.isi.dev.examenjava.service;

import org.sn.isi.dev.examenjava.dto.ProjetDto;
import org.sn.isi.dev.examenjava.dto.UserDto;
import org.sn.isi.dev.examenjava.dto.UserProfileDto;
import org.sn.isi.dev.examenjava.entity.Projet;
import org.sn.isi.dev.examenjava.entity.User;

import java.util.List;

public interface ProjetService {
    void saveProjet(ProjetDto projetDto);

    Projet findProjetByName(String name);

    List<ProjetDto> findAllProjets();
}
