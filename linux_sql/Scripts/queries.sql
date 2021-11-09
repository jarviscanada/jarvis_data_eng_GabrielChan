-- Group hosts by hardware
SELECT cpu_number, id, total_mem OVER(PARTITION BY cpu_number)
FROM host_info
ORDER BY total_mem DESC

/*
 CREATE FUNCTION function_name (@input input_data_type)
 RETURNS output_data_type
 AS
 BEGIN
    * Find 5 minute interval here
 END
 * Need to run this function multiple times to get all the 5 minute intervals
 */

-- Find average memory usage of each host
