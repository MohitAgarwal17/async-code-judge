CREATE TABLE test_cases (
    id              BIGSERIAL PRIMARY KEY,
    problem_id      BIGINT  NOT NULL REFERENCES problems(id) ON DELETE CASCADE,
    input           TEXT    NOT NULL,
    expected_output TEXT    NOT NULL,
    is_sample       BOOLEAN NOT NULL DEFAULT false
);

CREATE INDEX idx_test_cases_problem_id ON test_cases(problem_id);