# Advanced CDC for Astra Workshop

A workshop for learning advanced solutions using CDC for Astra

# Prerequisites

You will need expanded cloud access in Astra so your DB and streaming tenant can be in the same region. Request access [here](https://forms.gle/9y5gYrjeh8Qz9Y457).

# Labs/Modules

Lab 1: Creating the environment in Astra (5 minutes) [get started](/Lab1/01-setup-astra.md)
- Login to Astra account
- Create a new database in GCP/us-central1
- Create a new streaming tenant in GCP/us-central1
- Create a new token (or use existing)
- Open GitPod Pulsar environment
- Copy/paste broker.conf into environment
- Set up cqlsh
- List topics to confirm connection
- List databases to confirm connection
- Enable CDC for Astra on both tables
- Start a long running consumer on the data topic of the table

Lab 2: CRUD actions on a table and its schema (5 minutes)
- Using cql console, execute multiple commands to insert, update, and delete records
- Observe the messages in GitPod terminal showing a confirmation of all these actions
    - !notice one of data columns were dropped because of an invalid data type
- Add a new table column
- Using cql console, add another insert command using the data column
- Observe the message in GitPod terminal
    - !CDC automatically adapted the schema to the table schema change
- Stop the consumer
- Disable CDC on table

Challenge: Creating a site search index with CDC (30 minutes) [get started](/search-index-challenge.md)
- Using cql console, create schema of 2 data tables
- Enable CDC for Astra on both tables (use existing streaming tenant)
- Create a new streaming topic to hold tagged posts
- Load the Java function that will filter by tag and add to tagged posts topic
- Create a new topic to hold indexable posts
- Load the Python function that will consume tagged posts, parse post, decide if it's worthy, and add to indexable posts topic
- Create a sink to watch indexable posts topic and forward to GitPod Elastic search
- Using provided script, load 50 posts on a 1 second interval
- Open Kibana in the GitPod browser and watch the search index build
- Add a new tag to tags table
- Using provided script, load 500 posts on a 0.5 second interval
- Watch new indexes build

![Challenge Diagram](/images/challenge-diagram.png)
