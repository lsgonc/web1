-- Remove o banco de dados se ele já existir
DROP DATABASE IF EXISTS clinica_medica;

-- Cria o banco de dados
CREATE DATABASE IF NOT EXISTS clinica_medica;

-- Seleciona o banco de dados
USE clinica_medica;

-- Cria a tabela de usuario
CREATE TABLE IF NOT EXISTS usuario (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    nome VARCHAR(255) NOT NULL,
    tipo_usuario ENUM('paciente', 'medico', 'admin') NOT NULL
);

-- Cria a tabela de paciente
CREATE TABLE IF NOT EXISTS paciente (
    usuario_id INT NOT NULL,
    CPF VARCHAR(14) PRIMARY KEY,
    telefone VARCHAR(15),
    sexo ENUM('Masculino', 'Feminino', 'Outro'),
    data_nascimento DATE,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Cria a tabela de medico
CREATE TABLE IF NOT EXISTS medico (
    usuario_id INT NOT NULL,
    CRM VARCHAR(20) PRIMARY KEY,
    especialidade VARCHAR(255) NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Cria a tabela de consulta
CREATE TABLE IF NOT EXISTS consulta (
    id INT AUTO_INCREMENT PRIMARY KEY,
    paciente_cpf VARCHAR(11) NOT NULL,
    medico_crm VARCHAR(20) NOT NULL,
    data_consulta DATE NOT NULL,
    hora_consulta TIME NOT NULL,
    FOREIGN KEY (paciente_cpf) REFERENCES paciente(CPF) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (medico_crm) REFERENCES medico(CRM) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Insere um administrador padrão
INSERT INTO usuario (email, senha, nome, tipo_usuario) 
VALUES ('admin@clinica.com', 'admin', 'Administrador', 'admin');

-- Inserir um paciente pra testes
INSERT INTO usuario (email, senha, nome, tipo_usuario) 
VALUES ('paciente@clinica.com', '123', 'Paciente1', 'paciente');
SET @usuario_id = LAST_INSERT_ID();
INSERT INTO paciente (usuario_id, CPF, telefone, sexo, data_nascimento) 
VALUES (@usuario_id, '123.456.789.00', '00999999999', 'Masculino', '2000-01-01');

-- Insere médicos pra teste
INSERT INTO usuario (email, senha, nome, tipo_usuario) 
VALUES 
    ('medico1@clinica.com', '123', 'Medico1', 'medico'),
    ('medico2@clinica.com', '123', 'Medico2', 'medico'),
    ('medico3@clinica.com', '123', 'Medico3', 'medico');

SET @usuario_id_medico1 = LAST_INSERT_ID();
SET @usuario_id_medico2 = @usuario_id_medico1 + 1;
SET @usuario_id_medico3 = @usuario_id_medico2 + 1;
INSERT INTO medico (usuario_id, CRM, especialidade) 
VALUES 
    (@usuario_id_medico1, 'CRM001', 'Cardiologia'),
    (@usuario_id_medico2, 'CRM002', 'Pediatria'),
    (@usuario_id_medico3, 'CRM003', 'Neurologia');
