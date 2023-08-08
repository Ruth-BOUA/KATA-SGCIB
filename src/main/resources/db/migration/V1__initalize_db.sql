DROP TABLE IF EXISTS ACCOUNTS ;
DROP TABLE IF EXISTS TRANSACTIONS ;

CREATE TABLE ACCOUNTS (
  id INTEGER PRIMARY KEY,
  balance INTEGER NOT NULL
);
CREATE SEQUENCE ACCOUNTS_SEQ
  START WITH 3
  INCREMENT BY 1;

CREATE TABLE TRANSACTIONS (
  id INTEGER PRIMARY KEY,
  account_id INTEGER NOT NULL REFERENCES ACCOUNTS(id),
  operation ENUM ('DEPOSIT','WITHDRAWAL') NOT NULL,
  amount INTEGER NOT NULL,
  execution_date DATE NOT NULL
);

CREATE SEQUENCE TRANSACTIONS_SEQ
  START WITH 3
  INCREMENT BY 1;


INSERT INTO ACCOUNTS VALUES (1,100);
INSERT INTO ACCOUNTS VALUES (2,50);

INSERT INTO TRANSACTIONS VALUES (1,1,'DEPOSIT',10,CURRENT_TIMESTAMP);
INSERT INTO TRANSACTIONS VALUES (2,2,'WITHDRAWAL',20,CURRENT_TIMESTAMP);


