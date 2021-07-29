package com.time.allocation.project.utils;

import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.time.allocation.project.entity.Time;
import com.time.allocation.project.entity.Project;
import com.time.allocation.project.entity.User;

import java.time.DayOfWeek;
import java.util.Collections;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class TestEntityManagerUtils {

   
    public static User createUser(TestEntityManager entityManager, int index) {
        return createUser(entityManager, String.format("name%d", index), String.format("email%d", index), String.format("login%d", index), String.format("password%d", index));
    }

    public static User createUser(TestEntityManager entityManager, String name, String email, String login, String password) {
        User entity = entityManager.persistFlushFind(new User(null, name, email, login, password, Collections.emptyList()));
        assertThat(entity).isNotNull();
        entityManager.flush();
        return entity;
    }
    
    public static Project createProject(TestEntityManager entityManager, int index) {
        return createProject(entityManager, String.format("title%d", index), String.format("description%d", index));
    }

    public static Project createProject(TestEntityManager entityManager, String title, String description) {
    	Project entity = entityManager.persistFlushFind(new Project(null, title, description, Collections.emptyList()));
        assertThat(entity).isNotNull();
        entityManager.flush();
        return entity;
    }

    public static Time createTime(TestEntityManager entityManager, Date startAt, Date endedAt, User user, Project project) {
		Time entity = entityManager.persistFlushFind(new Time(null, startAt, endedAt, user, project));
		assertThat(entity).isNotNull();
		entityManager.flush();
		return entity;
	}

}
