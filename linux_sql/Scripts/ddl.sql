CREATE TABLE PUBLIC.host_info
(
    id                  SERIAL PRIMARY KEY,
    hostname            VARCHAR UNIQUE NOT NULL,
    cpu_number          INTEGER NOT NULL,
    cpu_architecture    VARCHAR NOT NULL,
    cpu_model           INTEGER NOT NULL,
    cpu_mhz             DECIMAL NOT NULL,
    L2_cache            VARCHAR NOT NULL,
    total_mem           VARCHAR NOT NULL,
    "timestamp"         TIMESTAMP NOT NULL
);

CREATE TABLE PUBLIC.host_usage
(
    "timestamp"         TIMESTAMP NOT NULL,
    host_id             SERIAL references host_info(id) NOT NULL,
    memory_free         VARCHAR NOT NULL,
    cpu_idle            INTEGER NOT NULL,
    cpu_kernel          INTEGER NOT NULL,
    disk_io             INTEGER NOT NULL,
    disk_available      VARCHAR NOT NULL
);