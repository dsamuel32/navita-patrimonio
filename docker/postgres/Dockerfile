FROM postgres
ENV POSTGRES_PASSWORD="postgres"
ADD init.sh /
EXPOSE 5432
RUN cat /init.sh >> /docker-entrypoint-initdb.d/postgres.sh