[Next Module >>](/Lab2/03-remove-cdc.md)

---

## CRUD commands

The goal of this module is to observe how CDC for Astra manages data and schema change.You will be using the previously configured GitPod environment to perform basic create, update, and delete commands on a data table and observing how those changes were transformed into broker messages. Then you'll be changing the data table's schema to see how that affects the message structure.

1. Go to the `Astra DB` terminal and run the following command

    ```sql
    insert into crud_data.testing_crud(id, first_name) values (eba95eb2-cd52-11ec-9d64-0242ac120002,'Bob');
    ```

1. Go to the `Astra Streaming` terminal to observe what content was included in the action

    ```logs
    ----- got message -----

    ...

    key:[SGViYTk1ZWIyLWNkNTItMTFlYy05ZDY0LTAyNDJhYzEyMDAwMg==], properties:[], content:{key={id=eba95eb2-cd52-11ec-9d64-0242ac120002}, value={first_name=Bob}}
    ```

1. Go back to the `Astra DB` terminal and run the following command

    ```sql
    update crud_data.testing_crud set first_name = 'Harry' where id=eba95eb2-cd52-11ec-9d64-0242ac120002;
    ```

1. An then to the `Astra Streaming` terminal to see the message content

1. Repeat this for one last command

    ```sql
    delete from crud_data.testing_crud where id=eba95eb2-cd52-11ec-9d64-0242ac120002;
    ```

## Summary

Facts about CDC for Astra:
- When adding a new record into a table that does not have a compatible data type with CDC, the data is dropped. [Here](https://docs.datastax.com/en/astra/docs/astream-cdc.html) is a list of compatible types.
- When updating a record only the updated data is transmitted.
- Sql errors don't register in CDC.
- Delta statements transmit the removed data.

Click the link at the top of the page to continue.
