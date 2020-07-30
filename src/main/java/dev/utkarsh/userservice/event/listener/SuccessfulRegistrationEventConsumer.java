package dev.utkarsh.userservice.event.listener;

import dev.utkarsh.userservice.event.SuccessfulRegistrationEvent;
import dev.utkarsh.userservice.model.User;
import dev.utkarsh.userservice.model.VerificationToken;
import dev.utkarsh.userservice.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class SuccessfulRegistrationEventConsumer implements ApplicationListener<SuccessfulRegistrationEvent> {

    @Autowired
    VerificationTokenRepository verificationTokenRepository;
    @Override
    public void onApplicationEvent(SuccessfulRegistrationEvent successfulRegistrationEvent) {
        User registeredUser = successfulRegistrationEvent.getRegisteredUser();
        VerificationToken token = new VerificationToken(registeredUser);
        verificationTokenRepository.save(token);
        //TODO: Send email to User
    }
}
