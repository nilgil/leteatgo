package kr.co.leteatgo.auth.application.common;

public class VersioningUtil {

	public static String rewritePhoneNumber(String phone) {
		if (phone.length() == 10) {
			phone = phone.substring(0, 3) + "-" + phone.substring(3, 6) + "-" + phone.substring(6);
		} else if (phone.length() == 11) {
			phone = phone.substring(0, 3) + "-" + phone.substring(3, 7) + "-" + phone.substring(7);
		}
		return phone;
	}
}
