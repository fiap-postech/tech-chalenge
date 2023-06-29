FROM mysql:8.0

LABEL description="Relational Database for FIAP Tech Challenge purposes"

ENV MYSQL_DATABASE tech_challenge

COPY ./script/ /docker-entrypoint-initdb.d/

CMD ["mysqld", "--default-authentication-plugin=mysql_native_password"]
