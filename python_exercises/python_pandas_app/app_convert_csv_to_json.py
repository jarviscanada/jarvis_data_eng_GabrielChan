import csv
import json
import sys

def csv_to_json(input_csv, output_json):
    # Read CSV file into a list of dictionaries
    with open(input_csv, newline='') as csvfile:
        reader = csv.DictReader(csvfile)
        dictList = []
        for row in reader:
            dictList.append(row)
        csvfile.close()
    # Write the list of dictionaries into JSON file
    with open(output_json, 'w') as outfile:
        json.dump(dictList, outfile, indent = 6)
        outfile.close()

def test_csv_to_json():
    csv_to_json('members.csv', 'output.json')
    file = open('output.json')
    data = json.load(file)
    assert(len(data) == 31)
    assert (data[0]['memid'] == 0 and data[0]['firstname'] == 'GUEST' and
            data[0]['surname'] == 'GUEST')
    assert (data[1]['memid'] == 1 and data[1]['firstname'] == 'Darren' and
            data[1]['surname'] == 'Smith')
    assert (data[2]['memid'] == 2 and data[2]['firstname'] == 'Tracy' and
            data[2]['surname'] == 'Smith')
    file.close()
    
              
if __name__ == "__main__":
    args = sys.argv
    if (len(args) == 3):
        csv_to_json(args[1], args[2])
    else:
        print('usage: python app_convert_csv_to_json.py [csv file] [json file]')
