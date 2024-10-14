CREATE TABLE IF NOT EXISTS Event (
    id SERIAL PRIMARY KEY,
    title varchar(250) NOT NULL,
    start_on TIMESTAMP NOT NULL,
    complete_on TIMESTAMP NOT NULL,
    participant INT NOT NULL,
    location varchar(10) NOT NULL,
    version INT
);