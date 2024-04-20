INSERT INTO tb_user_type (tx_description, bl_send, bl_receive)
VALUES
('Usuário', true, true),
('Lojista', false, true),
('Fundo de Investimento', true, false)
;

INSERT INTO tb_document_type(tx_description)
VALUES('CPF'),('CNPJ');


INSERT INTO tb_user(tx_complete_name, id_user_type, tx_email, vl_balance)
VALUES
    ('João Silva', 1, 'joao.silva@gmail.com', 200.00),
    ('Maria Pereira', 1, 'maria.pereira@gmail.com', 340.00),
    ('Monstros S.A.', 2, 'marketing@monstros.com', 2000.00),
    ('Fundo XP', 3, 'fundo@xp.com', 12000.00);


INSERT INTO tb_user_document(id_user, id_document_type, nm_document)
values
(1, 1, 100100100),
(2, 1, 200200200),
(3, 2, 300300300),
(4, 2, 400400400);