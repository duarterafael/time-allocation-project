package com.time.allocation.project.user;


import com.time.allocation.project.entity.User;
import com.time.allocation.project.repository.UserRepository;
import com.time.allocation.project.utils.TestEntityManagerUtils;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@DataJpaTest
public class UserRepositoryIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager entityManager;


    @Test
    public void findByName_ContainingAndIgnoreCase() {
       
        User u1 = TestEntityManagerUtils.createUser(entityManager, 1);
        User u2 = TestEntityManagerUtils.createUser(entityManager, "Cicrano José", "cj@email.com", "cj", "123");
        User u3 = TestEntityManagerUtils.createUser(entityManager, "Beltrano José", "bj@gmail.com", "bj", "123");

        User u4 = TestEntityManagerUtils.createUser(entityManager, "Fulano João", "fj@gmail.com", "fj", "123");
        User u5 = TestEntityManagerUtils.createUser(entityManager, "Cicrano João", "cj1@gmail.com", "cj1", "123");
        User u6 = TestEntityManagerUtils.createUser(entityManager, "Beltrano João", "bj1@gmail.com", "bj1", "123");

        // Act
        List<User> professors1 = userRepository.findByNameContainingIgnoreCase("jOs");
        List<User> professors2 = userRepository.findByNameContainingIgnoreCase("jOã");

        // Assert
        assertThat(professors1).contains(u2, u3);
        assertThat(professors1).doesNotContain(u4, u5, u6);

        assertThat(professors2).contains(u4, u5, u6);
        assertThat(professors2).doesNotContain(u2, u3);
    }
    
    @Test
    public void Find_by_id() {
       
        User user = TestEntityManagerUtils.createUser(entityManager, 1);
       
        User userInDb = userRepository.save(user);

        User stored = userRepository.findById(userInDb.getId()).orElse(null);
        assertThat(stored).isNotNull();

        assertThat(stored.getId()).isEqualTo(userInDb.getId());
        assertThat(stored.getName()).isEqualTo(userInDb.getName());
        assertThat(stored.getLogin()).isEqualTo(userInDb.getLogin());
        assertThat(stored.getEmail()).isEqualTo(userInDb.getEmail());
    }
    
    @Test
    public void Save_sucess() {
       
        User user = new User(null, "user name", "user email", "user login", "user pw", null);
       
        User userInDb = userRepository.save(user);

        User stored = userRepository.findById(userInDb.getId()).orElse(null);
        assertThat(stored).isNotNull();

        assertThat(stored.getId()).isEqualTo(userInDb.getId());
        assertThat(stored.getName()).isEqualTo(userInDb.getName());
        assertThat(stored.getLogin()).isEqualTo(userInDb.getLogin());
        assertThat(stored.getEmail()).isEqualTo(userInDb.getEmail());
    }
    
    @Test
    public void Update_sucess() {
       
        User user = new User(null, "user name", "user email", "user login", "user pw", null);
       
        User userInDb = userRepository.save(user);

        User stored = userRepository.findById(userInDb.getId()).orElse(null);
        assertThat(stored).isNotNull();

        assertThat(stored.getId()).isEqualTo(userInDb.getId());
        assertThat(stored.getName()).isEqualTo(userInDb.getName());
        assertThat(stored.getLogin()).isEqualTo(userInDb.getLogin());
        assertThat(stored.getEmail()).isEqualTo(userInDb.getEmail());
        
        user.setName("other name");
        user.setEmail("other email");
        user.setLogin("other login");
        user.setPassword("other pw");
        
        User userUpdated = userRepository.save(user);

        User userUpdatedStored = userRepository.findById(userInDb.getId()).orElse(null);
        assertThat(userUpdatedStored).isNotNull();

        assertThat(userUpdatedStored.getId()).isEqualTo(userUpdated.getId());
        assertThat(userUpdatedStored.getName()).isEqualTo(userUpdated.getName());
        assertThat(userUpdatedStored.getLogin()).isEqualTo(userUpdated.getLogin());
        assertThat(userUpdatedStored.getEmail()).isEqualTo(userUpdated.getEmail());     
    }

}
