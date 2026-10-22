CREATE TABLE problems (
    id               BIGSERIAL PRIMARY KEY,
    title            VARCHAR(200) NOT NULL,
    slug             VARCHAR(100) NOT NULL UNIQUE,
    description      TEXT         NOT NULL,
    difficulty       VARCHAR(20)  NOT NULL,
    time_limit_ms    INTEGER      NOT NULL CHECK (time_limit_ms > 0),
    memory_limit_mb  INTEGER      NOT NULL CHECK (memory_limit_mb > 0),
    created_at       TIMESTAMPTZ  NOT NULL DEFAULT now()
);