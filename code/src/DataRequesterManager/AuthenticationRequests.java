package DataRequesterManager;

import DataRequester.AccountHandler;

public class AuthenticationRequests {
	AuthenticationWindowListenerHandler authWindowListener;
	AccountHandler accountHandler;
	
	
	public AuthenticationRequests(AuthenticationWindowListenerHandler authWindowListener,AccountHandler accountHandler ){
		this.accountHandler=accountHandler;
		this.authWindowListener=authWindowListener;
	}
	
	

}
