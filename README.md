# Edit-image

Esse é um projeto de um app feito para edição de imagens.
## 🚀 Começando

Essas instruções permitirão que você obtenha uma cópia do projeto em operação na sua máquina local para fins de desenvolvimento e teste.

### 🔧 Como usar

Para usar é preciso desses passos:

- Primeiro crie um banco de dados MySQL.
- Depois crie um aquivo chamado configDb.properties e coloque as configurações abaixo e seus valores.
  ```
  spring.datasource.url= Url do banco de dados
  spring.datasource.username= Nome do banco de dados
  spring.datasource.password= Senha do banco de dados 
  api.security.token.secret= Secret para encriptografar suas senhas
  ```
- Faça a configuração no cloudinary com suas api keys
- Importe as rotas do insomnia <a href="https://github.com/matheus-valentim/edit-image/blob/master/Insomnia_2024-11-11.json">Clicando Aqui.</a>

Agora o app esta pronto para o uso!

## 📄 Licença

Este projeto está sob a licença (sua licença) - veja o arquivo [LICENSE.md](https://github.com/matheus-valentim/edit-image/blob/master/LICENSE) para detalhes.


---
