[Lab 2 >>](/Lab2/01-crud-commands.md)

---

## Create database schema and enable CDC

In this quick module you will be enabling CDC for Astra on a new data table within the previously created database. Then you will be starting a long running Pulsar consumer that will subscribe to the new CDC topic and watch for messages.

1. Using the `Astra DB` terminal, create the schema

    ```sql
    use crud_data;

    CREATE TABLE IF NOT EXISTS testing_crud (
    id          uuid,
    first_name  text,
    specialties set<text>,
    PRIMARY KEY ((id))
    );
    ```

1. Toggle back to your Astra account and enable CDC on the table

    Tenant: "pulsar-gcp-uscentral1 / camp-constellation"
    Keyspace: "crud-data"
    Table Name: `testing_crud`

## Start streaming consumer

1. In the GitPod environment, go to the `Astra Streaming` terminal and list topics

    ```bash
    ./bin/pulsar-admin topics list "camp-constellation/default"
    ```

    Output:

    ```logs
    "data-<some-long-string>-testing_crud"
    ```

1. Use the topic name to start a long running consumer that watches for new messages

    ```bash
    ./bin/pulsar-client consume -p Earliest -t Shared -n 0 -s "crud-subscription" persistent://camp-constellation/default/<REPLACE_WITH_TOPIC_NAME>
    ```

## Summary

You're now ready to have some fun! On to the next module (click the link at the top of the page to continue).
