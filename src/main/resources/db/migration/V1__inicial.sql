CREATE TABLE curriculo_ent (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    data_nascimento DATE NOT NULL,
    email VARCHAR(255) NOT NULL,
    telefone VARCHAR(255) NOT NULL,
    escolaridade VARCHAR(255) NOT NULL,
    funcao VARCHAR(255) NOT NULL,
    UNIQUE (cpf)
);

CREATE TABLE curriculo_competencia (
    curriculo_id BIGINT NOT NULL,
    descricao VARCHAR(255) NOT NULL,
    nivel VARCHAR(255) NOT NULL,
    CONSTRAINT fk_curriculo_competencia
        FOREIGN KEY (curriculo_id) REFERENCES curriculo_ent(id)
        ON DELETE CASCADE
);