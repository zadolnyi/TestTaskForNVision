# TestTaskForNVision
тестовое задание: REST-сервис (Spring Boot, PostgreSql)

REST-сервис, обрабатывающий два запроса: 
1) POST-запрос принимает XML, сохраняет данные в БД и выдаёт JSON аккумулирующий поступившие данные. 
2) По GET-запросу выдаётся JSON – статистика – запрос к БД с фильтрами (в параметрах GET-запроса).
Данные хранятся в PostgreSql.