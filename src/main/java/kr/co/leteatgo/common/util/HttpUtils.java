package kr.co.leteatgo.common.util;

import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class HttpUtils {

	private final HttpServletRequest request;

	public String getUserIP() {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public String getRequestURI() {
		return request.getRequestURI();
	}

	public String getHttpMethod() {
		return request.getMethod();
	}
}
