--20210621

CREATE TABLE  MEMBER (
                                IDX NUMBER(6) CONSTRAINT MEMBER_IDX_PK PRIMARY KEY,
                                ID VARCHAR2(20) CONSTRAINT MEMBER_ID_NN NOT NULL,
                                Password VARCHAR2(20)CONSTRAINT MEMBER_PW_NN NOT NULL,
                                NAME  VARCHAR2(20)CONSTRAINT MEMBER_NAME_NN NOT NULL ,
                                PHONENUM  VARCHAR2(20)CONSTRAINT MEMBER_NUM_NN NOT NULL,
                                EMAIL VARCHAR2(20)CONSTRAINT MEMBER_EMAIL_NN NOT NULL
                              ) ;
          
CREATE TABLE  PRODUCT (
                                ICODE NUMBER(6) CONSTRAINT PRODUCT_ICODE_PK PRIMARY KEY,
                                INAME  VARCHAR2(20)CONSTRAINT PRODUCT_NAME_NN NOT NULL ,
                                IPRICE  INTEGER  
                                
                              ) ;          
CREATE TABLE IORDER (
                                ORDERCODE NUMBER(6) CONSTRAINT ORDER_OCODE_PK PRIMARY KEY,
                                ICODE NUMBER(6) CONSTRAINT ORDER_ICODE_FK REFERENCES PRODUCT(ICODE) NOT NULL,
                                IDX NUMBER(6) CONSTRAINT ORDER_IDX_FK REFERENCES MEMBER(IDX) NOT NULL ,
                                ORDERDATE  DATE DEFAULT SYSDATE,
                                COUNT INTEGER
                                
                              ) ;
    drop table iorder;
    drop table product;

CREATE TABLE  MEMBER (
                                IDX NUMBER(6) CONSTRAINT MEMBER_IDX_PK PRIMARY KEY,
                                ID VARCHAR2(20) CONSTRAINT MEMBER_ID_NN NOT NULL,
                                Password VARCHAR2(20)CONSTRAINT MEMBER_PW_NN NOT NULL,
                                NAME  VARCHAR2(20)CONSTRAINT MEMBER_NAME_NN NOT NULL ,
                                PHONENUM  VARCHAR2(20)CONSTRAINT MEMBER_NUM_NN NOT NULL,
                                EMAIL VARCHAR2(20)CONSTRAINT MEMBER_EMAIL_NN NOT NULL
                              ) ;
          
CREATE TABLE  PRODUCT (
                                ICODE NUMBER(6) CONSTRAINT PRODUCT_ICODE_PK PRIMARY KEY,
                                INAME  VARCHAR2(20)CONSTRAINT PRODUCT_NAME_NN NOT NULL ,
                                IPRICE  INTEGER , 
                                COUNT INTEGER
                              ) ;          
          
          
          
                              
                              
CREATE TABLE IORDER (
                                ORDERCODE NUMBER(6) CONSTRAINT ORDER_OCODE_PK PRIMARY KEY,
                                ICODE NUMBER(6) CONSTRAINT ORDER_ICODE_FK REFERENCES PRODUCT(ICODE) NOT NULL,
                                IDX NUMBER(6) CONSTRAINT ORDER_IDX_FK REFERENCES MEMBER(IDX) NOT NULL ,
                                ORDERDATE  DATE DEFAULT SYSDATE
                               
                                
                              ) ;      
                              
insert into member values (1,'qwe','qwe','qwe','qwe','qwe');
select * from member;
commit;