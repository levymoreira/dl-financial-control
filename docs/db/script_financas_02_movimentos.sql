-- Insere movimento de receita
insert into movimento (id_usuario, id_conta, id_categoria, tipo, data, ordem, descricao, valor, status_segregado, status_transferencia, versao)
select u.id, c.id, ca.id, ca.tipo, '2017-01-02', 1, 'Juros recebidos do investimento XPTO', 1000, 0, 0, 1 from usuario u, conta c, categoria ca
where u.login = 'usuario' and c.descricao = 'Conta Bradesco 123' and ca.descricao = 'Cupom/Juros';

-- Saldo da conta = 1000

-- Insere movimento de receita
insert into movimento (id_usuario, id_conta, id_categoria, tipo, data, ordem, descricao, valor, status_segregado, status_transferencia, versao)
select u.id, c.id, ca.id, ca.tipo, '2017-01-04', 1, 'Dividendos ABDC5', 500, 0, 0, 1 from usuario u, conta c, categoria ca
where u.login = 'usuario' and c.descricao = 'Conta Bradesco 123' and ca.descricao = 'Dividendos';

-- Saldo da conta = 1500

--
-- INICIO DE OPERACAO ATOMICA - Receita Dividida
--

-- Insere receita principal que será dividida
insert into movimento (id_usuario, id_conta, tipo, data, ordem, descricao, valor, status_segregado, status_transferencia, versao)
select u.id, c.id, 'R', '2017-01-05', 1, 'Salário da Empresa X', 5000, 1, 0, 1 from usuario u, conta c
where u.login = 'usuario' and c.descricao = 'Conta Bradesco 123';

-- Insere "sub" receita dividida
insert into movimento (id_usuario, id_conta, id_categoria, id_movimento_pai, tipo, data, ordem, descricao, valor, status_segregado, status_transferencia, versao)
select u.id, c.id, ca.id, m.id, m.tipo, '2017-01-05', 1, 'Valor bruto', 6000, 0, 0, 1 from usuario u, conta c, categoria ca, movimento m
where u.login = 'usuario' and c.descricao = 'Conta Bradesco 123' and ca.descricao = 'Salário Bruto' and m.valor = 5000 and m.descricao = 'Salário da Empresa X';

-- Insere "sub" receita dividida
insert into movimento (id_usuario, id_conta, id_categoria, id_movimento_pai, tipo, data, ordem, descricao, valor, status_segregado, status_transferencia, versao)
select u.id, c.id, ca.id, m.id, m.tipo, '2017-01-05', 1, 'Vale alimentação', 500, 0, 0, 1 from usuario u, conta c, categoria ca, movimento m
where u.login = 'usuario' and c.descricao = 'Conta Bradesco 123' and ca.descricao = 'Vale Alimentação' and m.valor = 5000 and m.descricao = 'Salário da Empresa X';

-- Insere "sub" receita dividida
insert into movimento (id_usuario, id_conta, id_categoria, id_movimento_pai, tipo, data, ordem, descricao, valor, status_segregado, status_transferencia, versao)
select u.id, c.id, ca.id, m.id, m.tipo, '2017-01-05', 1, 'Auxílio creche', 300, 0, 0, 1 from usuario u, conta c, categoria ca, movimento m
where u.login = 'usuario' and c.descricao = 'Conta Bradesco 123' and ca.descricao = 'Auxílio Creche' and m.valor = 5000 and m.descricao = 'Salário da Empresa X';

-- Insere "sub" despesa dividida
insert into movimento (id_usuario, id_conta, id_categoria, id_movimento_pai, tipo, data, ordem, descricao, valor, status_segregado, status_transferencia, versao)
select u.id, c.id, ca.id, m.id, m.tipo, '2017-01-05', 1, 'Desconto INSS', -600, 0, 0, 1 from usuario u, conta c, categoria ca, movimento m
where u.login = 'usuario' and c.descricao = 'Conta Bradesco 123' and ca.descricao = 'INSS' and m.valor = 5000 and m.descricao = 'Salário da Empresa X';

