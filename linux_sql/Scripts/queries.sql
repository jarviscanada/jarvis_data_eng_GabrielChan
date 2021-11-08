-- Group hosts by hardware
SELECT cpu_number, id, total_mem OVER(PARTITION BY cpu_number)
FROM host_info
ORDER BY total_mem DESC

-- Find average memory usage of each host
SELECT host_id, host_name, timestamp, (total_mem - memory_free)