package core.support;

import java.io.IOException;
import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

/**
 * 1.这个是用来转换日期格式的类。格式是： yyyy-MM-dd(只有年月日)
 */
public class DateSerializer extends JsonSerializer<Date> {

	private static final String DATE_FORMAT = "yyyy-MM-dd";

	public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
		jgen.writeString(DateFormatUtils.format(value, DATE_FORMAT));
	}

}
