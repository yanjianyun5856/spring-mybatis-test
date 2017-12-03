create table sys_user
( id bigint not null auto_increment comment '用户ID' ,
  user_name varchar(50) comment '用户名',
  user_password varchar(50) comment '密码',
  user_email varchar(50) comment '邮箱',
  user_info text comment '简介' ,
  head_img blob comment '头像',
  create_time datetime comment '创建时间 ',
  primary key (id)
 );

create table sys_role (
 id bigint not null auto_increment comment '角色 ID',
 role_name varchar(50) comment  '角色名',
 enable int comment '有效标志',
 create_by bigint comment '创建人',
 create_time datetime comment '创建时间',
 primary key (id) 
);

create table sys_privilege (
 id bigint not null auto_increment comment '权限 ID',
 privilege_name varchar(50) comment '权限名称',
 privilege_url varchar (200 ) comment '限 URL',
 primary key (id)
);

create table sys_user_role (
  user_id bigint comment ' 用户 ID ',
  role_id bigint comment '角色 ID'
);


create table sys_role_privilege
  role_id bigint comment  '角色 ID ',
  privilege_id bigint comment  '权限 ID '
);

INSERT INTO  sys_user VALUES ('1','admin','123456','admin@mybatis.tk',
'管理员', null , '2016-04-01 17:00:58 ');
INSERT INTO  sys_user VALUES ('l00l','test','123456','test@mybatis.tk',
'测试用户', null, '2016-04-01 17:01:52' );
INSERT INTO  sys_role VALUES ( '1', '管理员', '1' ,'l',
'2016-04-01 17:02:14' );
INSERT INTO  sys_role VALUES ('2','普通用户', '1','1',
'2016-04-01 17:02:34' );
INSERT INTO  sys_user_role VALUES ('1','1');
INSERT INTO  sys_user_role VALUES ( '1','2' );
INSERT INTO  sys_user_role VALUES ( '1001','2' );
INSERT INTO  sys_privilege VALUES ( '1' , '用户管理 ', '/users' ) ;
INSERT INTO  sys_privilege VALUES ( '2', '角色管理 ', '/roles') ;
INSERT INTO  sys privilege VALUES ('3', '系统日志' , '/logs') ;
INSERT INTO  sys_privilege VALUES ( '4', '人员维护', '/persons' ) ;
INSERT INTO  sys_privilege VALUES ( '5', '单位维护', '/companies' ) ;
INSERT INTO  sys_role_privilege VALUES ( '1','l');
INSERT INTO  sys_role_privilege VALUES ('1','3');
INSERT INTO  sys_role_privilege VALUES ('1','2' ) ;
INSERT INTO  sys_role_privilege VALUES ('2','4' );
INSERT INTO  sys_role_privilege VALUES ('2','5' );