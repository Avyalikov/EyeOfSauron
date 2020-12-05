package org.example.EyeOfSauron.service;

import org.example.EyeOfSauron.domain.Message;
import org.example.EyeOfSauron.repos.MessasgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

import static java.time.temporal.ChronoUnit.MINUTES;


@Service
public class MessageService {
    @Autowired
    MessasgeRepository messasgeRepository;

    public List<Message> loadByFamilyId(Long familyId) {
        return messasgeRepository.findByFamilyId(familyId);
    }

    public String writeMessage(Message message, LocalTime lastMessageTime) {
        if (message.getFamilyId() == 0L){
            return "Family";
        }

        if (MINUTES.between(lastMessageTime, message.getMessageTime()) < 2L) {
            return "Time";
        }

        messasgeRepository.save(message);

        return "Complete";
    }

    public boolean divorceMessage(Message message) {
        if (message.getFamilyId() == 0L){
            return false;
        }

        messasgeRepository.save(message);
        return true;
    }

    public void deleteMessages(List<Message> messages) {
        messasgeRepository.deleteAll(messages);
    }
}
