# cqrs-micro

Pre-requisites to run this project:

https://developer.axoniq.io/download

Download Axon Server ZIP or Docker instance, Once it is up and running 
Start the projects api and satellite using the following commands:

Terminal 1:
cd api/
mvn spring-boot:run

Terminal 2:
cd satellite/
mvn spring-boot:run

Once both servers are up and running, check the axon dashboard at localhost:8024, 
If you see image in the overview just like the image given in repository root, then it is running properly.

Then hit this api using curl:

curl --location 'localhost:8081/messages/send' \
--header 'Content-Type: application/json' \
--data '[
    {
        "id": 1,
        "value": 800
    },
    {
        "id": 2,
        "value": 800
    }
]'
