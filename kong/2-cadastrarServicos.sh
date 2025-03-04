curl --location 'localhost:8001/services' \
--header 'Content-Type: application/json' \
--data '{
    "name":"livraria",
    "url":"http://192.168.100.155:8080/livraria/api/v1"
}'