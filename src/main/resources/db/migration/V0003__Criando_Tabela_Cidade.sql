create table tb_cidade(
    id bigint not null auto_increment,
    nome_cidade varchar(80) not null,
    estado_id bigint not null,

    primary key (id)
) engine=InnoDB default charset=utf8;