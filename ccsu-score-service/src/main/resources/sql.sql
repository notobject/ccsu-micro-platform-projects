set global time_zone = '+8:00';

create database `ccsu_test`;

use ccsu_test;


create table ccsu_score_student_data(
  `id` varchar(14) not null comment '学生学号',
  `total_score` decimal(10,1) default '0.0' comment '综测分数',
  primary key (`id`)
)engine = InnoDB default charset utf8MB4;

create table ccsu_score_event(
  `id` varchar(14) not null comment '学生学号',
  `record_time` datetime not null comment '事件发生的时间',
  `event` longtext not null comment '原因',
  `score` decimal(10,1) default '0.0' comment '综测分数',
  foreign key (`id`) references ccsu_score_student_data(`id`),
  primary key (`record_time`)
)engine = InnoDB default charset utf8MB4;