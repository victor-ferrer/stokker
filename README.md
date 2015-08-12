# Stokker - A stock screener based on Spring-Boot/Integration + Elastic Search

## Summary

This a personal learning project whose functionality, for the moment, is to:
- Gather historical stock quotations from Yahoo Finance 
- Gather "live" stock quotations from Google Finance
- Index everything in Elasticsearch.

Desired functionality
- Be able to perform some studies in the indexed data in ES (specially, I´m waiting for some ES 2.0 features such as the moving average).
- Use the module "Watcher" in order to define several types of alerts based on market conditions.
- Give the user a GUI, if possible, integrated with some Kibana dashboard.
- Assess whether some Portfolio management functionality would be interesting.

On technical side:
- Application contains a DockerFile and can create an Docker image from it.

 
## How to use it
You can either:
- Run mvn spring-boot:run from the projectroot directory, or
- Run java -jar <jar_file_name.jar> from the /target directory

In either case, the application will launch a local elasticsearch node where data will be indexed. If you want to use Kibana, you will have to launch it manually and set it up to use http://localhost:9200 

## Pending improvements
- Be able to not use a local ES node (based on profiles)
- Improve error reporting
- And many more...

## More info

I´m writing about this development experience in my blog: http://victorferrerjava.blogspot.com.es/
Feel free to contribute.


