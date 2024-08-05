![Java Icon](https://img.icons8.com/?size=100&id=13679&format=png&color=000000)
# ArticOS

### API RESTful para criação de artigos utilizando Java.

Projeto realizado para um desafio da DIO.

#### Funcionalidades:
O projeto funciona com requisições simples de GET, POST e DELETE. Podendo adicionar um usuário e um artigo, também podendo remover cada um deles, buscar todos ou buscar por ID.

#

**Endpoints:**


Para **usuários**:

    /user/getAll - GET 🟢
    /user/get/{Id} - GET 🟢
    /user/add - POST 🟡
    /user/delete/{Id} - DELETE 🔴

Para **artigos**:

    /article/getAll - GET 🟢
    /article/get/{Id} - GET 🟢
    /article/add - POST 🟡
    /article/delete/{Id} - DELETE 🔴


###### ⛔Regras:
    Usuários só podem ser aceitos se tiverem o username único.
    Não pode haver 2 artigos com o mesmo nome, ou sem um usuário.
#
**OBS:** Projeto feito apenas para o desafio, por isso não está tão complexo.
