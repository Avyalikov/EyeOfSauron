package org.example.EyeOfSauron.domain;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "msgs")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long userId;
    private String userName;
    private String message;
    private Long familyId;
    private LocalTime messageTime;

    public Message() {}

    public Message(Long userId, String message, Long familyId, String userName, LocalTime messageTime) {
        this.userId = userId;
        this.userName = userName;
        this.familyId = familyId;
        this.message = message;
        this.messageTime = messageTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() { return this.userId; }

    public void setUserId(Long userId) { this.userId = userId; }

    public String getMessage() { return this.message; }

    public void setMessage(String message) { this.message = message; }

    public Long getFamilyId() { return this.familyId; }

    public void setFamilyId(Long familyId) { this.familyId = familyId; }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public LocalTime getMessageTime() { return this.messageTime; }

    public void setMessageTime(LocalTime messageTime) { this.messageTime = messageTime; }
}
