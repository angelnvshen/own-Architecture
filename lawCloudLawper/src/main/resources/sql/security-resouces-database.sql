/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/5/10 15:43:52                           */
/*==============================================================*/


drop table if exists PERSISTENT_LOGINS;

drop table if exists SYS_AUTHORITIES;

drop table if exists SYS_AUTHORITIES_RESOURCES;

drop table if exists SYS_MODULES;

drop table if exists SYS_RESOURCES;

drop table if exists SYS_ROLES;

drop table if exists SYS_ROLES_AUTHORITIES;

drop table if exists SYS_ROLES_MOUDLES;

drop table if exists SYS_USERS;

drop table if exists SYS_USERS_ROLES;

/*==============================================================*/
/* Table: PERSISTENT_LOGINS                                     */
/*==============================================================*/
create table `PERSISTENT_LOGINS`
(
   `USERNAME` varchar(64),
   `SERIES` varchar(64) not null,
   `TOKEN` varchar(64),
   `LAST_USED` timestamp,
   primary key (`SERIES`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Spring Remember me 持久化';

/*==============================================================*/
/* Table: SYS_AUTHORITIES                                       */
/*==============================================================*/
create table `SYS_AUTHORITIES`
(
   `AUTHORITY_ID`varchar(100) not null,
   `AUTHORITY_MARK`varchar(100),
   `AUTHORITY_NAME`varchar(100) not null,
   `AUTHORITY_DESC`varchar(200),
   `MESSAGE`varchar(100),
   `ENABLE`numeric(8,0),
   `ISSYS`numeric(8,0),
   `MODULE_ID`varchar(100),
   primary key (`AUTHORITY_ID`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限';

/*==============================================================*/
/* Table: SYS_AUTHORITIES_RESOURCES                             */
/*==============================================================*/
create table `SYS_AUTHORITIES_RESOURCES`
(
   `ID`varchar(100) not null,
   `RESOURCE_ID`varchar(100) not null,
   `AUTHORITY_ID`varchar(100) not null,
   primary key (`ID`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限资源';

/*==============================================================*/
/* Table: SYS_MODULES                                           */
/*==============================================================*/
create table `SYS_MODULES`
(
   `MODULE_ID`varchar(100) not null,
   `MODULE_NAME`varchar(100) not null,
   `MODULE_DESC`varchar(200),
   `MODULE_TYPE`varchar(100),
   `PARENT`varchar(100),
   `MODULE_URL`varchar(100),
   `I_LEVEL`numeric(8,0)
          COMMENT '1',
   `LEAF`numeric(8,0),
   `APPLICATION`varchar(100),
   `CONTROLLER`varchar(100),
   `ENABLE`numeric(1,0),
   `PRIORITY`numeric(8,0),
   primary key (`MODULE_ID`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='模块';

/*==============================================================*/
/* Table: SYS_RESOURCES                                         */
/*==============================================================*/
create table `SYS_RESOURCES`
(
   `RESOURCE_ID`varchar(100) not null,
   `RESOURCE_TYPE`varchar(100)
          COMMENT 'URL,METHOD',
   `RESOURCE_NAME`varchar(100),
   `RESOURCE_DESC`varchar(200),
   `RESOURCE_PATH`varchar(200),
   `PRIORITY`varchar(100),
   `ENABLE`numeric(8,0),
   `ISSYS`numeric(8,0),
   `MODULE_ID`varchar(100),
   primary key (`RESOURCE_ID`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源';

/*==============================================================*/
/* Table: SYS_ROLES                                             */
/*==============================================================*/
create table `SYS_ROLES`
(
   `ROLE_ID`varchar(100) not null,
   `ROLE_NAME`varchar(100),
   `ROLE_DESC`varchar(200),
   `ENABLE`numeric(8,0),
   `ISSYS`numeric(8,0),
   `MODULE_ID`varchar(100),
   primary key (`ROLE_ID`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色';

/*==============================================================*/
/* Table: SYS_ROLES_AUTHORITIES                                 */
/*==============================================================*/
create table `SYS_ROLES_AUTHORITIES`
(
   `ID`varchar(100) not null,
   `AUTHORITY_ID`varchar(100) not null,
   `ROLE_ID`varchar(100) not null,
   primary key (`ID`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限';

/*==============================================================*/
/* Table: SYS_ROLES_MOUDLES                                     */
/*==============================================================*/
create table `SYS_ROLES_MOUDLES`
(
   `ID`varchar(100) not null,
   `MODULE_ID`varchar(100) not null,
   `ROLE_ID`varchar(100) not null,
   primary key (`ID`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='控制角色对模块的访问权，主要用于生成菜单';

/*==============================================================*/
/* Table: SYS_USERS                                             */
/*==============================================================*/
create table `SYS_USERS`
(
   `USER_ID`varchar(100) not null,
   `USERNAME`varchar(100) not null,
   `NAME`varchar(100),
   `PASSWORD`varchar(100) not null,
   `DT_CREATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   `LAST_LOGIN`datetime,
   `DEADLINE`datetime,
   `LOGIN_IP`varchar(100),
   `V_QZJGID`varchar(100),
   `V_QZJGMC`varchar(100),
   `DEP_ID`varchar(100),
   `DEP_NAME`varchar(100),
   `ENABLED`numeric(8,0),
   `ACCOUNT_NON_EXPIRED`numeric(8,0),
   `ACCOUNT_NON_LOCKED`numeric(8,0),
   `CREDENTIALS_NON_EXPIRED`numeric(8,0),
   primary key (`USER_ID`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户';

/*==============================================================*/
/* Table: SYS_USERS_ROLES                                       */
/*==============================================================*/
create table `SYS_USERS_ROLES`
(
   `ID`varchar(100) not null,
   `ROLE_ID`varchar(100) not null,
   `USER_ID`varchar(100) not null,
   primary key (`ID`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色';

-- alter table SYS_AUTHORITIES_RESOURCES add constraint FK_Reference_5 foreign key (AUTHORITY_ID)
--       references SYS_AUTHORITIES (AUTHORITY_ID) on delete restrict on update restrict;
--
-- alter table SYS_AUTHORITIES_RESOURCES add constraint FK_Reference_6 foreign key (RESOURCE_ID)
--       references SYS_RESOURCES (RESOURCE_ID) on delete restrict on update restrict;
--
-- alter table SYS_RESOURCES add constraint FK_Reference_9 foreign key (MODULE_ID)
--       references SYS_MODULES (MODULE_ID) on delete restrict on update restrict;
--
-- alter table SYS_ROLES_AUTHORITIES add constraint FK_Reference_3 foreign key (ROLE_ID)
--       references SYS_ROLES (ROLE_ID) on delete restrict on update restrict;
--
-- alter table SYS_ROLES_AUTHORITIES add constraint FK_Reference_4 foreign key (AUTHORITY_ID)
--       references SYS_AUTHORITIES (AUTHORITY_ID) on delete restrict on update restrict;
--
-- alter table SYS_ROLES_MOUDLES add constraint FK_Reference_7 foreign key (MODULE_ID)
--       references SYS_MODULES (MODULE_ID) on delete restrict on update restrict;
--
-- alter table SYS_ROLES_MOUDLES add constraint FK_S_ROLE_REFERENCE_SYS_ROLE foreign key (ROLE_ID)
--       references SYS_ROLES (ROLE_ID) on delete restrict on update restrict;
--
-- alter table SYS_USERS_ROLES add constraint FK_Reference_1 foreign key (USER_ID)
--       references SYS_USERS (USER_ID) on delete restrict on update restrict;
--
-- alter table SYS_USERS_ROLES add constraint FK_Reference_2 foreign key (ROLE_ID)
--       references SYS_ROLES (ROLE_ID) on delete restrict on update restrict;

