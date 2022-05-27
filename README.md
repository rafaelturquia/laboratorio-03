# Laboratório 03

- [Definições](#definições)

- [Diagrama de Caso De Uso](#diagrama-de-caso-de-uso)

- [Histórias do Usuário](#histórias-do-usuário)

- [Diagrama de Classes e Pacotes](#diagrama-de-classes-e-pacotes)

- [Diagrama de Classes e Pacotes - Atual](#diagrama-de-classes-e-pacotes-atual)

- [Diagrama de Componentes](#diagrama-de-componentes)

- [Diagrama de Implantação](#diagrama-de-implantação)

- [Modelagem ER](#modelagem-er)

- [Diagramas de Sequencia do Sistema](#diagramas-de-sequencia-do-Sistema)

- [Diagramas de Sequencia](#diagramas-de-sequencia)

## Definições

Sistema de Moeda Estudantil.

## Diagrama de Caso de Uso

![Diagrama_CasoDeUso](./projeto/Print%20-%20Diagrama%20de%20caso%20de%20Uso.png)

## Histórias do Usuário

* US01 - Como Aluno, eu quero me cadastrar no sistema, para ter acesso ao Sistema de Moeda Estudantil.
* US02 - Como Aluno, eu quero selecionar uma das vantagens, para trocar moedas.
* US03 - Como Aluno, eu quero consultar o extrato, para saber quantas moedas ainda possuo.
* US04 - Como Aluno, eu quero consultar o extrato, para visualizar as transações de recebimento ou troca de moedas.
* US05 - Como Aluno, eu quero resgatar vantagens, para trocar moedas.
* US06 - Como Aluno, eu quero receber um email contendo o cupom do resgate solicitado, para que utilize na troca presencial.
* US07 - Como Aluno, eu quero ser notificado por email quando receber moedas, para saber se recebi alguma moeda.
* US08 - Como Usuário, eu quero logar no sistema, para ter acesso a aplicação.
* US09 - Como Empresa Parceira, eu quero me cadastrar no sistema, para ter acesso ao Sistema de Moeda Estudantil. 
* US10 - Como Empresa Parceira, eu quero cadastrar vantagens no sistema, para os alunos trocarem por suas moedas.
* US11 - Como Empresa Parceira, eu quero adicionar uma descrição ao produto, para poder cadastrar uma vantagem.
* US12 - Como Empresa Parceira, eu quero adicionar uma foto do produto, para poder cadastrar uma vantagem.
* US13 - Como Empresa Parceira, receber um email contendo o cupom do resgate solicitado, para que possa conferir a troca.
* US14 - Como Professor, eu quero enviar moedas para o Aluno, com o objetivo de reconhecimento pelo bom desempenho.
* US15 - Como Professor, eu quero verificar o saldo, para saber quantas moedas ainda possuo.
* US16 - Como Professor, eu quero informar o motivo do envio de moedas, para descrever o motivo da transação.
* US17 - Como Professor, eu quero consultar extrato, para saber os alunos que enviei moedas. 

## Diagrama de classes e pacotes

![Diagrama_de_classe_e_Pacotes](./projeto/Print%20-%20Diagrama%20de%20Pacotes%20e%20Classes.png)

## Diagrama de classes e pacotes - Atual

![Diagrama_de_classe_e_Pacotes](./projeto/Print%20-%20Diagrama%20de%20Pacotes%20e%20Classes%20-%20Atual.png)


## Diagrama de Componentes

![Diagrama_de_componentes](./projeto/Print%20-%20Diagrama%20de%20Componentes.png)


## Diagrama de Implantação

![Diagrama_de_implantacao](./projeto/Print%20-%20Diagrama%20de%20Implantação.png)

## Modelagem ER

Endereco (pk_id, fk_id_aluno, rua, numero, bairro, cidade)

Usuario (pk_login, senha)

Aluno (pk_id, nome, email, CPF, curso, fk_id_sistemaDeMoeda, fk_usuario)

Professor (pk_id, nome, cpf, departamento, fk_usuario)

TransferenciaDePontos (pk_id, motivo, valor, fk_aluno, fk_conta, fk_professor)

Conta (pk_id, saldo, historico, fk_professor, fk_usuario?)

Resgate (pk_id, fk_aluno, fk_vantagem, fk_empresaParceira)

Vantagem (pk_id, preco, descricao, foto, fk_empresaParceira)

EmpresaParceira (pk_CNPJ, nome, fk_usuario)

Instituicao (pk_id, fk_sistemaDeMoeda)

SistemaDeMoeda (pk_id)

Semestre (pk_id, fk_SistemaDeMoeda)

![DER](./projeto/DER.png)

## Diagramas de Sequencia do Sistema

UC01
![UC01](./projeto/Diagrama%20de%20Sequencia%20do%20Sistema/UC01.png)

UC02-04-05-06-07
![UC02-04-05-06-07](./projeto/Diagrama%20de%20Sequencia%20do%20Sistema/UC02-04-05-06-07.png)

UC03
![UC03](./projeto/Diagrama%20de%20Sequencia%20do%20Sistema/UC03.png)

UC08
![UC08](./projeto/Diagrama%20de%20Sequencia%20do%20Sistema/UC08.png)

UC09
![UC09](./projeto/Diagrama%20de%20Sequencia%20do%20Sistema/UC09.png)

UC10-11-12
![UC10-11-12](./projeto/Diagrama%20de%20Sequencia%20do%20Sistema/UC10-11-12.png)

UC13-14-15-16
![UC13-14-15-16](./projeto/Diagrama%20de%20Sequencia%20do%20Sistema/UC13-14-15-16.png)

UC17
![UC17](./projeto/Diagrama%20de%20Sequencia%20do%20Sistema/UC17.png)

## Diagramas de Sequencia do Sistema

UC17
![UC17](./projeto/Diagrama%20de%20Sequencia%20do%20Sistema/UC17.png)


## Diagramas de Sequencia

SD Aluno - Atualizar - UC18
![SD Aluno - Atualizar - UC18](./projeto/Diagrama%20de%20Sequencia/SD%20Aluno%20-%20Atualizar%20-%20UC18.png)

SD Aluno - Cadastrar - UC09
![SD Aluno - Cadastrar - UC09](./projeto/Diagrama%20de%20Sequencia/SD%20Aluno%20-%20Cadastrar%20-%20UC09.png)

SD Aluno - Consultar - UC19
![SD Aluno - Consultar - UC19](./projeto/Diagrama%20de%20Sequencia/SD%20Aluno%20-%20Consultar%20-%20UC19.png)

SD Aluno - Deletar - UC20
![SD Aluno - Deletar - UC20](./projeto/Diagrama%20de%20Sequencia/SD%20Aluno%20-%20Deletar%20-%20UC20.png)

SD EmpresaParceira - Atualizar - UC23
![SD EmpresaParceira - Atualizar - UC23](./projeto/Diagrama%20de%20Sequencia/SD%20EmpresaParceira%20-%20Atualizar%20-%20UC23.png)

SD EmpresaParceira - Cadastrar - UC01
![SD EmpresaParceira - Cadastrar - UC01](./projeto/Diagrama%20de%20Sequencia/SD%20EmpresaParceira%20-%20Cadastrar%20-%20UC01.png)

SD EmpresaParceira - Consultar - UC22
![SD EmpresaParceira - Consultar - UC22](./projeto/Diagrama%20de%20Sequencia/SD%20EmpresaParceira%20-%20Consultar%20-%20UC22.png)

SD EmpresaParceira - Deletar - UC21
![SD EmpresaParceira - Deletar - UC21](./projeto/Diagrama%20de%20Sequencia/SD%20EmpresaParceira%20-%20Deletar%20-%20UC21.png)

SD Aluno - Consultar Extrato - UC03
![SD Aluno - Consultar Extrato - UC03](./projeto/Diagrama%20de%20Sequencia/SD%20Aluno%20-%20Consultar%20Extrato%20-%20UC03.png)

SD Professor - Consultar Extrato - UC17
![SD Professor - Consultar Extrato - UC17](./projeto/Diagrama%20de%20Sequencia/SD%20Professor%20-%20Consultar%20Extrato%20-%20UC17.png)

SD TranferenciaDePontos - UC14
![SD TranferenciaDePontos - UC14](./projeto/Diagrama%20de%20Sequencia/SD%20TranferenciaDePontos%20-%20UC14.png)


SD Usuario - Logar - UC08
![SD Usuario - Logar - UC08](./projeto/Diagrama%20de%20Sequencia/SD%20Usuario%20-%20Logar%20-%20UC08.png)

SD Cadastro de Vantagem - UC10
![SD Cadastro de Vantagem - UC10](./projeto/Diagrama%20de%20Sequencia/SD%20Cadastro%20de%20Vantagem%20-%20UC10.png)


SD Listar Vantagens - UC10
![SD Listar Vantagens - UC10](./projeto/Diagrama%20de%20Sequencia/SD%20Listar%20Vantagens%20UC02.png)

SD Aluno - Resgatar Vantagem - UC02
![SD Aluno - Resgatar Vantagem - UC02](./projeto/Diagrama%20de%20Sequencia/SD%20Aluno%20-%20Resgatar%20Vantagem%20-%20UC02.png)




