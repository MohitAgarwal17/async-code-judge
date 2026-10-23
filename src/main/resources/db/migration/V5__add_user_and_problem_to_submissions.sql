ALTER TABLE submissions
    ADD COLUMN user_id    BIGINT NOT NULL REFERENCES users(id),
    ADD COLUMN problem_id BIGINT NOT NULL REFERENCES problems(id);