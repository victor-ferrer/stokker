# Stokker - A stock screener based on Spring-Boot/Integration + Elastic Search

## Summary

This a personal learning project whose functionality, for the moment, is to:
- Gather historical stock quotations from Yahoo Finance 
- Gather "live" stock quotations from Google Finance
- Index everything in a Elasticsearch node so queries, graphs and alarms can be generated.

By "stock quotations", I mean:
- High, low, open and close values (daily values)
- Volume (daily)
- Ticker
- Timestamp of the current value


Desired functionality
- Be able to perform some studies in the indexed data in ES (specially, I´m waiting for some ES 2.0 features such as the moving average).
- Use the module "Watcher" in order to define several types of alerts based on market conditions.
- Give the user a GUI, if possible, integrated with some Kibana dashboard.
- Assess whether some Portfolio management functionality would be interesting.

On technical side:
- The latest Spring Boot version is to be used
- Application contains a DockerFile and can create an Docker image from it:
-- This image can be associated with another images running more ElasticSearch nodes and the Kibana server

 
## How to use it
You can either:
- Run mvn spring-boot:run from the projectroot directory, or
- Run java -jar <jar_file_name.jar> from the /target directory

In either case, the application will launch a local elasticsearch node where data will be indexed. If you want to use Kibana, you will have to launch it manually and set it up to use http://localhost:9200 

There are two profiles that can be triggered using spring.profiles.active
- default (or not setting anything): The elastic search node is embedded in the Stokker Java VM
- remote-es-node: The elastic search node is an external one running in an IP:PORT to be established by using the property spring.data.elasticsearch.cluster-nodes: [IP]:[PORT]

## Pending improvements
- Improve error reporting
- Provide a GUI with embedded Kibana visualizations
- And many more...

## More info
I´m writing about this development experience in my blog: http://victorferrerjava.blogspot.com.es/
Feel free to contribute.


