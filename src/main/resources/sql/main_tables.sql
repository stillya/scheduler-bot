CREATE TABLE tasks
(
    beginLesson timestamp,
    chatId bigint,
    firstName TEXT,
    lastName TEXT,
    groupId INTEGER,
    content TEXT,
    isFinished BOOLEAN
);