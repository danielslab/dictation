DELETE FROM users;
DELETE FROM authorities;
INSERT INTO users(username, password, enabled) VALUES('taka_2', 'password', true);
INSERT INTO authorities(username, authority) VALUES('taka_2', 'ROLE_USER');