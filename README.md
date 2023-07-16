# Projeto Store System with Springboot3 and MySQL

Projeto feito para melhorar os conhecimentos aprendidos na linguagem Java.

O projeto possui um sistema CRUD para usuários, produtos e pedidos.

# Detalhes

Por padrão o projeto roda no localhost porta 8080. Com conexão com o MySQL.

Frontend feito com HTML puro para facilitar o desenvolvimento. <br/>
Endereços: <br/>
http://localhost:8080 -> Página inicial, com links para registro e listagem de usuários e produtos. <br/>
http://localhost:8080/user/all -> Retorna todos os usuários, com links para registro, update e remoção dos usuários. <br/>
http://localhost:8080/user/?id=2 -> Retorna o usuário de id 2, com links para update e remoção. <br/>
http://localhost:8080/product/all -> Retorna todos os produtos, com links para registro, update e remoção dos produtos. <br/>
http://localhost:8080/product/?id=2 -> Retorna o produto de id 2, com links para update e remoção. <br/>
http://localhost:8080/order/ -> Retorna todos os pedidos, informando os produtos e quantidade de cada um, mostrando também opções para adicionar mais produtos ou remover. <br/>

Endereços com dados JSON: <br/>
http://localhost:8080/backend/dev/users -> Retorna todos os usuários em formato JSON. <br/>
http://localhost:8080/backend/dev/users/2 -> Retorna o usuário de ID 2 em formato JSON. <br/>
http://localhost:8080/backend/dev/users/search/?email=email@email.com -> Retorna todos os usuários com este email em formato JSON. <br/>
http://localhost:8080/backend/dev/products -> Retorna todos os produtos em formato JSON. <br/>
http://localhost:8080/backend/dev/products/2 -> Retorna o produto de ID 2 em formato JSON. <br/>
http://localhost:8080/backend/dev/orders -> Retorna todos os pedidos em formato JSON. <br/>
http://localhost:8080/backend/dev/orders/2 -> Retorna o pedido de ID 2 em formato JSON. <br/>

Para evitar que um usuário seja cadastrado mais de uma vez é feito uma verificação do nome e email, caso os dois campos sejam iguais a algum já registrado o cadastro não é efetuado. <br/>
Para o cadastro de pedidos, é necessário informar um usuário, e em seguida, inserir items neste pedido, informando o id do pedido e o id do produto que deseja adicionar, para atualização é necessário informar um usuário válido. <br/>
Após isso, o pedido completo será mostrado nas informações do usuário. <br/>
