import requests

def find_company_info(ticker):
    # Set URL, query parameters and authentication headers
    url = 'https://google-finance4.p.rapidapi.com/ticker/'
    params = {'t': ticker}
    headers = {'X-RapidAPI-Key': "22ec95478amsh7996e3f77dd3627p1c4d9bjsn7fdc003ce4ef",
               'X-RapidAPI-Host': "google-finance4.p.rapidapi.com"}

    # Send the request and retrieve the response
    response = requests.get(url, params = params, headers = headers)
    if (response.status_code == 200):
        # Convert response into dict
        data = dict(response.json())
        # Retrieve and print the basic information
        print(data['info']['title'] + ', ' + data['about']['ceo'] +
              ', ' + str(data['price']['previous_close']))
    else:
        print(response.status_code + ': ' + response.reason)
