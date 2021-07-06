CREATE TABLE estilos (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

CREATE TABLE cervejas (
    codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    sku VARCHAR(50) NOT NULL,
    nome VARCHAR(80) NOT NULL,
    descricao TEXT NOT NULL,
    valor DECIMAL(10, 2) NOT NULL,
    teor_alcoolico DECIMAL(10, 2) NOT NULL,
    comissao DECIMAL(10, 2) NOT NULL,
    sabor VARCHAR(50) NOT NULL,
    origem VARCHAR(50) NOT NULL,
    codigo_estilo BIGINT(20) NOT NULL,
    FOREIGN KEY (codigo_estilo) REFERENCES estilos(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

INSERT INTO estilos VALUES (0, 'Amber Lager');
INSERT INTO estilos VALUES (0, 'Dark Lager');
INSERT INTO estilos VALUES (0, 'Pale Lager');
INSERT INTO estilos VALUES (0, 'Pilsner');