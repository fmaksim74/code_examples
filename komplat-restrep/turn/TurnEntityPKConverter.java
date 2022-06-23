/**
 * 
 */
package ru.uralprom.komplat.rest.jpa.turn;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.rest.webmvc.spi.BackendIdConverter;
import org.springframework.stereotype.Component;

/**
 * {@summary This class provides serialization and deserialization for
 * TurnEntity primary key class.} {@link TurnEntity}
 * 
 * @author FedorchenkoMI
 *
 */
@Component
public class TurnEntityPKConverter implements BackendIdConverter, Converter<String, TurnEntityPK> {

	private final char delimiterChar = ',';
	private final String errorMessage = "Id for TurnEntity must consist of integer account id, short service id, short provider id and date values of existing entities separated by ','!";

	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

	@Override
	public boolean supports(Class<?> delimiter) {
		return TurnEntity.class.isAssignableFrom(delimiter);
	}

	@Override
	public Serializable fromRequestId(String id, Class<?> entityType) {

		String[] parts = id.split(String.valueOf(delimiterChar), 4);

		if (parts != null && parts.length == 4) {
			
			Integer accountId = Integer.parseInt(parts[0]);
			Short serviceId = Short.parseShort(parts[1]);
			Short providerId = Short.parseShort(parts[2]);

			Date parsed;
			try {
				parsed = sdf.parse(parts[3]);
			} catch (ParseException e) {
				throw new IllegalArgumentException(errorMessage);
			}
			
			return new TurnEntityPK(accountId, serviceId, providerId, parsed);
			
		} else {
			throw new IllegalArgumentException(errorMessage);
		}
	}

	@Override
	public TurnEntityPK convert(String source) {
		
		return (TurnEntityPK)fromRequestId(source, TurnEntityPK.class);
		
	}
	
	@Override
	public String toRequestId(Serializable id, Class<?> entityType) {
		
		return new StringBuilder()
				.append(((TurnEntityPK) id).getAccountId())
				.append(this.delimiterChar)
				.append(((TurnEntityPK) id).getServiceId())
				.append(this.delimiterChar)
				.append(((TurnEntityPK) id).getProviderId())
				.append(this.delimiterChar)
				.append(sdf.format(((TurnEntityPK) id).getMonth()))
				.toString();
		
	}

}
