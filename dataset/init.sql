-- user type
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE tb_user_type(
id_user_type bigint GENERATED ALWAYS as IDENTITY,
tx_description varchar(255),
bl_send boolean DEFAULT true,
bl_receive boolean DEFAULT true,
CONSTRAINT pk_user_type PRIMARY KEY(id_user_type)
);

INSERT INTO tb_user_type (tx_description, bl_send, bl_receive)
VALUES
('Usuário', true, true),
('Lojista', false, true),
('Fundo de Investimento', true, false)
;

-- tb_document_type
CREATE TABLE tb_document_type(
id_document_type bigint GENERATED ALWAYS AS IDENTITY,
tx_description varchar(255),
CONSTRAINT pk_document_type PRIMARY KEY (id_document_type)
);

INSERT INTO tb_document_type(tx_description)
VALUES('CPF'),('CNPJ');

-- tb_user
CREATE TABLE tb_user(
id_user bigint GENERATED ALWAYS AS IDENTITY,
tx_complete_name varchar(100) not null,
id_user_type bigint not null,
tx_email varchar(256) not null,
vl_balance decimal not null,
CONSTRAINT pk_user PRIMARY KEY (id_user),
CONSTRAINT fk_user_user_type FOREIGN KEY(id_user_type) REFERENCES tb_user_type(id_user_type),
CONSTRAINT uc_email UNIQUE(tx_email)
);

INSERT INTO tb_user(tx_complete_name, id_user_type, tx_email, vl_balance)
VALUES
    ('João Silva', 1, 'joao.silva@gmail.com', 200.00),
    ('Maria Pereira', 1, 'maria.pereira@gmail.com', 340.00),
    ('Monstros S.A.', 2, 'marketing@monstros.com', 2000.00),
    ('Fundo XP', 3, 'fundo@xp.com', 12000.00);

CREATE TABLE tb_transaction(
    id_transaction uuid DEFAULT uuid_generate_v4(),
    vl_amount decimal not null,
    id_sender bigint not null,
    id_receiver bigint not null,
    dt_timestamp timestamp,
    CONSTRAINT pk_transaction PRIMARY KEY (id_transaction),
    CONSTRAINT fk_sender_id FOREIGN KEY (id_sender) REFERENCES tb_user(id_user),
    CONSTRAINT fk_receiver_id FOREIGN KEY (id_receiver) REFERENCES tb_user(id_user)
);

CREATE TABLE tb_user_document(
    id_user_document bigint GENERATED ALWAYS AS IDENTITY,
    id_user bigint not null,
    nm_document bigint not null,
    id_document_type bigint not null,
    CONSTRAINT pk_user_document PRIMARY KEY (id_user_document),
    CONSTRAINT fk_user_document_user FOREIGN KEY (id_user) REFERENCES tb_user(id_user),
    CONSTRAINT fk_user_document_document_type FOREIGN KEY (id_document_type) REFERENCES tb_document_type(id_document_type),
    CONSTRAINT uc_document UNIQUE (nm_document)
);

INSERT INTO tb_user_document(id_user, id_document_type, nm_document)
values
(1, 1, 100100100),
(2, 1, 200200200),
(3, 2, 300300300),
(4, 2, 400400400);