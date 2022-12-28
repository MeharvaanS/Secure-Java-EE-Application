package ca.sheridancollege.simeharv.security;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SecureWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {
	public SecureWebApplicationInitializer() {
		super(SecurityConfiguration.class);
	}
}

