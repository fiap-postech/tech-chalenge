FROM nginx:alpine

LABEL description="Static Web Content for FIAP Tech Challenge purposes"

WORKDIR /tech-challenge

ADD static /tech-challenge/static

COPY nginx.conf /etc/nginx/nginx.conf