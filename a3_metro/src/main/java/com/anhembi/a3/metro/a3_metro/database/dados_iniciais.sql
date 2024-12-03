
INSERT INTO `tb_noticia` (`id_noticia_pk`, `ativo`, `dt_criacao`, `dt_modificacao`, `descricao`, `tipo_aviso`, `id_linha_fk`, `id_usuario_fk`) VALUES
(1, 1, '2024-12-02', '2024-12-02', NULL, 'ATRASO', NULL, NULL),
(2, 1, '2024-12-02', '2024-12-02', NULL, 'ATRASO', NULL, NULL);

INSERT INTO `tb_usuario` (`id_usuario_pk`, `ativo`, `dt_criacao`, `dt_modificacao`, `email`, `nm_usuario`, `senha`, `tecnico`) VALUES
(1, 1, '2024-12-02', '2024-12-02', 'teste@teste.com', 'Usuario de Teste', 'teste', 0),
(2, 1, '2024-12-02', '2024-12-02', 'admin@admin.com', 'Usuario Tecnico', 'admin', 0);

INSERT INTO `tb_aviso_usuario` (`id_aviso_usuario_pk`, `ativo`, `dt_criacao`, `dt_modificacao`, `tipo_aviso`, `id_usuario_fk`) VALUES
(1, 1, '2024-12-02', '2024-12-02', 'ATRASO', NULL),
(2, 1, '2024-12-02', '2024-12-02', 'SUPERLOTACAO', NULL),
(3, 1, '2024-12-02', '2024-12-02', 'VAZIO', NULL),
(4, 1, '2024-12-02', '2024-12-02', 'QUEBRADO', NULL),
(5, 1, '2024-12-02', '2024-12-02', 'ATRASO', NULL),
