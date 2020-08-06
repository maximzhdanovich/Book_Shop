# Book_Shop
Запускать на 8 версии java 

Проект представляет собой книжный магазин.
Зарегестрированные пользователи мотуг просмотривать имеющиеся книги и добавлять их себе в корзину, также они могут изменять свои данные и управлять корзиной.
Администратор может добавлять и изменять книги и авторов, может изменять роли пользователей.
В проекте есть 2 языка русский и английский.

Для запуска проекта и solr необходимо
1) Прописать свои данные для подключение к бд
2) Запустить файл src/main/resources/db/ddl/database.sql для создания базы 
(Так же есть файл для заполнения базы src/main/resources/db/insert/insert.sql)
3) Установить Solr версии 8.2.0 и выше
4) Запустить Solr из папки bin командой "solr start"
5) Создать Core при помощи команды "solr create -c shop"
6) В полученой папке \solr-8.2.0\server\solr\shop\conf в файл **solrconfig.xml** скопировать данные из solrconfig(copy).xml
7) В папку \solr-8.2.0\server\solr\shop\conf скопировать файл shop-data-config.xml
8) В папке \solr-8.2.0\server\solr\shop\conf в файл managed-schema скопировать данные из managed-schema(copy)
9) Перезапусить solr.