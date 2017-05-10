alter table SYS_AUTHORITIES_RESOURCES
   drop constraint FK_SYS_AUTH_REFERENCE_SYS_AUTH;

alter table SYS_AUTHORITIES_RESOURCES
   drop constraint FK_SYS_AUTH_REFERENCE_SYS_RESO;

alter table SYS_RESOURCES
   drop constraint FK_SYS_RESO_REFERENCE_SYS_MODU;

alter table SYS_ROLES_AUTHORITIES
   drop constraint FK_SYS_ROLE_REFERENCE_SYS_ROLE;

alter table SYS_ROLES_AUTHORITIES
   drop constraint FK_SYS_ROLE_REFERENCE_SYS_AUTH;

alter table SYS_ROLES_MOUDLES
   drop constraint FK_SYS_ROLE_REFERENCE_SYS_MODU;

alter table SYS_ROLES_MOUDLES
   drop constraint FK_S_ROLE_REFERENCE_SYS_ROLE;

alter table SYS_USERS_ROLES
   drop constraint FK_SYS_USER_REFERENCE_SYS_USER;

alter table SYS_USERS_ROLES
   drop constraint FK_SYS_USER_REFERENCE_SYS_ROLE;

drop table PERSISTENT_LOGINS cascade constraints;

drop table SYS_AUTHORITIES cascade constraints;

drop table SYS_AUTHORITIES_RESOURCES cascade constraints;

drop table SYS_MODULES cascade constraints;

drop table SYS_RESOURCES cascade constraints;

drop table SYS_ROLES cascade constraints;

drop table SYS_ROLES_AUTHORITIES cascade constraints;

drop table SYS_ROLES_MOUDLES cascade constraints;

drop table SYS_USERS cascade constraints;

drop table SYS_USERS_ROLES cascade constraints;

