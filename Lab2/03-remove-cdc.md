[Home](/README.md)

---

## Remove the CDC feature from a table

In this final module you will turn off CDC and observe what effects it (doesn't) have on things.

1. Navigate back to your Astra account, to the CDC tab of your serverless database and disable the CDC feature

    ![Disable CDC](/images/disable-cdc.png)

1. Observe the database, keyspace, and table are unchanged

1. Go to the streaming tenant and observe the topics are still there for use

    ![Disable CDC](/images/astra-list-topics.png)

## Summary

It Simply disables the connection between DB and Streaming. Nothing is lost.

On to the challenge! View the directions [here](/search-index-challenge.md).
