CREATE TABLE if not exists user (
    id SERIAL,
    email varchar(100) NOT NULL,
    password varchar(100) NOT NULL,
    primary key (id)
);

CREATE TABLE if not exists event (
    id SERIAL,
    title varchar(100) NOT NULL,
    description varchar(255) NOT NULL,
    price float not null,
    date timestamp not null,
    user_id integer not null,
    primary key (id)
);

CREATE TABLE if not exists booking (
    id SERIAL,
    user_id integer not null,
    event_id integer not null,
    created_at timestamp not null,
    updated_at timestamp not null,
    primary key (id)
);

CREATE INDEX idx_user_id ON booking (user_id);
