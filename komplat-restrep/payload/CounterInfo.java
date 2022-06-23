/**
 * 
 */
package ru.uralprom.komplat.rest.payload;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author FedorchenkoMI
 *
 */
public class CounterInfo {
  
  /**
   * Идентификатор счётчика в базе
   */
  public Long id;
  
  /**
   * Номер счётчика
   */
  public String numb;
  
  /**
   * Наименование счётчика
   */
  public String name;
  
  /**
   * Дата следующей поверки
   */
  public Date checkDate;

  /**
   * Последнее показание 
   */
  public BigDecimal value;
  
  /**
   * URL Для обновления показаний
   */
  public String url;
  
}
