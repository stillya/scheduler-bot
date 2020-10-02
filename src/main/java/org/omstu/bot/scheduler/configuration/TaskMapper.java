package org.omstu.bot.scheduler.configuration;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.omstu.bot.scheduler.entities.TaskEntity;
import org.springframework.jdbc.core.RowMapper;

public class TaskMapper implements RowMapper {

    @Override
    public TaskEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        return TaskEntity.builder()
                .content(rs.getString("content"))
                .beginLesson(rs.getTimestamp("beginlesson"))
                .chatId(rs.getLong("chatid"))
                .firstName(rs.getString("firstname"))
                .group(rs.getInt("groupid"))
                .isFinished(rs.getBoolean("isfinished"))
                .lastName(rs.getString("lastname"))
                .subGroup(rs.getString("subgroup"))
                .build();
    }
}
