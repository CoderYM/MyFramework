/**********ϵͳ�˵���Ϣ��**********/
create table FRAMEWORK_SYS_MENU
(
  ID                   NUMBER not null,   --ID       
  PID                  NUMBER,            --����ID     
  SYS_MENU_CODE        VARCHAR2(20),      --�˵�����     
  SYS_MENU_NAME        VARCHAR2(50),      --�˵���      
  SYS_MENU_URL         VARCHAR2(100),     --�˵�URL    
  IS_LEAF              VARCHAR2(1),       --�Ƿ�Ҷ��     
  SYS_MENU_DESCRIPTION VARCHAR2(200),     --�˵�����     
  STATE                VARCHAR2(2),       --״̬       
  REMARK               VARCHAR2(200),     --��ע       
  CREATE_USER_ID       NUMBER,            --������ID    
  CREATE_TIME          DATE,              --����ʱ��     
  MODIFY_USER_ID       NUMBER,            --�޸���ID    
  MODIFY_TIME          DATE,              --�޸�ʱ��     
  VERSION              NUMBER,            --�汾��
  ORDER_INDEX          NUMBER             --��ʾ˳��
);
COMMENT ON TABLE FRAMEWORK_SYS_MENU IS 'ϵͳ�˵���Ϣ��';
comment on column FRAMEWORK_SYS_MENU.ID  is 'seq';
comment on column FRAMEWORK_SYS_MENU.PID  is '����ID';
comment on column FRAMEWORK_SYS_MENU.SYS_MENU_CODE  is '�˵�����';
comment on column FRAMEWORK_SYS_MENU.SYS_MENU_NAME  is '�˵���';
comment on column FRAMEWORK_SYS_MENU.SYS_MENU_URL  is '�˵�URL';
comment on column FRAMEWORK_SYS_MENU.IS_LEAF  is '�Ƿ�Ҷ��';
comment on column FRAMEWORK_SYS_MENU.SYS_MENU_DESCRIPTION  is '�˵�����';
comment on column FRAMEWORK_SYS_MENU.STATE  is '״̬';
comment on column FRAMEWORK_SYS_MENU.REMARK  is '��ע';
comment on column FRAMEWORK_SYS_MENU.CREATE_USER_ID  is '������ID';
comment on column FRAMEWORK_SYS_MENU.CREATE_TIME  is '����ʱ��';
comment on column FRAMEWORK_SYS_MENU.MODIFY_USER_ID  is '�޸���ID';
comment on column FRAMEWORK_SYS_MENU.MODIFY_TIME  is '�޸�ʱ��';
comment on column FRAMEWORK_SYS_MENU.VERSION  is '�汾��';

alter table FRAMEWORK_SYS_MENU  add constraint PK_FRAMEWORK_SYS_MENU primary key (ID)  using index;

/******************ϵͳȨ�ޱ�********************/
create table FRAMEWORK_SYS_PERMISSION
(
  ID                     NUMBER not null,  --ID    
  SYS_MENU_ID            NUMBER,           --�˵�ID  
  PERMISSION_CODE        VARCHAR2(20),     --Ȩ�ޱ���  
  PERMISSION_NAME        VARCHAR2(50),     --Ȩ����   
  PERMISSION_DESCRIPTION VARCHAR2(200),    --Ȩ������  
  STATE                  VARCHAR2(2),      --״̬    
  REMARK                 VARCHAR2(200),    --��ע    
  CREATE_USER_ID         NUMBER,           --������ID 
  CREATE_TIME            DATE,             --����ʱ��  
  MODIFY_USER_ID         NUMBER,           --�޸���ID 
  MODIFY_TIME            DATE,             --�޸�ʱ��  
  VERSION                NUMBER            --�汾��
);
COMMENT ON TABLE FRAMEWORK_SYS_PERMISSION IS 'ϵͳȨ�ޱ�';
comment on column FRAMEWORK_SYS_PERMISSION.ID  is 'seq';
comment on column FRAMEWORK_SYS_PERMISSION.SYS_MENU_ID  is '�˵�ID';
comment on column FRAMEWORK_SYS_PERMISSION.PERMISSION_CODE  is 'Ȩ�ޱ���';
comment on column FRAMEWORK_SYS_PERMISSION.PERMISSION_NAME  is 'Ȩ����';
comment on column FRAMEWORK_SYS_PERMISSION.PERMISSION_DESCRIPTION  is 'Ȩ������';
comment on column FRAMEWORK_SYS_PERMISSION.STATE  is '״̬';
comment on column FRAMEWORK_SYS_PERMISSION.REMARK  is '��ע';
comment on column FRAMEWORK_SYS_PERMISSION.CREATE_USER_ID  is '������ID';
comment on column FRAMEWORK_SYS_PERMISSION.CREATE_TIME  is '����ʱ��';
comment on column FRAMEWORK_SYS_PERMISSION.MODIFY_USER_ID  is '�޸���ID';
comment on column FRAMEWORK_SYS_PERMISSION.MODIFY_TIME  is '�޸�ʱ��';
comment on column FRAMEWORK_SYS_PERMISSION.VERSION  is '�汾��';

