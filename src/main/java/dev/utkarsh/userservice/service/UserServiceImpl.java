package dev.utkarsh.userservice.service;

import dev.utkarsh.userservice.dto.PasswordDto;
import dev.utkarsh.userservice.dto.UserDto;
import dev.utkarsh.userservice.event.SuccessfulRegistrationEvent;
import dev.utkarsh.userservice.event.producer.SuccessfulPasswordResetEvent;
import dev.utkarsh.userservice.event.producer.SuccessfulResetEvent;
import dev.utkarsh.userservice.model.User;
import dev.utkarsh.userservice.model.VerificationToken;
import dev.utkarsh.userservice.repository.UserRepository;
import dev.utkarsh.userservice.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;

    @Autowired
    VerificationTokenRepository verificationTokenRepository;
    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

    @Override
    public User registerUser(UserDto userDto) {
        if(userRepository.findByEmail(userDto.getEmail()) != null) {
            //TODO: Throw Exceptions;
            return null;
        }
        User user = new User();
        user.setFullName(userDto.getFullName());
        user.setEmail(userDto.getEmail());
        user.setActive(false);
        user.setPassword(userDto.getPassword()); //TODO: Encrypt password
        User savedUser = userRepository.save(user);
        applicationEventPublisher.publishEvent(
                new SuccessfulRegistrationEvent(savedUser)
        );
        return savedUser;


    }

    @Override
    public User validateUser(String token) {
        Optional<VerificationToken>verificationToken = Optional.ofNullable(verificationTokenRepository.findByToken(token));
        if(verificationToken.isEmpty()){
            return null;
        }
        if(verificationToken.get().getExpiryTime().getTime() - new Date().getTime() > 0){
            User verifiedUser = verificationToken.get().getUser();
            verifiedUser.setActive(true);
            userRepository.save(verifiedUser);
            return  verifiedUser;
        }
        else{
            return null;
        }
        //User tokenUser = verificationToken.get().getUser();

    }

    @Override
    public void generateResetToken(User user) {
     VerificationToken verificationToken = verificationTokenRepository.findByUser(user);
        applicationEventPublisher.publishEvent(
                new SuccessfulResetEvent(verificationToken)
        );
    }

    @Override
    public User newPassword(PasswordDto passwordDto) {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(passwordDto.getToken());
        User user = verificationToken.getUser();
        user.setPassword(passwordDto.getNewPassword());
        applicationEventPublisher.publishEvent(
                new SuccessfulPasswordResetEvent(user)
        );
        return user;
    }
}
