CREATE TABLE users (
    user_id varchar(36),
    user_name varchar(50),
    CONSTRAINT user_id PRIMARY KEY (user_id)
);

INSERT INTO users(user_id,user_name)
VALUES
    ('1', 'Bob'),
    ('2', 'Piter'),
    ('3', 'Alice');

