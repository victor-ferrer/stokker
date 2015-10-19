# Stokker - Stock screener based on Spring-Boot/Integration + Elastic Search

## Summary

This a personal learning project whose functionality, for the moment, is to:
- Gather historical stock quotations from Yahoo Finance 
- Gather "live" stock quotations from Google Finance
- Index everything in a Elasticsearch node so queries, graphs and alarms can be generated.
- Provide an AngularJS web interface wrapping the Kibana dashboards.
- Provide portfolio management functionalities using the Stock prices stored in ES.

By "stock quotations", I mean:
- High, low, open and close values (daily values)
- Volume (daily)
- Ticker
- Timestamp of the current value


Desired functionality
- Be able to perform some studies in the indexed data in ES (specially, I´m waiting for some ES 2.0 features such as the moving average). This is a sample dashboard created with the information stored in ES and embedded in the AngularJS interface:

![Sample Kibana Dashboard](https://raw.githubusercontent.com/victor-ferrer/stokker/master/sample%20dashboard.PNG)

- Use the module "Watcher" in order to define several types of alerts based on market conditions.


On technical side:
- The latest Spring Boot version is to be used
- Application contains a DockerFile and can create an Docker image from it:
-- This image can be associated with another images running more ElasticSearch nodes and the Kibana server
- Introduce OAuth2 as authentication system

This is how the deployment looks like:

![Deployment diagram](https://raw.githubusercontent.com/victor-ferrer/stokker/master/deployment_model.PNG)
 
 - Stokker will read the stock information from the Web and will index it in an ElasticSearch cluster.
 - The ElasticSearch cluster will be deployed either in its own Docker container or along with the Stokker JVM.
 - Another Docker container will host Kibana
 - Portfolio manager will host a local database with stock portolios, whose valuations will be calculated based on what is stored in the ES cluster.
 - An AngularJS web interface will be shown to the user combining data from the local database and ES.
 
## How to use it
Check [this Wiki Page](https://github.com/victor-ferrer/stokker/wiki/How-to-run-the-Stokker-services) on how to run the project and its related tools.

## Pending improvements
- Improve error reporting
- Provide a GUI with embedded Kibana visualizations
- And many more...

## More info
I´m writing about this development experience in my blog: http://victorferrerjava.blogspot.com.es/
Feel free to contribute.


