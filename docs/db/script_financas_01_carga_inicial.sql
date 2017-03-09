-- Insere usuário
insert into usuario (nome, login, senha, status_ativo, versao) values ('Usuário','usuario','HASH DE SENHA',1, 1);

-- Insere categorias pai do usuário
insert into categoria (id_usuario, id_categoria_pai, tipo, descricao, versao) select id, null, 'R', 'Benefício', 1 from usuario where login = 'usuario';
insert into categoria (id_usuario, id_categoria_pai, tipo, descricao, versao) select id, null, 'R', 'Investimento', 1 from usuario where login = 'usuario';
insert into categoria (id_usuario, id_categoria_pai, tipo, descricao, versao) select id, null, 'R', 'Renda Assalariada', 1 from usuario where login = 'usuario';

insert into categoria (id_usuario, id_categoria_pai, tipo, descricao, versao) select id, null, 'D', 'Alimentação', 1 from usuario where login = 'usuario';
insert into categoria (id_usuario, id_categoria_pai, tipo, descricao, versao) select id, null, 'D', 'Comunicação', 1 from usuario where login = 'usuario';
insert into categoria (id_usuario, id_categoria_pai, tipo, descricao, versao) select id, null, 'D', 'Desconto na Fonte', 1 from usuario where login = 'usuario';
insert into categoria (id_usuario, id_categoria_pai, tipo, descricao, versao) select id, null, 'D', 'Educação', 1 from usuario where login = 'usuario';
insert into categoria (id_usuario, id_categoria_pai, tipo, descricao, versao) select id, null, 'D', 'Lazer', 1 from usuario where login = 'usuario';
insert into categoria (id_usuario, id_categoria_pai, tipo, descricao, versao) select id, null, 'D', 'Moradia', 1 from usuario where login = 'usuario';
insert into categoria (id_usuario, id_categoria_pai, tipo, descricao, versao) select id, null, 'D', 'Transporte', 1 from usuario where login = 'usuario';

-- Insere categorias filho do usuário (pode ser quantos níveis desejar, mas vou me ater apenas ao segundo nível)
insert into categoria (id_usuario, id_categoria_pai, tipo, descricao, versao) select u.id, c.id, c.tipo, 'Vale Alimentação', 1 from categoria c inner join usuario u ON u.id = c.id_usuario where c.tipo = 'R' and c.descricao = 'Benefício' and u.login = 'usuario';
insert into categoria (id_usuario, id_categoria_pai, tipo, descricao, versao) select u.id, c.id, c.tipo, 'Auxílio Creche', 1 from categoria c inner join usuario u ON u.id = c.id_usuario where c.tipo = 'R' and c.descricao = 'Benefício' and u.login = 'usuario';

insert into categoria (id_usuario, id_categoria_pai, tipo, descricao, versao) select u.id, c.id, c.tipo, 'Cupom/Juros', 1 from categoria c inner join usuario u ON u.id = c.id_usuario where c.tipo = 'R' and c.descricao = 'Investimento' and u.login = 'usuario';
insert into categoria (id_usuario, id_categoria_pai, tipo, descricao, versao) select u.id, c.id, c.tipo, 'Dividendos', 1 from categoria c inner join usuario u ON u.id = c.id_usuario where c.tipo = 'R' and c.descricao = 'Investimento' and u.login = 'usuario';
insert into categoria (id_usuario, id_categoria_pai, tipo, descricao, versao) select u.id, c.id, c.tipo, 'Resgate', 1 from categoria c inner join usuario u ON u.id = c.id_usuario where c.tipo = 'R' and c.descricao = 'Investimento' and u.login = 'usuario';

insert into categoria (id_usuario, id_categoria_pai, tipo, descricao, versao) select u.id, c.id, c.tipo, 'Descanso Remunerado', 1 from categoria c inner join usuario u ON u.id = c.id_usuario where c.tipo = 'R' and c.descricao = 'Renda Assalariada' and u.login = 'usuario';
insert into categoria (id_usuario, id_categoria_pai, tipo, descricao, versao) select u.id, c.id, c.tipo, 'Salário Bruto', 1 from categoria c inner join usuario u ON u.id = c.id_usuario where c.tipo = 'R' and c.descricao = 'Renda Assalariada' and u.login = 'usuario';
insert into categoria (id_usuario, id_categoria_pai, tipo, descricao, versao) select u.id, c.id, c.tipo, 'Salário 13o', 1 from categoria c inner join usuario u ON u.id = c.id_usuario where c.tipo = 'R' and c.descricao = 'Renda Assalariada' and u.login = 'usuario';

