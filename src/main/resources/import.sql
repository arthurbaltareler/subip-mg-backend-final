-- Pessoas
INSERT INTO tb_pessoa (nome, cpf, email) VALUES ('Ana Souza', '11111111111', 'ana.souza@email.com');
INSERT INTO tb_pessoa (nome, cpf, email) VALUES ('Bruno Lima', '22222222222', 'bruno.lima@email.com');
INSERT INTO tb_pessoa (nome, cpf, email) VALUES ('Carla Rocha', '33333333333', 'carla.rocha@email.com');
INSERT INTO tb_pessoa (nome, cpf, email) VALUES ('Laura Mendes', '55555555555', 'laura.mendes@email.com');
INSERT INTO tb_pessoa (nome, cpf, email) VALUES ('Pedro Almeida', '66666666666', 'pedro.almeida@email.com');
INSERT INTO tb_pessoa (nome, cpf, email) VALUES ('Mariana Alves', '88888888888', 'mariana.alves@email.com');

-- Usuarios
INSERT INTO tb_usuario (login, senha, perfil, pessoa_id) VALUES ('ana', '123456', 'LEITOR', 1);
INSERT INTO tb_usuario (login, senha, perfil, pessoa_id) VALUES ('bruno', '123456', 'ATENDENTE', 2);
INSERT INTO tb_usuario (login, senha, perfil, pessoa_id) VALUES ('carla', '123456', 'ADMIN', 3);
INSERT INTO tb_usuario (login, senha, perfil, pessoa_id) VALUES ('laura', '123456', 'LEITOR', 4);
INSERT INTO tb_usuario (login, senha, perfil, pessoa_id) VALUES ('pedro', '123456', 'LEITOR', 5);
INSERT INTO tb_usuario (login, senha, perfil, pessoa_id) VALUES ('mariana', '123456', 'LEITOR', 6);

-- Generos
INSERT INTO tb_genero (descricao) VALUES ('Romance');
INSERT INTO tb_genero (descricao) VALUES ('Realismo');
INSERT INTO tb_genero (descricao) VALUES ('Literatura Brasileira');
INSERT INTO tb_genero (descricao) VALUES ('Historia');
INSERT INTO tb_genero (descricao) VALUES ('Tecnologia');
INSERT INTO tb_genero (descricao) VALUES ('Infantil');
INSERT INTO tb_genero (descricao) VALUES ('Ficcao');
INSERT INTO tb_genero (descricao) VALUES ('Educacao');

-- Livros
INSERT INTO tb_livro (titulo, isbn, genero_id) VALUES ('Dom Casmurro', '9788535910663', 1);
INSERT INTO tb_livro (titulo, isbn, genero_id) VALUES ('O Cortico', '9788572326972', 2);
INSERT INTO tb_livro (titulo, isbn, genero_id) VALUES ('Capitaes da Areia', '9788535914067', 3);
INSERT INTO tb_livro (titulo, isbn, genero_id) VALUES ('Vidas Secas', '9788535926138', 3);
INSERT INTO tb_livro (titulo, isbn, genero_id) VALUES ('Grande Sertao Veredas', '9788535929559', 1);
INSERT INTO tb_livro (titulo, isbn, genero_id) VALUES ('A Hora da Estrela', '9788532508126', 1);
INSERT INTO tb_livro (titulo, isbn, genero_id) VALUES ('Memorias Postumas de Bras Cubas', '9788535910664', 2);
INSERT INTO tb_livro (titulo, isbn, genero_id) VALUES ('Quarto de Despejo', '9788503012924', 3);
INSERT INTO tb_livro (titulo, isbn, genero_id) VALUES ('Algoritmos', '9788576058819', 5);
INSERT INTO tb_livro (titulo, isbn, genero_id) VALUES ('Introducao ao Spring Boot', '9788550804624', 5);
INSERT INTO tb_livro (titulo, isbn, genero_id) VALUES ('Historia de Minas Gerais', '9788570416699', 4);
INSERT INTO tb_livro (titulo, isbn, genero_id) VALUES ('O Pequeno Principe', '9788595081512', 6);
INSERT INTO tb_livro (titulo, isbn, genero_id) VALUES ('1984', '9788535914849', 7);
INSERT INTO tb_livro (titulo, isbn, genero_id) VALUES ('Didatica Geral', '9786555871780', 8);

