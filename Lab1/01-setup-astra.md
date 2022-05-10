[Next Module >>](/Lab1/02-start-gitpod.md)

---

## Astra Setup

This module will get your Astra account ready for the next mododules by creating the necessary assets and download the needed configurations.

1. Log in to your Astra account

    [![](https://dabuttonfactory.com/button.png?t=+Connect+to+Astra&f=Open+Sans-Bold&ts=12&tc=fff&hp=23&vp=16&c=11&bgt=gradient&bgc=0b5394&ebgc=073763)](https://astra.dev/4-6)

    [How to create an Astra Account](https://awesome-astra.github.io/docs/pages/astra/create-account/)

1. Create a new serverless database

    Name: `camp-constellation`

    Keyspace: `crud_data`

    Region: Google Cloud >> North America >> us-central1

    ![Create Database](/images/create-database.png)

    > Wait for the database's "Status" to be "Active" in your Dashboard

1. Create a new streaming tenant

    Name: `<NAME>-camp-const`

    Region: Google Cloud >> uscentral1

    ![Create Stream](/images/create-stream.png)

    > The tenant will be ready immediatly

1. Download the tenant's client.conf for later use

    ![Download client.conf](/images/download-client-conf.png)

1. Create a new organization token

    Role: `Organization Administrator`

    Copy the *Token* to clipboard

    ![Create Organization Token](/images/create-organization-token.png)

    ![Copy Token](/images/copy-token.png)

    [How to create an Astra Token](https://awesome-astra.github.io/docs/pages/astra/create-token/#c-procedure)

## Summary

You're ready to start the next module. Click the link at the top of the page.
