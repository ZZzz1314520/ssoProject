package cn.cqu.edu.sso.controller;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import cn.cqu.edu.sso.domain.User;
import cn.cqu.edu.sso.repository.UserRepository;

@RestController
public class UserController {
 @Autowired
 private UserRepository userRepository;
 @RequestMapping(value="/user/add")
 public User add(User user)
 {
 return userRepository.insert(user);
 }
 @RequestMapping(value="/user/findAll")
 public List<User> findAll()
 {
 return userRepository.findAll();
 }
}