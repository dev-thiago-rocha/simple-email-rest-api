CREATE TABLE IF NOT EXISTS emaildb.email (
    id                   BIGINT       PRIMARY KEY AUTO_INCREMENT,
    destination          VARCHAR(100) NOT NULL,
    sender               VARCHAR(100) NOT NULL,
    title                VARCHAR(100) NOT NULL,
    text                 VARCHAR(500) NOT NULL,
    status               VARCHAR(100) NOT NULL
)

ENGINE = InnoDB;