FROM postgres

ENV POSTGRES_PASSWORD postgres

COPY setup.sql /docker-entrypoint-initdb.d/

VOLUME ["/docker-entrypoint-initdb.d"]
