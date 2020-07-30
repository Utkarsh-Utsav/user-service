package dev.utkarsh.userservice.event.producer;

import dev.utkarsh.userservice.model.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;
@Getter
@Setter
public class SuccessfulPasswordResetEvent extends ApplicationEvent {
    private final User user;

    public SuccessfulPasswordResetEvent( User user) {
        super(user);
        this.user = user;
    }
}
