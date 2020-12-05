package org.example.EyeOfSauron.domain;

import javax.persistence.*;

@Entity
@Table(name = "msgs_archive")
public class Archive {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long userId;
    private Long familyId;
    private String archiveFile;

    public Archive() {}

    public Archive(Long userId, Long familyId, String archiveFile) {
        this.userId = userId;
        this.familyId = familyId;
        this.archiveFile = archiveFile;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFamilyId() {
        return familyId;
    }

    public void setFamilyId(Long familyId) {
        this.familyId = familyId;
    }

    public String getArchiveFile() {
        return archiveFile;
    }

    public void setArchiveFile(String archiveFile) {
        this.archiveFile = archiveFile;
    }
}
