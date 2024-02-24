CREATE TABLE IF NOT EXISTS transactions(
    transaction_id UUID PRIMARY KEY,
    account_from UUID NOT NULL,
    account_to UUID NOT NULL,
    amount INT NOT NULL
);