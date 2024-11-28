package com.softdevsix.domain.repository;

import com.softdevsix.domain.entity.file.File;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IFileRepository extends JpaRepository<File, UUID> {
}
