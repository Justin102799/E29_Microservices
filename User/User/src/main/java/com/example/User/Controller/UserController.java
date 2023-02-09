package com.example.User.Controller;


import com.example.User.Entity.Users;
import com.example.User.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping ("/registerUser")
    public ResponseEntity<Users> registerUsers(@RequestBody Users users){
        Users registerUsers = userService.registerUser(users);
        return new ResponseEntity<>(registerUsers, HttpStatus.CREATED);
    }
    @GetMapping ("/getUsers/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable("id") Long userId){
        Users users = userService.getUserById(userId);
        return ResponseEntity.ok(users);
    }
    @DeleteMapping("/deleteUsers/{id}")
    public void delete(@PathVariable Long id){
        userService.deleteById(id);
    }
    @PutMapping("/updateUsers/{id}")
    public Users updateUsers(@PathVariable Long id, @RequestBody Users users){
        users.setUserId(id);
        return userService.update(id, users);
    }
}
