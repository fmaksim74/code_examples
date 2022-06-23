/**
 * 
 */
package ru.uralprom.komplat.rest.payload;

/**
 * @author FedorchenkoMI
 *
 */
public class PayItem {
  /**
   * {@summary Идентификатор услуги}
   */
  public int itemId;

  /**
   * {@summary Наименование услуги}
   */
  public String itemName;

  /**
   * {@summary Начисление по услуге}
   */
  public int accrual;

  /**
   * {@summary Задолженность по услуге}
   */
  public int debt;

  /**
   * {@summary К оплате за услугу}
   */
  public int amount;

  /**
   * {@summary Перерасчёт}
   */
  public int recalc;

  /**
   * {@summary Оплачено за услугу}
   */
  public int paid;
  
  /**
   * {@summary Оплачено по данным базы Internet Acquiring}
   */
  public int paidIA;
  
  /**
   * {@summary Внутренний код услуги}
   */
  public String srvCode;

  /**
   * {@summary Внутреннее наименование услуги}
   */
  public String srvName;

}
