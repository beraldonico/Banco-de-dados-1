
insert into cliente values (1, 'Cliente 1', 'Araranguá', 'SC', 'Endereco 1', '1976-10-5');
insert into cliente values (2, 'Cliente 2', 'Arroio do Silva', 'SC', 'Endereco 2', '1980-09-11');
insert into cliente values (3, 'Cliente 3', 'Araranguá', 'SC', 'Endereco 3', '1952-01-29');
insert into cliente values (4, 'Cliente 4', 'Turvo', 'SC', 'Endereco 4', '1980-03-21');

insert into produto values (1, 10, 'Produto 1', 12.00);
insert into produto values (2, 21, 'Produto 2', 33.00);

insert into nota values (1, '2015-09-02', 0, 1);
insert into nota_produto values (1, 1, 12.00, 3);
insert into nota_produto values (1, 2, 33.00, 8);
insert into venda values (1, '2015-09-02', '2015-10-02', null, 300, null);

insert into nota values (2, '2015-10-02', 2, 1);
insert into nota_produto values (2, 1, 12.00, 3);
insert into venda values (2, '2015-11-02', null, null, 18, null);
insert into venda values (2, '2015-12-03', null, null, 18, null);

insert into nota values (3, '2015-10-02', 2, 2);
insert into nota_produto values (3, 1, 12.00, 1);
insert into nota_produto values (3, 2, 33.00, 1);
insert into venda values (3, '2015-11-02', null, null, 25, null);
insert into venda values (3, '2015-12-03', null, null, 20, null);

commit;

update venda
  set desconto = 1.00,
      data_pagto = '2015-10-28'
  where num_nota = 2 and data_vencto = '2015-11-02';

update venda
  set multa_juro = 1.20,
      data_pagto = '2015-11-15'
  where num_nota = 3 and data_vencto = '2015-11-02';

