CREATE TABLE users (
    user_id  varchar(36),
    user_name varchar(50),
    CONSTRAINT user_id PRIMARY KEY (user_id)
);

INSERT INTO users (user_id, user_name)
VALUES
    ('1', 'Alice'),
    ('2', 'Bob'),
    ('3', 'Charlie');

CREATE TABLE role (
    role_id varchar(36),
    role_name varchar(36),
    user_id varchar(36),
    CONSTRAINT role_id PRIMARY KEY (role_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

INSERT INTO role (role_id, role_name,user_id)
VALUES
    ('1', 'Admin','1'),
    ('2', 'User','2'),
    ('3', 'User','3');

CREATE TABLE Permission (
    permission_id varchar(36),
    permission_name varchar(50),
    CONSTRAINT permission_id PRIMARY KEY (permission_id)
);

INSERT INTO permission (permission_id, permission_name)
VALUES
    ('1', 'READ'),
    ('2', 'WRITE');

CREATE TABLE Role_Permission (
    role_id varchar(36),
    permission_id varchar(36),
    PRIMARY KEY (role_id, permission_id),
    FOREIGN KEY (role_id) REFERENCES Role(role_id),
    FOREIGN KEY (permission_id) REFERENCES Permission(permission_id)
);