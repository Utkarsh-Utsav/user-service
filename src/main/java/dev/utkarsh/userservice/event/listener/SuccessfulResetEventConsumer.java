package dev.utkarsh.userservice.event.listener;


import dev.utkarsh.userservice.event.producer.SuccessfulResetEvent;
import dev.utkarsh.userservice.model.VerificationToken;
import dev.utkarsh.userservice.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class SuccessfulResetEventConsumer implements ApplicationListener<SuccessfulResetEvent> {
    @Autowired
    VerificationTokenRepository verificationTokenRepository;
    @Override
    public void onApplicationEvent(SuccessfulResetEvent successfulResetEvent) {
        VerificationToken verificationToken = successfulResetEvent.getVerificationToken();
        verificationToken.updateToken();
        verificationTokenRepository.save(verificationToken);
        //Todo: send mail
    }
}
