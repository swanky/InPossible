package com.insnergy.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insnergy.domain.User;
import com.insnergy.repo.UserRepo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping("/v1/users")
public class UserApi {
  
  @Autowired
  private UserRepo userRepo;
  
  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  @Builder
  public static class UserVO {
    private String name;
    private String password;
  }
  
  @PostMapping
  public ResponseEntity<User> add(@RequestBody UserVO userVO) {
    return new ResponseEntity<User>(userRepo.save(User.builder()
                                                      .name(userVO.getName())
                                                      .password(userVO.getPassword())
                                                      .build()),
        HttpStatus.OK);
  }
  
  @GetMapping
  public ResponseEntity<User> findById(Long id) {
    return new ResponseEntity<User>(userRepo.findOne(id), HttpStatus.OK);
  }
  
  @DeleteMapping
  public ResponseEntity<Void> delete(Long id) {
    userRepo.delete(id);
    return new ResponseEntity<Void>(HttpStatus.OK);
  }
  
}
