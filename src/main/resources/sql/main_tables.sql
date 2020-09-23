CREATE TABLE task
(
    beginLesson timestamp,
    chatId bigint,
    firstName TEXT,
    lastName TEXT,
    groupId INTEGER,
    content TEXT,
    subgroup TEXT,
    isFinished BOOLEAN
);