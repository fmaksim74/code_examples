/**
 * 
 */
package ru.uralprom.komplat.rest.jpa.turn;

import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

/**
 * @author FedorchenkoMI
 */
public interface TurnEntityRepository extends CrudRepository<TurnEntity, TurnEntityPK>
{

  Optional<TurnEntity> findById( TurnEntityPK id );

  @Query( "SELECT t FROM TurnEntity t WHERE t.id.accountId = :accountId" )
  List<TurnEntity> findByAccountId( @Param( "accountId" ) Integer id );

  @Query( """
          SELECT t
            FROM TurnEntity t
           WHERE t.id.accountId = :accountId
             AND t.id.serviceId = :serviceId
             AND t.id.providerId = :providerId
           ORDER BY t.id.month DESC
          """ )
  @RestResource( rel = "lastById", path = "lastById" )
  List<TurnEntity> findFirstByPartialKey( @Param( "accountId" ) Integer acountId,
      @Param( "serviceId" ) Short serviceId,
      @Param( "providerId" ) Short providerId ); 

}