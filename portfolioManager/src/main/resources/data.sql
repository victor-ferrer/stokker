insert into portfolio (portfolio_id, label, incorporation_date, currency) values (1, 'Dividend portfolio', '2015-04-01','EUR');

insert into stock (stock_id, ticker, market, need_market_suffix, label, currency) values (1, 'MAP', 'MC', 1, 'Mapfre seguros', 'EUR');
insert into stock (stock_id, ticker, market, need_market_suffix, label, currency) values (2, 'SAN', 'MC', 1, 'Banco Santander', 'EUR');
insert into stock (stock_id, ticker, market, need_market_suffix, label, currency) values (3, 'REP', 'MC', 1, 'Repsol', 'EUR');
insert into stock (stock_id, ticker, market, need_market_suffix, label, currency) values (4, 'ENG', 'MC', 1, 'Enagás', 'EUR');
insert into stock (stock_id, ticker, market, need_market_suffix, label, currency) values (5, 'ABE', 'MC', 1, 'Abertis Infraestructuras', 'EUR');
insert into stock (stock_id, ticker, market, need_market_suffix, label, currency) values (6, 'TEF', 'MC', 1, 'Telefónica', 'EUR');
insert into stock (stock_id, ticker, market, need_market_suffix, label, currency) values (7, 'IBE', 'MC', 1, 'Iberdrola', 'EUR');
insert into stock (stock_id, ticker, market, need_market_suffix, label, currency) values (8, 'GAS', 'MC', 1, 'Gas Natural', 'EUR');
insert into stock (stock_id, ticker, market, need_market_suffix, label, currency) values (9, 'REE', 'MC', 1, 'Red Eléctrica Corporación', 'EUR');
insert into stock (stock_id, ticker, market, need_market_suffix, label, currency) values (10, 'ELE', 'MC', 1, 'Endesa', 'EUR');
insert into stock (stock_id, ticker, market, need_market_suffix, label, currency) values (11, 'T', 'NYSE', 0, 'AT&T', 'USD');
insert into stock (stock_id, ticker, market, need_market_suffix, label, currency) values (12, 'OHI', 'NYSE', 0, 'Omega Healthcare Investors', 'USD');
insert into stock (stock_id, ticker, market, need_market_suffix, label, currency) values (13, 'BME', 'MC', 1, 'Bolsas y Mercados', 'EUR');
insert into stock (stock_id, ticker, market, need_market_suffix, label, currency) values (14, 'ACS', 'MC', 1, 'ACS Contrucciones', 'EUR');

insert into position (position_id, amount, price, trade_date, stock_id, portfolio_id) values (1, 1550, 3.6, '2015-04-01', 1, 1);
insert into position (position_id, amount, price, trade_date, stock_id, portfolio_id) values (2, 1550, 3.37, '2015-05-01', 1, 1);
insert into position (position_id, amount, price, trade_date, stock_id, portfolio_id) values (3, 1550, 3.23, '2015-06-01', 1, 1);
insert into position (position_id, amount, price, trade_date, stock_id, portfolio_id) values (4, 1550, 3.2, '2015-07-01', 1, 1);
insert into position (position_id, amount, price, trade_date, stock_id, portfolio_id) values (5, 1550, 3.0, '2015-08-01', 1, 1);
insert into position (position_id, amount, price, trade_date, stock_id, portfolio_id) values (6, 1550, 2.92, '2015-09-01', 1, 1);
insert into position (position_id, amount, price, trade_date, stock_id, portfolio_id) values (7, 1550, 2.56, '2015-10-01', 1, 1);

