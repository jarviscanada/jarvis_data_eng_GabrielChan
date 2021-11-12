-- Group hosts by hardware
SELECT cpu_number, id, "total_mem(KB)"
FROM host_info
ORDER BY cpu_number, "total_mem(KB)" DESC;

-- Helper function for rounding timestamps to 5 minute intervals
CREATE FUNCTION round5(ts timestamp) RETURNS timestamp AS
    $$
BEGIN
RETURN date_trunc('hour', ts) + date_part('minute', ts):: int / 5 * interval '5 min';
END;
$$
LANGUAGE PLPGSQL;

-- Find average memory usage of each host
SELECT host_id, hostname, round5(host_usage.timestamp) AS "timestamp", ROUND(AVG((("total_mem(KB)" - "memory_free(KB)") * 1.0 / "total_mem(KB)" * 1.0) * 100), 0) AS "avg_used_mem(%)"
FROM host_info
         INNER JOIN host_usage
                    ON host_info.id = host_usage.host_id
GROUP BY round5(host_usage.timestamp), host_id, hostname;

-- Detect host failures
SELECT *
FROM (SELECT host_id, round5(host_usage.timestamp) AS "timestamp", COUNT(*) AS num_data_points
      FROM host_info
               INNER JOIN host_usage
                          ON host_info.id = host_usage.host_id
      GROUP BY host_id, round5(host_usage.timestamp)) AS sub_query
WHERE num_data_points < 5;