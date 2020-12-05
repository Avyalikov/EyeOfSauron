package org.example.EyeOfSauron.controllers;


import org.example.EyeOfSauron.domain.Message;
import org.example.EyeOfSauron.domain.User;
import org.example.EyeOfSauron.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;


@Controller
public class MessageController {
    @Autowired
    MessageService messageService;

    private LocalTime lastMessageTime;

    private boolean messagesNotEmpty;

    @PreAuthorize("hasAnyAuthority('USER')")
    @GetMapping("/messages")
    public String messages(@AuthenticationPrincipal User user, Model model) {
        Long familyId = user.getFamilyId();
        List<Message> messages = messageService.loadByFamilyId(familyId);
        if (messages.size() != 0) {
            messagesNotEmpty = true;
            lastMessageTime = messages.get(messages.size()-1).getMessageTime();
        }
        model.addAttribute("message", messages);
        model.addAttribute("lmt", lastMessageTime);
        return "messages";
    }

    @PreAuthorize("hasAnyAuthority('USER')")
    @PostMapping("/messages")
    public String addMessage(@AuthenticationPrincipal User user, @RequestParam String message, Map<String, Object> model) {
        Long familyId = user.getFamilyId();
        Long userId = user.getId();
        String userName = user.getFullName();
        LocalTime messageTime = LocalTime.now();

        Message msg = new Message(userId, message, familyId, userName, messageTime);

        if(!messagesNotEmpty) {
            messageService.divorceMessage(msg);
        }

        else if(messageService.writeMessage(msg, lastMessageTime).equals("Family")) {
            return "redirect:/failureFamily";
        }

        else if(messageService.writeMessage(msg, lastMessageTime).equals("Time")) {
            return "redirect:/failureTime";
        }

        return "redirect:/complete";

    }
}
