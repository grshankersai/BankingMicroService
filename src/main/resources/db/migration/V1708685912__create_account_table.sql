CREATE TABLE IF NOT EXISTS account (
    account_number UUID PRIMARY KEY,
    account_holder_name VARCHAR(255) NOT NULL,
    account_balance INT NOT NULL
);