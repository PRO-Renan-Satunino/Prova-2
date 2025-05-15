-- Criação do banco de dados
CREATE DATABASE IF NOT EXISTS consultas_db;
USE consultas_db;

-- Tabela de Pacientes
CREATE TABLE IF NOT EXISTS paciente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    telefone VARCHAR(20) NOT NULL
);

-- Tabela de Médicos
CREATE TABLE IF NOT EXISTS medico (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    especialidade VARCHAR(100) NOT NULL,
    crm VARCHAR(20) NOT NULL UNIQUE
);

-- Tabela de Consultas
CREATE TABLE IF NOT EXISTS consulta (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_paciente INT NOT NULL,
    id_medico INT NOT NULL,
    data DATE NOT NULL,
    hora TIME NOT NULL,
    observacao TEXT,
    
    CONSTRAINT fk_paciente FOREIGN KEY (id_paciente) REFERENCES paciente(id)
        ON DELETE CASCADE ON UPDATE CASCADE,
        
    CONSTRAINT fk_medico FOREIGN KEY (id_medico) REFERENCES medico(id)
        ON DELETE CASCADE ON UPDATE CASCADE
);
