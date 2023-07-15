CREATE TABLE IF NOT EXISTS board
(
    id          BIGINT NOT NULL AUTO_INCREMENT,
    title       VARCHAR(100),
    author      VARCHAR(32),
    description VARCHAR(300),

    PRIMARY KEY (id)
);
