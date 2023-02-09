package com.example.User.Service;


import com.example.User.Entity.Users;
import com.example.User.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Users registerUser(Users users) {
        return userRepository.save(users);
    }
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
    public Users getUserById(Long userId) {
        return userRepository.findById(userId).get();
    }
    public Users update(Long id, Users users) {
        users.setUserId(id);
        return userRepository.save(users);
    }



}


