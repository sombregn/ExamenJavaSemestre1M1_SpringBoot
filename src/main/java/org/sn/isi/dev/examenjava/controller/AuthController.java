package org.sn.isi.dev.examenjava.controller;

import jakarta.validation.Valid;
import org.sn.isi.dev.examenjava.dto.ProjetDto;
import org.sn.isi.dev.examenjava.dto.UserDto;
import org.sn.isi.dev.examenjava.dto.UserProfileDto;
import org.sn.isi.dev.examenjava.entity.Projet;
import org.sn.isi.dev.examenjava.entity.User;
import org.sn.isi.dev.examenjava.service.ProjetService;
import org.sn.isi.dev.examenjava.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AuthController {

    private UserService userService;

    private ProjetService projetService;

    public AuthController(UserService userService, ProjetService projetService) {
        this.userService = userService;
        this.projetService = projetService;
    }

    // handler method to handle home page request
    @GetMapping("/index")
    public String home(){
        return "index";
    }

    // handler method to handle home page request Projet
    @GetMapping("/indexProjet")
    public String homeProjet(Model model){
        ProjetDto projetDto = new ProjetDto();
        model.addAttribute("projetDto", projetDto);
        return "projet_form";
    }

    // handler method to handle user registration form request
    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        // create model object to store form data
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    // handler method to handle user registration form submit request
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto,
                               BindingResult result,
                               Model model){
        User existingUser = userService.findUserByEmail(userDto.getEmail());

        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }

        if(result.hasErrors()){
            model.addAttribute("user", userDto);
            return "/register";
        }

        userService.saveUser(userDto);
        return "redirect:/register?success";
    }

    // handler method to handle projet registration form submit request
    @PostMapping("/register/projet")
    public String registrationProjet(@Valid @ModelAttribute("user") ProjetDto projetDto,
                               BindingResult result,
                               Model model){
        Projet existingProjet = projetService.findProjetByName(projetDto.getName());

        if(existingProjet != null && existingProjet.getName() != null && !existingProjet.getName().isEmpty()){
            result.rejectValue("name", null,
                    "There is already an projet registered with the same email");
        }

        if(result.hasErrors()){
            model.addAttribute("name", projetDto);
            return "/indexProjet";
        }

        projetService.saveProjet(projetDto);
        return "redirect:/projets?success";
    }

    // handler method to handle login request
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    // handler method to handle list of users
    @GetMapping("/users")
    public String users(Model model){
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    // handler method to handle list of projets
    @GetMapping("/projets")
    public String projets(Model model){
        List<ProjetDto> projets = projetService.findAllProjets();
        model.addAttribute("projets", projets);
        return "projets";
    }

    // handler method to handle list of users
    @GetMapping("/editProfile")
    public String edit(Model model){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        auth.getPrincipal();
        User userProfile = userService.findUserByEmail(auth.getName());
        model.addAttribute("userProfile", userProfile);
        //System.out.printf(auth.getName());
        return "editProfile";
    }

    @PostMapping("/editProfile/edit")
    public String editProfile(@Valid @ModelAttribute("user") UserProfileDto userProfileDto,
                               BindingResult result,
                               Model model){

        userService.UpdateUser(userProfileDto);

        return "redirect:/users?success";
    }
}
