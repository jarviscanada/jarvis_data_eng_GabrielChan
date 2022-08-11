# syntax=docker/dockerfile:1
FROM python:3.8-slim
WORKDIR /csv_to_json

COPY requirements.txt requirements.txt
RUN pip install -r requirements.txt

COPY . .

CMD ["python", "app_convert_csv_to_json.py"] 