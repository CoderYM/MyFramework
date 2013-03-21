/**********系统菜单信息表**********/
create table FRAMEWORK_SYS_MENU
(
  ID                   NUMBER not null,   --ID       
  PID                  NUMBER,            --父类ID     
  SYS_MENU_CODE        VARCHAR2(20),      --菜单编码     
  SYS_MENU_NAME        VARCHAR2(50),      --菜单名      
  SYS_MENU_URL         VARCHAR2(100),     --菜单URL    
  IS_LEAF              VARCHAR2(1),       --是否叶子     
  SYS_MENU_DESCRIPTION VARCHAR2(200),     --菜单描述     
  STATE                VARCHAR2(2),       --状态       
  REMARK               VARCHAR2(200),     --备注       
  CREATE_USER_ID       NUMBER,            --创建人ID    
  CREATE_TIME          DATE,              --创建时间     
  MODIFY_USER_ID       NUMBER,            --修改人ID    
  MODIFY_TIME          DATE,              --修改时间     
  VERSION              NUMBER,            --版本号
  ORDER_INDEX          NUMBER             --显示顺序
);
COMMENT ON TABLE FRAMEWORK_SYS_MENU IS '系统菜单信息表';
comment on column FRAMEWORK_SYS_MENU.ID  is 'seq';
comment on column FRAMEWORK_SYS_MENU.PID  is '父类ID';
comment on column FRAMEWORK_SYS_MENU.SYS_MENU_CODE  is '菜单编码';
comment on column FRAMEWORK_SYS_MENU.SYS_MENU_NAME  is '菜单名';
comment on column FRAMEWORK_SYS_MENU.SYS_MENU_URL  is '菜单URL';
comment on column FRAMEWORK_SYS_MENU.IS_LEAF  is '是否叶子';
comment on column FRAMEWORK_SYS_MENU.SYS_MENU_DESCRIPTION  is '菜单描述';
comment on column FRAMEWORK_SYS_MENU.STATE  is '状态';
comment on column FRAMEWORK_SYS_MENU.REMARK  is '备注';
comment on column FRAMEWORK_SYS_MENU.CREATE_USER_ID  is '创建人ID';
comment on column FRAMEWORK_SYS_MENU.CREATE_TIME  is '创建时间';
comment on column FRAMEWORK_SYS_MENU.MODIFY_USER_ID  is '修改人ID';
comment on column FRAMEWORK_SYS_MENU.MODIFY_TIME  is '修改时间';
comment on column FRAMEWORK_SYS_MENU.VERSION  is '版本号';

alter table FRAMEWORK_SYS_MENU  add constraint PK_FRAMEWORK_SYS_MENU primary key (ID)  using index;

/******************系统权限表********************/
create table FRAMEWORK_SYS_PERMISSION
(
  ID                     NUMBER not null,  --ID    
  SYS_MENU_ID            NUMBER,           --菜单ID  
  PERMISSION_CODE        VARCHAR2(20),     --权限编码  
  PERMISSION_NAME        VARCHAR2(50),     --权限名   
  PERMISSION_DESCRIPTION VARCHAR2(200),    --权限描述  
  STATE                  VARCHAR2(2),      --状态    
  REMARK                 VARCHAR2(200),    --备注    
  CREATE_USER_ID         NUMBER,           --创建人ID 
  CREATE_TIME            DATE,             --创建时间  
  MODIFY_USER_ID         NUMBER,           --修改人ID 
  MODIFY_TIME            DATE,             --修改时间  
  VERSION                NUMBER            --版本号
);
COMMENT ON TABLE FRAMEWORK_SYS_PERMISSION IS '系统权限表';
comment on column FRAMEWORK_SYS_PERMISSION.ID  is 'seq';
comment on column FRAMEWORK_SYS_PERMISSION.SYS_MENU_ID  is '菜单ID';
comment on column FRAMEWORK_SYS_PERMISSION.PERMISSION_CODE  is '权限编码';
comment on column FRAMEWORK_SYS_PERMISSION.PERMISSION_NAME  is '权限名';
comment on column FRAMEWORK_SYS_PERMISSION.PERMISSION_DESCRIPTION  is '权限描述';
comment on column FRAMEWORK_SYS_PERMISSION.STATE  is '状态';
comment on column FRAMEWORK_SYS_PERMISSION.REMARK  is '备注';
comment on column FRAMEWORK_SYS_PERMISSION.CREATE_USER_ID  is '创建人ID';
comment on column FRAMEWORK_SYS_PERMISSION.CREATE_TIME  is '创建时间';
comment on column FRAMEWORK_SYS_PERMISSION.MODIFY_USER_ID  is '修改人ID';
comment on column FRAMEWORK_SYS_PERMISSION.MODIFY_TIME  is '修改时间';
comment on column FRAMEWORK_SYS_PERMISSION.VERSION  is '版本号';

