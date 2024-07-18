-- Remove o banco de dados se ele já existir
DROP DATABASE IF EXISTS clinica_medica;

-- Cria o banco de dados
CREATE DATABASE IF NOT EXISTS clinica_medica;

-- Seleciona o banco de dados
USE clinica_medica;

-- Cria a tabela de usuarios
CREATE TABLE IF NOT EXISTS usuarios (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    nome VARCHAR(255) NOT NULL,
    sexo ENUM('Masculino', 'Feminino', 'Outro'),
    tipo_usuario ENUM('paciente', 'medico', 'admin') NOT NULL
);

-- Cria a tabela de pacientes
CREATE TABLE IF NOT EXISTS pacientes (
    usuario_id INT NOT NULL,
    CPF VARCHAR(11) PRIMARY KEY,
    telefone VARCHAR(15),
    data_nascimento DATE,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);

-- Cria a tabela de medicos
CREATE TABLE IF NOT EXISTS medicos (
    usuario_id INT NOT NULL,
    CRM VARCHAR(20) PRIMARY KEY,
    especialidade VARCHAR(255) NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);

-- Cria a tabela de consultas
CREATE TABLE IF NOT EXISTS consultas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    paciente_cpf VARCHAR(11) NOT NULL,
    medico_crm VARCHAR(20) NOT NULL,
    data_hora DATETIME NOT NULL,
    FOREIGN KEY (paciente_cpf) REFERENCES pacientes(CPF),
    FOREIGN KEY (medico_crm) REFERENCES medicos(CRM)
);

-- Insere um administrador padrão
INSERT INTO usuarios (email, senha, nome, sexo, tipo_usuario) 
VALUES ('admin@clinica.com', 'senhaAdmin123', 'Administrador', 'Outro', 'admin');
