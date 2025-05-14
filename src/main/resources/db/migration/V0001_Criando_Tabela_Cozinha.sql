Create Table tb_cozinhas(
    id bigint not null auto_increment,
    nome_cozinha varchar(70),

    primary key(id)
)engine+InnoDB default charset=utf8;

Create Table tb_estado(
    id bigint not null auto_increment,
    nome_estado varchar(70),

    primary key(id)
)engine+InnoDB default charset=utf8;
