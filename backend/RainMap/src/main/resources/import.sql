INSERT INTO tb_user(name, lastname, email, password) VALUES('somi', 'nozes', 'sominozes@gmail.com', 'sominozes123')

INSERT INTO tb_address(endereco, latitude, longitude) VALUES('Rua das Flores', -36.8532, -10.9286)

INSERT INTO tb_alert(data, tipo_ocorrencia, urgencia) VALUES('2025-06-09 14:30:00', 'Description of Alert 1', 'Critico')

UPDATE tb_address SET alert_id = 1 WHERE id = 1;
