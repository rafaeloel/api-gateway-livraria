curl --location 'localhost:8001/services/raiz/routes' \
--header 'Content-Type: application/json' \
--data '{
    "paths": ["/v1/gateway/livraria"]
}'