# syntax=docker/dockerfile:1
FROM python:3.8-slim-buster

WORKDIR /http_app

COPY requirements.txt requirements.txt
RUN pip install -r requirements.txt

COPY . .

CMD ["python", "app_company_info.py"]