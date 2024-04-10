CREATE TABLE users (
    user_id varchar(50),
    user_name varchar,
    user_email
        CONSTRAINT user_id PRIMARY KEY (user_id)
);

ALTER TABLE records ADD FOREIGN KEY (author_id) REFERENCES "users" (user_id);