//package com.codegym.controller;
//
//import com.codegym.model.User;
//import com.codegym.service.user.IUserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.web.PageableDefault;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.ModelAndView;
//
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/admin")
//public class UserController {
//    @Autowired
//    private IUserService userService;
//
//    @ModelAttribute("user")
//    public Iterable<User> users() {
//        return userService.findAll();
//    }
//
//    @GetMapping("/list")
//    public ModelAndView showUser() {
//        ModelAndView modelAndView = new ModelAndView("/admin");
//        modelAndView.addObject("user", users());
//        return modelAndView;
//    }
//    @GetMapping
//public ResponseEntity<Iterable<User>> findAll(){
//        return new ResponseEntity<>(userService.findAll(),HttpStatus.OK);
//
//    }
//    @PostMapping
//    private ResponseEntity<User> createUser(@RequestBody User user){
//        return new ResponseEntity<>(userService.save(user),HttpStatus.CREATED);
//    }
//    @DeleteMapping("/{id}")
//    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
//        Optional<User> userOptional = userService.findById(id);
//        if (!userOptional.isPresent()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        userService.remove(id);
//        return new ResponseEntity<>(userOptional.get(), HttpStatus.NO_CONTENT);
//    }
//    @PutMapping("/{id}")
//    public ResponseEntity<User> editUser(@PathVariable Long id,@RequestBody User user){
//        Optional<User> userOptional = userService.findById(id);
//                if(!userOptional.isPresent()){
//                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//                }
//                user.setId(id);
//                return new ResponseEntity<>(userService.save(user),HttpStatus.OK);
//    }
//    @GetMapping("/{id}")
//    public ResponseEntity<User> findById(@PathVariable Long id){
//        Optional<User > userOptional = userService.findById(id);
//        if(!userOptional.isPresent()){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(userOptional.get(),HttpStatus.OK);
//    }
//    @GetMapping()
//    public ResponseEntity<Page<User>> searchUser(@RequestParam Optional<String> q, @PageableDefault (size = 10)Pageable pageable){
//        if(!q.isPresent()){
//            return  new ResponseEntity<>(userService.findAll(pageable),HttpStatus.NOT_FOUND);
//        }else {
//            return new ResponseEntity<>(userService.findAllByCategoryContaining(q.get(),pageable),HttpStatus.OK);
//        }
//    }
//
//}