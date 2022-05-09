
## Search index challenge

After completing the labs of this workshop, you now have all the assets and configurations ready to complete this challenge.

The challenge is to create a search index from a collection of Stack Overflow posts. You are only interested in posts that match certain tags and don't want posts that are not deemed meaningful. To accomplish this flow you are going to be using the following:

- `Astra DB` terminal to create the store of posts and add new posts
- `Astra Streaming` terminal to create the functions that will process new posts
- CDC for Astra to connect DB and Streaming together
- `Elasticsearch` and `Kibana` terminals are for logging reference

## Getting started

The basic outline you should follow to complete the challenge:

Database schema to enable CDC

```sql
CREATE TABLE IF NOT EXISTS crud_data.posts (
id          int,
postTypeId  int,
title        text,
body        text,
creationDate dateTime,
tags        set<text> NULL,
score       int NULL,
viewCount   int NULL,
answerCount int NULL
PRIMARY KEY ((id))
);
```

### Message Processing Functions

#### Java

> Update `resources/conversion-function.yaml` with your cdc topic name

```bash
./bin/pulsar-admin topics create persistent://camp-constellation/astracdc/conversion-output-topic
./bin/pulsar-admin topics create persistent://camp-constellation/astracdc/conversion-function-logs
```

```bash
# Did you remember to set the function topic name??

./bin/pulsar-admin functions create --function-config-file ../resources/conversion-function.yaml
```

#### Python

```bash
./bin/pulsar-admin topics create persistent://camp-constellation/astracdc/decisions-output-topic
./bin/pulsar-admin topics create persistent://camp-constellation/astracdc/decisions-function-logs
```

```bash
./bin/pulsar-admin functions create --function-config-file ../resources/decisions-function.yaml
```

### Elastic Search

> Update `resources/elasticsearch-sink.yaml` with a valid index name

```bash
# Did you remember to set the index name??

./bin/pulsar-admin sinks create \
    --archive ../resources/pulsar-io-elastic-search-2.9.2.nar \
    --sink-config-file ../resources/elasticsearch-sink.yaml
```

### Load Data
Add a few posts

```bash
SOURCE './resources/insert-10-posts.sql'
```

### Observe Index

1. Build a data view in Kibana

1. View the indexed data in Kibana

### Load Bulk Data

1. Start to add a large batch of posts in the background

    ```bash
    ./resources/data-loader.sh -posts=100 -latency=1000
    ```

1. While that is running go back to Kibana and watch

## Summary

Armed with your new CDC skills go to your customers, ask for their architectures, hear their pains, and heal all their hurts. You have reached ninja status.

## Troubleshooting

- Notice the data table has a "tags" column of type `set<text>` but it was lost during conversion. Thats because the data type is currecntly supported by CDC for Astra, so it is quietly dropped.

- Each function has its own logging topic. You could start a new consumer that watches a given logging topic to see exception details.
