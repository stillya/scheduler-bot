package org.omstu.bot.scheduler.services.crud;

import lombok.AllArgsConstructor;
import org.omstu.bot.scheduler.entities.TaskEntity;
import org.omstu.bot.scheduler.repositories.TaskRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class TaskCrudService implements TaskRepository {

    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(TaskEntity task) {
        TaskEntity oldTask = this.findByChatId(task.getChatId());
        if (oldTask != null) {
            this.jdbcTemplate.update(
                    "update task set beginLesson = ?, firstName = ?, lastName = ?, groupId = ?, content = ?, isFinished = ? where chatId = ?",
                    task.getBeginLesson(),
                    task.getFirstName(),
                    task.getLastName(),
                    task.getGroup(),
                    task.getContent(),
                    task.getIsFinished(),
                    task.getChatId());
        } else {
            this.jdbcTemplate.update(
                    "insert into task(beginLesson, chatId, firstName, lastName, groupId, content, isFinished) values(?, ?, ?, ?, ?, ?, ?)",
                    task.getBeginLesson(),
                    task.getChatId(),
                    task.getFirstName(),
                    task.getLastName(),
                    task.getGroup(),
                    task.getContent(),
                    task.getIsFinished());
        }
    }

    @Override
    public TaskEntity findByChatId(Long id) {
        return this.jdbcTemplate.queryForObject("select * from task where task.chatId = ?", TaskEntity.class, id);
    }

    @Override
    public void deleteByChatId(Long id) {
        this.jdbcTemplate.update("delete from task where task.chatId = ?", id);
    }
}
