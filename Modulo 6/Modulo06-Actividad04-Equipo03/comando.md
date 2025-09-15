```sh
newman run .\Api-Students.postman_collection.json --env-var base_url=http://localhost:9090 --env-var api_prefix=/api --env-var students_base='{{api_prefix}}/students' --env-var accept=application/json --env-var content_type=application/json


```