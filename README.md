# code_examples

## Некоторые примеры кода из закрытых репозиториев

* ```checkpoint.h``` - набор макросов для замера времени работы программы от этапа к этапу.
* ```rfc-pserial.lua``` - работа с последовательным портом с таймаутом ожидания данных, как граница сообщения и использованием полинга.
* ```rfcsubmit.sh``` - shell скрипт для отправки JSON сообщений в программу управления Wi-Fi антеной. Далее JSON преобразуется в protobuf и отправляется через последовательный порт в устройство.
* ```stm32.c``` - CRC32 алгоритм как в котнроллере STM32, но с поправками т.к. у заказчика была обнаружена ошибка и пришлось подстраиваться под их реализацию.

kopmlat-restrep/
    KomplatRestRepositoryConfiguration.java
    KomplatRestRepositoryService.java
    banner.txt
    payload/
        ClientLogMessage.java
        CounterInfo.java
        PayInfo.java
        PayInfoIA.java
        PayInfoKey.java
        PayItem.java
    turn/
        TurnEntity.java
        TurnEntityPK.java
        TurnEntityPKConverter.java
        TurnEntityRepository.java