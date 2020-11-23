package br.com.sga.binder;

import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

/**
 * @author sga
 *
 */
public class LenientDateParser extends PropertyEditorSupport {
//
//	private static final List<String> formats = new ArrayList<String>();
//
//	private String outputFormat;
//
//	static {
//		formats.add("yyyy-MM-dd'T'HH:mm");
//		formats.add("dd-MM-yyyy HH:ss");
//		formats.add("dd-MM-yyyy HH:mm");
//		formats.add("dd-MM-yyyy HH:mm:ss");
//		formats.add("dd/MM/yyyy HH:ss");
//		formats.add("dd/MM/yyyy HH:mm");
//		formats.add("dd/MM/yyyy HH:mm:ss");
//		formats.add("dd-MM-yyyy");
//		formats.add("dd/MM/yyyy");
//		formats.add("dd MMM yyyy");
//		formats.add("MMM-yyyy HH:ss");
//		formats.add("MMM-yyyy HH:mm:ss");
//		formats.add("MMM-yyyy");
//		formats.add("MMM yyyy");
//	}
//
//	public LenientDateParser(String outputFormat) {
//		this.outputFormat = outputFormat;
//	}
//
//	@Override
//	public void setAsText(String text) throws IllegalArgumentException {
//		if (StringUtils.isEmpty(text))
//			return;
//		Date dt = null;
//		for (String format : formats) {
//
//			try {
//
//
//				dt = new SimpleDateFormat(format).parse(text);
//
//				break;
//
//			} catch (Exception e) {
//
//			}
//		}
//		if (dt != null)
//			setValue(dt);
//	}
//	
//	@Override
//	public String getAsText() {
//		Date date = (Date) getValue();
//
//		if (date == null)
//			return "";
//		
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(outputFormat);
//
//		return simpleDateFormat.format(date);
//	}
	
	private final DateFormat dateFormat;

	private final boolean allowEmpty;

	private final int exactDateLength;


	/**
	 * Create a new CustomDateEditor instance, using the given DateFormat
	 * for parsing and rendering.
	 * <p>The "allowEmpty" parameter states if an empty String should
	 * be allowed for parsing, i.e. get interpreted as null value.
	 * Otherwise, an IllegalArgumentException gets thrown in that case.
	 * @param dateFormat DateFormat to use for parsing and rendering
	 * @param allowEmpty if empty strings should be allowed
	 */
	public LenientDateParser(DateFormat dateFormat, boolean allowEmpty) {
		this.dateFormat = dateFormat;
		this.allowEmpty = allowEmpty;
		this.exactDateLength = -1;
	}

	/**
	 * Create a new CustomDateEditor instance, using the given DateFormat
	 * for parsing and rendering.
	 * <p>The "allowEmpty" parameter states if an empty String should
	 * be allowed for parsing, i.e. get interpreted as null value.
	 * Otherwise, an IllegalArgumentException gets thrown in that case.
	 * <p>The "exactDateLength" parameter states that IllegalArgumentException gets
	 * thrown if the String does not exactly match the length specified. This is useful
	 * because SimpleDateFormat does not enforce strict parsing of the year part,
	 * not even with {@code setLenient(false)}. Without an "exactDateLength"
	 * specified, the "01/01/05" would get parsed to "01/01/0005". However, even
	 * with an "exactDateLength" specified, prepended zeros in the day or month
	 * part may still allow for a shorter year part, so consider this as just
	 * one more assertion that gets you closer to the intended date format.
	 * @param dateFormat DateFormat to use for parsing and rendering
	 * @param allowEmpty if empty strings should be allowed
	 * @param exactDateLength the exact expected length of the date String
	 */
	public LenientDateParser(DateFormat dateFormat, boolean allowEmpty, int exactDateLength) {
		this.dateFormat = dateFormat;
		this.allowEmpty = allowEmpty;
		this.exactDateLength = exactDateLength;
	}


	/**
	 * Parse the Date from the given text, using the specified DateFormat.
	 */
	@Override
	public void setAsText(@Nullable String text) throws IllegalArgumentException {
		if (this.allowEmpty && !StringUtils.hasText(text)) {
			// Treat empty String as null value.
			setValue(null);
		}
		else if (text != null && this.exactDateLength >= 0 && text.length() != this.exactDateLength) {
			throw new IllegalArgumentException(
					"Could not parse date: it is not exactly" + this.exactDateLength + "characters long");
		}
		else {
			try {
				setValue(this.dateFormat.parse(text));
			}
			catch (ParseException ex) {
				throw new IllegalArgumentException("Could not parse date: " + ex.getMessage(), ex);
			}
		}
	}

	/**
	 * Format the Date as String, using the specified DateFormat.
	 */
	@Override
	public String getAsText() {

	    SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");//2020-11-22T15:51
	    dateTimeFormat.setLenient(false);
		
		Date value = (Date) getValue();
		return (value != null ? dateTimeFormat.format(value) : "");
	}

}