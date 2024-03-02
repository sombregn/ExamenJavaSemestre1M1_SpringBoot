package org.sn.isi.dev.examenjava.service;


import org.sn.isi.dev.examenjava.dto.UserDto;
import org.sn.isi.dev.examenjava.dto.UserProfileDto;
import org.sn.isi.dev.examenjava.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);
    User findUserByEmail(String email);
    void UpdateUser(UserProfileDto userProfileDto);
    List<UserDto> findAllUsers();
}