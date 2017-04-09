create table users (
    username varchar(50) not null primary key,
    password varchar(50) not null,
    enabled boolean not null
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table authorities (
    username varchar(50) not null,
    authority varchar(50) not null,
    constraint fk_authorities_users foreign key (username)
        references users (username)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
      create unique index ix_auth_username on authorities (username,authority);



create table groups (
    id bigint NOT NULL AUTO_INCREMENT,
    group_name varchar(50) NOT NULL,
    PRIMARY KEY (`Id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table group_authorities (
    group_id bigint NOT NULL,
    authority varchar(50) NOT NULL,
    constraint fk_group_authorities_group foreign key (group_id)
        references groups (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table group_members (
    id bigint NOT NULL AUTO_INCREMENT,
    username varchar(50) NOT NULL,
    group_id bigint NOT NULL,
    PRIMARY KEY (`Id`),
    constraint fk_group_members_group foreign key (group_id)
        references groups (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;