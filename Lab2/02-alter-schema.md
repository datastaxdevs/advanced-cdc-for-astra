[Next Module >>](/Lab2/03-remove-cdc.md)

---

## Alter Schema

In this module we will alter the table schema and add new records. This will give you the opportunity to see how CDC messaging adapts.

1. Navigate to the "Astra DB" tab and alter the table schema

    ```sql
    use crud_data;

    ALTER TABLE testing_crud (
    id          uuid,
    first_name  text,
    specialties set<text>,
    new_column  blob,
    PRIMARY KEY ((id))
    );
    ```

    > Notice the new column named "new_column" with a data type of "blob". This will become a "bytes" data type in Streaming.

1. Add a new record to the table

    ```sql
    insert into testing_crud('first_name','specialties','new_column') values ('David', {'car', 'truck', 'van'}, bigintAsBlob(3))
    ```

1. Go to the `Astra Streaming` terminal to observe the CDC message with added column

    ```logs
    
    ...
    
    ----- got message -----
    asdasdasd
    ```

## Summary

CDC for Astra adapts with your data schmea. When the schmea was updated, behind the scenes a new Streaming Avro schema was created, and the topic was associated correctly.