alter table FRAMEWORK_SYS_PERMISSION  add constraint PK_FRAMEWORK_SYS_PERMISSION primary key (ID)  using index;

/*****************系统角色表****************/
create table FRAMEWORK_SYS_ROLE
(
  ID                   NUMBER not null,     --ID
  SYS_ROLE_CODE        VARCHAR2(50),        --系统角色编码        
  SYS_ROLE_NAME        VARCHAR2(50),        --系统角色名         
  SYS_ROLE_DESCRIPTION VARCHAR2(200),       --系统角色描述        
  STATE                VARCHAR2(2),         --状态            
  REMARK               VARCHAR2(200),       --备注            
  CREATE_USER_ID       NUMBER,              --创建人ID         
  CREATE_TIME          DATE,                --创建时间          
  MODIFY_USER_ID       NUMBER,              --修改人ID         
  MODIFY_TIME          DATE,                --修改时间          
  VERSION              NUMBER               --版本号
);
COMMENT ON TABLE FRAMEWORK_SYS_ROLE IS '系统角色表';
comment on column FRAMEWORK_SYS_ROLE.ID  is 'seq';
comment on column FRAMEWORK_SYS_ROLE.SYS_ROLE_CODE  is '系统角色编码';
comment on column FRAMEWORK_SYS_ROLE.SYS_ROLE_NAME  is '系统角色名';
comment on column FRAMEWORK_SYS_ROLE.SYS_ROLE_DESCRIPTION  is '系统角色描述';
comment on column FRAMEWORK_SYS_ROLE.STATE  is '状态';
comment on column FRAMEWORK_SYS_ROLE.REMARK  is '备注';
comment on column FRAMEWORK_SYS_ROLE.CREATE_USER_ID  is '创建人ID';
comment on column FRAMEWORK_SYS_ROLE.CREATE_TIME  is '创建时间';
comment on column FRAMEWORK_SYS_ROLE.MODIFY_USER_ID  is '修改人ID';
comment on column FRAMEWORK_SYS_ROLE.MODIFY_TIME  is '修改时间';
comment on column FRAMEWORK_SYS_ROLE.VERSION  is '版本号';

alter table FRAMEWORK_SYS_ROLE  add constraint PK_FRAMEWORK_SYS_ROLE primary key (ID)  using index;


/***************用户角色关系表*************/
create table FRAMEWORK_USER_ROLE
(
  ID             NUMBER not null,    --ID       
  USER_ID        NUMBER,             --人员ID     
  SYS_ROLE_ID    NUMBER,             --角色ID     
  STATE          VARCHAR2(2),        --状态       
  REMARK         VARCHAR2(200),      --备注       
  CREATE_USER_ID NUMBER,             --创建人ID    
  CREATE_TIME    DATE,               --创建时间     
  MODIFY_USER_ID NUMBER,             --修改人ID    
  MODIFY_TIME    DATE,               --修改时间     
  VERSION        NUMBER
);
COMMENT ON TABLE FRAMEWORK_USER_ROLE IS '用户角色关系表';
comment on column FRAMEWORK_USER_ROLE.ID  is 'seq';
comment on column FRAMEWORK_USER_ROLE.USER_ID  is '人员ID';
comment on column FRAMEWORK_USER_ROLE.SYS_ROLE_ID  is '角色ID';
comment on column FRAMEWORK_USER_ROLE.STATE  is '状态';
comment on column FRAMEWORK_USER_ROLE.REMARK  is '备注';
comment on column FRAMEWORK_USER_ROLE.CREATE_USER_ID  is '创建人ID';
comment on column FRAMEWORK_USER_ROLE.CREATE_TIME  is '创建时间';
comment on column FRAMEWORK_USER_ROLE.MODIFY_USER_ID  is '修改人ID';
comment on column FRAMEWORK_USER_ROLE.MODIFY_TIME  is '修改时间';
comment on column FRAMEWORK_USER_ROLE.VERSION  is '版本号  ';

alter table FRAMEWORK_USER_ROLE  add constraint PK_FRAMEWORK_USER_ROLE primary key (ID)  using index;


