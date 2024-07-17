DROP DATABASE IF EXISTS clinica_medica;

-- Cria o banco de dados
CREATE DATABASE clinica_medica;

-- Seleciona o banco de dados
USE clinica_medica;

-- Cria a tabela de pacientes
CREATE TABLE IF NOT EXISTS pacientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    CPF VARCHAR(11) UNIQUE NOT NULL,
    nome VARCHAR(255) NOT NULL,
    telefone VARCHAR(15),
    sexo ENUM('Masculino', 'Feminino', 'Outro'),
    data_nascimento DATE
);

-- Cria a tabela de medicos
CREATE TABLE IF NOT EXISTS medicos (
    CRM VARCHAR(20) PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
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

CREATE TABLE IF NOT EXISTS administradores (
    email VARCHAR(255) PRIMARY KEY,
    senha VARCHAR(255) NOT NULL,
    nome VARCHAR(255) NOT NULL
);

-- Insere um administrador padrão
INSERT INTO administradores (email, senha, nome) VALUES ('admin@clinica.com', 'root', 'Admin1');

-- Inserção de Pacientes
INSERT INTO pacientes (email, senha, CPF, nome, telefone, sexo, data_nascimento) VALUES ('marialuiza@gmail.com', 'paciente', '54678325861', 'Maria Luiza Alves', '16994582366', 'Feminino', '2000-07-21');
INSERT INTO pacientes (email, senha, CPF, nome, telefone, sexo, data_nascimento) VALUES ('fernandosouza@gmail.com', 'paciente', '56823944871', 'Fernando Lima Souza', '11925684162', 'Masculino', '2002-01-08');
INSERT INTO pacientes (email, senha, CPF, nome, telefone, sexo, data_nascimento) VALUES ('brunosilva@gmail.com', 'paciente', '67214587874', 'Bruno Henrique Silva', '16934450871', 'Masculino', '1998-10-28');
INSERT INTO pacientes (email, senha, CPF, nome, telefone, sexo, data_nascimento) VALUES ('leticiacorreia@gmail.com', 'paciente', '54755308873', 'Leticia Emilia Correia', '11973483667', 'Feminino', '2003-04-16');

-- Inserção de Médicos
INSERT INTO medicos (CRM, email, senha, nome, especialidade) VALUES ('000001/SP', 'medico1@gmail.com', 'medico1', 'Antonio Maldonado Guerra da Cunha', 'Cardiologia');
INSERT INTO medicos (CRM, email, senha, nome, especialidade) VALUES ('000002/SP', 'medico2@gmail.com', 'medico2', 'Enzo Youji Murayama', 'Oftalmologia');
INSERT INTO medicos (CRM, email, senha, nome, especialidade) VALUES ('000003/SP', 'medico3@gmail.com', 'medico3', 'Lucas Sciarra Gonçalves', 'Neurologia');