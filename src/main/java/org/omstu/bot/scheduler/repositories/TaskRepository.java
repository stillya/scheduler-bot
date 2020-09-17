package org.omstu.bot.scheduler.repositories;

import java.util.List;

import org.omstu.bot.scheduler.entities.TaskEntity;

public interface TaskRepository {

    void save(TaskEntity task);

    TaskEntity findByChatId(Long id);

    void deleteByChatId(Long id);

    List<TaskEntity> findAll();
}