insert into categoria (id_usuario, id_categoria_pai, tipo, descricao, versao) select u.id, c.id, c.tipo, 'Lanche', 1 from categoria c inner join usuario u ON u.id = c.id_usuario where c.tipo = 'D' and c.descricao = 'Alimentação' and u.login = 'usuario';
insert into categoria (id_usuario, id_categoria_pai, tipo, descricao, versao) select u.id, c.id, c.tipo, 'Restaurante', 1 from categoria c inner join usuario u ON u.id = c.id_usuario where c.tipo = 'D' and c.descricao = 'Alimentação' and u.login = 'usuario';
insert into categoria (id_usuario, id_categoria_pai, tipo, descricao, versao) select u.id, c.id, c.tipo, 'Supermercado', 1 from categoria c inner join usuario u ON u.id = c.id_usuario where c.tipo = 'D' and c.descricao = 'Alimentação' and u.login = 'usuario';

insert into categoria (id_usuario, id_categoria_pai, tipo, descricao, versao) select u.id, c.id, c.tipo, 'Internet', 1 from categoria c inner join usuario u ON u.id = c.id_usuario where c.tipo = 'D' and c.descricao = 'Comunicação' and u.login = 'usuario';
insert into categoria (id_usuario, id_categoria_pai, tipo, descricao, versao) select u.id, c.id, c.tipo, 'Telefone Celular', 1 from categoria c inner join usuario u ON u.id = c.id_usuario where c.tipo = 'D' and c.descricao = 'Comunicação' and u.login = 'usuario';
insert into categoria (id_usuario, id_categoria_pai, tipo, descricao, versao) select u.id, c.id, c.tipo, 'Telefone Fixo', 1 from categoria c inner join usuario u ON u.id = c.id_usuario where c.tipo = 'D' and c.descricao = 'Comunicação' and u.login = 'usuario';

insert into categoria (id_usuario, id_categoria_pai, tipo, descricao, versao) select u.id, c.id, c.tipo, 'INSS', 1 from categoria c inner join usuario u ON u.id = c.id_usuario where c.tipo = 'D' and c.descricao = 'Desconto na Fonte' and u.login = 'usuario';
insert into categoria (id_usuario, id_categoria_pai, tipo, descricao, versao) select u.id, c.id, c.tipo, 'IRPF', 1 from categoria c inner join usuario u ON u.id = c.id_usuario where c.tipo = 'D' and c.descricao = 'Desconto na Fonte' and u.login = 'usuario';

insert into categoria (id_usuario, id_categoria_pai, tipo, descricao, versao) select u.id, c.id, c.tipo, 'Cursos e Treinamentos', 1 from categoria c inner join usuario u ON u.id = c.id_usuario where c.tipo = 'D' and c.descricao = 'Educação' and u.login = 'usuario';
insert into categoria (id_usuario, id_categoria_pai, tipo, descricao, versao) select u.id, c.id, c.tipo, 'Material de Estudo', 1 from categoria c inner join usuario u ON u.id = c.id_usuario where c.tipo = 'D' and c.descricao = 'Educação' and u.login = 'usuario';
insert into categoria (id_usuario, id_categoria_pai, tipo, descricao, versao) select u.id, c.id, c.tipo, 'Vale Universidade / Faculdade', 1 from categoria c inner join usuario u ON u.id = c.id_usuario where c.tipo = 'D' and c.descricao = 'Educação' and u.login = 'usuario';

insert into categoria (id_usuario, id_categoria_pai, tipo, descricao, versao) select u.id, c.id, c.tipo, 'Hospedagem', 1 from categoria c inner join usuario u ON u.id = c.id_usuario where c.tipo = 'D' and c.descricao = 'Lazer' and u.login = 'usuario';
insert into categoria (id_usuario, id_categoria_pai, tipo, descricao, versao) select u.id, c.id, c.tipo, 'Livros', 1 from categoria c inner join usuario u ON u.id = c.id_usuario where c.tipo = 'D' and c.descricao = 'Lazer' and u.login = 'usuario';
insert into categoria (id_usuario, id_categoria_pai, tipo, descricao, versao) select u.id, c.id, c.tipo, 'Parques e Afins', 1 from categoria c inner join usuario u ON u.id = c.id_usuario where c.tipo = 'D' and c.descricao = 'Lazer' and u.login = 'usuario';

