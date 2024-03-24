FROM postgres:15.3-alpine3.18
EXPOSE 5433

#Variaveis de ambiente
ENV POSTGRES_USER=gestao-de-produtos
ENV POSTGRES_PASSWORD=123456
ENV POSTGRES_DB=gestao-de-produtos