alter table FRAMEWORK_SYS_PERMISSION  add constraint PK_FRAMEWORK_SYS_PERMISSION primary key (ID)  using index;

/*****************ϵͳ��ɫ��****************/
create table FRAMEWORK_SYS_ROLE
(
  ID                   NUMBER not null,     --ID
  SYS_ROLE_CODE        VARCHAR2(50),        --ϵͳ��ɫ����        
  SYS_ROLE_NAME        VARCHAR2(50),        --ϵͳ��ɫ��         
  SYS_ROLE_DESCRIPTION VARCHAR2(200),       --ϵͳ��ɫ����        
  STATE                VARCHAR2(2),         --״̬            
  REMARK               VARCHAR2(200),       --��ע            
  CREATE_USER_ID       NUMBER,              --������ID         
  CREATE_TIME          DATE,                --����ʱ��          
  MODIFY_USER_ID       NUMBER,              --�޸���ID         
  MODIFY_TIME          DATE,                --�޸�ʱ��          
  VERSION              NUMBER               --�汾��
);
COMMENT ON TABLE FRAMEWORK_SYS_ROLE IS 'ϵͳ��ɫ��';
comment on column FRAMEWORK_SYS_ROLE.ID  is 'seq';
comment on column FRAMEWORK_SYS_ROLE.SYS_ROLE_CODE  is 'ϵͳ��ɫ����';
comment on column FRAMEWORK_SYS_ROLE.SYS_ROLE_NAME  is 'ϵͳ��ɫ��';
comment on column FRAMEWORK_SYS_ROLE.SYS_ROLE_DESCRIPTION  is 'ϵͳ��ɫ����';
comment on column FRAMEWORK_SYS_ROLE.STATE  is '״̬';
comment on column FRAMEWORK_SYS_ROLE.REMARK  is '��ע';
comment on column FRAMEWORK_SYS_ROLE.CREATE_USER_ID  is '������ID';
comment on column FRAMEWORK_SYS_ROLE.CREATE_TIME  is '����ʱ��';
comment on column FRAMEWORK_SYS_ROLE.MODIFY_USER_ID  is '�޸���ID';
comment on column FRAMEWORK_SYS_ROLE.MODIFY_TIME  is '�޸�ʱ��';
comment on column FRAMEWORK_SYS_ROLE.VERSION  is '�汾��';

alter table FRAMEWORK_SYS_ROLE  add constraint PK_FRAMEWORK_SYS_ROLE primary key (ID)  using index;


/***************�û���ɫ��ϵ��*************/
create table FRAMEWORK_USER_ROLE
(
  ID             NUMBER not null,    --ID       
  USER_ID        NUMBER,             --��ԱID     
  SYS_ROLE_ID    NUMBER,             --��ɫID     
  STATE          VARCHAR2(2),        --״̬       
  REMARK         VARCHAR2(200),      --��ע       
  CREATE_USER_ID NUMBER,             --������ID    
  CREATE_TIME    DATE,               --����ʱ��     
  MODIFY_USER_ID NUMBER,             --�޸���ID    
  MODIFY_TIME    DATE,               --�޸�ʱ��     
  VERSION        NUMBER
);
COMMENT ON TABLE FRAMEWORK_USER_ROLE IS '�û���ɫ��ϵ��';
comment on column FRAMEWORK_USER_ROLE.ID  is 'seq';
comment on column FRAMEWORK_USER_ROLE.USER_ID  is '��ԱID';
comment on column FRAMEWORK_USER_ROLE.SYS_ROLE_ID  is '��ɫID';
comment on column FRAMEWORK_USER_ROLE.STATE  is '״̬';
comment on column FRAMEWORK_USER_ROLE.REMARK  is '��ע';
comment on column FRAMEWORK_USER_ROLE.CREATE_USER_ID  is '������ID';
comment on column FRAMEWORK_USER_ROLE.CREATE_TIME  is '����ʱ��';
comment on column FRAMEWORK_USER_ROLE.MODIFY_USER_ID  is '�޸���ID';
comment on column FRAMEWORK_USER_ROLE.MODIFY_TIME  is '�޸�ʱ��';
comment on column FRAMEWORK_USER_ROLE.VERSION  is '�汾��  ';

