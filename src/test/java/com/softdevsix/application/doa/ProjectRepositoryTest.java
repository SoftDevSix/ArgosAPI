package com.softdevsix.application.doa;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProjectRepositoryTest {

    ProjectRepository projectRepository;

    @Test
    void storeProject(){
        projectRepository.save(new Project());
    }
}