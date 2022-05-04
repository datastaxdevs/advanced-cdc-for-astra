[Next Module >>](/Lab1/03-enable-cdc-and-consumer.md)

---

## GitPod Environment

1. Start the GitPod environment by clicking the button below

    [![Open in Gitpod](https://gitpod.io/button/open-in-gitpod.svg)](https://gitpod.io/https://github.com/ddieruf/advanced-cdc-for-astra)

1. Once initialized there will a few terminal sessions started at the lower right corner. Choose the `Astra DB` terminal

1. That terminal will ask for the organization token you copied to clipboard

    The initialization will complete with access to `cqlsh`.

    ```
    ...
    
    ✔ Please paste the Database Admin Token here
    ***********
    Credentials set up, checking database
    
    ...

    [OK] - Launching CQLSH....
    Connected to cndb at 127.0.0.1:9042.
    [cqlsh 6.8.0 | Cassandra 4.0.0.6816 | CQL spec 3.4.5 | Native protocol v4]
    Use HELP for help.
    token@cqlsh>
    ```

1. Validate cqlsh access by listing the serverless database created previously

    ```sql
    describe keyspaces;
    ```

    Output:

    ```
    crud-data
    ```

1. Looking to the left navigation, open the 'apache-pulsar-2.9.2/conf/broker.conf' file

1. Replace the contents of the file with the contents from the downloaded broker.conf

1. Open the terminal session named 'Pulsar', from the lower right corner

1. Validate the connection with Astra Streaming broker

    ```bash
    ./bin/pulsar-admin tenants list
    ```

    Output:

    ```logs
    "public"
    "camp-constellation"
    ```

## Summary

asdasdasd