-- Bibliotecas
INSERT INTO tb_biblioteca (nome) VALUES ('Biblioteca Publica Estadual de Minas Gerais');
INSERT INTO tb_biblioteca (nome) VALUES ('Biblioteca Municipal de Timoteo');
INSERT INTO tb_biblioteca (nome) VALUES ('Biblioteca Comunitaria Vale do Aco');
INSERT INTO tb_biblioteca (nome) VALUES ('Biblioteca Publica de Belo Horizonte');
INSERT INTO tb_biblioteca (nome) VALUES ('Biblioteca CEFET Timoteo');

-- Exemplares
INSERT INTO tb_exemplar (tombo, situacao, livro_id, biblioteca_id) VALUES ('TOMBO-0001', 'DISPONIVEL', 1, 1);
INSERT INTO tb_exemplar (tombo, situacao, livro_id, biblioteca_id) VALUES ('TOMBO-0002', 'EMPRESTADO', 1, 2);
INSERT INTO tb_exemplar (tombo, situacao, livro_id, biblioteca_id) VALUES ('TOMBO-0003', 'DISPONIVEL', 2, 1);
INSERT INTO tb_exemplar (tombo, situacao, livro_id, biblioteca_id) VALUES ('TOMBO-0004', 'INDISPONIVEL', 3, 3);
INSERT INTO tb_exemplar (tombo, situacao, livro_id, biblioteca_id) VALUES ('TOMBO-0005', 'DISPONIVEL', 4, 2);
INSERT INTO tb_exemplar (tombo, situacao, livro_id, biblioteca_id) VALUES ('TOMBO-0006', 'EMPRESTADO', 4, 1);
INSERT INTO tb_exemplar (tombo, situacao, livro_id, biblioteca_id) VALUES ('TOMBO-0007', 'DISPONIVEL', 5, 1);
INSERT INTO tb_exemplar (tombo, situacao, livro_id, biblioteca_id) VALUES ('TOMBO-0008', 'RESERVADO', 6, 4);
INSERT INTO tb_exemplar (tombo, situacao, livro_id, biblioteca_id) VALUES ('TOMBO-0009', 'DISPONIVEL', 7, 5);
INSERT INTO tb_exemplar (tombo, situacao, livro_id, biblioteca_id) VALUES ('TOMBO-0010', 'EMPRESTADO', 8, 3);
INSERT INTO tb_exemplar (tombo, situacao, livro_id, biblioteca_id) VALUES ('TOMBO-0011', 'DISPONIVEL', 9, 1);
INSERT INTO tb_exemplar (tombo, situacao, livro_id, biblioteca_id) VALUES ('TOMBO-0012', 'DISPONIVEL', 10, 2);
INSERT INTO tb_exemplar (tombo, situacao, livro_id, biblioteca_id) VALUES ('TOMBO-0013', 'DISPONIVEL', 11, 4);
INSERT INTO tb_exemplar (tombo, situacao, livro_id, biblioteca_id) VALUES ('TOMBO-0014', 'DISPONIVEL', 12, 3);
INSERT INTO tb_exemplar (tombo, situacao, livro_id, biblioteca_id) VALUES ('TOMBO-0015', 'EMPRESTADO', 13, 1);
INSERT INTO tb_exemplar (tombo, situacao, livro_id, biblioteca_id) VALUES ('TOMBO-0016', 'DISPONIVEL', 14, 5);
INSERT INTO tb_exemplar (tombo, situacao, livro_id, biblioteca_id) VALUES ('TOMBO-0017', 'DISPONIVEL', 1, 3);
INSERT INTO tb_exemplar (tombo, situacao, livro_id, biblioteca_id) VALUES ('TOMBO-0018', 'INDISPONIVEL', 2, 4);
INSERT INTO tb_exemplar (tombo, situacao, livro_id, biblioteca_id) VALUES ('TOMBO-0019', 'DISPONIVEL', 5, 2);
INSERT INTO tb_exemplar (tombo, situacao, livro_id, biblioteca_id) VALUES ('TOMBO-0020', 'EMPRESTADO', 6, 1);
INSERT INTO tb_exemplar (tombo, situacao, livro_id, biblioteca_id) VALUES ('TOMBO-0021', 'DISPONIVEL', 8, 2);
INSERT INTO tb_exemplar (tombo, situacao, livro_id, biblioteca_id) VALUES ('TOMBO-0022', 'DISPONIVEL', 9, 5);
INSERT INTO tb_exemplar (tombo, situacao, livro_id, biblioteca_id) VALUES ('TOMBO-0023', 'DISPONIVEL', 10, 4);
INSERT INTO tb_exemplar (tombo, situacao, livro_id, biblioteca_id) VALUES ('TOMBO-0024', 'RESERVADO', 12, 1);

