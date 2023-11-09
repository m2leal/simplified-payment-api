INSERT INTO users (first_name, last_name, document, email, password, balance, user_type) VALUES ('Marcio', 'Leal', '8079107929', 'mr.leal@gmail.com', '01152708090', 5000, 'COMMON');
INSERT INTO users (first_name, last_name, document, email, password, balance, user_type) VALUES ('Leonardo', 'Leal', '9180218030', 'lmleal@gmail.com', '01152707080', 10000, 'MERCHANT');

INSERT INTO transactions (amount, sender_id, receiver_id, timestamp) VALUES (10, 1, 2, '2023-11-07 22.00.00');
INSERT INTO transactions (amount, sender_id, receiver_id, timestamp) VALUES (15, 1, 2, '2023-11-08 22.30.00');
INSERT INTO transactions (amount, sender_id, receiver_id, timestamp) VALUES (20, 1, 2, '2023-11-09 23.00.00');