/****************角色权限映射表******************/
create table FRAMEWORK_PERM_ASSIGN
(
  ID             NUMBER not null,    --ID           
  USER_DEP_ID    NUMBER,             --权限所有者对象ID    
  USER_DEP_TYPE  VARCHAR2(2),        --权限所有者类别      
  PERM_ID        NUMBER,             --权限ID         
  STATE          VARCHAR2(2),        --状态           
  REMARK         VARCHAR2(200),      --备注           
  CREATE_USER_ID NUMBER,             --创建人ID        
  CREATE_TIME    DATE,               --创建时间         
  MODIFY_USER_ID NUMBER,             --修改人ID        
  MODIFY_TIME    DATE,               --修改时间         
  VERSION        NUMBER
);
COMMENT ON TABLE FRAMEWORK_PERM_ASSIGN IS '角色权限映射表';
comment on column FRAMEWORK_PERM_ASSIGN.ID  is 'seq';
comment on column FRAMEWORK_PERM_ASSIGN.USER_DEP_ID  is '权限所有者对象ID';
comment on column FRAMEWORK_PERM_ASSIGN.USER_DEP_TYPE  is '权限所有者类别';
comment on column FRAMEWORK_PERM_ASSIGN.PERM_ID  is '权限ID';
comment on column FRAMEWORK_PERM_ASSIGN.STATE  is '状态';
comment on column FRAMEWORK_PERM_ASSIGN.REMARK  is '备注';
comment on column FRAMEWORK_PERM_ASSIGN.CREATE_USER_ID  is '创建人ID';
comment on column FRAMEWORK_PERM_ASSIGN.CREATE_TIME  is '创建时间';
comment on column FRAMEWORK_PERM_ASSIGN.MODIFY_USER_ID  is '修改人ID';
comment on column FRAMEWORK_PERM_ASSIGN.MODIFY_TIME  is '修改时间';
comment on column FRAMEWORK_PERM_ASSIGN.VERSION  is '版本号';

alter table FRAMEWORK_PERM_ASSIGN  add constraint PK_FRAMEWORK_PERM_ASSIGN primary key (ID)  using index;

--==============================================================
-- TABLE: "FRAMEWORK_SYS_ACTION"
-- DESC: 系统权限表
--==============================================================
create table FRAMEWORK_SYS_ACTION
(
  ID                NUMBER not null,   --编号
  SYS_MENU_ID           NUMBER not null,   --菜单ID
  PERMISSION_CODE		VARCHAR2(20),		
  ACTION_NAME       VARCHAR2(500),     --访问的action名字
  METHOD_NAME       VARCHAR2(500),     --访问的method名字
  SIMPLE_CLASS_NAME VARCHAR2(500),     --访问的class名字
  ACTION_TYPE       VARCHAR2(2),       --类型 1：新增数据2：修改数据3：删除数据4：访问查询数据5：数据审批6：数据认证7:打开界面
  ACTION_DES        VARCHAR2(500)      --操作描述
);
COMMENT ON TABLE FRAMEWORK_SYS_ACTION IS '请求权限配置表';
comment on column FRAMEWORK_SYS_ACTION.ID  is 'seq';
comment on column FRAMEWORK_SYS_ACTION.SYS_MENU_ID  is '菜单ID';
COMMENT ON COLUMN FRAMEWORK_SYS_ACTION.PERMISSION_CODE IS '权限编码';
comment on column FRAMEWORK_SYS_ACTION.ACTION_NAME  is '访问的action名字';
comment on column FRAMEWORK_SYS_ACTION.METHOD_NAME  is '访问的method名字';
comment on column FRAMEWORK_SYS_ACTION.SIMPLE_CLASS_NAME  is '访问的class名字';
comment on column FRAMEWORK_SYS_ACTION.ACTION_TYPE  is '类型';
comment on column FRAMEWORK_SYS_ACTION.ACTION_DES  is '操作描述';

alter table FRAMEWORK_SYS_ACTION  add constraint PK_FRAMEWORK_SYS_ACTION primary key (ID)  using index;


--==============================================================
-- TABLE: "FRAMEWORK_ACTION_LOG"
-- DESC: 系统日志记录表
--==============================================================
CREATE TABLE FRAMEWORK_ACTION_LOG (
	ID	NUMBER	NOT NULL,
	LOG_TIME	DATE,
	LOG_USER	VARCHAR2(18),
	LOG_OPERATE	VARCHAR2(2),
	LOG_CONTENT	VARCHAR2(512),
	REMARK	VARCHAR2(512),
	VERSION	NUMBER,
	LOG_OPERATE_CLASS	VARCHAR2(512),
	LOG_OPERATE_METHOD	VARCHAR2(512),
	LOG_OPERATE_RESULT	VARCHAR2(512),
	LOG_TYPE	VARCHAR2(2),
	IS_AUTHED	VARCHAR2(50),
	LOG_OPERATE_ACTION_NAME	VARCHAR2(512),
	CHANGE_TABLE_INFO	VARCHAR2(4000)
);
 
