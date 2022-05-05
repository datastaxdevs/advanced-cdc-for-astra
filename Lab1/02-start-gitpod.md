[Next Module >>](/Lab1/03-enable-cdc-and-consumer.md)

---

## GitPod Environment

In this module you will be configuring all the needed services and cli connections. This will be needed in the next module as well as the challenge.

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

1. Open the terminal session named 'Elasticsearch', from the lower right corner

1. The output should show a license key, select and copy that key to clipboard

1. Open the terminal session named 'Kibana', from the lower right corner

1. Click the link displaied to open the Kibana dashboard. It will ask for the key you previously copied. Paste and submit the key.

1. The two services will be connected and Kibana will redirect to its main dashboard

## Summary

Your GitPod environment has configured the Pulsar binaries to interact with your Astra Streaming Pulsar broker, you have a working cqlsh session with your Astra DB, and you have a publicly accessible, configured ELK stack.

With these services you are now ready for the next module, as well as the upcoming challenge. Click the link at the top of the page to continue.
