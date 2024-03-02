package org.sn.isi.dev.examenjava.service;

import org.sn.isi.dev.examenjava.dto.UserDto;
import org.sn.isi.dev.examenjava.dto.UserProfileDto;
import org.sn.isi.dev.examenjava.entity.Role;
import org.sn.isi.dev.examenjava.entity.User;
import org.sn.isi.dev.examenjava.repository.RoleRepository;
import org.sn.isi.dev.examenjava.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }



    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getFirstName() + " " + userDto.getLastName());
        user.setPrenom(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        // encrypt the password using spring security
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        Role role = roleRepository.findByName("ROLE_ADMIN");
        if(role == null){
            role = checkRoleExist();
        }
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public void UpdateUser(UserProfileDto userProfileDto) {
        User existingUser = this.findUserByEmail(userProfileDto.getEmail());
        System.out.println(userProfileDto.toString());
        existingUser.setPrenom(userProfileDto.getPrenom());
        existingUser.setName(userProfileDto.getName());
        existingUser.setEmail(userProfileDto.getEmail());
        existingUser.setPassword(passwordEncoder.encode(userProfileDto.getPassword()));

        userRepository.save(existingUser);
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map((user) -> mapToUserDto(user))
                .collect(Collectors.toList());
    }

    private UserDto mapToUserDto(User user){
        UserDto userDto = new UserDto();
        String[] str = user.getName().split(" ");
        userDto.setFirstName(str[0]);
        userDto.setLastName(user.getPrenom());
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    private Role checkRoleExist(){
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }
}
