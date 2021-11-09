#! /bin/sh

# CLI arguments
cmd=$1
db_username=$2
db_password=$3

# Start docker if the docker server is not running
sudo systemctl status docker || systemctl start docker

# Check container status and save its exit code
docker container inspect jrvs-psql
container_status=$?

# If the user wants to create a container
case $cmd in
  create)

  # Container is already created
  if [ $container_status -eq 0 ]; then
    echo 'Container already exists'
    exit 1
  fi

  # Incorrect number of CLI arguments
  if [ $# -ne 3 ]; then
    echo 'Need a username and password to create a Container'
    exit 1
  fi

  # Create Container
  docker volume create pgdata
  docker run --name jrvs-psql -e POSTGRES_USER=$db_username -e POSTGRES_PASSWORD=$db_password -d -v pgdata:/var/lib/postgresql/data -p 5432:5432 postgres:9.6-alpine
  exit $?
  ;;

  # If the user wants to start or stop a container
  start | stop)
  # If the container has not been created
  if [ $container_status -ne 0 ]; then
    echo 'Container does not exist'
    exit 1
  fi

  # Start or stop the container
  docker container $cmd jrvs-psql
  exit $?
  ;;

  # If a command other than start, stop or create is given
  *)
  echo 'Illegal Command'
  echo 'Commands: start | stop | create'
  exit 1
  ;;
esac
