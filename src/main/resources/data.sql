INSERT INTO hash_algo VALUES(1,'HMAC256');

INSERT INTO user_login_data (email, hash_algo_id, login_name, password_hash) VALUES('pierre@pierre.fr', 1, 'Pierre', '$2y$10$zOzwDqOv6Ht1z5NRH.qoXebMJjschJuovHtsd1HdUnk40qj2CZtbK');
INSERT INTO user_login_data (email, hash_algo_id, login_name, password_hash) VALUES('anais@anais.fr', 1, 'Anais', '$2y$10$IQD68V/bXDxSnY2n0JiNheuZWcpZ6M4wKXYNJZjTSCm5PpeiEnQ4q');