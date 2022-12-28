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

![alt text](http://url/to/img.png)

![alt text](http://url/to/img.png)
