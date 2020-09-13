package org.omstu.bot.scheduler.repositories;

import org.omstu.bot.scheduler.entities.TaskEntity;

public interface TaskRepository {
    void save(TaskEntity task);
    TaskEntity findByChatId(Long id);
    void deleteByChatId(Long id);
}
