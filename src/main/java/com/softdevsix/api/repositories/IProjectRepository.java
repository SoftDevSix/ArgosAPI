package com.softdevsix.api.repositories;

import com.softdevsix.api.domain.entities.file.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IProjectRepository extends JpaRepository<File, UUID> {
}
