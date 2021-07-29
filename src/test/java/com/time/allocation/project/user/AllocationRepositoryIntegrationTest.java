package com.time.allocation.project.user;

import com.time.allocation.project.entity.Time;
import com.time.allocation.project.entity.Project;
import com.time.allocation.project.entity.User;
import com.time.allocation.project.repository.TimeRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Date;
import java.util.List;
import static com.time.allocation.project.utils.TestEntityManagerUtils.*;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class AllocationRepositoryIntegrationTest {

    @Autowired
    private TimeRepository timeRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void findAll() {
        // Arrange
        User u1 = createUser(entityManager, 1);
        User u2 = createUser(entityManager, 2);

        Project p1 = createProject(entityManager, 1);
        Project p2 = createProject(entityManager, 2);

        Time a1 = createTime(entityManager, new Date(), new Date(), u1, p1);
        Time a2 = createTime(entityManager, new Date(), new Date(), u1, p2);
        Time a3 = createTime(entityManager, new Date(), new Date(), u2, p1);
        Time a4 = createTime(entityManager, new Date(), new Date(), u2, p2);

        // Act
        List<Time> times = timeRepository.findAll();
       
        // Assert
        assertThat(times).contains(a1, a2, a3, a4);
        times.get(0).getUser().equals(u1);
        times.get(0).getProject().equals(p1);
        times.get(1).getUser().equals(u1);
        times.get(1).getProject().equals(p2);
        times.get(2).getUser().equals(u2);
        times.get(2).getProject().equals(p1);
        times.get(3).getUser().equals(u2);
        times.get(3).getProject().equals(p2);
    }

    @Test
    public void findById() {
        // Arrange
        User u1 = createUser(entityManager, 1);
        User u2 = createUser(entityManager, 2);

        Project p1 = createProject(entityManager, 1);
        Project p2 = createProject(entityManager, 2);

        Time a1 = createTime(entityManager, new Date(), new Date(), u1, p1);
        Time a2 = createTime(entityManager, new Date(), new Date(), u1, p2);
        Time a3 = createTime(entityManager, new Date(), new Date(), u2, p1);
        Time a4 = createTime(entityManager, new Date(), new Date(), u2, p2);

        // Act
        Time times = timeRepository.findById(a3.getId()).orElse(null);
        // Assert
        assertThat(times).isNotNull();
       
        
        times.getUser().equals(u2);
        times.getProject().equals(p1);
    }
}
