package personal.wh.tinyspring.util;

public abstract class Assert {

	public static void notNull(Object object) {
		notNull(object, "[Assertion failed] - this argument is required; it must not be null");
	}
	
	public static void notNull(Object object, String message) {
		if (object == null) {
			throw new IllegalArgumentException(message);
		}
	}
	
	public static void notEmpty(String str) {
		notEmpty(str, "[Assertion failed] - this String argument must have length; it must not be null or empty");
	}
	
	public static void notEmpty(String str, String message) {
		if (StringUtils.isEmpty(str)) {
			throw new IllegalArgumentException(message);
		}
	}
	
}
