package com.softdevsix.domain.repositories;


import com.softdevsix.domain.entities.project.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProjectRepository extends JpaRepository<Project, UUID> { }
