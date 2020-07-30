package dev.utkarsh.userservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Entity
@Setter
@Getter
public class VerificationToken {
    private  static  final int VALIDITY_TIME = 4 * 60; // minutes

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String token;
    @OneToOne(targetEntity = User.class)
    private User user;
    private Date expiryTime;

    public  VerificationToken(){
        super();
    }

    public VerificationToken(User user) {
        this.user = user;
        this.token = generateRandomUniqueToken();
        this.expiryTime = calculateExpiryTime();
    }
    public  void updateToken()
    {
        this.token = generateRandomUniqueToken();
        this.expiryTime = calculateExpiryTime();
    }
    private String generateRandomUniqueToken(){
        return UUID.randomUUID().toString();
    }
    private Date calculateExpiryTime(){
        Calendar calendar = Calendar.getInstance();
        Date currentDateAndTime = new Date();
        calendar.setTimeInMillis(currentDateAndTime.getTime());
        calendar.add(Calendar.MINUTE, VALIDITY_TIME);
        return calendar.getTime();

    }
}

