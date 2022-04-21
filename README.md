
Урок-1
1. Установить сервер приложений TomCat или Wildfly (либо любой другой по выбору);
2. Создать и запустить новый проект по инструкции из данной методички;
3. Создайте класс Product с полями (id, title, cost);
4. Реализуйте сервлет выводящий на страницу список из 10 продуктов (создаете продукты в момент обработки запроса).
5. Реализуйте страницу для каждого отдельного продукта. Поле c идентификатором продукта должно быть ссылкой на эту страницу продукта.


Урок-2
1. Есть класс Product (id, название, цена). Товары хранятся в бине ProductRepository, в виде List<Product>, при старте в него нужно добавить 5 любых товаров.
2. ProductRepository позволяет получить весь список или один товар по id. Создаем бин Cart, в который можно добавлять и удалять товары по id.
3. Написать консольное приложение, позволяющее управлять корзиной.
4. При каждом запросе корзины из контекста, должна создаваться новая корзина.

Урок-3
1. Разобраться с примером проекта на Spring MVC;
2. Создать класс Товар (Product), с полями id, title, cost;
3. Товары необходимо хранить в репозитории (класс, в котором в виде List<Product> хранятся товары). Репозиторий должен уметь выдавать список всех товаров и      товар по id;
4. Сделать форму для добавления товара в репозиторий и логику работы этой формы;
5. Сделать страницу, на которой отображаются все товары из репозитория.
6. По инструкции преподавателя добавить функцию редактирования товара или пользователя

Урок-4
1.Перенести функциональность, реализованную на прошлом занятии, на платформу Spring Boot.
2.Для сущности продукта добавить валидацию цены по диапазону значений, от 0 до 100 000
3.Добавить возможность удаления продукта

Урок-5
1. Создайте сущность Product (Long id, String title, int price) и таблицу в базе данных для хранения объектов этой сущности;
2. Создайте класс ProductDao и реализуйте в нем логику выполнения CRUD-операций над сущностью Product (Product findById(Long id), List<Product> findAll(), void deleteById(Long id), Product saveOrUpdate(Product product));

Урок-6
1. В базе данных необходимо реализовать возможность хранить информацию о покупателях (id, имя) и товарах (id, название, стоимость). У каждого покупателя свой набор купленных товаров;
2. Для обеих сущностей создаете Dao классы. Работу с SessionFactory выносите во вспомогательный класс;
3. * Создаете сервис, позволяющий по id покупателя узнать список купленных им товаров, и по id товара узнавать список покупателей этого товара;
