-- auto-generated definition
create table personinfo
(
  id          int                                 not null
    primary key,
  username    varchar(255)                        null,
  create_time timestamp default CURRENT_TIMESTAMP null
);
INSERT INTO persontest.personinfo (id, username, create_time) VALUES (0, 'CSDN yunlingfly', '2019-06-03 02:29:41');
INSERT INTO persontest.personinfo (id, username, create_time) VALUES (1, 'dakl');
INSERT INTO persontest.personinfo (id, username, create_time) VALUES (2, 'dhasjk');
