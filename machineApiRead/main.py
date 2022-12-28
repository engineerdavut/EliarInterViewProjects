import influxdb_client
import self as self
from influxdb_client import  Point
from influxdb_client.client.write_api import SYNCHRONOUS
import requests
from time import sleep
from threading import Thread
def random_washing_create(n):
    url="http://localhost:8080/api/machines/randomCreateMachine"
    paramaters={"n": n}

    washing_request=requests.get(url,params=paramaters)
    return washing_request.json()
#REST apiden veriler çekildi.
def washing_request():
    url="http://localhost:8080/api/machines/getAll"

    washing_request=requests.get(url)
    return washing_request.json()
n=input("kac makine var.")
random_washing_create(n)
#thread getitem ile çözüldü.

bucket = "machine_bucket"
token = "C4vTcFkJ1ma7S_UdMvo9lduobxkPrDSTMl2dnR3cEIPYozZ86jGoy5Dj9bWvzT6869T9kRDnKfphYq07zht3Bg=="
org = "eliar"
url = "http://localhost:8086"

client = influxdb_client.InfluxDBClient(url=url, token=token, org=org)

write_api = client.write_api(write_options=SYNCHRONOUS)
def __getitem__(self):
    while True:
        washing_request_influx = washing_request()
        for i in range(int(n)):
            point = (
                Point("machine")
                .tag("machineid", washing_request_influx['data'][i]['id'])
                .field("drumSpeed", washing_request_influx['data'][i]['drumSpeed'])
                .field("heat",  washing_request_influx['data'][i]['heat'])
                .field("level",  washing_request_influx['data'][i]['level'])
                .field("isOpenTheDoor",  washing_request_influx['data'][i]['isopenthedoor'])
            )
            write_api.write(bucket=bucket, org=org, record=point)
        sleep(1)
my_washing_request=Thread(target = __getitem__(self))
my_washing_request.start()




