package com.hyeonhwa.blog.springboot.service.user;

import com.hyeonhwa.blog.springboot.domain.user.User;
import com.hyeonhwa.blog.springboot.domain.user.UserRepository;
import com.hyeonhwa.blog.springboot.web.dto.user.UserResponseDto;
import com.hyeonhwa.blog.springboot.web.dto.user.UserSaveRequestDto;
import com.hyeonhwa.blog.springboot.web.dto.user.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService{

    private final UserRepository userRepository;

    @Transactional
    public Long save(UserSaveRequestDto userSaveRequestDto){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userSaveRequestDto.setPassword(passwordEncoder.encode(userSaveRequestDto.getPassword()));
        return userRepository.save(userSaveRequestDto.toEntity()).getId();
    }

    @Override
    public User loadUserByUsername(String uid) throws UsernameNotFoundException {
        User user=userRepository.findByUid(uid);
        //System.out.println("비밀번호 : "+us);
        return userRepository.findByPassword(user.getUid(), user.getPassword());
        /* System.out.println(userRepository.findByPassword(uid, user.getPassword()));
       return userRepository.findByPassword(uid, user.getPassword());*/
    }

    public User login(String uid, String password) throws UsernameNotFoundException{
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        User user=userRepository.findByUid(uid);

        if(user==null){
            return null;
        }
        if(encoder.matches(password,user.getPassword())){
            password=user.getPassword();
        }

        return userRepository.findByPassword(uid, password);
    }

    @Transactional(readOnly = true)
    public List<UserResponseDto> findAllDesc(){
        return userRepository.findAllDesc().stream()
                .map(UserResponseDto::new)
                .collect(Collectors.toList());
    }

    public boolean checkUidDuplicate(String uid){
        return userRepository.existsByUid(uid);
    }


    @Transactional
    public void update(String uid, UserUpdateDto requestDto){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        User user = userRepository.findByUid(uid);

        String password = encoder.encode(requestDto.getPassword());

        user.update(requestDto.getName(), password, requestDto.getEmail());

    }

    @Transactional
    public void delete(String uid){
        User user = userRepository.findByUid(uid);
        userRepository.delete(user);
    }

}
