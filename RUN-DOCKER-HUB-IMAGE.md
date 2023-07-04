# Tech Chalenge Pos-Tech - Fiap

## Aprovisionamento do ambinete completo fazendo uso da imagem do DockerHub

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
3. Execute o comando de build do projeto
```sh
./gradlew build
```

### Execução 🏃

1. Execute o docker-compose.yml para iniciar o provisionamento da solução
```sh
docker-compose -f infra/docker-compose.yml up -d
```
Ao executar o docker-compose será aprovisionado:
* Database - MySQL [8.0] - `localhost:3366`
* In-Memory Database - Redis [6-alpine] - `localhost:6380`
* CDN - NGINX [alpine] - `localhost:8888`
* Serviço - `localhost:8080`
  <br><br>

Para conexão do database - MySQL, basta utilizar as seguintes infos de conexão:
```sh
Host: localhost
Port: 3366
User: sys_tech_challenge
Password: 6#233zJMNTjkQb#sgps#
```
> ℹ️ **As informações acima são utilizadas para conexão com o banco de dados, independente do modelo que escolha para
> execução do projeto**