/*==============================================================*/
/* Table: PERSISTENT_LOGINS                                     */
/*==============================================================*/
create table PERSISTENT_LOGINS  (
   USERNAME             VARCHAR2(64),
   SERIES               VARCHAR2(64)                    not null,
   TOKEN                VARCHAR2(64),
   LAST_USED            TIMESTAMP,
   constraint PK_PERSISTENT_LOGINS primary key (SERIES)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Spring Remember me 持久化';

/*==============================================================*/
/* Table: SYS_AUTHORITIES                                       */
/*==============================================================*/
create table SYS_AUTHORITIES  (
   AUTHORITY_ID         VARCHAR2(100)                   not null,
   AUTHORITY_MARK       VARCHAR2(100),
   AUTHORITY_NAME       VARCHAR2(100)                   not null,
   AUTHORITY_DESC       VARCHAR2(200),
   MESSAGE              VARCHAR2(100),
   ENABLE               NUMBER,
   ISSYS                NUMBER,
   MODULE_ID            VARCHAR2(100),
   constraint PK_SYS_AUTHORITIES primary key (AUTHORITY_ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限';

/*==============================================================*/
/* Table: SYS_AUTHORITIES_RESOURCES                             */
/*==============================================================*/
create table SYS_AUTHORITIES_RESOURCES  (
   ID                   VARCHAR2(100)                   not null,
   RESOURCE_ID          VARCHAR2(100)                   not null,
   AUTHORITY_ID         VARCHAR2(100)                   not null,
   constraint PK_SYS_AUTHORITIES_RESOURCES primary key (ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限资源';

/*==============================================================*/
/* Table: SYS_MODULES                                           */
/*==============================================================*/
create table SYS_MODULES  (
   MODULE_ID            VARCHAR2(100)                   not null,
   MODULE_NAME          VARCHAR2(100)                   not null,
   MODULE_DESC          VARCHAR2(200),
   MODULE_TYPE          VARCHAR2(100),
   PARENT               VARCHAR2(100),
   MODULE_URL           VARCHAR2(100),
   I_LEVEL              NUMBER,
   LEAF                 NUMBER,
   APPLICATION          VARCHAR2(100),
   CONTROLLER           VARCHAR2(100),
   ENABLE               NUMBER(1),
   PRIORITY             NUMBER,
   constraint PK_SYS_MODULES primary key (MODULE_ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='模块';

comment on column SYS_MODULES.I_LEVEL is
'1';

/*==============================================================*/
/* Table: SYS_RESOURCES                                         */
/*==============================================================*/
create table SYS_RESOURCES  (
   RESOURCE_ID          VARCHAR2(100)                   not null,
   RESOURCE_TYPE        VARCHAR2(100),
   RESOURCE_NAME        VARCHAR2(100),
   RESOURCE_DESC        VARCHAR2(200),
   RESOURCE_PATH        VARCHAR2(200),
   PRIORITY             VARCHAR2(100),
   ENABLE               NUMBER,
   ISSYS                NUMBER,
   MODULE_ID            VARCHAR2(100),
   constraint PK_SYS_RESOURCES primary key (RESOURCE_ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源';

comment on column SYS_RESOURCES.RESOURCE_TYPE is
'URL,METHOD';

/*==============================================================*/
/* Table: SYS_ROLES                                             */
/*==============================================================*/
create table SYS_ROLES  (
   ROLE_ID              VARCHAR2(100)                   not null,
   ROLE_NAME            VARCHAR2(100),
   ROLE_DESC            VARCHAR2(200),
   ENABLE               NUMBER,
   ISSYS                NUMBER,
   MODULE_ID            VARCHAR2(100),
   constraint PK_SYS_ROLES primary key (ROLE_ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色';

/*==============================================================*/
/* Table: SYS_ROLES_AUTHORITIES                                 */
/*==============================================================*/
create table SYS_ROLES_AUTHORITIES  (
   ID                   VARCHAR2(100)                   not null,
   AUTHORITY_ID         VARCHAR2(100)                   not null,
   ROLE_ID              VARCHAR2(100)                   not null,
   constraint PK_SYS_ROLES_AUTHORITIES primary key (ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限';

/*==============================================================*/
/* Table: SYS_ROLES_MOUDLES                                     */
/*==============================================================*/
create table SYS_ROLES_MOUDLES  (
   ID                   VARCHAR2(100)                   not null,
   MODULE_ID            VARCHAR2(100)                   not null,
   ROLE_ID              VARCHAR2(100)                   not null,
   constraint PK_SYS_ROLES_MOUDLES primary key (ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='控制角色对模块的访问权，主要用于生成菜单';

/*==============================================================*/
/* Table: SYS_USERS                                             */
/*==============================================================*/
create table SYS_USERS  (
   USER_ID              VARCHAR2(100)                   not null,
   USERNAME             VARCHAR2(100)                   not null,
   NAME                 VARCHAR2(100),
   PASSWORD             VARCHAR2(100)                   not null,
   DT_CREATE            DATE                           default SYSDATE,
   LAST_LOGIN           DATE,
   DEADLINE             DATE,
   LOGIN_IP             VARCHAR2(100),
   V_QZJGID             VARCHAR2(100),
   V_QZJGMC             VARCHAR2(100),
   DEP_ID               VARCHAR2(100),
   DEP_NAME             VARCHAR2(100),
   ENABLED              NUMBER,
   ACCOUNT_NON_EXPIRED  NUMBER,
   ACCOUNT_NON_LOCKED   NUMBER,
   CREDENTIALS_NON_EXPIRED NUMBER,
   constraint PK_SYS_USERS primary key (USER_ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户';

/*==============================================================*/
/* Table: SYS_USERS_ROLES                                       */
/*==============================================================*/
create table SYS_USERS_ROLES  (
   ID                   VARCHAR2(100)                   not null,
   ROLE_ID              VARCHAR2(100)                   not null,
   USER_ID              VARCHAR2(100)                   not null,
   constraint PK_SYS_USERS_ROLES primary key (ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色';

alter table SYS_AUTHORITIES_RESOURCES
   add constraint FK_SYS_AUTH_REFERENCE_SYS_AUTH foreign key (AUTHORITY_ID)
      references SYS_AUTHORITIES (AUTHORITY_ID);

alter table SYS_AUTHORITIES_RESOURCES
   add constraint FK_SYS_AUTH_REFERENCE_SYS_RESO foreign key (RESOURCE_ID)
      references SYS_RESOURCES (RESOURCE_ID);

alter table SYS_RESOURCES
   add constraint FK_SYS_RESO_REFERENCE_SYS_MODU foreign key (MODULE_ID)
      references SYS_MODULES (MODULE_ID);

alter table SYS_ROLES_AUTHORITIES
   add constraint FK_SYS_ROLE_REFERENCE_SYS_ROLE foreign key (ROLE_ID)
      references SYS_ROLES (ROLE_ID);

alter table SYS_ROLES_AUTHORITIES
   add constraint FK_SYS_ROLE_REFERENCE_SYS_AUTH foreign key (AUTHORITY_ID)
      references SYS_AUTHORITIES (AUTHORITY_ID);

alter table SYS_ROLES_MOUDLES
   add constraint FK_SYS_ROLE_REFERENCE_SYS_MODU foreign key (MODULE_ID)
      references SYS_MODULES (MODULE_ID);

alter table SYS_ROLES_MOUDLES
   add constraint FK_S_ROLE_REFERENCE_SYS_ROLE foreign key (ROLE_ID)
      references SYS_ROLES (ROLE_ID);

alter table SYS_USERS_ROLES
   add constraint FK_SYS_USER_REFERENCE_SYS_USER foreign key (USER_ID)
      references SYS_USERS (USER_ID);

alter table SYS_USERS_ROLES
   add constraint FK_SYS_USER_REFERENCE_SYS_ROLE foreign key (ROLE_ID)
      references SYS_ROLES (ROLE_ID);
