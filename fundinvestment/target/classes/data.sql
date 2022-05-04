insert into investor_details(id, name) values(100, 'Manshavi Kumar');
insert into investor_details(id, name) values(101, 'Ranjan Jha');

insert into fund_details(id, name) values(1000, 'Willington Care');
insert into fund_details(id, name) values(1001, 'HDFC Life');
insert into fund_details(id, name) values(1002, 'SBI Insurance');

insert into holding_details(id, name, price) values(10000, 'Amazon Ltd', 15);
insert into holding_details(id, name, price) values(10001, 'Tata Consumer', 17);
insert into holding_details(id, name, price) values(10002, 'Tesla', 19);
insert into holding_details(id, name, price) values(10003, 'Reliance Ltd.', 21);

insert into holding_quantity(fund_id, holding_id, quantity) values(1000, 10000, 25);
insert into holding_quantity(fund_id, holding_id, quantity) values(1000, 10001, 20);
insert into holding_quantity(fund_id, holding_id, quantity) values(1000, 10003, 15);

insert into holding_quantity(fund_id, holding_id, quantity) values(1001, 10000, 30);
insert into holding_quantity(fund_id, holding_id, quantity) values(1001, 10003, 35);

insert into holding_quantity(fund_id, holding_id, quantity) values(1002, 10000, 10);
insert into holding_quantity(fund_id, holding_id, quantity) values(1002, 10002, 5);

insert into investor_funds(investor_id, fund_id) values(100, 1000);
insert into investor_funds(investor_id, fund_id) values(100, 1001);
insert into investor_funds(investor_id, fund_id) values(101, 1001);
insert into investor_funds(investor_id, fund_id) values(101, 1002);