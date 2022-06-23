package ru.uralprom.komplat.rest.jpa.turn;

import javax.persistence.*;

import java.math.BigDecimal;

import org.springframework.data.domain.Persistable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ru.uralprom.komplat.rest.jpa.account.AccountEntity;
import ru.uralprom.komplat.rest.jpa.provider.ProviderEntity;
import ru.uralprom.komplat.rest.jpa.service.ServiceEntity;

/**
 * @author FedorchenkoMI
 *         The persistent class for the "Turn" database table.
 */
@Entity
@Table( name = "\"Turn\"" )
@NamedQuery( name = "TurnEntity.findAll", query = "SELECT t FROM TurnEntity t" )
public class TurnEntity implements Persistable<TurnEntityPK>
{

  @EmbeddedId
  private TurnEntityPK Id;

  @MapsId( value = "accountId" )
  @ManyToOne( targetEntity = AccountEntity.class, optional = true, fetch = FetchType.LAZY )
  @JoinColumn( name = "\"AccountId\"", referencedColumnName = "\"AccountId\"" )
  @JsonIgnore
  private AccountEntity account;

  public AccountEntity getAccount()
  {
    return this.account;
  }

  public void setAccountId( AccountEntity account )
  {
    this.account = account;
  }

  @MapsId( value = "serviceId" )
  @ManyToOne( targetEntity = ServiceEntity.class, optional = true, fetch = FetchType.LAZY )
  @JoinColumn( name = "\"ServiceId\"", referencedColumnName = "\"ServiceId\"" )
  @JsonIgnore
  private ServiceEntity service;

  public ServiceEntity getService()
  {
    return this.service;
  }

  public void setService( ServiceEntity service )
  {
    this.service = service;
  }

  @MapsId( value = "providerId" )
  @ManyToOne( targetEntity = ProviderEntity.class, optional = true, fetch = FetchType.LAZY )
  @JoinColumn( name = "\"ProviderId\"", referencedColumnName = "\"ProviderId\"" )
  @JsonIgnore
  private ProviderEntity provider;

  public ProviderEntity getProvider()
  {
    return this.provider;
  }

  public void setProvider( ProviderEntity provider )
  {
    this.provider = provider;
  }

  @Column( name = "\"Charge\"", nullable = false, precision = 19, scale = 4 )
  private BigDecimal charge;

  @Column( name = "\"Credit\"", precision = 19, scale = 4 )
  private BigDecimal credit;

  @Column( name = "\"DbtMon\"" )
  private Short dbtMon;

  @Column( name = "\"Debit\"", precision = 19, scale = 4 )
  private BigDecimal debit;

  @Column( name = "\"Maket\"", nullable = false, precision = 19, scale = 4 )
  private BigDecimal maket;

  @Column( name = "\"Pay\"", nullable = false, precision = 19, scale = 4 )
  private BigDecimal pay;

  @Column( name = "\"Repay\"", nullable = false, precision = 19, scale = 4 )
  private BigDecimal repay;

  @Column( name = "\"SaldoIn\"", nullable = false, precision = 19, scale = 4 )
  private BigDecimal saldoIn;

  @Column( name = "\"SaldoOut\"", nullable = false, precision = 19, scale = 4 )
  private BigDecimal saldoOut;

  public TurnEntity()
  {
  }

  public TurnEntityPK getId()
  {
    return this.Id;
  }

  public void setId( TurnEntityPK id )
  {
    this.Id = id;
  }

  @Override
  public boolean isNew()
  {
    return this.Id == null;
  }

  public BigDecimal getCharge()
  {
    return this.charge;
  }

  public void setCharge( BigDecimal charge )
  {
    this.charge = charge;
  }

  public BigDecimal getCredit()
  {
    return this.credit;
  }

  public void setCredit( BigDecimal credit )
  {
    this.credit = credit;
  }

  public Short getDbtMon()
  {
    return this.dbtMon;
  }

  public void setDbtMon( Short dbtMon )
  {
    this.dbtMon = dbtMon;
  }

  public BigDecimal getDebit()
  {
    return this.debit;
  }

  public void setDebit( BigDecimal debit )
  {
    this.debit = debit;
  }

  public BigDecimal getMaket()
  {
    return this.maket;
  }

  public void setMaket( BigDecimal maket )
  {
    this.maket = maket;
  }

  public BigDecimal getPay()
  {
    return this.pay;
  }

  public void setPay( BigDecimal pay )
  {
    this.pay = pay;
  }

  public BigDecimal getRepay()
  {
    return this.repay;
  }

  public void setRepay( BigDecimal repay )
  {
    this.repay = repay;
  }

  public BigDecimal getSaldoIn()
  {
    return this.saldoIn;
  }

  public void setSaldoIn( BigDecimal saldoIn )
  {
    this.saldoIn = saldoIn;
  }

  public BigDecimal getSaldoOut()
  {
    return this.saldoOut;
  }

  public void setSaldoOut( BigDecimal saldoOut )
  {
    this.saldoOut = saldoOut;
  }

}