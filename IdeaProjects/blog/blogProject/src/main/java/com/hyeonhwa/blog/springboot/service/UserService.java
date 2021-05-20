package com.hyeonhwa.blog.springboot.service;

import com.hyeonhwa.blog.springboot.domain.user.UserRepository;
import com.hyeonhwa.blog.springboot.web.dto.UserSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public String save(UserSaveRequestDto requestDto){
        return userRepository.save(requestDto.toEntity()).getUid();
    }


}
