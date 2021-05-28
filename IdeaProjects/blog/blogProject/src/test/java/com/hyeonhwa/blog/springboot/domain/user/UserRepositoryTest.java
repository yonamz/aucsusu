package com.hyeonhwa.blog.springboot.domain.user;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

@RunWith( SpringRunner.class )
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @After
    public void cleanup(){
        userRepository.deleteAll();
    }

    @Test
    public void 사용자_저장(){
        String uid="admin";
        String user_name = "관리자";
        String password = "1234";
        String confirmpw = password;
        String user_email = "admin@naver.com";
        int user_birth = 980403;
        String reg_date="2021-05-20";

        userRepository.save(User.builder()
        .uid(uid).name(user_name)
        .password(password).email(user_email)
        .birth(user_birth).reg_date(reg_date).build());

        List<User> userList = userRepository.findAll();

        User user = userList.get(0);
        assertThat(user.getUid()).isEqualTo(uid);
        assertThat(user.getName()).isEqualTo(user_name);
        assertThat(user.getPassword()).isEqualTo(password);
        assertThat(user.getEmail()).isEqualTo(user_email);
        assertThat(user.getBirth()).isEqualTo(user_birth);
        assertThat(user.getReg_date()).isEqualTo(reg_date);
        /* String uid;
    String user_name;
    String password;
    String confirmpw;
    String user_email;
    int user_birth;
    String reg_date;*/
    }
}
