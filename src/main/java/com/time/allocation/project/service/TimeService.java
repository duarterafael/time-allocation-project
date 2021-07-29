package com.time.allocation.project.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.time.allocation.project.entity.Time;
import com.time.allocation.project.entity.Project;
import com.time.allocation.project.entity.User;
import com.time.allocation.project.exception.TimeCollisionException;
import com.time.allocation.project.exception.EntityInstanceNotFoundException;
import com.time.allocation.project.repository.TimeRepository;

import java.util.List;
import java.util.function.Supplier;

@Transactional
@Service
public class TimeService {

    private final TimeRepository timeRepository;
    private final UserService userService;
    private final ProjectService projectService;

    public TimeService(TimeRepository timeRepository, UserService userService,
    		ProjectService projectService) {
        super();
        this.timeRepository = timeRepository;
        this.userService = userService;
        this.projectService = projectService;
    }

    @Transactional(readOnly = true)
    public List<Time> findAll() {
        return timeRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Time findById(Long id) {
        return timeRepository.findById(id).orElseThrow(getEntityInstanceNotFoundExceptionSupplier(id));
    }

    public Time save(Time time) {
        time.setId(null);
        return saveInternal(time);
    }

    public Time update(Time time) {
        Long id = time.getId();
        if (id == null || !timeRepository.existsById(id)) {
            throw getEntityInstanceNotFoundExceptionSupplier(id).get();
        }

        return saveInternal(time);
    }

    private Time saveInternal(Time time) {
    	if (time.getStartAt().before(time.getEndedAt() ) && !hasCollision(time)) {
            time = timeRepository.save(time);

            User user = time.getUser();
            user = userService.findById(user.getId());
            time.setUser(user);

            Project project = time.getProject();
            project = projectService.findById(project.getId());
            time.setProject(project);

            return time;
        } else {
            throw new TimeCollisionException(time);
        }
    }
    
    @Transactional(readOnly = true)
    public List<Time> findByUser(Long userId) {
        return timeRepository.findByUserId(userId);
    }
    
    @Transactional(readOnly = true)
    public List<Time> findByProject(Long projectId) {
        return timeRepository.findByProjectId(projectId);
    }
    
    boolean hasCollision(Time newTime) {
        boolean hasCollision = false;

        List<Time> currentTimes = timeRepository.findByUserId(newTime.getUser().getId());

        for (Time currentTime : currentTimes) {
            hasCollision = hasCollision(currentTime, newTime);
            if (hasCollision) {
                break;
            }
        }

        return hasCollision;
    }

    private boolean hasCollision(Time currentTime, Time newTime) {
        return !currentTime.getId().equals(newTime.getId())
                && currentTime.getStartAt().compareTo(newTime.getEndedAt()) < 0
                && newTime.getStartAt().compareTo(currentTime.getEndedAt()) < 0;
    }

    private Supplier<EntityInstanceNotFoundException> getEntityInstanceNotFoundExceptionSupplier(Long id) {
        return () -> new EntityInstanceNotFoundException(Time.class, id);
    }
}
