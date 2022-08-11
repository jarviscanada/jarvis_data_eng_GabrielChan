import psycopg2 as ps
import csv

def find_company_info(host, port, username, password, database,
                      table, output_file):
    # Create database connection
    conn = ps.connect(database = database, user = username,
                      password = password, host = host, port = port)
    cursor = conn.cursor()
    # Execute 'GET' request
    cursor.execute('SELECT * FROM ' + table)
    # Write response to output file
    file = open(output_file, 'w')
    writer = csv.writer(file)
    for record in cursor:
        writer.writerow(record)
    file.close()
