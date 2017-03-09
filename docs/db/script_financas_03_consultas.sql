--
-- Calcula o saldo atual das contas
--
select c.descricao, sum(m.valor) as saldo
from movimento m
inner join conta c ON (c.id = m.id_conta)
where m.status_segregado = 0
group by c.descricao;

--
-- Calcula o saldo atual das contas até 16/01/2017
--
select c.descricao, sum(m.valor) as saldo
from movimento m
inner join conta c ON (c.id = m.id_conta)
where m.status_segregado = 0 and m.data < '2017-01-17'
group by c.descricao;

--
-- Calcula o valor movimentado mensal
--
select c.tipo, c.descricao,  month(m.data), sum(m.valor)
from movimento m
left join categoria c ON (c.id = m.id_categoria)
where year(m.data) = 2017 and  m.status_segregado = 0 and m.status_transferencia = 0
group by c.tipo, c.descricao,  month(m.data);