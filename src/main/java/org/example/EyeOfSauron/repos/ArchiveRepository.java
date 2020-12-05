package org.example.EyeOfSauron.repos;

import org.example.EyeOfSauron.domain.Archive;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArchiveRepository extends JpaRepository<Archive, Long> {
    List<Archive> findByUserId(Long userId);
}
