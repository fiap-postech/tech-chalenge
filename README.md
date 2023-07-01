# Tech Chalenge Pos-Tech - Fiap

## Problema
Há uma lanchonete de bairro que está expandindo devido seu grande sucesso. Porém, com a expansão e sem um sistema de controle de pedidos, o atendimento aos clientes pode ser caótico e confuso. Por exemplo, imagine que um cliente faça um pedido complexo, como um hambúrguer personalizado com ingredientes específicos, acompanhado de batatas fritas e uma bebida. O atendente pode anotar o pedido em um papel e entregá-lo à cozinha, mas não há garantia de que o pedido será preparado corretamente. Sem um sistema de controle de pedidos, pode haver confusão entre os atendentes e a cozinha, resultando em atrasos na preparação e entrega dos pedidos. Os pedidos podem ser perdidos, mal interpretados ou esquecidos, levando à insatisfação dos clientes e a perda de negócios. Em resumo, um sistema de controle de pedidos é essencial para garantir que a lanchonete possa atender os clientes de maneira eficiente, gerenciando seus pedidos e estoques de forma adequada. Sem ele, expandir a lanchonete pode acabar não dando certo, resultando em clientes insatisfeitos e impactando os negócios de forma negativa. Para solucionar o problema, a lanchonete irá investir em um sistema de autoatendimento de fast food, que é composto por uma série de dispositivos e interfaces que permitem aos clientes selecionar e fazer pedidos sem precisar interagir com um atendente.

## Descrição do Sistema
Sistema de gerenciamento de pedidos de fast food, este sistema tem como objetivo permitir que a rede de fast food possa atender os clientes de maneira eficiente, gerenciando seus pedidos e estoques de forma adequada assim viabilizando a expansão da rede. 

## Tópicos

- [Pré-Requisitos](#pre-requisitos)
- [Utilização](#utilização)

## Pré-Requisitos ⚙️

<br>![Static Badge](https://img.shields.io/badge/java-v17.0.0-blue)
<br>![Static Badge](https://img.shields.io/badge/docker-latest-blue)
<br>![Static Badge](https://img.shields.io/badge/gradle-v8.1.1-blue)

## Utilização 💻

1) Clone o projeto desse repositório do Github
```sh
git clone https://github.com/fiap-postech/tech-chalenge.git
```
2) Acesse a pasta do projeto pelo terminal/cmd
```sh
cd tech-chalenge
```
3) Execute o comando para instalação das dependências
```sh
./gradlew install
```
4) Execute após o comando de build do projeto
```sh
./gradlew build
```
5) Execute o docker-compose.yml para iniciar o provisionamento da infra local
```sh
docker-compose --project-directory infra up -d
```
7) Ao executar o docker-compose será provisionado:
   * Database - MySQL [8.0] - `localhost:3366`
   * In-Memory Database - Redis [6-alphine] - `localhost:6380`
<br><br>
8) Execute o comando gradle abaixo para subir o projeto:
```sh
./gradlew bootRun
```
9) Para conexão do database - MySQL, basta utilizar as seguintes infos de conexão:
```sh
Host: localhost
Port: 3366
User: sys_tech_challenge
Password: 6#233zJMNTjkQb#sgps#
```
## Funcionalidades ✅
> De repente trazer aqui as funcionalidades que teremos no projeto?