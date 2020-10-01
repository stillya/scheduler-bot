package org.omstu.bot.scheduler.services.crud;

import java.util.List;

import lombok.AllArgsConstructor;
import org.omstu.bot.scheduler.configuration.TaskMapper;
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
                    "update task set beginlesson = ?, firstname = ?, lastname = ?, groupid = ?, content = ?, isfinished = ?, subgroup = ? where chatid = ?",
                    task.getBeginLesson(),
                    task.getFirstName(),
                    task.getLastName(),
                    task.getGroup(),
                    task.getContent(),
                    task.getIsFinished(),
                    task.getSubGroup(),
                    task.getChatId());
        } else {
            this.jdbcTemplate.update(
                    "insert into task(beginlesson, chatid, firstname, lastname, groupid, content, subgroup, isfinished) values(?, ?, ?, ?, ?, ?, ?, ?)",
                    task.getBeginLesson(),
                    task.getChatId(),
                    task.getFirstName(),
                    task.getLastName(),
                    task.getGroup(),
                    task.getContent(),
                    task.getSubGroup(),
                    task.getIsFinished());
        }
    }

    @Override
    public TaskEntity findByChatId(Long id) {
        try {
            return (TaskEntity) this.jdbcTemplate.queryForObject("select * from task where task.chatid = ?",
                    new Object[]{id},
                    new TaskMapper());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void deleteByChatId(Long id) {
        this.jdbcTemplate.update("delete from task where task.chatid = ?", id);
    }

    @Override
    public List<TaskEntity> findAll() {
        return this.jdbcTemplate.query("select * from task", new TaskMapper());
    }
}