COMMENT ON TABLE FRAMEWORK_ACTION_LOG IS '系统日志记录表';
COMMENT ON COLUMN FRAMEWORK_ACTION_LOG.ID IS 'ID<seq>  ';
COMMENT ON COLUMN FRAMEWORK_ACTION_LOG.LOG_TIME IS '操作时间';
COMMENT ON COLUMN FRAMEWORK_ACTION_LOG.LOG_USER IS '操作人员';
COMMENT ON COLUMN FRAMEWORK_ACTION_LOG.LOG_OPERATE IS '操作类型';
COMMENT ON COLUMN FRAMEWORK_ACTION_LOG.LOG_CONTENT IS '操作内容';
COMMENT ON COLUMN FRAMEWORK_ACTION_LOG.REMARK IS '备注    ';
COMMENT ON COLUMN FRAMEWORK_ACTION_LOG.VERSION IS '版本号  ';
COMMENT ON COLUMN FRAMEWORK_ACTION_LOG.LOG_OPERATE_CLASS IS '操作对象名';
COMMENT ON COLUMN FRAMEWORK_ACTION_LOG.LOG_OPERATE_METHOD IS '操作对象方法';
COMMENT ON COLUMN FRAMEWORK_ACTION_LOG.LOG_OPERATE_RESULT IS '操作结果';
COMMENT ON COLUMN FRAMEWORK_ACTION_LOG.LOG_TYPE IS '日志类型';
COMMENT ON COLUMN FRAMEWORK_ACTION_LOG.IS_AUTHED IS '授权是否通过:true通过；false未通过';
COMMENT ON COLUMN FRAMEWORK_ACTION_LOG.LOG_OPERATE_ACTION_NAME IS '操作action名字';
COMMENT ON COLUMN FRAMEWORK_ACTION_LOG.CHANGE_TABLE_INFO IS '捕捉到的参数信息';

alter table FRAMEWORK_ACTION_LOG  add constraint PK_FRAMEWORK_ACTION_LOG primary key (ID)  using index;

--==============================================================
-- TABLE: "FRAMEWORK_USER_INF"
-- DESC: 系统用户表
--==============================================================
CREATE TABLE FRAMEWORK_USER_INF
    (
        ID NUMBER NOT NULL,
        USER_NAME VARCHAR2(20) NOT NULL,
        USER_PASSWORD VARCHAR2(50) NOT NULL,
        STATE VARCHAR2(1) NOT NULL,
        USER_INST_ID VARCHAR2(10),
        REAL_NAME VARCHAR2(30),
        MOBILE VARCHAR2(32),
        CONTACT_PHONE VARCHAR2(20),
        CONTACT_EMAIL VARCHAR2(100),
        ATTRIBUTIVE_ORG NUMBER,
        MISC1 VARCHAR2(100),
        MISC2 VARCHAR2(100),
        USER_DESC VARCHAR2(1024),
        REC_UPD_USR VARCHAR2(16) NOT NULL,
        ROW_CRT_TS DATE NOT NULL,
        REC_UPD_TS DATE NOT NULL
    );
alter table FRAMEWORK_USER_INF  add constraint PK_FRAMEWORK_USER_INF primary key (ID)  using index;
COMMENT ON TABLE FRAMEWORK_USER_INF
IS
    'BOSS操作员基本信息表';
COMMENT ON COLUMN FRAMEWORK_USER_INF.ID
IS
    'ID<seq>';
COMMENT ON COLUMN FRAMEWORK_USER_INF.USER_NAME
IS
    '操作员名称';
COMMENT ON COLUMN FRAMEWORK_USER_INF.USER_PASSWORD
IS
    '操作员密码';
COMMENT ON COLUMN FRAMEWORK_USER_INF.STATE
IS
    '当前状态';
COMMENT ON COLUMN FRAMEWORK_USER_INF.USER_INST_ID
IS
    'USER_INST_ID';
COMMENT ON COLUMN FRAMEWORK_USER_INF.REAL_NAME
IS
    '操作员真实姓名';
COMMENT ON COLUMN FRAMEWORK_USER_INF.MOBILE
IS
    '手机号码';
COMMENT ON COLUMN FRAMEWORK_USER_INF.CONTACT_PHONE
IS
    '座机号码';
COMMENT ON COLUMN FRAMEWORK_USER_INF.CONTACT_EMAIL
IS
    '邮件地址';
COMMENT ON COLUMN FRAMEWORK_USER_INF.ATTRIBUTIVE_ORG
IS
    '归属机构';
COMMENT ON COLUMN FRAMEWORK_USER_INF.MISC1
IS
    '扩展1';
COMMENT ON COLUMN FRAMEWORK_USER_INF.MISC2
IS
    '扩展2';
COMMENT ON COLUMN FRAMEWORK_USER_INF.USER_DESC
IS
    '操作员描述';
COMMENT ON COLUMN FRAMEWORK_USER_INF.REC_UPD_USR
IS
    '最后更新者';
COMMENT ON COLUMN FRAMEWORK_USER_INF.ROW_CRT_TS
IS
    '创建时间';
COMMENT ON COLUMN FRAMEWORK_USER_INF.REC_UPD_TS
IS
    '最后更新时间';

