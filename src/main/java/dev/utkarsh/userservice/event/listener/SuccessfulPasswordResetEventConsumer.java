package dev.utkarsh.userservice.event.listener;

import dev.utkarsh.userservice.event.producer.SuccessfulPasswordResetEvent;
import dev.utkarsh.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class SuccessfulPasswordResetEventConsumer implements ApplicationListener<SuccessfulPasswordResetEvent> {
    @Autowired
    UserRepository userRepository;

    @Override
    public void onApplicationEvent(SuccessfulPasswordResetEvent successfulPasswordResetEvent) {
        userRepository.save(successfulPasswordResetEvent.getUser());
        //TODO: Send Mail, password has been reset
    }
}
