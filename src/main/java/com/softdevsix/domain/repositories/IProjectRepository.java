package com.softdevsix.domain.repositories;


import com.softdevsix.domain.entities.project.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IProjectRepository extends JpaRepository<Project, UUID> {
}
