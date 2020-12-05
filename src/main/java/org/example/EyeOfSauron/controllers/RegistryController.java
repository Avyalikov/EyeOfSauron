package org.example.EyeOfSauron.controllers;

import org.example.EyeOfSauron.domain.Message;
import org.example.EyeOfSauron.domain.User;
import org.example.EyeOfSauron.service.ArchiveService;
import org.example.EyeOfSauron.service.MessageService;
import org.example.EyeOfSauron.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@Controller
public class RegistryController {
    @Autowired
    private UserService userService;
    @Autowired
    MessageService messageService;
    @Autowired
    ArchiveService archiveService;

    @PreAuthorize("hasAnyAuthority('REGISTRY')")
    @GetMapping("/divorce")
    public String divorce() { return "divorce"; }

    @PreAuthorize("hasAnyAuthority('REGISTRY')")
    @PostMapping("/divorce")
    public String doDivorce(@RequestParam String username1, @RequestParam String username2,
                            @RequestParam String message1, @RequestParam String message2, Map<String, Object> model) {
        User user1 = userService.loadByUsername(username1);
        User user2 = userService.loadByUsername(username2);

        if (user1.getFamilyId() != user2.getFamilyId() & user1.getFamilyId() != 0L){
            model.put("error", "Пользователи не являются семьей");
            return "divorce";
        }

        Message msg1 = new Message(user1.getId(), message1, user1.getFamilyId(), user1.getFullName(), LocalTime.now());
        Message msg2 = new Message(user2.getId(), message2, user2.getFamilyId(), user2.getFullName(), LocalTime.now());
        messageService.divorceMessage(msg1);
        messageService.divorceMessage(msg2);

        Long familyId = user1.getFamilyId();
        List<Message> messages = messageService.loadByFamilyId(familyId);

        archiveService.newArchive(messages, user1.getId(), familyId);
        archiveService.newArchive(messages, user2.getId(), familyId);

        messageService.deleteMessages(messages);

        userService.updateFamilyId(user1, 0L);
        userService.updateFamilyId(user2, 0L);

        return "redirect:/divorceComplete";
    }

    @PreAuthorize("hasAnyAuthority('REGISTRY')")
    @GetMapping("/marry")
    public String marry() { return "marry"; }

    @PreAuthorize("hasAnyAuthority('REGISTRY')")
    @PostMapping("/marry")
    public String doMarry(@RequestParam String username1, @RequestParam String username2, Long familyId,
                            Map<String, Object> model) {
        User user1 = userService.loadByUsername(username1);
        User user2 = userService.loadByUsername(username2);

        if (user1.getFamilyId() != 0L || user2.getFamilyId() != 0L){
            model.put("error", "Один из пользователей уже состоит в браке!");
            return "marry";
        }

        userService.updateFamilyId(user1, familyId);
        userService.updateFamilyId(user2, familyId);

        return "redirect:/marryComplete";
    }
}
