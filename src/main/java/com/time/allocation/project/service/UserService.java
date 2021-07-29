package com.time.allocation.project.service;

import com.time.allocation.project.exception.EntityInstanceNotFoundException;
import com.time.allocation.project.entity.User;
import com.time.allocation.project.repository.UserRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@Transactional
@Service
public class UserService implements UserDetailsService{

    private final UserRepository userRepository;
    
    private final PasswordEncoder passwordEncoder;
    
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        super();
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = true)
    public List<User> findAll(String name) {
        if (name == null) {
            return userRepository.findAll();
        } else {
            return userRepository.findByNameContainingIgnoreCase(name);
        }
    }

    @Transactional(readOnly = true)
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(getEntityInstanceNotFoundExceptionSupplier(id));
    }

    public User save(User user) {
        user.setId(null);
        return internalSave(user);
    }

    public User update(User user) {
        Long id = user.getId();
        if (id == null || !userRepository.existsById(id)) {
            throw getEntityInstanceNotFoundExceptionSupplier(id).get();
        }

        return internalSave(user);
    }
    
    private User internalSave(User user)
    {
    	user.setPassword(passwordEncoder.encode(user.getPassword()));
    	 return userRepository.save(user);
    }

    private Supplier<EntityInstanceNotFoundException> getEntityInstanceNotFoundExceptionSupplier(Long id) {
        return () -> new EntityInstanceNotFoundException(User.class, id);
    }

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User loggedUser = userRepository.findByLogin(username).orElseThrow(() -> new UsernameNotFoundException(username));
		return new org.springframework.security.core.userdetails.User(loggedUser.getLogin(),loggedUser.getPassword(),new ArrayList<>());
	
	}
}
