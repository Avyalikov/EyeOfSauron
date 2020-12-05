package org.example.EyeOfSauron.repos;

import org.example.EyeOfSauron.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessasgeRepository extends JpaRepository<Message, Long> {
    List<Message> findByFamilyId(Long familyId);
}
