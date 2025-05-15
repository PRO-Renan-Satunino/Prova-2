# 1. Enunciado do Trabalho
Desenvolva um sistema de gerenciamento de consultas médicas utilizando Java com padrão de projeto
MVC e acesso a banco de dados MySQL via JDBC. O projeto deve conter a modelagem lógica e física do
banco de dados, assim como a implementação de um sistema com cadastro, atualização, remoção e
listagem de pacientes, médicos e consultas. O sistema deve apresentar uma interface gráfica desenvolvida
com Java Swing.
# 2. Entidades do Banco de Dados
- Paciente (id, nome, cpf, telefone)
- Médico (id, nome, especialidade, crm)
- Consulta (id, id_paciente, id_medico, data, hora, observacao)
#### Relacionamentos:
- Um paciente pode ter várias consultas.
- Um médico pode atender várias consultas.
# 3. Requisitos do Sistema
##  3.1 Requisitos Funcionais
- RF01. O sistema deve permitir o cadastro de pacientes, informando: nome, CPF e telefone.
- RF02. O sistema deve permitir o cadastro de médicos, informando: nome, especialidade e CRM.
- RF03. O sistema deve permitir o agendamento de consultas, vinculando paciente e médico, com data, hora
e observações.
- RF04. O sistema deve listar todas as consultas agendadas, com paciente, médico, data, hora e
observações.
- RF05. O sistema deve permitir atualizar os dados de pacientes, médicos e consultas.
- RF06. O sistema deve permitir excluir pacientes, médicos e consultas.
- RF07. O sistema deve permitir buscar consultas por nome do paciente ou por data.
## 3.2 Requisitos Não Funcionais
- RNF01. O sistema deve ser desenvolvido em Java, utilizando Java Swing para interface gráfica.
Projeto Avaliativo - Sistema de Gerenciamento de Consultas Médicas
- RNF02. A arquitetura do sistema deve seguir o padrão MVC com separação entre modelo, controle, visão e
DAO.
- RNF03. A persistência dos dados deve ser realizada através de MySQL com JDBC.
- RNF04. O sistema deve ter código-fonte organizado em pacotes claros e identificáveis.
- RNF05. O sistema deve validar campos obrigatórios antes de efetuar cadastros ou atualizações.
- RNF06. O sistema deve tratar erros de banco de dados com mensagens amigáveis.
- RNF07. O sistema deve ser projetado para facilitar manutenção e adição de funcionalidades futuras