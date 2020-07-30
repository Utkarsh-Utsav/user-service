package dev.utkarsh.userservice.event.producer;

import dev.utkarsh.userservice.model.VerificationToken;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class SuccessfulResetEvent extends ApplicationEvent {
    private   final VerificationToken verificationToken;

    public SuccessfulResetEvent(VerificationToken verificationToken) {
        super(verificationToken);
        this.verificationToken = verificationToken;
    }
}
