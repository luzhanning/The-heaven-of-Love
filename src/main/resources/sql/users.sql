create table user
(
  id       char(20)                 not null
    primary key,
  password char(255)                null,
  sex      varchar(10) charset utf8 null,
  sex1     varchar(10) charset utf8 null,
  nickname varchar(20) charset utf8 null
);
