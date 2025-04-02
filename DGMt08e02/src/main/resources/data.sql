-- Inserción de cuentas
INSERT INTO cuenta (IBAN, alias, saldo) VALUES ('ES10000000000000000001', 'Cuenta Principal', 1500.00);
INSERT INTO cuenta (IBAN, alias, saldo) VALUES ('ES10000000000000000002', 'Cuenta Ahorros', 3000.00);
INSERT INTO cuenta (IBAN, alias, saldo) VALUES ('ES10000000000000000003', 'Cuenta Nomina', 2200.00);

-- Inserción de movimientos para la primera cuenta
INSERT INTO movimiento (fecha_hora, saldo, cuenta_iban) VALUES ('2024-02-14 08:00:00', -200.00, 'ES10000000000000000001');
INSERT INTO movimiento (fecha_hora, saldo, cuenta_iban) VALUES ('2024-02-14 10:30:00', 500.00, 'ES10000000000000000001');
INSERT INTO movimiento (fecha_hora, saldo, cuenta_iban) VALUES ('2024-02-14 12:00:01', -100.00, 'ES10000000000000000001');
INSERT INTO movimiento (fecha_hora, saldo, cuenta_iban) VALUES ('2024-02-14 14:45:00', -50.00, 'ES10000000000000000001');
INSERT INTO movimiento (fecha_hora, saldo, cuenta_iban) VALUES ('2024-02-14 16:20:00', 200.00, 'ES10000000000000000001');
INSERT INTO movimiento (fecha_hora, saldo, cuenta_iban) VALUES ('2024-02-14 18:00:00', -300.00, 'ES10000000000000000001');
INSERT INTO movimiento (fecha_hora, saldo, cuenta_iban) VALUES ('2024-02-14 20:10:00', 100.00, 'ES10000000000000000001');
INSERT INTO movimiento (fecha_hora, saldo, cuenta_iban) VALUES ('2024-02-14 22:30:00', -50.00, 'ES10000000000000000001');
INSERT INTO movimiento (fecha_hora, saldo, cuenta_iban) VALUES ('2024-02-15 08:00:00', 300.00, 'ES10000000000000000001');
INSERT INTO movimiento (fecha_hora, saldo, cuenta_iban) VALUES ('2024-02-15 09:15:00', -150.00, 'ES10000000000000000001');

-- Inserción de movimientos para la segunda cuenta
INSERT INTO movimiento (fecha_hora, saldo, cuenta_iban) VALUES ('2024-02-14 08:00:00', -100.00, 'ES10000000000000000002');
INSERT INTO movimiento (fecha_hora, saldo, cuenta_iban) VALUES ('2024-02-14 09:15:00', 1000.00, 'ES10000000000000000002');
INSERT INTO movimiento (fecha_hora, saldo, cuenta_iban) VALUES ('2024-02-14 10:30:00', -50.00, 'ES10000000000000000002');
INSERT INTO movimiento (fecha_hora, saldo, cuenta_iban) VALUES ('2024-02-14 11:45:00', 200.00, 'ES10000000000000000002');
INSERT INTO movimiento (fecha_hora, saldo, cuenta_iban) VALUES ('2024-02-14 13:00:00', -150.00, 'ES10000000000000000002');
INSERT INTO movimiento (fecha_hora, saldo, cuenta_iban) VALUES ('2024-02-14 14:30:00', 500.00, 'ES10000000000000000002');
INSERT INTO movimiento (fecha_hora, saldo, cuenta_iban) VALUES ('2024-02-14 16:00:00', -250.00, 'ES10000000000000000002');
INSERT INTO movimiento (fecha_hora, saldo, cuenta_iban) VALUES ('2024-02-14 18:15:00', 300.00, 'ES10000000000000000002');
INSERT INTO movimiento (fecha_hora, saldo, cuenta_iban) VALUES ('2024-02-14 20:00:00', -50.00, 'ES10000000000000000002');
INSERT INTO movimiento (fecha_hora, saldo, cuenta_iban) VALUES ('2024-02-14 21:45:00', 100.00, 'ES10000000000000000002');

-- Inserción de movimientos para la tercera cuenta
INSERT INTO movimiento (fecha_hora, saldo, cuenta_iban) VALUES ('2024-02-14 08:00:00', -200.00, 'ES10000000000000000003');
INSERT INTO movimiento (fecha_hora, saldo, cuenta_iban) VALUES ('2024-02-14 09:30:00', 700.00, 'ES10000000000000000003');
INSERT INTO movimiento (fecha_hora, saldo, cuenta_iban) VALUES ('2024-02-14 11:00:00', -50.00, 'ES10000000000000000003');
INSERT INTO movimiento (fecha_hora, saldo, cuenta_iban) VALUES ('2024-02-14 12:15:00', 300.00, 'ES10000000000000000003');
INSERT INTO movimiento (fecha_hora, saldo, cuenta_iban) VALUES ('2024-02-14 13:30:00', -100.00, 'ES10000000000000000003');
INSERT INTO movimiento (fecha_hora, saldo, cuenta_iban) VALUES ('2024-02-14 15:00:00', 400.00, 'ES10000000000000000003');
INSERT INTO movimiento (fecha_hora, saldo, cuenta_iban) VALUES ('2024-02-14 16:45:00', -150.00, 'ES10000000000000000003');
INSERT INTO movimiento (fecha_hora, saldo, cuenta_iban) VALUES ('2024-02-14 18:30:00', 200.00, 'ES10000000000000000003');
INSERT INTO movimiento (fecha_hora, saldo, cuenta_iban) VALUES ('2024-02-14 20:15:00', -50.00, 'ES10000000000000000003');
INSERT INTO movimiento (fecha_hora, saldo, cuenta_iban) VALUES ('2024-02-14 22:00:00', 100.00, 'ES10000000000000000003');