insert into categoria (id_usuario, id_categoria_pai, tipo, descricao, versao) select u.id, c.id, c.tipo, 'Água e Esgoto', 1 from categoria c inner join usuario u ON u.id = c.id_usuario where c.tipo = 'D' and c.descricao = 'Moradia' and u.login = 'usuario';
insert into categoria (id_usuario, id_categoria_pai, tipo, descricao, versao) select u.id, c.id, c.tipo, 'Aluguel', 1 from categoria c inner join usuario u ON u.id = c.id_usuario where c.tipo = 'D' and c.descricao = 'Moradia' and u.login = 'usuario';
insert into categoria (id_usuario, id_categoria_pai, tipo, descricao, versao) select u.id, c.id, c.tipo, 'Condomínio', 1 from categoria c inner join usuario u ON u.id = c.id_usuario where c.tipo = 'D' and c.descricao = 'Moradia' and u.login = 'usuario';
insert into categoria (id_usuario, id_categoria_pai, tipo, descricao, versao) select u.id, c.id, c.tipo, 'Energia Elétrica', 1 from categoria c inner join usuario u ON u.id = c.id_usuario where c.tipo = 'D' and c.descricao = 'Moradia' and u.login = 'usuario';
insert into categoria (id_usuario, id_categoria_pai, tipo, descricao, versao) select u.id, c.id, c.tipo, 'Gás', 1 from categoria c inner join usuario u ON u.id = c.id_usuario where c.tipo = 'D' and c.descricao = 'Moradia' and u.login = 'usuario';
insert into categoria (id_usuario, id_categoria_pai, tipo, descricao, versao) select u.id, c.id, c.tipo, 'Manutenção Imóvel', 1 from categoria c inner join usuario u ON u.id = c.id_usuario where c.tipo = 'D' and c.descricao = 'Moradia' and u.login = 'usuario';
insert into categoria (id_usuario, id_categoria_pai, tipo, descricao, versao) select u.id, c.id, c.tipo, 'Mobiliário', 1 from categoria c inner join usuario u ON u.id = c.id_usuario where c.tipo = 'D' and c.descricao = 'Moradia' and u.login = 'usuario';

insert into categoria (id_usuario, id_categoria_pai, tipo, descricao, versao) select u.id, c.id, c.tipo, 'Combustível', 1 from categoria c inner join usuario u ON u.id = c.id_usuario where c.tipo = 'D' and c.descricao = 'Transporte' and u.login = 'usuario';
insert into categoria (id_usuario, id_categoria_pai, tipo, descricao, versao) select u.id, c.id, c.tipo, 'Estacionamento', 1 from categoria c inner join usuario u ON u.id = c.id_usuario where c.tipo = 'D' and c.descricao = 'Transporte' and u.login = 'usuario';
insert into categoria (id_usuario, id_categoria_pai, tipo, descricao, versao) select u.id, c.id, c.tipo, 'Táxi', 1 from categoria c inner join usuario u ON u.id = c.id_usuario where c.tipo = 'D' and c.descricao = 'Transporte' and u.login = 'usuario';

-- Insere tipos de conta (tabela de domínio fixa)
insert into tipo_conta (descricao, codigo) values ('Caixa / Dinheiro','CAIXA');
insert into tipo_conta (descricao, codigo) values ('Conta Corrente','CORRENTE');
insert into tipo_conta (descricao, codigo) values ('Conta Poupança','POUPANCA');
insert into tipo_conta (descricao, codigo) values ('Cartão de Crédito','CARTAOCREDITO');
insert into tipo_conta (descricao, codigo) values ('Conta Investimento','INVESTIMENTO');

-- Insere contas do usuário
insert into conta (id_usuario, id_tipo_conta, descricao, versao) select u.id, t.id, 'Carteira', 1 from usuario u, tipo_conta t where u.login = 'usuario' and t.codigo = 'CAIXA';
insert into conta (id_usuario, id_tipo_conta, descricao, versao) select u.id, t.id, 'Conta Bradesco 123', 1 from usuario u, tipo_conta t where u.login = 'usuario' and t.codigo = 'CORRENTE';
insert into conta (id_usuario, id_tipo_conta, descricao, versao) select u.id, t.id, 'Conta BB 456', 1 from usuario u, tipo_conta t where u.login = 'usuario' and t.codigo = 'CORRENTE';
insert into conta (id_usuario, id_tipo_conta, descricao, versao) select u.id, t.id, 'Cartão Master', 1 from usuario u, tipo_conta t where u.login = 'usuario' and t.codigo = 'CARTAOCREDITO';
insert into conta (id_usuario, id_tipo_conta, descricao, versao) select u.id, t.id, 'Cartão Visa', 1 from usuario u, tipo_conta t where u.login = 'usuario' and t.codigo = 'CARTAOCREDITO';
