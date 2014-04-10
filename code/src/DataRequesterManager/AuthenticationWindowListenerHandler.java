package DataRequesterManager;

import GUI.LoginPage;

public class AuthenticationWindowListenerHandler {
	AuthenticationRequests authRequester;
	LoginPage loginPage;
	public AuthenticationWindowListenerHandler(AuthenticationRequests authRequester, LoginPage loginPage){
		this.authRequester=authRequester;
		this.loginPage=loginPage;
	}
	
}
