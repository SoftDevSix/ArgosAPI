package com.softdevsix.application.doa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepositoryDoa extends JpaRepository<ProjectDoa, Long> {
}
