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
    CPF VARCHAR(11) PRIMARY KEY,
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
