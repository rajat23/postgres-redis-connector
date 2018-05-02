#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL
    CREATE DATABASE test_db;
    GRANT ALL PRIVILEGES ON DATABASE test_db TO "$POSTGRES_USER";
\du
\dt
EOSQL