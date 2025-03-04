curl --location 'https://localhost:8443/v1/gateway/livraria/autor/35767/livro' \
--header 'Content-Type: application/json' \
--header 'apikey: ••••••' \
--data '{
    "titulo": "Memórias Póstumas de Brás Cubas",
    "edicao": "1ª Edição",
    "isbn": "123456",
    "categoria": "Romance"
}'