alter table FRAMEWORK_USER_ROLE  add constraint PK_FRAMEWORK_USER_ROLE primary key (ID)  using index;


/****************��ɫȨ��ӳ���******************/
create table FRAMEWORK_PERM_ASSIGN
(
  ID             NUMBER not null,    --ID           
  USER_DEP_ID    NUMBER,             --Ȩ�������߶���ID    
  USER_DEP_TYPE  VARCHAR2(2),        --Ȩ�����������      
  PERM_ID        NUMBER,             --Ȩ��ID         
  STATE          VARCHAR2(2),        --״̬           
  REMARK         VARCHAR2(200),      --��ע           
  CREATE_USER_ID NUMBER,             --������ID        
  CREATE_TIME    DATE,               --����ʱ��         
  MODIFY_USER_ID NUMBER,             --�޸���ID        
  MODIFY_TIME    DATE,               --�޸�ʱ��         
  VERSION        NUMBER
);
COMMENT ON TABLE FRAMEWORK_PERM_ASSIGN IS '��ɫȨ��ӳ���';
comment on column FRAMEWORK_PERM_ASSIGN.ID  is 'seq';
comment on column FRAMEWORK_PERM_ASSIGN.USER_DEP_ID  is 'Ȩ�������߶���ID';
comment on column FRAMEWORK_PERM_ASSIGN.USER_DEP_TYPE  is 'Ȩ�����������';
comment on column FRAMEWORK_PERM_ASSIGN.PERM_ID  is 'Ȩ��ID';
comment on column FRAMEWORK_PERM_ASSIGN.STATE  is '״̬';
comment on column FRAMEWORK_PERM_ASSIGN.REMARK  is '��ע';
comment on column FRAMEWORK_PERM_ASSIGN.CREATE_USER_ID  is '������ID';
comment on column FRAMEWORK_PERM_ASSIGN.CREATE_TIME  is '����ʱ��';
comment on column FRAMEWORK_PERM_ASSIGN.MODIFY_USER_ID  is '�޸���ID';
comment on column FRAMEWORK_PERM_ASSIGN.MODIFY_TIME  is '�޸�ʱ��';
comment on column FRAMEWORK_PERM_ASSIGN.VERSION  is '�汾��';

alter table FRAMEWORK_PERM_ASSIGN  add constraint PK_FRAMEWORK_PERM_ASSIGN primary key (ID)  using index;

--==============================================================
-- TABLE: "FRAMEWORK_SYS_ACTION"
-- DESC: ϵͳȨ�ޱ�
--==============================================================
create table FRAMEWORK_SYS_ACTION
(
  ID                NUMBER not null,   --���
  SYS_MENU_ID           NUMBER not null,   --�˵�ID
  PERMISSION_CODE		VARCHAR2(20),		
  ACTION_NAME       VARCHAR2(500),     --���ʵ�action����
  METHOD_NAME       VARCHAR2(500),     --���ʵ�method����
  SIMPLE_CLASS_NAME VARCHAR2(500),     --���ʵ�class����
  ACTION_TYPE       VARCHAR2(2),       --���� 1����������2���޸�����3��ɾ������4�����ʲ�ѯ����5����������6��������֤7:�򿪽���
  ACTION_DES        VARCHAR2(500)      --��������
);
COMMENT ON TABLE FRAMEWORK_SYS_ACTION IS '����Ȩ�����ñ�';
comment on column FRAMEWORK_SYS_ACTION.ID  is 'seq';
comment on column FRAMEWORK_SYS_ACTION.SYS_MENU_ID  is '�˵�ID';
COMMENT ON COLUMN FRAMEWORK_SYS_ACTION.PERMISSION_CODE IS 'Ȩ�ޱ���';
comment on column FRAMEWORK_SYS_ACTION.ACTION_NAME  is '���ʵ�action����';
comment on column FRAMEWORK_SYS_ACTION.METHOD_NAME  is '���ʵ�method����';
comment on column FRAMEWORK_SYS_ACTION.SIMPLE_CLASS_NAME  is '���ʵ�class����';
comment on column FRAMEWORK_SYS_ACTION.ACTION_TYPE  is '����';
comment on column FRAMEWORK_SYS_ACTION.ACTION_DES  is '��������';

