1. Прочитать файл sms.json
2. Десериализовать файл в Java class
3. Создать новую структуру: список из полей 
   <chat_sessions.chat_identifier> -
   <chat_sessions.members.last> - 
   <chat_sessions.messages.belong_number> -
   <chat_sessions.messages.send_date> -
   <chat_sessions.messages.text> 
   с группировкой по полю <chat_sessions.messages.belong_number> 
   и сортировкой от более старых сообщений к более новым
4. Данные дублироваться не должны (файл должен получиться как можно меньше)
5. Сериализовать полученные данные и записать их в файл (текстовой или бинарный)
6. Десериализовать полученные данный и вывести результат на консоль
использовать текстовой формат: json, xml, yml (любой фреймворк)