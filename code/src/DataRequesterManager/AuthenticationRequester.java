package DataRequesterManager;

import java.io.IOException;
import DataRequester.AccountHandler;
import GUIManager.GUIManager;

public class AuthenticationRequester {
	
	AccountHandler accountHandler;
	GUIManager guiManager;
	DataRequestManager dataRequestManager;
	
	public AuthenticationRequester(AccountHandler accountHandler, GUIManager guiManager,DataRequestManager dataReqManager ){
		this.accountHandler=accountHandler;
		this.guiManager=guiManager;
		this.dataRequestManager=dataReqManager;
	}


	public boolean checkPIN(String pin) throws IOException {		
		return accountHandler.loginTwitterNewUser(pin);
	}
	
	public boolean isAuthExists() {
		boolean result = false;
		try {
			result = accountHandler.loginTwitterFromStorage();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	

}
