DELETE
FROM deposits;
DELETE
FROM banks;
DELETE
FROM clients;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO banks (id, bik, name)
values (1, 11111111, 'Sber'),
       (2, 22222122, 'Tinkof'),
       (3, 33333333, 'Alpha');
INSERT INTO clients (id,name, address, legal_status)
values (11,'Anton', 'somewhere', 'Общества с ' ||
                              'ограниченной ответственностью'),
       (12,'Max','somewhere','Общества с ограниченной ответственностью');
INSERT INTO deposits (interest_rate, period_of_deposit, client_id, bank_id)
values (12,10,11,1),(3,7,12,2);


