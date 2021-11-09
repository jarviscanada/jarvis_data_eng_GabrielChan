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

INSERT INTO host_usage
VALUES
    (2021-11-06 21:44:01, 1, 767283, 99, 0, 0, '23198M'),
    (2021-11-06 21:45:01, 1, 655672, 99, 0, 0, '23198M'),
    (2021-11-06 21:46:01, 1, 665277, 99, 0, 0, '23198M'),
    (2021-11-06 21:47:01, 1, 640627, 99, 0, 0, '23198M'),
    (2021-11-06 21:48:01, 1, 798034, 99, 0, 0, '23198M'),
    (2021-11-06 21:49:01, 1, 739106, 99, 0, 0, '23198M'),
    (2021-11-06 21:50:01, 1, 739593, 99, 0, 0, '23198M'),
    (2021-11-06 21:51:01, 1, 751803, 99, 0, 0, '23198M'),
    (2021-11-06 21:52:01, 1, 713841, 99, 0, 0, '23198M'),
    (2021-11-06 21:53:01, 1, 720732, 99, 0, 0, '23198M'),
    (2021-11-06 21:54:01, 1, 751679, 99, 0, 0, '23198M'),
    (2021-11-06 21:55:01, 1, 793311, 99, 0, 0, '23198M'),
    (2021-11-06 21:56:01, 1, 794025, 99, 0, 0, '23198M'),
    (2021-11-06 21:57:01, 1, 683881, 99, 0, 0, '23198M');