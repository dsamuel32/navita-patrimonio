# Teste Navita Controle de Patrimônio

Tecnologias Utilizadas
- Spring Boot
- Spring Security
- Spring Security OAuth
- Spring MVC
- Spring Data JPA
- Liquibase
- Swagger
- Postgresql
- Docker

## Instruções

#### Executar o comando para criar o container do Postgresql

```
docker-compose up
```

#### Executar o build do projeto

```
mvn clean package
```

- Endpoint de autentição
```
curl -X POST -vu testjwtclientid:XY7kmzoNzl100  http://localhost:8080/patrimonio/api/oauth/token  -H "Accept: application/json" -d grant_type=password -d username=teste@teste.com -d password=123
```

- Use o access_token para se autenticar
```
{
	"access_token":"eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOlsidGVzdGp3dHJlc291cmNlaWQiXSwidXNlcl9uYW1lIjoidGVzdGUiLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXSwiZXhwIjoxNTI4Mjg4MzczLCJhdXRob3JpdGllcyI6WyJBRE0iXSwianRpIjoiMDEyZGNhMDMtMGYxNy00YzE4LWE1Y2QtMTU5MmFlNWRmNGNkIiwiY2xpZW50X2lkIjoidGVzdGp3dGNsaWVudGlkIn0.gAKUbBpHw_kkntUE2D2IB_De_usekG8KbTkc-7MHNKE",
	"token_type":"bearer",
	"refresh_token": "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOlsidGVzdGp3dHJlc291cmNlaWQiXSwidXNlcl9uYW1lIjoidGVzdGUiLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXSwiYXRpIjoiYTVjZGYzMmQtOTY4YS00YjI5LWI3ODctZjlmNTNkZTJjY2IxIiwiZXhwIjoxNTMwOTE4OTc3LCJhdXRob3JpdGllcyI6WyJBRE0iXSwianRpIjoiMDA0NTJlNzItYjBiMi00M2U0LWJjZTAtOGQ1OGUwZTU5OTg2IiwiY2xpZW50X2lkIjoidGVzdGp3dGNsaWVudGlkIn0.DFX0xw-Wa_mgRnsHuFikIrQugyipc4HAMhrrWVySJEc",
	"expires_in":43199,
	"scope":"read write",
	"jti":"012dca03-0f17-4c18-a5cd-1592ae5df4cd"
}
```
