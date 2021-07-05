//package com.codegym.controller;
//
//import com.codegym.model.User;
//import com.codegym.service.user.IUserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.ModelAndView;
//
//<<<<<<< HEAD
//import java.util.Optional;
//=======
//
//@RestController
//@RequestMapping("/admin")
//public class UserController {
//<<<<<<< HEAD
//    @Autowired
//    private IUserService userService;
//
//    @ModelAttribute("user")
//    public Iterable<User> users() {
//        return userService.findAll();
//    }
//
//    @GetMapping("")
//    public ModelAndView showUser() {
//        ModelAndView modelAndView = new ModelAndView("/admin");
//        modelAndView.addObject("user", users());
//    }
//
//    @PostMapping("")
//    public ResponseEntity<User> saveUser(@RequestBody User user) {
//        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
//    }
//
//    @GetMapping("listUser")
//    public ResponseEntity<Iterable<User>> allUser() {
//        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
//        Optional<User> userOptional = userService.findById(id);
//        if (!userOptional.isPresent()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        userService.remove(id);
//        return new ResponseEntity<>(userOptional.get(), HttpStatus.NO_CONTENT);
//    }
//    @PutMapping("{id}")
//    public ResponseEntity<User> editUser(@PathVariable Long id,@RequestBody User user){
//        Optional<User> userOptional = userService.findById(id);
//                if(!userOptional.isPresent()){
//                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//                }
//                user.setId(id);
//                return new ResponseEntity<>(userService.save(user),HttpStatus.OK);
//    }
//
//}