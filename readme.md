# Projeto Livraria Gateway API - Impacta

## Descrição do projeto

- API foi criada utilizando Java 17.
- Utilizando PostgreSql como banco de dados.

## Rotas para execuções locais

- **POST** http://localhost:8080/livraria/api/v1/autor → Cadastra um Autor;
- **POST** http://localhost:8080/livraria/api/v1/autor/{numero}/livro → Cadastra um livro para o Autor
- **GET** http://localhost:8080/livraria/api/v1/autor → Busca todos os Autores
- **GET** http://localhost:8080/livraria/api/v1/autor/{numero} → Busca somente o Autor expecifico
- **GET** http://localhost:8080/livraria/api/v1/autor/{numero}/livro → Busca livros atrelado ao Autor enviado via parâmetro

## Rotas Pós Configuração do Gateway API

- **POST** https://localhost:8443/v1/gateway/livraria/autor
- **POST** https://localhost:8443/v1/gateway/livraria/autor/{numero}/livro
- **GET** https://localhost:8443/v1/gateway/livraria/autor
- **GET** https://localhost:8443/v1/gateway/livraria/autor/{numero}
- **GET** https://localhost:8443/v1/gateway/livraria/autor/{numero}/livro

# Como rodar o projeto?

## Rodar a API

- Na pasta raiz do projeto executar:

```
mvn spring-boot:run
```

## Configurar o Banco de Dados PostgreSQL

- Rodar o seguinte comando para subir o contêiner docker que esta localizado na pasta **/database-docker**:
- Executar o comando abaixo:


```
docker-compose -f ./database-docker/docker-compose.yaml up -d
```


## Subir contêiner do Kong

- Abrir o terminal WSL ou GitBash na pasta raiz do projeto

```
📂 api-gateway-livraria
```

- Rodar o seguinte comando para iniciar e configurar o Kong:

```
./kong/1-start.sh
```

Pronto! A aplicação ja esta pronta para uso na sua forma simples de um CRUD.

---
# Configurações do Gateway

⚠️ **ACESSAR O ARQUIVO → 2-cadastrarServicos.sh ← e ajustar o IP do Servidor que está rodando sua API**

- Comando para identificar configurações de IP (copiar o IPv4)


**Windows**
```
ipconfig
```

**Linux**
```
ip addr show
```

### Iniciando Configurações

- Cadastrando serviço no Kong:

```
./kong/2-cadastrarServicos.sh
```

- Criando sua rota no Gateway:

```
./kong/3-criarRota.sh
```

Pronto, aqui finalizamos a configuração do Gateway, neste momento você pode chamar as aplicações através da URL
cadastrada no Gateway, porem, sem nenhuma autenticação.

### Configurando um API Key

- Para configurar o plugin do API Key:

```
./kong/4-pluginApiKey.sh
```

- Criando um consumidor para o API Key:

OBS: Caso queira, pode trocar o usuário no campo "username" dentro do arquivo → 5-criandoConsumidor.sh ← 
mas lembrando este ajuste é OPCIONAL para o teste.

```
./kong/5-criandoConsumidor.sh
```

- Gerando API Key:

Ao executar o comando abaixo, tenha atenção, sua chave estara no campo "key" (Caso tenha alterado seu usuário
nos passos anteriores, é necessário alterar na URL do arquivo → 6-gerandoApiKey.sh ← também)

```
./kong/6-gerandoApiKey.sh
```

Guarde essa chave pois, será necessário para conseguir utilizar os endpoints!

Uffa 😅 Agora finalizamos as configurações do Gateway e a aplicação ja esta configurada com as rotas e segurança
necessária para sua aplicação.

---

# Usando a aplicação

### Requisições

⚠️⚠️ Caso tenha problemas pra executar os comandos abaixo, pode ser por conta do Certificado SSL que esta bloqueando ⚠️⚠️

Caso queira desabilitar:

```
git config --global http.sslVerify false
```
git config --global http.sslVerify false

Caso queira, deixei na pasta **./postman** a collection com todas as chamadas com base nas configurações padrões feitas.

⚠️ LEMBRANDO que ao realizar as chamadas é necessário trocar o Api Key para o que foi gerado nos passos anteriores. 

E caso queira maior facilidade, deixei os arquivos com as chamadas que podem ser executadas na pasta **./postman/chamadas**

Segue todos os comandos:


**GET Busca autores**
```
./postman/chamadas/GET-autor.sh
```
**GET Busca Autor por número**
```
./postman/chamadas/GET-autorByNumero.sh
```
**GET Busca livros**
```
./postman/chamadas/GET-livro.sh
```
**POST Cadastra autor**
```
./postman/chamadas/POST-autor.sh
```
**POST Cadastra livro**
```
./postman/chamadas/POST-livro.sh
```

Por fim, para finalizar o Kong basta executar:

```
./kong/7-stop.sh
```

