package com.yonamz.aucsusu.user;

import com.yonamz.aucsusu.chat.Message;
import com.yonamz.aucsusu.chat.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final MessageRepository msgRepository;

    @Transactional
    public Long save(UserSaveRequestDto userSaveRequestDto){
        return userRepository.save(userSaveRequestDto.toEntity()).getId();
    }

    @Transactional
    public boolean saveIDCheck(String uid){
        return userRepository.existsByUid(uid);
    }

    public User login(String uid, String password) {
        User user = userRepository.findByUid(uid);
        return userRepository.findByPassword(uid,password);
    }
    @Transactional
    boolean saveEmailCheck(String email){
        return userRepository.existsByEmail(email);
    }

    @Transactional
    public void update(String uid, UserUpdateDto updateDto){
        User user=userRepository.findByUid(uid);

        user.update(updateDto.getName(), updateDto.getPassword(), updateDto.getEmail());

    }

    @Transactional
    public void delete(String uid){
        User user = userRepository.findByUid(uid);
        Message msg = msgRepository.findByUser1AndUser2(uid);
        userRepository.delete(user);
        msgRepository.delete(msg);
    }

    @Transactional
    public void userReport(String uid) {
        userRepository.userReport(uid);
    }

    @Transactional
    public int getUserReportNum(String uid) {
        return userRepository.getUserReportNum(uid);
    }

    @Transactional
    public User findByUid(String uid){
        return userRepository.findByUid(uid);
    }



}
