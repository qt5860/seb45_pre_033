package preproject.spring.User.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import preproject.spring.User.entity.User;
import preproject.spring.User.mapper.UserDto;
import preproject.spring.User.mapper.UserMapper;
import preproject.spring.User.service.UserService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("")
@Validated
@Slf4j
public class UserController {

    private final UserService service;
    private final UserMapper mapper;

    public UserController(UserService service, UserMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/login/check") //이메일 검증하는곳
    public ResponseEntity<User> checkEmail(@RequestBody UserDto.Check email){
        //존재할 경우 409에러
        if(service.checkEmail(email.getEmail())){
            return new ResponseEntity<>(HttpStatus.OK);
        }else return new ResponseEntity<>(HttpStatus.CONFLICT);

    }

    @PostMapping("/login") //회원 가입 시 (email,password,nickname이 값으로 들어옴)
    public ResponseEntity<User> postUser(@RequestBody UserDto.Post userpost){
        User user = service.createUser(mapper.userPostChanger(userpost));

        URI uri = UriComponentsBuilder
                .newInstance()
                .path("/user/" + user.getUserId())
                .buildAndExpand(user.getUserId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PatchMapping("/user/mypage/{user-id}") //마이페이지에서 user정보 수정 시 (주소로는 id값, email,nickname,profile message/image가 들어옴)
    public ResponseEntity<User> patchMyUser(@PathVariable("user-id") Long userId,
                                            @RequestBody UserDto.Patch userpatch) {
        User Changer = mapper.userPatchChanger(userpatch);
        Changer.setUserId(userId);
        User user = service.updateUser(Changer);

        return ResponseEntity.ok(user);
    }

    @GetMapping("/user/mypage/{user-id}") // 마이페이지 조회 시
    public ResponseEntity<User> getMyUser(@PathVariable("user-id") Long userId){
        User user = service.findUser(userId);

        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/user/mypage/{user-id}") //회원 탈퇴 시
    public ResponseEntity<User> deleteUser(@PathVariable("user-id") Long userId) {
        service.deleteUser(userId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search/users")
    public ResponseEntity<List<User>> searchUsers(@RequestParam int page,
                                                  @RequestParam int size){
        Page<User> pageMembers = service.findUsers(page - 1, size);
        List<User> users = pageMembers.getContent();

        return ResponseEntity.ok(users);
    }



}
