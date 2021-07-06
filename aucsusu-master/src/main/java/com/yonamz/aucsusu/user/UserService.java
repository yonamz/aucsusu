package com.yonamz.aucsusu.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public Long save(UserSaveRequestDto userSaveRequestDto){
        return userRepository.save(userSaveRequestDto.toEntity()).getId();
    }

    @Transactional
    public boolean saveCheck(String uid){
        return userRepository.existsByUid(uid);
    }

    public User login(String uid, String password) {
        User user = userRepository.findByUid(uid);
        return userRepository.findByPassword(uid,password);
    }

    @Transactional
    public void update(String uid,UserUpdateDto updateDto){
        User user=userRepository.findByUid(uid);
        user.update(updateDto.getName(), updateDto.getPassword(), updateDto.getEmail());
    }

    @Transactional
    public void delete(String uid){
        User user = userRepository.findByUid(uid);
        userRepository.delete(user);
    }

}
