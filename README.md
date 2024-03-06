### Hibernate
Посмотреть всех пользователей:
`/AppAston_war_exploded/api/getAllUsers`
>GET запрос дает список пользователей.

Регистрация нового пользователя:
`/AppAston_war_exploded/api/signUpUser`
>POST с запрос с телом JSON
Ex:
{
    "username": "Mks",
    "password": "1"
}
Вернет сообщение в случае успеха или неудачи.

Вход пользователя:
`/AppAston_war_exploded/api/signInUser`
>POST запрос с телом JSON
Ex:
{
    "username": "Mks",
    "password": "1"
}
В случае успеха или неудачи выдаст сообщение.

Смена пароля:
`/AppAston_war_exploded/api/changePassword`
>POST запрос с телом JSON
Ex:
{
    "username": "Mks",
    "oldPassword": "1",
    "newPassword": "12"
}
В случае успеха скажет на какой пароль поменял
