import csv
import json
import pandas as pd

def find_members_by_city(csv_file, city_name, output_file):
    # Read CSV file into DataFrame
    members_df = pd.read_csv(csv_file)
    # Retrieve all members from the given city name
    members_city_df = members_df[(members_df['address'].str.contains(
        city_name, regex=False))]
    # Write these members into JSON file
    members_city_df.to_json(path_or_buf=output_file, orient='records',
                            indent = 6)

def test_find_members_by_city():
    # Find members in Boston
    find_members_by_city('members.csv', 'Boston', 'members_in_Boston.json')
    file = open('members_in_Boston.json')
    data = json.load(file)
    assert len(data) == 15
    assert (data[0]['memid'] == 1 and data[0]['firstname'] == 'Darren' and
            data[0]['surname'] == 'Smith')
    assert (data[1]['memid'] == 3 and data[1]['firstname'] == 'Tim' and
            data[1]['surname'] == 'Rownam')
    assert (data[2]['memid'] == 5 and data[2]['firstname'] == 'Gerald' and
            data[2]['surname'] == 'Butters')
    file.close()
    # Find members in Vancouver
    find_members_by_city('members.csv', 'Vancouver', 'members_in_Vancouver.json')
    file = open('members_in_Vancouver.json')
    data = json.load(file)
    assert len(data) == 0
    file.close()
