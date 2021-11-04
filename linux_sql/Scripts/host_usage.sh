#! /bin/sh

psql_host=$1
psql_port=$2
db_name=$3
psql_user=$4
psql_password=$5

# Incorrect number of CLI arguments
if [ $# -ne 5 ]; then
  echo 'Incorrect number of arguments'
  echo 'Format: host_usage.sh [psql_host] [psql_port] [db_name] [psql_user] [psql_password]'
  exit 1
fi

export PGPASSWORD=$psql_password

hostname=$(hostname -f)

# Obtain server usage information
meminfo_out=$(cat /proc/meminfo)
vmstat_t_out=$(vmstat -t)
vmstat_d_out=$(vmstat -d)
df_out=$(df -BM /)

# Filter and assign specific stats to variables
memory_free=$(echo $(echo "$meminfo_out" | egrep "^MemFree:") | cut -d' ' -f 2-3)
cpu_idle=$(echo $(echo "$vmstat_t_out" | tail -n1) | cut -d' ' -f15)
cpu_kernel=$(echo $(echo "$vmstat_t_out" | tail -n1) | cut -d' ' -f14)
disk_io=$(echo $(echo "$vmstat_d_out" | tail -n1) | cut -d' ' -f10)
disk_available=$(echo $(echo "$df_out" | tail -n1) | cut -d' ' -f4)
timestamp=$(echo $(echo "$vmstat_t_out" | tail -n1) | cut -d' ' -f 18-19)

# Query for finding the matching id in the host_info table
id_query="(SELECT id FROM host_usage WHERE hostname='$hostname')";
host_id=$(psql -h $psql_host -p $psql_post -d $db_name -U $psql_user -c "$id_query")

# Query for inserting the data into the host_usage table
insert_stmt="INSERT INTO host_usage VALUES ('$timestamp', '$host_id', '$memory_free',
'$cpu_idle', '$cpu_kernel', '$disk_io', '$disk_available')";

psql -h $psql_host -p $psql_post -d $db_name -U $psql_user -c "$insert_stmt"

exit $?