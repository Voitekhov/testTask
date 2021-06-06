DROP TABLE IF EXISTS deposits;
DROP TABLE IF EXISTS banks;
DROP TABLE IF EXISTS clients;
DROP SEQUENCE IF EXISTS global_seq;
CREATE SEQUENCE global_seq START WITH 100000;

CREATE TABLE banks
(
    id   INTEGER UNIQUE PRIMARY KEY DEFAULT nextval('global_seq'),
    name VARCHAR NOT NULL,
    bik  VARCHAR UNIQUE NOT NULL
);

CREATE TABLE clients
(
    id           INTEGER UNIQUE PRIMARY KEY DEFAULT nextval('global_seq'),
    name         VARCHAR NOT NULL,
    address      VARCHAR NOT NULL,
    legal_status VARCHAR NOT NULL
);
CREATE TABLE deposits
(
    id   INTEGER UNIQUE PRIMARY KEY DEFAULT nextval('global_seq'),
    interest_rate DOUBLE PRECISION NOT NULL,
    period_of_deposit INTEGER NOT NULL,
    start_deposit_date DATE DEFAULT NOW() NOT NULL,
    client_id INTEGER NOT NULL,
    bank_id INTEGER NOT NULL,
    FOREIGN KEY (client_id) REFERENCES clients(id) ON DELETE CASCADE,
    FOREIGN KEY (bank_id) REFERENCES  banks(id) ON DELETE CASCADE
);