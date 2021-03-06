package personal.wh.tinyspring.util;

public abstract class StringUtils {

	/**
	 * 首字母大写
	 * @param str
	 * @return
	 */
	public static String capitalize(String str) {
		StringBuilder sb = new StringBuilder();
		sb.append(Character.toUpperCase(str.charAt(0))).append(str.substring(1));
		return sb.toString();
	}
	
	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}
	
	public static boolean isNotEmpty(String str) {
		return str != null && str.length() > 0;
	}
}
