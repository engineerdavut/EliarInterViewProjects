# EliarInterViewProjects

The Java Backend Project has a machine entity. Postgresql was used as database.
It was coded with the code first approach.
It was coded with java spring boot version 2.7.7. Nhibernate, jpa was used.
Layered architecture was used. Generating random machine values for the number entered in our service
and a method that changes these variables every second with the thread structure is defined.

Our python code is making a request to our machineApiRead rest api.
We are asked how many machines we want to enter. With the getall method when creating rest api machines
We take the machine values and give the variables 1 per second to influxdb.
InfluxDb is an open source time series database (data from vagrants etc.)
With another python module, we are coding it to get the hourly average values of the machine in influxdb.
We write the hourly average data to the csv file.

Used
Java,Java Spring Boot, PostgreSql, Influx DB ,Python 

Screenshots of Operation

![postgre](https://user-images.githubusercontent.com/68922691/209849170-3083e91e-93c6-4d8d-a629-1eb8aef63d6c.PNG)

![InfluxDb drumspeed](https://user-images.githubusercontent.com/68922691/209849338-3c36077e-fe72-4a3a-a17c-39f1545469d9.PNG)

![InfluxDb heat](https://user-images.githubusercontent.com/68922691/209849482-f0910486-bbbe-4906-b541-c4d2ee24d2cd.PNG)

![InfluxDb level](https://user-images.githubusercontent.com/68922691/209849599-cd5ec4fd-0407-4ba0-b34a-899d9ad747e7.PNG)

![machine average values in CSV file](https://user-images.githubusercontent.com/68922691/209849725-d34ad734-f6ac-442f-9386-2cd1e2d382a9.png)
