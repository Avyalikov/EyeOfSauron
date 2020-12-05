package org.example.EyeOfSauron.service;

import org.example.EyeOfSauron.domain.Archive;
import org.example.EyeOfSauron.domain.Message;
import org.example.EyeOfSauron.repos.ArchiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class ArchiveService {
    @Autowired
    ArchiveRepository archiveRepository;

    public List<Archive> loadByUserId(Long userId) { return archiveRepository.findByUserId(userId); }

    public boolean newArchive(List<Message> messages, Long userId, Long familyId) {
        String archiveName = String.format("archive_%d.txt", familyId);
        File archiveFile = new File("C:\\Users\\Avyal\\Desktop\\EyeOfSauron\\archive", archiveName);

        try (FileWriter writer = new FileWriter(archiveFile)){
            for (int i = 0; i < messages.size(); i++) {
                writer.write(messages.get(i).getMessage() + "\n");
            }

        } catch (IOException e) {
            return  false;
        }

        Archive archive = new Archive(userId, familyId, archiveName);
        archiveRepository.save(archive);
        return true;
    }
}