alter table FRAMEWORK_SYS_ACTION  add constraint PK_FRAMEWORK_SYS_ACTION primary key (ID)  using index;


--==============================================================
-- TABLE: "FRAMEWORK_ACTION_LOG"
-- DESC: ϵͳ��־��¼��
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
 
COMMENT ON TABLE FRAMEWORK_ACTION_LOG IS 'ϵͳ��־��¼��';
COMMENT ON COLUMN FRAMEWORK_ACTION_LOG.ID IS 'ID<seq>  ';
COMMENT ON COLUMN FRAMEWORK_ACTION_LOG.LOG_TIME IS '����ʱ��';
COMMENT ON COLUMN FRAMEWORK_ACTION_LOG.LOG_USER IS '������Ա';
COMMENT ON COLUMN FRAMEWORK_ACTION_LOG.LOG_OPERATE IS '��������';
COMMENT ON COLUMN FRAMEWORK_ACTION_LOG.LOG_CONTENT IS '��������';
COMMENT ON COLUMN FRAMEWORK_ACTION_LOG.REMARK IS '��ע    ';
COMMENT ON COLUMN FRAMEWORK_ACTION_LOG.VERSION IS '�汾��  ';
COMMENT ON COLUMN FRAMEWORK_ACTION_LOG.LOG_OPERATE_CLASS IS '����������';
COMMENT ON COLUMN FRAMEWORK_ACTION_LOG.LOG_OPERATE_METHOD IS '�������󷽷�';
COMMENT ON COLUMN FRAMEWORK_ACTION_LOG.LOG_OPERATE_RESULT IS '�������';
COMMENT ON COLUMN FRAMEWORK_ACTION_LOG.LOG_TYPE IS '��־����';
COMMENT ON COLUMN FRAMEWORK_ACTION_LOG.IS_AUTHED IS '��Ȩ�Ƿ�ͨ��:trueͨ����falseδͨ��';
COMMENT ON COLUMN FRAMEWORK_ACTION_LOG.LOG_OPERATE_ACTION_NAME IS '����action����';
COMMENT ON COLUMN FRAMEWORK_ACTION_LOG.CHANGE_TABLE_INFO IS '��׽���Ĳ�����Ϣ';

alter table FRAMEWORK_ACTION_LOG  add constraint PK_FRAMEWORK_ACTION_LOG primary key (ID)  using index;

--==============================================================
-- TABLE: "FRAMEWORK_USER_INF"
-- DESC: ϵͳ�û���
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
    'BOSS����Ա������Ϣ��';
COMMENT ON COLUMN FRAMEWORK_USER_INF.ID
IS
    'ID<seq>';
COMMENT ON COLUMN FRAMEWORK_USER_INF.USER_NAME
IS
    '����Ա����';
COMMENT ON COLUMN FRAMEWORK_USER_INF.USER_PASSWORD
IS
    '����Ա����';
COMMENT ON COLUMN FRAMEWORK_USER_INF.STATE
IS
    '��ǰ״̬';
COMMENT ON COLUMN FRAMEWORK_USER_INF.USER_INST_ID
IS
    'USER_INST_ID';
COMMENT ON COLUMN FRAMEWORK_USER_INF.REAL_NAME
IS
    '����Ա��ʵ����';
COMMENT ON COLUMN FRAMEWORK_USER_INF.MOBILE
IS
    '�ֻ�����';
COMMENT ON COLUMN FRAMEWORK_USER_INF.CONTACT_PHONE
IS
    '��������';
COMMENT ON COLUMN FRAMEWORK_USER_INF.CONTACT_EMAIL
IS
    '�ʼ���ַ';
COMMENT ON COLUMN FRAMEWORK_USER_INF.ATTRIBUTIVE_ORG
IS
    '��������';
COMMENT ON COLUMN FRAMEWORK_USER_INF.MISC1
IS
    '��չ1';
COMMENT ON COLUMN FRAMEWORK_USER_INF.MISC2
IS
    '��չ2';
COMMENT ON COLUMN FRAMEWORK_USER_INF.USER_DESC
IS
    '����Ա����';
COMMENT ON COLUMN FRAMEWORK_USER_INF.REC_UPD_USR
IS
    '��������';
COMMENT ON COLUMN FRAMEWORK_USER_INF.ROW_CRT_TS
IS
    '����ʱ��';
COMMENT ON COLUMN FRAMEWORK_USER_INF.REC_UPD_TS
IS
    '������ʱ��';

