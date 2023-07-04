# Tech Chalenge Pos-Tech - Fiap

## Aprovisionamento apenas das dependências externas do projeto

### Preparação 💻

Independente da forma que escolha para executar o projeto, temos o seguinte procedimento a ser executado:

1. Clone o projeto desse repositório do Github
```sh
git clone https://github.com/fiap-postech/tech-challenge.git
```
2. Acesse a pasta do projeto pelo terminal/cmd
```sh
cd tech-challenge
```
3. Execute o comando para instalação das dependências
```sh
./gradlew install
```
4. Execute após o comando de build do projeto
```sh
./gradlew build
```

### Execução 🏃

1. Execute o docker-compose.yml para iniciar o provisionamento da infra local
```sh
docker-compose -f infra/docker-compose-local.yml up -d
```
Ao executar o docker-compose será aprovisionado:
* Database - MySQL [8.0] - `localhost:3366`
* In-Memory Database - Redis [6-alpine] - `localhost:6380`
* CDN - NGINX [alpine] - `localhost:8888`
<br><br>
2. Com isso, você já pode executar o projeto pela IDE de sua preferência ou, se preferir, pode executar o projeto pela
linha de comando com:
```sh
./gradlew bootRun
```
Ao executar o serviço, o servidor responderá no endereço: `localhost:8688` 

3. Para conexão do database - MySQL, basta utilizar as seguintes infos de conexão:
```sh
Host: localhost
Port: 3366
User: sys_tech_challenge
Password: 6#233zJMNTjkQb#sgps#
```
> ℹ️ **As informações acima são utilizadas para conexão com o banco de dados, independente do modelo que escolha para 
> execução do projeto**  
