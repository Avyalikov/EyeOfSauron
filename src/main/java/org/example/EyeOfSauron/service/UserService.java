package org.example.EyeOfSauron.service;

import org.example.EyeOfSauron.domain.Role;
import org.example.EyeOfSauron.domain.User;
import org.example.EyeOfSauron.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public User loadByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public boolean addUser(User user){
        User userFromDb = userRepository.findByUsername(user.getUsername());

        if (userFromDb != null){
            return false;
        }

        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);

        return true;
    }


    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<User> findAllById(Set<Long> singleton) {
        return userRepository.findAllById(singleton);
    }

    public void saveUser(User user, String username, Map<String, String> form) {
        user.setUsername(username);

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)){
                user.getRoles().add(Role.valueOf(key));
            }
        }

        userRepository.save(user);
    }

    public void updateProfile(User user, String password, String email, String fullName) {
        String userEmail = user.getEmail();
        String userFullName = user.getFullName();

        boolean isEmailChanged = (email != null && !email.equals(userEmail)) ||
                (userEmail != null && !userEmail.equals(email));

        boolean isNameChanged = (fullName != null && !fullName.equals(userFullName)) ||
                (userFullName != null && !userFullName.equals(fullName));

        if (isEmailChanged){
            user.setEmail(email);
        }

        if (!StringUtils.isEmpty(password)){
            user.setPassword(password);
        }

        if (isNameChanged){
            user.setFullName(fullName);
        }

        userRepository.save(user);
    }

    public void updateFamilyId(User user, Long familyId) {
        user.setFamilyId(familyId);
        userRepository.save(user);
    }
}
