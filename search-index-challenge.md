
## Search index challenge

After completing the labs of this workshop, you now have all the assets and configurations ready to complete this challenge.

The challenge is to create a search index from a collection of Stack Overflow posts. You are only interested in posts that match certain criteria and don't want posts that are not deemed meaningful. To accomplish this flow you are going to be using the following:

- `Astra DB` terminal to create the store of posts and add new posts
- `Astra Streaming` terminal to create the functions that will process new posts
- CDC for Astra to connect DB and Streaming together
- `Elasticsearch` and `Kibana` for the search index

## Getting started

The basic outline you should follow to complete the challenge:

Database schema to enable CDC

```sql
CREATE TABLE IF NOT EXISTS crud_data.posts (
id          int,
postTypeId  int,
title        text,
score       int,
viewCount   int,
answerCount int,
PRIMARY KEY ((id))
);
```

### Message Processing Functions

#### Java

> Update `resources/conversion-function.yaml` with your cdc topic name

```bash
./bin/pulsar-admin topics create persistent://<NAME>-camp-const/astracdc/conversion-output-topic
./bin/pulsar-admin topics create persistent://<NAME>-camp-const/astracdc/conversion-function-logs
```

```bash
# Did you remember to set the function topic name??

./bin/pulsar-admin functions create --function-config-file ../resources/conversion-function.yaml
```

#### Python

```bash
./bin/pulsar-admin topics create persistent://<NAME>-camp-const/astracdc/decisions-output-topic
./bin/pulsar-admin topics create persistent://<NAME>-camp-const/astracdc/decisions-function-logs
```

```bash
./bin/pulsar-admin functions create --function-config-file ../resources/decisions-function.yaml
```

### Elastic Search

Use `resources/elasticsearch-sink.yaml` in the Astra Streaming UI to create a new sink.

### Load Data

Add a few posts

```bash
SOURCE '/workspace/advanced-cdc-for-astra/resources/insert-posts.sql'
```

### Observe Index

1. Login to Elastic instance: [https://camp-constellation.kb.us-central1.gcp.cloud.es.io:9243/app/home#/](https://camp-constellation.kb.us-central1.gcp.cloud.es.io:9243/app/home#/)

1. In the Elasticsearch deployment choose "Management" > "Stack Management" from the left menu

    ![image12](https://user-images.githubusercontent.com/16946028/160866704-0d6dacfc-c35e-4271-b957-74fa314e37d9.png)

1. Then choose "Kibana" > "Data Views" to get a prompt that you already have data in ElasticSearch. Choose "Create data view".

    ![image5](https://user-images.githubusercontent.com/16946028/160866922-3609aeb4-a1a4-4a87-935e-751cf6d21250.png)

1. Find your index on the right, type in that name in the left text box, and save

    ![image14](https://user-images.githubusercontent.com/16946028/160867122-8e83732f-7531-44d9-a321-c266a74091d2.png)

1. Navigate to the "Discover" option of Analytics from the left menu

    ![image6](https://user-images.githubusercontent.com/16946028/160867343-60ec87ac-db5c-461d-8a92-6307e752b14b.png)

## Summary

Armed with your new CDC skills go to your customers, ask for their architectures, hear their pains, and heal all their hurts. You have reached ninja status.

## Troubleshooting

- Each function and sink provide quite a bit of logging. You have the choice of either using the Astra UI to view output or you can use the `pulsar-admin` cli to query the logs topic.
