import influxdb_client
import csv
from urllib3.connectionpool import xrange

bucket = "machine_bucket"
org = "eliar"
token = "C4vTcFkJ1ma7S_UdMvo9lduobxkPrDSTMl2dnR3cEIPYozZ86jGoy5Dj9bWvzT6869T9kRDnKfphYq07zht3Bg=="
url="http://localhost:8086"

client = influxdb_client.InfluxDBClient(
    url=url,
    token=token,
    org=org
)

id_list=[]
heat_avg_list=[]
level_avg_list=[]
drum_speed_avg_list=[]
true_counter_list = []
false_counter_list = []
def avg_variables_calculate(params):
    query_api = client.query_api()
    for item in params:
        query = f'from(bucket: "machine_bucket")\
        |> range(start: -1h)\
        |> filter(fn: (r) => r["_measurement"] == "machine")\
        |> filter(fn: (r) => r["_field"] == "{item}")\
        |> aggregateWindow(every: 1h, fn: mean, createEmpty: false)\
        |> window(every:1h)\
        |> duplicate(column: "_stop", as: "_time")\
        |> yield(name: "mean")'
        result = query_api.query(org=org, query=query)
        for table in result:
            value_avg = 0.0
            for record in table.records:
                value_avg += record.get_value()
                if not record.values.get("machineid") in id_list:
                    id_list.append(record.values.get("machineid"))
            if (item == "heat"):
                heat_avg_list.append(value_avg)
            elif (item == "level"):
                level_avg_list.append(value_avg)
            elif (item == "drumSpeed"):
                drum_speed_avg_list.append(value_avg)
def true_count_calculate():
    query_api = client.query_api()
    true_query = 'from(bucket: "machine_bucket")\
    |> range(start: -1h)\
    |> filter(fn: (r) => r["_measurement"] == "machine")\
    |> filter(fn: (r) => r["_field"] == "isOpenTheDoor")\
    |> aggregateWindow(every: 1h,\
            fn: (column, tables=<-) => tables\
                |> reduce(\
                    identity: {true_counter: 0.0},\
                    fn: (r, accumulator) => ({ \
        true_counter: if r._value == true then \
        accumulator.true_counter + 1.0 \
        else \
        accumulator.true_counter + 0.0, \
        }), \
        ), \
        )\
        |> duplicate(column: "_stop", as: "_time")'
    true_result = query_api.query(org=org, query=true_query)
    for true_table in true_result:
        true_counter = 0
        for true_record in true_table.records:
            true_counter += true_record.values.get("true_counter")
        true_counter_list.append(true_counter)
def false_count_calculate():
    query_api = client.query_api()
    false_query = 'from(bucket: "machine_bucket")\
    |> range(start: -1h)\
    |> filter(fn: (r) => r["_measurement"] == "machine")\
    |> filter(fn: (r) => r["_field"] == "isOpenTheDoor")\
    |> aggregateWindow(every: 1h,\
            fn: (column, tables=<-) => tables\
                |> reduce(\
                    identity: {false_counter: 0.0},\
                    fn: (r, accumulator) => ({ \
        false_counter: if r._value == false then \
        accumulator.false_counter + 1.0 \
        else \
        accumulator.false_counter + 0.0, \
        }), \
        ), \
        )\
        |> duplicate(column: "_stop", as: "_time")'
    false_result = query_api.query(org=org, query=false_query)
    for false_table in false_result:
        false_counter = 0
        for false_record in false_table.records:
            false_counter += false_record.values.get("false_counter")
        false_counter_list.append(false_counter)
params=["heat","level","drumSpeed"]
avg_variables_calculate(params)
true_count_calculate()
false_count_calculate()
header = ['machine_id', 'heat_avg(1h)', 'level_avg(1h)', 'drum_speed_avg(1h)'
,'open_door_count(1h)','close_door_count(1h)']
height=len(id_list)
width=len(header)
data = [[None] * width for i in xrange(height)]
for i in range(len(id_list)):
    data[i][header.index('machine_id')]=id_list[i]
    data[i][header.index('heat_avg(1h)')]=heat_avg_list[i]
    data[i][header.index('level_avg(1h)')]=level_avg_list[i]
    data[i][header.index('drum_speed_avg(1h)')] = drum_speed_avg_list[i]
    data[i][header.index('open_door_count(1h)')] = true_counter_list[i]
    data[i][header.index('close_door_count(1h)')] = false_counter_list[i]
with open('machine_average_values.csv', 'w', encoding='UTF8', newline='') as f:
    writer = csv.writer(f)
    # write the header
    writer.writerow(header)
    writer.writerows(data)
