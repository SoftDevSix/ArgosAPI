package com.softdevsix.repositories;

import com.softdevsix.domain.entities.file.File;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface IFileRepository extends JpaRepository<File, UUID> {
}
