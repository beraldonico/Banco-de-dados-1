-- Selecione o total de vendas por cliente e por mês/ano;

select cli.cod_cliente, cli.nome, extract(month from data) as mes, extract(year from data) as ano, sum(vda.valor)
  from cliente as cli, nota as nt, venda as vda
  where cli.cod_cliente = nt.cod_cliente and 
        nt.num_nota = vda.num_nota
  group by cli.cod_cliente, nome, mes, ano
  order by nome, ano, mes;

-- Selecione o total de vendas por estado;

select cli.uf, sum(vda.valor)
  from cliente as cli, nota as nt, venda as vda
  where cli.cod_cliente = nt.cod_cliente and 
        nt.num_nota = vda.num_nota
  group by cli.uf
  order by cli.uf;

-- Selecione o produto mais vendido em um período determinado de tempo;

-- 1a solução

select prod.cod_produto, sum_qtde.qtde from produto as prod, 
  (select np.cod_produto, sum(np.qtde) as qtde 
             from nota_produto as np, nota as nt
             where nt.num_nota = np.num_nota and
                   nt.data between '2015-06-01' and '2015-12-31'
             group by np.cod_produto) as sum_qtde,
  (select max(sqtde) as qtde
      from (select np.cod_produto, sum(np.qtde) as sqtde 
             from nota_produto as np, nota as nt
             where nt.num_nota = np.num_nota and
                   nt.data between '2015-06-01' and '2015-12-31'
             group by np.cod_produto) as sum_qtde) as max_qtde
  where prod.cod_produto = sum_qtde.cod_produto and
         sum_qtde.qtde = max_qtde.qtde;

-- 2a solução

select cod_produto, sqtde from (
select np.cod_produto, max_qtde.mqtde, sum(np.qtde) as sqtde
  from nota_produto as np, nota as nt,
    (select max(sqtde) as mqtde
      from (select np.cod_produto, sum(np.qtde) as sqtde 
             from nota_produto as np, nota as nt
             where nt.num_nota = np.num_nota and
                   nt.data between '2015-06-01' and '2015-12-31'
             group by np.cod_produto) as sum_qtde) as max_qtde
  where nt.num_nota = np.num_nota and
        nt.data between '2015-06-01' and '2015-12-31'
  group by np.cod_produto, max_qtde.mqtde) as geral
where mqtde = sqtde;


-- Selecione o total recebido por cliente um determinado período de tempo;


select cli.cod_cliente, cli.nome,  
       sum(vda.valor+coalesce(multa_juro,0)-coalesce(desconto,0)) as valor
  from cliente as cli, nota as nt, venda as vda
  where cli.cod_cliente = nt.cod_cliente and 
        nt.num_nota = vda.num_nota and
        vda.data_pagto between '2015-06-01' and '2015-12-31' 
  group by cli.cod_cliente, nome
  order by nome;

-- Selecione o total a receber por cliente em um determinado período de tempo;

select cli.cod_cliente, cli.nome, sum(vda.valor) as valor
  from cliente as cli, nota as nt, venda as vda
  where cli.cod_cliente = nt.cod_cliente and 
        nt.num_nota = vda.num_nota and
        vda.data_vencto between '2015-06-01' and '2015-12-31' and
        vda.data_pagto is null        
  group by cli.cod_cliente, nome
  order by nome;

