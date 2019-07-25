
-- 创建用户
INSERT INTO user VALUES ('1', '$2a$10$TxqCViOF0G78rYxsXSVpU.jI7VVF49E7ZNipfLvyNt.YCPqA3t8Fm', 'jxc');

-- 创建角色
INSERT INTO role VALUES ('1', 'ROLE_ADMIN');
INSERT INTO role VALUES ('2', 'ROLE_DEVLOP');
INSERT INTO role VALUES ('3', 'ROLE_STUDENT');

-- 创建角色用户关系
INSERT INTO user_role VALUES ('1', '1');
INSERT INTO user_role VALUES ('1', '2');
INSERT INTO user_role VALUES ('1', '3');
