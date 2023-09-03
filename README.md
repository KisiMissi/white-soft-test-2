# WhiteSoft test
Решение к тестовому заданию WhiteSoft

## Задание<br/>
В одной крупной компании произошел сбой! Злоумышленники нашли уязвимость и испортили сообщения пользователей! К счастью, аналитики смогли обнаружить, какие изменения были произведены. Но неизвестно для каких сообщений! Ваша задача написать консольное приложение, которое восстановит исходные сообщения. Аналитики также смогли выяснить, что измененная однажды часть сообщения больше не модицифировалась!<br/>

## Входные данные<br/>
- replacment.json. Файл лежит в **resources** проекта.<br/>
- data.json. Файл c изменненым текстом получается с указанного [API](https://raw.githubusercontent.com/thewhitesoft/student-2022-assignment/main/data.json) с использованием HTTP GET запроса<br/>

## Выходные данные<br/>
- result.json. Содержит исправленные сообщения и содержать массив строк. Создается в **resources** проекта.

## Запуск
Проект можно запустить сразу после скачивания и изменений не требует.
### Опционально
- Изменить расположение файлов **data.json** и **replacement.json** можно через константы **DATA_URL** и **REPLACEMETN_FILE_PATH** в классе **JsonReader**.</br></br>
- Файл **data.json** можно считывать из **resources**.</br>
Для этого в классе **MessageProofreader** нужно заменить **JsonReader.readUserMessagesFromAPI()** на **JsonReader.readUserMessanges()**.
```
public List<String> getCorrectedMessages() {
        List<OriginalMessage> originalMessagesList =
                deleteDuplicatedOriginalMessages(JsonReader.readOriginalMessages());
        return retrieveModifiedMessages(JsonReader.readUserMessagesFromAPI(), originalMessagesList);
    }

```
