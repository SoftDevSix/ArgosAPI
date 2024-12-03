package com.softdevsix.application.doa;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
class ProjectRepositoryDoaTest {

    @Autowired
    @MockBean
    ProjectServiceDoa projectServiceDoa;

    @Test
    void storeProject() {
        ProjectDoa projectDoa = new ProjectDoa(1L);
        projectServiceDoa.createProjectDoa(projectDoa);
        ProjectDoa aux = projectServiceDoa.getProjectDoa(1L);
        assertEquals(projectDoa,aux);
    }
}
