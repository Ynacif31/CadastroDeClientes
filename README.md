##Cadastro de Clientes

Este projeto consiste em um sistema simples de cadastro de clientes desenvolvido em Java, utilizando Swing para a interface gráfica e implementando um padrão DAO (Data Access Object) para gerenciamento dos dados dos clientes.

### Funcionalidades

Cadastro de Clientes: Permite cadastrar novos clientes com nome, e-mail, CPF e telefone.
Validação de Dados: Verifica se as informações inseridas são válidas antes de adicionar um cliente ao sistema.
Armazenamento dos Dados: Utiliza duas implementações de DAO (ClienteSetDAO e ClienteMapDAO) para armazenar os dados localmente em estruturas de dados Java.
Interface Gráfica: Uma interface simples e intuitiva desenvolvida com Swing, permitindo interação fácil com o sistema.
Operações de CRUD: Oferece as operações básicas de criação, leitura, atualização e remoção de clientes.

### Tecnologias Utilizadas

Java: Linguagem principal utilizada no desenvolvimento do projeto.
Swing: Biblioteca usada para criar a interface gráfica do usuário (GUI).
DAO: Padrão de design para abstrair as operações de banco de dados.
Estruturas de Dados: Utilização de Set e Map para gerenciar os dados dos clientes.

## Melhorias Futuras

Implementação de um banco de dados real para persistência dos dados.
Inclusão de filtros e pesquisa de clientes.
Validação aprimorada para CPF e e-mail.
Integração com APIs para envio de e-mails automáticos para os clientes cadastrados.
