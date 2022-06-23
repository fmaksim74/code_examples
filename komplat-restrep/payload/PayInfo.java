/**
 * 
 */
package ru.uralprom.komplat.rest.payload;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author FedorchenkoMI
 *
 */
public class PayInfo {
    /**
     * {@summary Идентификатор начисления клиента}
     */
    public UUID id;

    /**
     * {@summary Полный адрес плательщика}
     */
    public String address;
    
    /**
     * {@summary Наименование поставщика услуг лицевого счета}
     */
    public String provider;

    /**
     * {@summary Номер лицевого счета}
     */
    public String account;
    
    /**
     * {@summary Дата открытия лицевого счета}
     */
    public Date dateOpen;

    /**
     * {@summary Площадь помещения}
     */
    public BigDecimal square;

    /**
     * {@summary Кол-во проживающих}
     */
    public Integer residents;

    /**
     * {@summary Год периода платежа}
     */
    public Integer year;

    /**
     * {@summary Месяц периода платежа}
     */
    public Integer month;

    /**
     * {@summary Город проживающего}
     */
    public String city;

    /**
     * {@summary Кладр код}
     */
    public String kladrCode;

    /**
     * {@summary Адрес: Улица}
     */
    public String street;

    /**
     * {@summary Адрес: Дом}
     */
    public String house;

    /**
     * {@summary Адрес. Квартира}
     */
    public String flat;

    /**
     * {@summary ФИО ответственного плательщика}
     */
    public String ownerName;

    /**
     * {@summary e-mail клиента для получения фискальных данных}
     */
    public String eMail;

    /**
     * {@summary Список услуг}
     */
    public List<PayItem> payItems;

    /**
     * {@summary Имя клиента для платформы эквайринга}
     */
    public String acqMerchantName;

    /**
     * {@summary Ссылка для скачивания последней квитанции}
     */
    public String url;
    /**
     * {@summary Cookie для авторизации на lk.komplat.ru}
     */
    public String cookie;
    
    /**
     *  Данные по счётчикам
     */
    public List<CounterInfo> counters;
    
}
