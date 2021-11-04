#! /bin/sh

psql_host=$1
psql_port=$2
db_name=$3
psql_user=$4
psql_password=$5

# Incorrect number of CLI arguments
if [ $# -ne 5 ]; then
  echo 'Incorrect number of arguments'
  echo 'Format: host_info.sh [psql_host] [psql_port] [db_name] [psql_user] [psql_password]'
  exit 1
fi

export PGPASSWORD=$psql_password

hostname=$(hostname -f)

# Obtain hardware specification information
lscpu_out=`lscpu`
meminfo_out=$(cat /proc/meminfo)
vmstat_t_out=$(vmstat -t)

cpu_number=$(echo $(echo "$lscpu_out" | egrep "^CPU\(s\):") | cut -d' ' -f2)
cpu_architecture=$(echo $(echo "$lscpu_out" | egrep "^Architecture:") | cut -d' ' -f2)
cpu_model=$(echo $(echo "$lscpu_out" | egrep "^Model:") | cut -d' ' -f2)
cpu_mhz=$(echo $(echo "$lscpu_out" | egrep "^CPU MHz:") | cut -d' ' -f3)
l2_cache=$(echo $(echo "$lscpu_out" | egrep "^L2 cache:") | cut -d' ' -f3)
total_mem=$(echo $(echo "$meminfo_out" | egrep "^MemTotal:") | cut -d' ' -f 2-3)
timestamp=$(echo $(echo "$vmstat_t_out" | tail -n1) | cut -d' ' -f 18-19)

# Obtain number of rows in host_info to determine value of id
id_query="SELECT COUNT(*) FROM host_info"
id=$($(psql -h $psql_host -p $psql_host -d $db_name -U $psql_user -t -c "$id_query") + 1)

# Query for inserting the data into the host_usage table
insert_stmt="INSERT INTO host_info VALUES ('$id', '$hostname', '$cpu_number', '$cpu_architecture', '$cpu_model',
'$cpu_mhz', '$l2_cache', '$total_mem', '$timestamp')";

psql -h $psql_host -p $psql_post -d $db_name -U $psql_user -c "$insert_stmt"

exit $?