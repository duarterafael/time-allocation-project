package com.time.allocation.project.user;

import com.time.allocation.project.entity.Project;
import com.time.allocation.project.repository.ProjectRepository;
import com.time.allocation.project.utils.TestEntityManagerUtils;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ProjectRepositoryIntegrationTest {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void findByNameContainingIgnoreCase_whenPassContainedName_thenReturnTheCourseList() {
        // Arrange
        Project p1 = TestEntityManagerUtils.createProject(entityManager, "Project 1", "dsc");
        Project p2 = TestEntityManagerUtils.createProject(entityManager, "Project 2", "dsc");
        Project p3 = TestEntityManagerUtils.createProject(entityManager, "Project 3", "dsc");

        Project p4 = TestEntityManagerUtils.createProject(entityManager, "Other name1", "dsc");
        Project p5 = TestEntityManagerUtils.createProject(entityManager, "Other name2", "dsc");
        Project p6 = TestEntityManagerUtils.createProject(entityManager, "Other name3", "dsc");

        // Act
        List<Project> courses1 = projectRepository.findByTitleContainingIgnoreCase("ojec");
        List<Project> courses2 = projectRepository.findByTitleContainingIgnoreCase("NAM");

        // Assert
        assertThat(courses1).contains(p1, p2, p3);
        assertThat(courses1).doesNotContain(p4, p5, p6);

        assertThat(courses2).contains(p4, p5, p6);
        assertThat(courses2).doesNotContain(p1, p2, p3);
    }

    @Test
    public void findByNameContainingIgnoreCase_whenPassNotContainedName_thenReturnEmptyCourseList() {
        // Arrange
    	 Project p1 = TestEntityManagerUtils.createProject(entityManager, "Project 1", "dsc");
         Project p2 = TestEntityManagerUtils.createProject(entityManager, "Project 2", "dsc");
         Project p3 = TestEntityManagerUtils.createProject(entityManager, "Project 3", "dsc");

        // Act
        List<Project> courses = projectRepository.findByTitleContainingIgnoreCase("NAM");

        // Assert
        assertThat(courses).isEmpty();
    }
}
