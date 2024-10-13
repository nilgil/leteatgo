package kr.co.leteatgo.common.auth;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LegRoleHierarchy {

	public static final String AUTHORITY_PREFIX = "ROLE_";

	public static Map<String, List<String>> hierarchyMap() {
		return new HashMap<>() {{
			put("ROLE_SUPER", List.of("ROLE_ADMIN"));
			put("ROLE_ADMIN", List.of("ROLE_STORE", "ROLE_USER", "ROLE_CLIENT", "ROLE_SYSTEM"));
			put("ROLE_SYSTEM", List.of("ROLE_ANONYMOUS"));
			put("ROLE_USER", List.of("ROLE_ANONYMOUS"));
			put("ROLE_STORE", List.of("ROLE_ANONYMOUS"));
			put("ROLE_CLIENT", List.of("ROLE_ANONYMOUS"));
		}};
	}
}
