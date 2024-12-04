package com.softdevsix.domain.repositories;

import com.softdevsix.domain.entities.file.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface IFileRepository extends JpaRepository<File, UUID> {
}
