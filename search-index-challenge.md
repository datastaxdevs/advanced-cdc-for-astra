
## Search index challenge

After completing the labs of this workshop, you now have all the assets and configurations ready to complete this challenge.

The challenge is to create a search index from a collection of Stack Overflow posts. You are only interested in posts that match certain tags and don't want posts that are not deemed meaningful. To accomplish this flow you are going to be using the following:

- `Astra DB` terminal to create the store of posts and add new posts
- `Astra Streaming` terminal to create the functions that will process new posts
- CDC for Astra to connect DB and Streaming together
- `Elasticsearch` and `Kibana` terminals are for logging reference

## Getting started

The basic outline you should follow to complete the challenge:

1. Create schema

    ```sql
    use crud_data;

    CREATE TABLE IF NOT EXISTS posts (
    id          uuid,
    postTypeId  int,
    body        text,
    tags        set<text>,
    PRIMARY KEY ((id))
    );
    ```

1. Enable CDC on `posts` table

1. Load the Java filtering function

    ```bash
    ./bin/pulsar-admin functions create --function-config-file ../resources/tags-function.yaml
    ```

1. Load the Python decisions function

    ```bash
    ./bin/pulsar-admin functions create --function-config-file ../resources/decisions-function.yaml
    ```

1. Create the sink to Elasticsearch

    ```bash
    ./bin/pulsar-admin sinks create \
        --archive ../resources/pulsar-io-elastic-search-2.9.2.nar \
        --source-config-file ../resources/elasticsearch-sink.yaml
    ```

1. Add a few posts

    ```bash
    ./resources/data-loader.sh -posts=10 -latency=1000
    ```

1. Build a data view in Kibana

1. View the indexed data in Kibana

1. Start to add a large batch of posts in the background

    ```bash
    ./resources/data-loader.sh -posts=100 -latency=1000
    ```

1. While that is running go back to Kibana and watch

## Summary

Armed with your new CDC skills go to your customers, ask for their architectures, hear their pains, and heal all their hurts. You have reached ninja status.
