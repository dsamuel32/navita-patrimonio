FROM postgres
ENV POSTGRES_PASSWORD="postgres"
ADD init.sh /
RUN cat /init.sh >> /docker-entrypoint-initdb.d/postgres.sh