-- Emprestimos de demonstracao
INSERT INTO tb_emprestimo (situacao, data_retirada, data_devolucao_prevista, data_devolucao, exemplar_id, pessoa_id) VALUES ('EM_ANDAMENTO', CURRENT_DATE - 3, CURRENT_DATE + 12, NULL, 2, 4);
INSERT INTO tb_emprestimo (situacao, data_retirada, data_devolucao_prevista, data_devolucao, exemplar_id, pessoa_id) VALUES ('RENOVADO', CURRENT_DATE - 20, CURRENT_DATE + 10, NULL, 6, 5);
INSERT INTO tb_emprestimo (situacao, data_retirada, data_devolucao_prevista, data_devolucao, exemplar_id, pessoa_id) VALUES ('ATRASADO', CURRENT_DATE - 25, CURRENT_DATE - 10, NULL, 10, 6);
INSERT INTO tb_emprestimo (situacao, data_retirada, data_devolucao_prevista, data_devolucao, exemplar_id, pessoa_id) VALUES ('EM_ANDAMENTO', CURRENT_DATE - 4, CURRENT_DATE + 11, NULL, 15, 5);
INSERT INTO tb_emprestimo (situacao, data_retirada, data_devolucao_prevista, data_devolucao, exemplar_id, pessoa_id) VALUES ('EM_ANDAMENTO', CURRENT_DATE - 7, CURRENT_DATE + 8, NULL, 20, 6);
INSERT INTO tb_emprestimo (situacao, data_retirada, data_devolucao_prevista, data_devolucao, exemplar_id, pessoa_id) VALUES ('DEVOLVIDO', CURRENT_DATE - 18, CURRENT_DATE - 3, CURRENT_DATE, 8, 4);
INSERT INTO tb_emprestimo (situacao, data_retirada, data_devolucao_prevista, data_devolucao, exemplar_id, pessoa_id) VALUES ('DEVOLVIDO', CURRENT_DATE - 16, CURRENT_DATE - 1, CURRENT_DATE, 24, 6);

-- Reservas de demonstracao
INSERT INTO tb_reserva (situacao, data_registro, exemplar_id, pessoa_id) VALUES ('ATIVA', CURRENT_DATE - 1, 6, 4);
INSERT INTO tb_reserva (situacao, data_registro, exemplar_id, pessoa_id) VALUES ('ATIVA', CURRENT_DATE - 2, 8, 5);
INSERT INTO tb_reserva (situacao, data_registro, exemplar_id, pessoa_id) VALUES ('ATIVA', CURRENT_DATE - 1, 15, 6);
INSERT INTO tb_reserva (situacao, data_registro, exemplar_id, pessoa_id) VALUES ('CANCELADA', CURRENT_DATE - 5, 10, 4);
INSERT INTO tb_reserva (situacao, data_registro, exemplar_id, pessoa_id) VALUES ('EXPIRADA', CURRENT_DATE - 6, 24, 5);
