CREATE TABLE users (
  username VARCHAR(50) NOT NULL,
  password VARCHAR(100) NOT NULL,
  enabled TINYINT NOT NULL DEFAULT 1,
  PRIMARY KEY (username)
);
   
CREATE TABLE authorities (
  username VARCHAR(50) NOT NULL,
  authority VARCHAR(50) NOT NULL,
  FOREIGN KEY (username) REFERENCES users(username)
);

CREATE UNIQUE INDEX ix_auth_username
  on authorities (username,authority);
  
INSERT INTO users (username, password, enabled)
  values ('user','1234',1);
 
INSERT INTO authorities (username, authority)
  values ('user', 'ROLE_USER');
  
INSERT INTO users (username, password, enabled)
  values ('admin','1',1);
 
INSERT INTO authorities (username, authority)
  values ('admin', 'ROLE_ADMIN');
  
DROP TABLE c_authorities;  
DROP TABLE c_users;
  
CREATE TABLE c_users (
  num INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  email VARCHAR(50) NOT NULL,
  password VARCHAR(100) NOT NULL,
  enabled TINYINT NOT NULL DEFAULT 1,
  regDate DATETIME NOT NULL DEFAULT NOW(),
  PRIMARY KEY (num)
);
   
CREATE TABLE c_authorities (
  num int NOT NULL,
  authority VARCHAR(50) NOT NULL DEFAULT 'ROLE_USER',
  FOREIGN KEY (num) REFERENCES c_users(num)
);
 
CREATE UNIQUE INDEX ix_auth_email on c_authorities (num,authority);
  
INSERT INTO c_users (name, email, PASSWORD) values ('구디', 'gdj@email.com','1234');
INSERT INTO c_users (name, email, password) values ('관리자', 'admin@email.com','1');
 
INSERT INTO c_authorities (num, authority) values (1, 'ROLE_USER');
INSERT INTO c_authorities (num, authority) values (2, 'ROLE_ADMIN');



CREATE TABLE b_users (
  num INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  email VARCHAR(50) NOT NULL,
  password VARCHAR(100) NOT NULL,
  phone VARCHAR(11) NULL,
  enabled TINYINT NOT NULL DEFAULT 1,
  regDate DATETIME NOT NULL DEFAULT NOW(),
  PRIMARY KEY (num)
);
   
CREATE TABLE b_authorities (
  num int NOT NULL,
  authority VARCHAR(50) NOT NULL DEFAULT 'ROLE_USER',
  FOREIGN KEY (num) REFERENCES b_users(num)
);
 
CREATE UNIQUE INDEX ix_auth_email on b_authorities (num,authority);

INSERT INTO b_users (name, email, PASSWORD, phone) VALUES (#{}, #{}, #{}, #{});

INSERT INTO b_authorities (num) VALUES (#{num});
  
SELECT CASE WHEN MAX(num) IS NULL THEN 0 ELSE MAX(num) END AS num FROM b_users;  
  
CREATE TABLE b_message (
  num INT NOT NULL AUTO_INCREMENT,
  target_num INT NOT NULL,
  reg_num INT NOT NULL,
  message VARCHAR(255) NULL,
  regDate DATETIME NOT NULL DEFAULT NOW(),
  PRIMARY KEY (num)
);

INSERT INTO b_message (target_num, reg_num, message) VALUES (1,1,'테스트');

SELECT a.num, a.message,
       b.name, b.img
  FROM b_message AS a 
 INNER JOIN b_users AS b
   ON (a.reg_num = b.num)
 WHERE a.target_num = 1
 ORDER BY 1;
 
CREATE TABLE b_interests (
  num INT NOT NULL,
  target_num INT NOT NULL,
  state INT NOT NULL DEFAULT 0,
  regDate DATETIME NOT NULL DEFAULT NOW(),
  PRIMARY KEY (num, target_num)
);

CREATE VIEW v_interests AS 
SELECT 1  AS num,  'C' 				AS interest_name
UNION ALL 
SELECT 2  AS num,  'C++' 			AS interest_name
UNION ALL 
SELECT 3  AS num,  'C#' 			AS interest_name
UNION ALL 
SELECT 4  AS num,  'JAVA' 			AS interest_name
UNION ALL 
SELECT 5  AS num,  'WEBSERVICE' 	AS interest_name
UNION ALL 
SELECT 6  AS num,  'DATABASE' 	AS interest_name
UNION ALL 
SELECT 7  AS num,  'SQL' 			AS interest_name
UNION ALL 
SELECT 8  AS num,  'HTML' 			AS interest_name
UNION ALL 
SELECT 9  AS num,  'CSS' 			AS interest_name
UNION ALL 
SELECT 10 AS num,  'JAVASCRIPT' 	AS interest_name
UNION ALL 
SELECT 11 AS num,  'PYTHON' 		AS interest_name
UNION ALL 
SELECT 12 AS num,  'RUBE' 			AS interest_name
UNION ALL 
SELECT 13 AS num,  'GO' 			AS interest_name
UNION ALL 
SELECT 14 AS num,  'PHP' 			AS interest_name
UNION ALL 
SELECT 15 AS num,  'ASP' 			AS interest_name
UNION ALL 
SELECT 16 AS num,  'JSP' 			AS interest_name
;

INSERT INTO b_interests (num, target_num)
SELECT num, '1' AS target_num  FROM v_interests; 



SELECT b.num, b.interest_name, a.state
 FROM b_interests AS a
INNER JOIN v_interests AS b
  ON (a.num = b.num)
WHERE a.target_num = 2;


UPDATE b_users 
   SET NAME = #{}, 
	    email = #{}, 
		 PASSWORD = #{}, 
		 phone = #{}
WHERE num = #{};

UPDATE b_interests
   SET state = #{state}
 WHERE num = #{num}
   AND target_num = #{reg_num}