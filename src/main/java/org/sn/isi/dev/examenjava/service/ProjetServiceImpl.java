package org.sn.isi.dev.examenjava.service;

import org.sn.isi.dev.examenjava.dto.ProjetDto;
import org.sn.isi.dev.examenjava.dto.UserDto;
import org.sn.isi.dev.examenjava.dto.UserProfileDto;
import org.sn.isi.dev.examenjava.entity.Projet;
import org.sn.isi.dev.examenjava.entity.Role;
import org.sn.isi.dev.examenjava.entity.User;
import org.sn.isi.dev.examenjava.repository.ProjetRepository;
import org.sn.isi.dev.examenjava.repository.RoleRepository;
import org.sn.isi.dev.examenjava.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class ProjetServiceImpl implements ProjetService{
    private UserRepository userRepository;
    private ProjetRepository projetRepository;

    public ProjetServiceImpl(UserRepository userRepository,ProjetRepository projetRepository) {
        this.userRepository = userRepository;
       this.projetRepository = projetRepository;
    }

    private ProjetDto mapToProjetDto(Projet projet){
        ProjetDto projetDto = new ProjetDto();
        projetDto.setName(projet.getName());
        projetDto.setDescrption(projet.getDescrption());
        projetDto.setBudget(projet.getBudget());
        return projetDto;
    }

    @Override
    public void saveProjet(ProjetDto projetDto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        auth.getPrincipal();
        //auth.getName()
        User user = this.userRepository.findUserByEmail(auth.getName());
        Projet projet = new Projet();
        projet.setName(projetDto.getName());
        projet.setDescrption(projetDto.getDescrption());
        projet.setBudget(projetDto.getBudget());
        projet.setUser(user);

        projetRepository.save(projet);
    }

    @Override
    public Projet findProjetByName(String name) {
        return projetRepository.findProjetByName(name);
    }

    @Override
    public List<ProjetDto> findAllProjets() {
        List<Projet> projets = projetRepository.findAll();
        return projets.stream()
                .map((projet) -> mapToProjetDto(projet))
                .collect(Collectors.toList());
    }
}
