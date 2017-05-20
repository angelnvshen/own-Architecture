drop table if exists users;
create table users (
    id bigint NOT NULL AUTO_INCREMENT primary key,
    username varchar(50) not null,
    password varchar(100) not null,
    enabled boolean not null
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table if exists authorities;
create table authorities (
    id bigint NOT NULL AUTO_INCREMENT primary key,
    username varchar(50) not null,
    authority varchar(50) not null,
    constraint fk_authorities_users foreign key (username)
        references users (username)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
 create unique index ix_auth_username on authorities (username,authority);

drop table if exists groups;
create table groups (
    id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    group_name varchar(50) NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table if exists group_authorities;
create table group_authorities (
    id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    group_id bigint NOT NULL,
    authority varchar(50) NOT NULL,
    constraint fk_group_authorities_group foreign key (group_id)
        references groups (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table if exists group_members;
create table group_members (
    id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    username varchar(50) NOT NULL,
    group_id bigint NOT NULL,
    constraint fk_group_members_group foreign key (group_id)
        references groups (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- -----------------------------
insert into users values(1,'user','user',true);
insert into users values(2,'admin','admin',true);

insert into authorities values(1,'user','USER');
insert into authorities values(2,'admin','USER');
insert into authorities values(3,'admin','ADMIN');

insert into groups values(1,'user');
insert into groups values(2,'admin');
insert into group_authorities values(1,1,'USER');
insert into group_authorities values(2,2,'ADMIN');
insert into group_authorities values(3,2,'USER');
insert into group_members values(1,'user',1);
insert into group_members values(2,'admin',2);
insert into group_members values(3,'admin',2);