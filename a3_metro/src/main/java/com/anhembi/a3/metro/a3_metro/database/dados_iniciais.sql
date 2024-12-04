
INSERT INTO `tb_usuario` (`id_usuario_pk`, `ativo`, `dt_criacao`, `dt_modificacao`, `email`, `nm_usuario`, `senha`, `tecnico`) VALUES
(1, 1, '2024-12-02', '2024-12-02', 'teste@teste.com', 'Usuario de Teste', 'teste', 0),
(2, 1, '2024-12-02', '2024-12-02', 'admin@admin.com', 'Usuario Tecnico', 'admin', 1);

INSERT INTO `tb_noticia` (`id_noticia_pk`, `ativo`, `dt_criacao`, `dt_modificacao`, `descricao`, `tipo_aviso`, `id_linha_fk`, `id_usuario_fk`) VALUES
(1, 1, '2024-12-02', '2024-12-02', NULL, 'ATRASO', NULL, 2),
(2, 1, '2024-12-02', '2024-12-02', NULL, 'ATRASO', NULL, 2);


INSERT INTO `tb_aviso_usuario` (`id_aviso_usuario_pk`, `ativo`, `dt_criacao`, `dt_modificacao`, `tipo_aviso`, `id_usuario_fk`) VALUES
(1, 1, '2024-12-02', '2024-12-02', 'ATRASO', 1),
(2, 1, '2024-12-02', '2024-12-02', 'SUPERLOTACAO', 1),
(3, 1, '2024-12-02', '2024-12-02', 'VAZIO', 1),
(4, 1, '2024-12-02', '2024-12-02', 'QUEBRADO', 1),
(5, 1, '2024-12-02', '2024-12-02', 'ATRASO', 1);
