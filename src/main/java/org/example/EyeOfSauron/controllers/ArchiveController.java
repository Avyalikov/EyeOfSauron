package org.example.EyeOfSauron.controllers;

import org.example.EyeOfSauron.domain.Archive;
import org.example.EyeOfSauron.domain.User;
import org.example.EyeOfSauron.service.ArchiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class ArchiveController {
    @Autowired
    ArchiveService archiveService;

    @PreAuthorize("hasAnyAuthority('USER')")
    @GetMapping("/archives")
    public String archives(@AuthenticationPrincipal User user, Model model) {
        Long userId = user.getId();
        List<Archive> archives = archiveService.loadByUserId(userId);

        model.addAttribute("archive", archives);
        return "archives";
    }

    @PreAuthorize("hasAnyAuthority('USER')")
    @RequestMapping(value = "/archives/{file_name}", method = RequestMethod.GET)
    public void downloadArchive(@PathVariable("file_name") String fileName, HttpServletResponse response) {
        Path file = Paths.get("C:\\Users\\Avyal\\Desktop\\EyeOfSauron\\archive\\" + fileName);
        if (Files.exists(file)){
            response.setHeader("Content-disposition", "attachment;filename=" + fileName);
            response.setContentType("text/plain");
            try {
                Files.copy(file, response.getOutputStream());
                response.getOutputStream().flush();
            } catch (IOException e) {
                throw new RuntimeException("IOError writing file to output stream");
            }
        }
    }
}
