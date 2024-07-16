-- Cria o banco de dados
CREATE DATABASE IF NOT EXISTS clinica_medica;

-- Seleciona o banco de dados
USE clinica_medica;

-- Cria a tabela de pacientes
CREATE TABLE IF NOT EXISTS pacientes (
    email VARCHAR(255) PRIMARY KEY,
    senha VARCHAR(255) NOT NULL,
    CPF VARCHAR(11) UNIQUE NOT NULL,
    nome VARCHAR(255) NOT NULL,
    telefone VARCHAR(15),
    sexo ENUM('Masculino', 'Feminino', 'Outro'),
    data_nascimento DATE
);

-- Cria a tabela de medicos
CREATE TABLE IF NOT EXISTS medicos (
    email VARCHAR(255) PRIMARY KEY,
    senha VARCHAR(255) NOT NULL,
    CRM VARCHAR(20) UNIQUE NOT NULL,
    nome VARCHAR(255) NOT NULL,
    especialidade VARCHAR(255) NOT NULL
);

-- Cria a tabela de consultas
CREATE TABLE IF NOT EXISTS consultas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    paciente_cpf VARCHAR(11),
    medico_crm VARCHAR(20),
    data_hora DATETIME NOT NULL,
    FOREIGN KEY (paciente_cpf) REFERENCES pacientes(CPF),
    FOREIGN KEY (medico_crm) REFERENCES medicos(CRM)
);