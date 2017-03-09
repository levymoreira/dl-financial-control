-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: financas
-- ------------------------------------------------------
-- Server version	5.7.10-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS categoria;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE categoria (
  id int(11) NOT NULL AUTO_INCREMENT COMMENT 'Identificador único e sequencial do registro.',
  id_usuario int(11) NOT NULL COMMENT 'Identificação do usuário ao qual pertence a categoria.',
  id_categoria_pai int(11) DEFAULT NULL COMMENT 'Identificação da categoria pai. Se este campo for nulo, então a categoria é de primeiro nível.',
  tipo char(1) NOT NULL COMMENT 'R=Receita;D=Despesa',
  descricao varchar(100) NOT NULL COMMENT 'Descrição da categoria.',
  versao int(11) NOT NULL DEFAULT '1' COMMENT 'Controle de versionamento do registro.',
  temp1_id int(11) DEFAULT NULL,
  temp2_id_pai int(11) DEFAULT NULL,
  PRIMARY KEY (id),
  KEY fk_categoria_categoria_idx (id_categoria_pai),
  KEY fk_categoria_usuario_idx (id_usuario),
  CONSTRAINT fk_categoria_categoria FOREIGN KEY (id_categoria_pai) REFERENCES categoria (id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT fk_categoria_usuario FOREIGN KEY (id_usuario) REFERENCES usuario (id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=115 DEFAULT CHARSET=utf8 COMMENT='Tabela que armazena a árvore de categorias dos usuários. As categorias são multi-níveis.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `configuracao_usuario`
--

DROP TABLE IF EXISTS configuracao_usuario;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE configuracao_usuario (
  id_usuario int(11) NOT NULL COMMENT 'Identificação do usuário e chave da tabela.',
  id_conta_padrao int(11) DEFAULT NULL COMMENT 'Identificação da conta padrão/principal do usuário.',
  id_orcamento_padrao int(11) DEFAULT NULL COMMENT 'Identificação do orçamento padrão/principal do usuário.',
  PRIMARY KEY (id_usuario),
  KEY fk_configuracao_usuario_usuario_idx (id_usuario),
  KEY fk_configuracao_usuario_conta_idx (id_conta_padrao),
  KEY fk_configuracao_usuario_orcamento_idx (id_orcamento_padrao),
  CONSTRAINT fk_configuracao_usuario_conta FOREIGN KEY (id_conta_padrao) REFERENCES conta (id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT fk_configuracao_usuario_orcamento FOREIGN KEY (id_orcamento_padrao) REFERENCES orcamento (id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT fk_configuracao_usuario_usuario FOREIGN KEY (id_usuario) REFERENCES usuario (id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Tabela que armazana itens de configuração de cada usuário.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `conta`
--

DROP TABLE IF EXISTS conta;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE conta (
  id int(11) NOT NULL AUTO_INCREMENT COMMENT 'Identificador único e sequencial do registro.',
  id_usuario int(11) NOT NULL COMMENT 'Identificação do usuário ao qual pertence a conta.',
  id_tipo_conta int(11) NOT NULL COMMENT 'Identificação do tipo de conta.',
  descricao varchar(100) NOT NULL COMMENT 'Descrição da conta.',
  versao int(11) NOT NULL DEFAULT '1' COMMENT 'Controle de versionamento do registro.',
  temp1_id int(11) DEFAULT NULL,
  temp2_tipo_conta int(11) DEFAULT NULL,
  PRIMARY KEY (id),
  KEY fk_conta_usuario_idx (id_usuario),
  CONSTRAINT fk_conta_usuario FOREIGN KEY (id_usuario) REFERENCES usuario (id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='Tabela que armazena as contas dos usuários.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `fatura_cartao`
--

DROP TABLE IF EXISTS fatura_cartao;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE fatura_cartao (
  id int(11) NOT NULL AUTO_INCREMENT COMMENT 'Identificador único e sequencial do registro.',
  id_conta int(11) NOT NULL COMMENT 'Identificação da conta do tipo cartão.',
  ano int(11) NOT NULL COMMENT 'Ano da fatura.',
  mes int(11) NOT NULL COMMENT 'Mês da fatura.',
  PRIMARY KEY (id),
  UNIQUE KEY uq_fatura_cartao_periodo (id_conta,ano,mes),
  KEY fk_fatura_cartao_conta_idx (id_conta),
  CONSTRAINT fk_fatura_cartao_conta FOREIGN KEY (id_conta) REFERENCES conta (id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Tabela que armazena as faturas de cartão.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `fatura_cartao_movimento`
--

DROP TABLE IF EXISTS fatura_cartao_movimento;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE fatura_cartao_movimento (
  id_fatura_cartao int(11) NOT NULL COMMENT 'Identificação da fatura do cartão.',
  id_movimento int(11) NOT NULL COMMENT 'Identificação do movimento.',
  PRIMARY KEY (id_fatura_cartao,id_movimento),
  KEY fk_fatura_cartao_movimento_movimento_idx (id_movimento),
  CONSTRAINT fk_fatura_cartao_movimento_fatura FOREIGN KEY (id_fatura_cartao) REFERENCES fatura_cartao (id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT fk_fatura_cartao_movimento_movimento FOREIGN KEY (id_movimento) REFERENCES movimento (id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Tabela que armazena a vinculação dos movimentos com a fatura.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `favorecido`
--

DROP TABLE IF EXISTS favorecido;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE favorecido (
  id int(11) NOT NULL AUTO_INCREMENT COMMENT 'Identificador único e sequencial do registro.',
  id_usuario int(11) NOT NULL COMMENT 'Identificação do usuário ao qual pertence o favorecido.',
  nome varchar(100) NOT NULL COMMENT 'Nome do favorecido.',
  observacao varchar(300) DEFAULT NULL COMMENT 'Anotações sobre o favorecido.',
  versao int(11) NOT NULL DEFAULT '1',
  temp1_id int(11) DEFAULT NULL,
  PRIMARY KEY (id),
  KEY fk_favorecido_usuario_idx (id_usuario),
  CONSTRAINT fk_favorecido_usuario FOREIGN KEY (id_usuario) REFERENCES usuario (id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=183 DEFAULT CHARSET=utf8 COMMENT='Tabela que armazena os clientes e fornecedores dos usuários.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `movimento`
--

DROP TABLE IF EXISTS movimento;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE movimento (
  id int(11) NOT NULL AUTO_INCREMENT COMMENT 'Identificador único e sequencial do registro.',
  id_usuario int(11) NOT NULL COMMENT 'Identificação do usuário ao qual pertence o movimento.',
  id_conta int(11) NOT NULL COMMENT 'Identificação da conta a qual pertence o movimento.',
  id_categoria int(11) DEFAULT NULL COMMENT 'Identificação da categoria do movimento.',
  id_favorecido int(11) DEFAULT NULL COMMENT 'Identificação do favorecido do movimento.',
  id_movimento_pai int(11) DEFAULT NULL COMMENT 'Identificação do movimento pai. Esse campo será preenchido quando houver subdivisão ou parcelamento do movimento pai.',
  id_movimento_origem int(11) DEFAULT NULL COMMENT 'Identificação do movimento de origem. Esse campo será preenchido quando o movimento for do tipo transferência, indicando que esse é um movimento destino dos recursos. Os movimentos origem e destino devem ter contas diferentes.',
  tipo char(1) NOT NULL COMMENT 'R=Receita;D=Despesa.',
  `data` datetime NOT NULL COMMENT 'Data da realização do movimento.',
  ordem int(11) NOT NULL DEFAULT '1' COMMENT 'Ordem de apresentação do movimento.',
  numero varchar(20) DEFAULT NULL COMMENT 'Número transacional do movimento fornecido pela entidade financeira.',
  descricao varchar(100) DEFAULT NULL COMMENT 'Descrição do movimento.',
  complemento varchar(300) DEFAULT NULL COMMENT 'Informações adicionais do movimento.',
  valor decimal(10,4) NOT NULL COMMENT 'Valor do movimento. Positivo = Receita, Negativo = Despesa.',
  status_segregado bit(1) NOT NULL DEFAULT b'0' COMMENT 'Status que indica se o movimento é segregado/dividido. Quando esse movimento for segregado, não deve ter movimento pai.',
  status_transferencia bit(1) NOT NULL DEFAULT b'0' COMMENT 'Status que indica se o movimento é do tipo transferência.',
  versao int(11) NOT NULL DEFAULT '1' COMMENT 'Controle de versionamento do registro.',
  temp1_id int(11) DEFAULT NULL,
  temp2_id_movimento_origem int(11) DEFAULT NULL,
  temp3_id_movimento_pai int(11) DEFAULT NULL,
  temp4_id_categoria int(11) DEFAULT NULL,
  temp5_id_conta int(11) DEFAULT NULL,
  temp6_id_favorecido int(11) DEFAULT NULL,
  PRIMARY KEY (id),
  KEY fk_movimento_categoria_idx (id_categoria),
  KEY fk_movimento_conta_idx (id_conta),
  KEY fk_movimento_favorecido_idx (id_favorecido),
  KEY fk_movimento_movimento_origem_idx (id_movimento_origem),
  KEY fk_movimento_movimento_pai_idx (id_movimento_pai),
  KEY fk_movimento_usuario_idx (id_usuario),
  CONSTRAINT fk_movimento_categoria FOREIGN KEY (id_categoria) REFERENCES categoria (id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT fk_movimento_conta FOREIGN KEY (id_conta) REFERENCES conta (id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT fk_movimento_favorecido FOREIGN KEY (id_favorecido) REFERENCES favorecido (id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT fk_movimento_movimento_origem FOREIGN KEY (id_movimento_origem) REFERENCES movimento (id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT fk_movimento_movimento_pai FOREIGN KEY (id_movimento_pai) REFERENCES movimento (id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT fk_movimento_usuario FOREIGN KEY (id_usuario) REFERENCES usuario (id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1378 DEFAULT CHARSET=utf8 COMMENT='Tabela que armazena os movimentos financeiros dos usuários.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `orcamento`
--

DROP TABLE IF EXISTS orcamento;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE orcamento (
  id int(11) NOT NULL AUTO_INCREMENT COMMENT 'Identificador único e sequencial do registro.',
  usuario_id int(11) NOT NULL COMMENT 'Identificação do usuário ao qual pertence o orçamento.',
  descricao varchar(100) NOT NULL COMMENT 'Descrição do orçamento.',
  ano_base int(11) NOT NULL COMMENT 'Ano base do orçamento.',
  versao int(11) NOT NULL DEFAULT '1' COMMENT 'Controle de versionamento do registro.',
  PRIMARY KEY (id),
  KEY fk_orcamento_usuario_idx (usuario_id),
  CONSTRAINT fk_orcamento_usuario FOREIGN KEY (usuario_id) REFERENCES usuario (id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='Tabela que armazena os orçamentos dos usuários.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `orcamento_item`
--

DROP TABLE IF EXISTS orcamento_item;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE orcamento_item (
  id int(11) NOT NULL AUTO_INCREMENT COMMENT 'Identificador único e sequencial do registro.',
  id_orcamento int(11) NOT NULL COMMENT 'Identificação do orçamento ao qual o item pertence.',
  id_categoria int(11) NOT NULL COMMENT 'Identificação da categoria do item.',
  frequencia char(1) NOT NULL COMMENT '(M)ensal = Valores são inseridos diferentes mês a mês; (A)nual = Valores são iguais todos os meses do ano.',
  valor decimal(10,4) NOT NULL COMMENT 'Valor total do item.',
  versao int(11) NOT NULL DEFAULT '1' COMMENT 'Controle de versionamento do registro.',
  PRIMARY KEY (id),
  UNIQUE KEY uq_orcamento_item_categoria (id_orcamento,id_categoria),
  KEY fk_orcamento_orcamento_item_idx (id_orcamento),
  KEY fk_orcamento_item_categoria_idx (id_categoria),
  CONSTRAINT fk_orcamento_item_categoria FOREIGN KEY (id_categoria) REFERENCES categoria (id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT fk_orcamento_item_orcamento FOREIGN KEY (id_orcamento) REFERENCES orcamento (id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='Tabela que armazena os itens de orçamento.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `orcamento_item_valor`
--

DROP TABLE IF EXISTS orcamento_item_valor;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE orcamento_item_valor (
  id_orcamento_item int(11) NOT NULL COMMENT 'Identificação do item de orçamento.',
  mes int(11) NOT NULL COMMENT 'Mês ao qual o valor se refere.',
  valor decimal(10,4) NOT NULL COMMENT 'Valor do item no mês.',
  versao int(11) NOT NULL DEFAULT '1' COMMENT 'Controle de versionamento do registro.',
  PRIMARY KEY (id_orcamento_item,mes)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Tabela que armazena os valores mês a mês dos itens de orçamento que não forem anuais.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tipo_conta`
--

DROP TABLE IF EXISTS tipo_conta;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE tipo_conta (
  id int(11) NOT NULL AUTO_INCREMENT COMMENT 'Identificador único e sequencial do registro.',
  descricao varchar(50) NOT NULL COMMENT 'Descrição do tipo de conta.',
  codigo varchar(20) NOT NULL COMMENT 'Código do tipo de conta a ser usado como referência na aplicação.',
  PRIMARY KEY (id),
  UNIQUE KEY uq_tipo_conta_codigo (codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Tabela de domínio que armazena os tipos de conta.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS usuario;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE usuario (
  id int(11) NOT NULL AUTO_INCREMENT COMMENT 'Identificador único e sequencial do registro.',
  nome varchar(100) NOT NULL COMMENT 'Nome do usuário.',
  login varchar(100) NOT NULL COMMENT 'Login que identifica unicamente o usuário.',
  senha varchar(40) NOT NULL COMMENT 'Hash da senha do usuário (SHA1).',
  status_ativo bit(1) NOT NULL DEFAULT b'1' COMMENT 'Status que indica se o usuário está ativo ou não.',
  versao int(11) NOT NULL DEFAULT '1' COMMENT 'Controle de versionamento do registro.',
  PRIMARY KEY (id),
  UNIQUE KEY uq_usuario_login (login)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Tabela que armazena os dados dos usuários que possuirão dados financeiros.';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-02-17 21:51:55
