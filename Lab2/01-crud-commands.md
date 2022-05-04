[Next Module >>](/Lab2/03-remove-cdc.md)

---

## CRUD commands

The goal of this module is to observe how CDC for Astra manages data and schema change.You will be using the previously configured GitPod environment to perform basic create, update, and delete commands on a data table and observing how those changes were transformed into broker messages. Then you'll be changing the data table's schema to see how that affects the message structure.

1. Go to the `cqlsh` terminal and run the following command

    ```sql
    insert into testing_crud('first_name','specialties') values ('asdf', {'bird', 'cat', 'dog', 'lizard','hamster','snake'})
    update testing_crud set 'specialties' = {'red','green','blue'} where 'first_name' = 'asdf'
    delete from testing_crud where 'first_name' = '111'
    delete from testing_crud where 'first_name' = 'asdf'
    ```

1. Go to the `pulsar` terminal to observe the new CDC messages

    ```logs
    
    ...
    
    ----- got message -----
    asdasdasd
    ----- got message -----
    asdasdasd
    ----- got message -----
    asdasdasd
    ----- got message -----
    asdasdasd

    ```

## Summary

When adding a new record into a table that does not have a compatible data type with CDC, the data is dropped.
When updating a record only the updated data is transmitted.
Sql errors don't register in CDC.
Delata statements transmit the removed data.

Click the link at the top of the page to continue.