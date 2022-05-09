[Lab 2 >>](/Lab2/01-crud-commands.md)

---

## Create database schema and enable CDC

In this quick module you will be enabling CDC for Astra on a new data table within the previously created database. Then you will be starting a long running Pulsar consumer that will subscribe to the new CDC topic and watch for messages.

1. Using the `Astra DB` terminal, create the schema

    ```sql
    CREATE TABLE IF NOT EXISTS crud_data.testing_crud (
    id          uuid,
    first_name  text,
    PRIMARY KEY ((id))
    );
    ```

1. Toggle back to your Astra account and enable CDC on the table

    Tenant: "pulsar-gcp-uscentral1 / camp-constellation"

    Keyspace: "crud-data"

    Table Name: `testing_crud`

    ![Enable CDC](/images/astra-enable-cdc.png)

## Start streaming consumer

1. In the GitPod environment, go to the `Astra Streaming` terminal and list topics

    ```bash
    ./bin/pulsar-admin topics list "camp-constellation/astracdc"
    ```

    Output:

    ```logs
    "persistent://camp-constellation/astracdc/log-40e2d3c8-ffb2-46c6-b6bc-283d7bb3e547-crud_data.testing_crud-partition-2"
    "persistent://camp-constellation/astracdc/log-40e2d3c8-ffb2-46c6-b6bc-283d7bb3e547-crud_data.testing_crud-partition-1"
    "persistent://camp-constellation/astracdc/log-40e2d3c8-ffb2-46c6-b6bc-283d7bb3e547-crud_data.testing_crud-partition-0"
    "persistent://camp-constellation/astracdc/data-40e2d3c8-ffb2-46c6-b6bc-283d7bb3e547-crud_data.testing_crud-partition-2"
    "persistent://camp-constellation/astracdc/data-40e2d3c8-ffb2-46c6-b6bc-283d7bb3e547-crud_data.testing_crud-partition-1"
    "persistent://camp-constellation/astracdc/data-40e2d3c8-ffb2-46c6-b6bc-283d7bb3e547-crud_data.testing_crud-partition-0"
    ```

    > These are all the topics created for the CDC integrations. What you will need in the next step is the topic identifier. Thats the alphanumeric section of the name. From the above example you would copy "40e2d3c8-ffb2-46c6-b6bc-283d7bb3e547".

1. Use the topic name to start a long running consumer that watches for new messages

    ```bash
    ./bin/pulsar-client consume -st auto_consume persistent://camp-constellation/astracdc/data-<REPLACE_WITH_ALPHANUMERIC>-crud_data.testing_crud
    ```

    > There will be quite a bit of output with the last few lines like below:

    Output:

    ```logs
    ...
    2022-05-06T15:35:27,240+0000 [pulsar-client-io-1-1] INFO  org.apache.pulsar.client.impl.ConsumerImpl - [persistent://camp-constellation/astracdc/data-40e2d3c8-ffb2-46c6-b6bc-283d7bb3e547-crud_data.testing_crud-partition-0][crud-subscription] Subscribed to topic on pulsar-gcp-uscentral1.streaming.datastax.com/35.232.116.225:6651 -- consumer: 0
    2022-05-06T15:35:27,261+0000 [pulsar-client-io-1-1] INFO  org.apache.pulsar.client.impl.ConsumerImpl - [persistent://camp-constellation/astracdc/data-40e2d3c8-ffb2-46c6-b6bc-283d7bb3e547-crud_data.testing_crud-partition-1][crud-subscription] Subscribed to topic on pulsar-gcp-uscentral1.streaming.datastax.com/35.232.116.225:6651 -- consumer: 1
    2022-05-06T15:35:27,263+0000 [pulsar-client-io-1-1] INFO  org.apache.pulsar.client.impl.ConsumerImpl - [persistent://camp-constellation/astracdc/data-40e2d3c8-ffb2-46c6-b6bc-283d7bb3e547-crud_data.testing_crud-partition-2][crud-subscription] Subscribed to topic on pulsar-gcp-uscentral1.streaming.datastax.com/35.232.116.225:6651 -- consumer: 2
    2022-05-06T15:35:27,268+0000 [pulsar-client-io-1-1] INFO  org.apache.pulsar.client.impl.MultiTopicsConsumerImpl - [persistent://camp-constellation/astracdc/data-40e2d3c8-ffb2-46c6-b6bc-283d7bb3e547-crud_data.testing_crud] [crud-subscription] Success subscribe new topic persistent://camp-constellation/astracdc/data-40e2d3c8-ffb2-46c6-b6bc-283d7bb3e547-crud_data.testing_crud in topics consumer, partitions: 3, allTopicPartitionsNumber: 3
    ```

## Summary

You're now ready to have some fun! On to the next module (click the link at the top of the page to continue).
