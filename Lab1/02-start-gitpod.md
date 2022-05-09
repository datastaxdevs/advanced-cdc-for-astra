[Next Module >>](/Lab1/03-enable-cdc-and-consumer.md)

---

## GitPod Environment

In this module you will be configuring all the needed services and cli connections. These will be needed through the lab as well as the challenge.

1. Start the GitPod environment by clicking the button below

    [![Open in Gitpod](https://gitpod.io/button/open-in-gitpod.svg)](https://gitpod.io#https://github.com/ddieruf/advanced-cdc-for-astra)

1. Once initialized there will a few terminal sessions started at the lower right corner. Choose the `Astra DB` terminal

    ![Astra DB Terminal](/images/terminal-astra-db.png)

1. That terminal will be asking for the organization token you copied to clipboard. Paste and press 'enter'.

    The initialization will complete with access to `cqlsh`.

    ```bash
    ...

    ✔ Please paste the Database Admin Token here
    ```

    Once validated a cqlsh session will open automatically

    ```log
    Credentials set up, checking database
    Looking for camp-constellation
        camp-constellation: Current status is ACTIVE
            ... status is ACTIVE
        existing camp-constellation database found.
    Looking for crud_data keyspace
        keyspace crud_data already exists
    Setting up secure bundle
    .cassandra Directory created successfully!

    Deleted file: cqlshrc
    [OK] - Database ID is 40e2d3c8-ffb2-46c6-b6bc-283d7bb3e547
    [OK] - Database REGION is us-central1
    [OK] - Database TOKEN is AstraCS:WoCEFZzskLpWyUiPBzwulGxO:1bf505ed13f23eaa9d39b461142308612be9a01f80765f90b0dcca793f47058d
    Picked up JAVA_TOOL_OPTIONS:  -Xmx3435m
    [OK] - Secure Connect Bundle downloaded
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
    system_virtual_schema  system_views  datastax_sla      
    system_schema          system        data_endpoint_auth
    system_auth            crud_data     system_traces  
    ```

1. Looking to the left navigation, open the '[apache-pulsar-2.9.2/conf/client.conf](/workspace/advanced-cdc-for-astra/apache-pulsar-2.9.2/conf/client.conf)' file

    ![Pulsar Client Conf Original](/images/client-conf-original.png)

1. Replace the entire contents of the file with the contents from the downloaded client.conf and close the file

    ![Pulsar Client Conf Contents](/images/client-conf-contents.png)

1. Open the terminal session named 'Astra Streaming', from the lower right corner

    ![Astra Streaming Terminal](/images/terminal-astra-streaming.png)

1. Validate the connection with Astra Streaming broker by running the following command in the terminal

    ```bash
    ./bin/pulsar-admin namespaces list camp-constellation
    ```

    Output:

    ```logs
    "camp-constellation/default"
    ```

## Summary

Your GitPod environment has configured the Pulsar binaries to interact with your Astra Streaming Pulsar broker, you have a working cqlsh session with your Astra DB, and you have a publicly accessible, configured ELK stack.

With these services you are now ready for the next module, as well as the upcoming challenge. Click the link at the top of the page to continue.
