create table user(
`studyNumberId` varchar(30) not null,
`name` varchar(30) not null,
`password` varchar(32) not null,
`class` varchar(20),
`securityAnswer` varchar(30) null,
`securityQuestion` varchar(100) null,
primary key (studyNumberId)
);