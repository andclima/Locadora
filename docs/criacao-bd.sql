create table cliente (
    idCliente serial primary key,
    nome varchar(200),
    email varchar(100),
    telefone varchar(30),
    tipo int
);

create table pessoafisica (
    idCliente int primary key,
    cpf varchar(15),
    identidade varchar(15),
    orgao varchar(30),
    cnh varchar(30),
    constraint fk_cliente_pessoafisica foreign key (idCliente) references cliente(idCliente)
);

create table pessoajuridica (
    idCliente int primary key,
    cnpj varchar(20),
    inscricaoEstadual varchar(20),
    nomeResponsavel varchar(100),
    cnhResponsavel varchar(30),
    constraint fk_cliente_pessoajuridica foreign key (idCliente) references cliente(idCliente)
);

create table veiculo (
    idVeiculo serial primary key,
    modelo varchar(200),
    placa varchar(100),
    renavam varchar(30),
    quilometragem int,
    valorDiaria decimal(10,2),
    disponivel smallint
);

create table locacao (
    idLocacao serial primary key,
    idCliente int,
    idVeiculo int,
    dataLocacao date,
    valorDiaria decimal(10,2),
    quantidadeDiarias int,
    constraint fk_veiculo_locacao foreign key (idVeiculo) references veiculo(idVeiculo),
    constraint fk_cliente_locacao foreign key (idCliente) references cliente(idCliente)
);

create table devolucao (
    idLocacao int primary key,
    dataDevolucao date,
    valorLocacao decimal(10,2),
    quantidadeDiarias int,
    constraint fk_locacao_devolucao foreign key (idLocacao) references locacao(idLocacao)
);

