-- =====================================================================
-- Ejecutar UNA VEZ conectado como ADMIN (conexion "foros_admin").
-- Crea el esquema propio del microservicio de biblioteca.
-- Reemplaza la clave por la tuya antes de ejecutar (F5).
-- =====================================================================
CREATE USER BIBLIOTECA_MS IDENTIFIED BY "CambiaEstaClave1";
GRANT CREATE SESSION, CREATE TABLE, CREATE SEQUENCE, CREATE VIEW TO BIBLIOTECA_MS;
ALTER USER BIBLIOTECA_MS QUOTA UNLIMITED ON DATA;
