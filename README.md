# projeto-pd
Projeto referente ao módulo de Programação Distribuída LetsCode

O projeto do módulo de programação distribuída foi a utilização do Kafka no projeto desenvolvido no módulo anterior: https://github.com/ezambomsantana/letscode-banco-de-dados/blob/main/projeto.md

 - A alteração foi na rota POST /compras em compras-api, essa rota apenas recebe o produto e o coloca em uma fila Kafka. A compras-validator recebe a mensagem do Kafka e faz as validações da compra;

 - Os dados da compra salvos no banco de dados são feitos pelo compras-validator;

 - A produto-api continua a cadastrando e retornando os produtos mas com o banco de dados separado do de compras; 
