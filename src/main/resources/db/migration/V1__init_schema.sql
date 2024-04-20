CREATE TABLE IF NOT EXISTS "user"
(
    id         UUID PRIMARY KEY,
    email      VARCHAR(255)                  NOT NULL,
    password   VARCHAR(255)                  NOT NULL,
    first_name VARCHAR(100),
    last_name  VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS project
(
    id          UUID PRIMARY KEY,
    name        VARCHAR(255)                  NOT NULL,
    description TEXT,
    user_id     UUID                          NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    FOREIGN KEY (user_id) REFERENCES "user" (id)
);

CREATE TABLE IF NOT EXISTS board
(
    id          UUID PRIMARY KEY,
    name        VARCHAR(255),
    description TEXT,
    project_id  UUID                          NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    FOREIGN KEY (project_id) REFERENCES project (id)
);

CREATE TABLE IF NOT EXISTS "column"
(
    id             UUID PRIMARY KEY,
    name           VARCHAR(255)                  NOT NULL,
    order_position INTEGER                       NOT NULL CHECK (order_position > 0),
    board_id       UUID                          NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    FOREIGN KEY (board_id) REFERENCES board (id)
);

CREATE TABLE IF NOT EXISTS note
(
    id          UUID PRIMARY KEY,
    description TEXT                          NOT NULL,
    user_id     UUID                          NOT NULL,
    column_id   UUID                          NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    FOREIGN KEY (user_id) REFERENCES "user" (id),
    FOREIGN KEY (column_id) REFERENCES "column" (id)
);

CREATE TABLE IF NOT EXISTS action
(
    id          UUID PRIMARY KEY,
    description TEXT,
    user_id     UUID                          NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    FOREIGN KEY (user_id) REFERENCES "user" (id)
);

CREATE TABLE IF NOT EXISTS action_board
(
    action_id UUID NOT NULL,
    board_id  UUID NOT NULL,
    PRIMARY KEY (action_id, board_id),
    FOREIGN KEY (action_id) REFERENCES action (id),
    FOREIGN KEY (board_id) REFERENCES board (id)
);
