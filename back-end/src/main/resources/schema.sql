
CREATE DATABASE mentalist_quiz;
USE mentalist_quiz;


CREATE TABLE usuarios (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          nome VARCHAR(100) NOT NULL,
                          email VARCHAR(100) NOT NULL UNIQUE
);


CREATE TABLE quiz_respostas (
                                id INT AUTO_INCREMENT PRIMARY KEY,
                                usuario_id INT NOT NULL,
                                q1_input VARCHAR(100),
                                q2_checkbox VARCHAR(50),
                                q3_input VARCHAR(100),
                                q4_input VARCHAR(100),
                                q5_input VARCHAR(100),
                                q6_input INT,
                                q7_select VARCHAR(50),
                                pontuacao_final INT

);
