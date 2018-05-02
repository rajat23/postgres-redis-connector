# postgres-redis-connector **[Work in progress]**

```cd pg-docker

docker build -t pg_spike .

docker run  -it -p 5432:5432 -e POSTGRES_USER=dbuser -e POSTGRES_PASSWORD=dbpass pg_spike


psql -h localhost -p 5432 -U dbuser

\connect test_db

create following table,trigger and procedure

CREATE TABLE user(
 user_id serial PRIMARY KEY,
 username VARCHAR (50) UNIQUE NOT NULL,
 email VARCHAR (355) UNIQUE NOT NULL
);

CREATE TRIGGER USER_INSERT
AFTER INSERT
ON user
FOR EACH ROW
EXECUTE PROCEDURE NOTIFY();

CREATE OR REPLACE FUNCTION NOTIFY() RETURNS trigger AS
$BODY$
BEGIN
    PERFORM pg_notify('user_channel', row_to_json(NEW)::text);
    RETURN new;
END;
$BODY$
LANGUAGE 'plpgsql' VOLATILE COST 100;
```

Run java application 