-- Insere "sub" despesa dividida
insert into movimento (id_usuario, id_conta, id_categoria, id_movimento_pai, tipo, data, ordem, descricao, valor, status_segregado, status_transferencia, versao)
select u.id, c.id, ca.id, m.id, m.tipo, '2017-01-05', 1, 'Imposto de Renda', -1200, 0, 0, 1 from usuario u, conta c, categoria ca, movimento m
where u.login = 'usuario' and c.descricao = 'Conta Bradesco 123' and ca.descricao = 'IRPF' and m.valor = 5000 and m.descricao = 'Salário da Empresa X';

--
-- FIM DE OPERACAO ATOMICA
--

-- Saldo da conta = 6500

-- Insere movimento de despesa
insert into movimento (id_usuario, id_conta, id_categoria, tipo, data, ordem, descricao, valor, status_segregado, status_transferencia, versao)
select u.id, c.id, ca.id, ca.tipo, '2017-01-08', 1, 'Pagamento da conta de internet', -100, 0, 0, 1 from usuario u, conta c, categoria ca
where u.login = 'usuario' and c.descricao = 'Conta Bradesco 123' and ca.descricao = 'Internet';

-- Saldo da conta = 6400

-- Insere movimento de despesa
insert into movimento (id_usuario, id_conta, id_categoria, tipo, data, ordem, descricao, valor, status_segregado, status_transferencia, versao)
select u.id, c.id, ca.id, ca.tipo, '2017-01-10', 1, 'Compras no supermercado', -1200, 0, 0, 1 from usuario u, conta c, categoria ca
where u.login = 'usuario' and c.descricao = 'Conta Bradesco 123' and ca.descricao = 'Supermercado';

-- Saldo da conta = 5200

-- Insere movimento de despesa
insert into movimento (id_usuario, id_conta, id_categoria, tipo, data, ordem, descricao, valor, status_segregado, status_transferencia, versao)
select u.id, c.id, ca.id, ca.tipo, '2017-01-15', 1, 'Almoço', -50, 0, 0, 1 from usuario u, conta c, categoria ca
where u.login = 'usuario' and c.descricao = 'Conta Bradesco 123' and ca.descricao = 'Restaurante';

-- Saldo da conta = 5150

-- Insere movimento de despesa
insert into movimento (id_usuario, id_conta, id_categoria, tipo, data, ordem, descricao, valor, status_segregado, status_transferencia, versao)
select u.id, c.id, ca.id, ca.tipo, '2017-01-15', 1, 'Pagamento do aluguel', -800, 0, 0, 1 from usuario u, conta c, categoria ca
where u.login = 'usuario' and c.descricao = 'Conta Bradesco 123' and ca.descricao = 'Aluguel';

-- Saldo da conta = 4350

--
-- INICIO DE OPERACAO ATOMICA - Transferência entre contas
--

-- Insere movimento de saída
insert into movimento (id_usuario, id_conta, tipo, data, ordem, descricao, valor, status_segregado, status_transferencia, versao)
select u.id, c.id, 'D', '2017-01-16', 1, 'Saque bancário', -100, 0, 1, 1 from usuario u, conta c
where u.login = 'usuario' and c.descricao = 'Conta Bradesco 123';

-- Insere movimento de entrada
insert into movimento (id_usuario, id_conta, id_movimento_origem, tipo, data, ordem, descricao, valor, status_segregado, status_transferencia, versao)
select u.id, c.id, m.id, 'R', '2017-01-16', 1, 'Saque bancário', 100, 0, 1, 1 from usuario u, conta c, movimento m
where u.login = 'usuario' and c.descricao = 'Carteira' and m.descricao = 'Saque bancário';

--
-- FIM DE OPERACAO ATOMICA
--

-- Saldo da conta Bradesco = 4250
-- Saldo da Carteira = 100

-- Insere movimento de despesa
insert into movimento (id_usuario, id_conta, id_categoria, tipo, data, ordem, descricao, valor, status_segregado, status_transferencia, versao)
select u.id, c.id, ca.id, ca.tipo, '2017-01-17', 1, 'Compra na lanchonete Y', -30, 0, 0, 1 from usuario u, conta c, categoria ca
where u.login = 'usuario' and c.descricao = 'Carteira' and ca.descricao = 'Lanche';

-- Saldo da conta Bradesco = 4250
-- Saldo da Carteira = 70