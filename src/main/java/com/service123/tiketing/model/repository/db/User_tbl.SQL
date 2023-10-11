CREATE TABLE USER_TBL(

                         ID NUMBER PRIMARY KEY,

                         USER_ROLES VARCHAR2(8),

                         NAME NVARCHAR2(20),

                         FAMILY NVARCHAR2(20),

                         USERNAME NVARCHAR2(20),

                         PASSWORD NVARCHAR2(20),

                         ACTIVE NUMBER(1),

                         DELETED NUMBER(1)
);


CREATE SEQUENCE USER_SEQ START WITH 1 INCREMENT BY 1;