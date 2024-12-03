package com.softdevsix.application.doa;

import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.Assert.assertEquals;

@DataJpaTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class ProjectJPARepositoryDoaTest {

    @PersistenceContext
    private TestEntityManager entityManager;

    @MockBean
    ProjectRepositoryDoa projectRepository;

    @Test
    void storeProject(){
        ProjectDoa doa = new ProjectDoa(1L);
        entityManager.persist(doa);
        entityManager.flush();
        ProjectDoa doa2 = projectRepository.findById(1L).orElseThrow(() -> new EntityNotFoundException("ProjectDoa with ID not found"));
        assertEquals(doa,doa2);
    }
}
