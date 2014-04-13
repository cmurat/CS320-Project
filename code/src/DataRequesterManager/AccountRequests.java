package DataRequesterManager;

import java.io.IOException;
import DataRequester.AccountHandler;
import DataRequester.DetailedAccount;
import GUIManager.GUIManager;

public class AccountRequests {

	AccountHandler accountHandler;
	GUIManager guiManager;
	DataRequestManager dataRequestManager;

	public AccountRequests(AccountHandler accountHandler, GUIManager guiManager,DataRequestManager dataReqManager ){
		this.accountHandler=accountHandler;
		this.guiManager=guiManager;
		this.dataRequestManager=dataReqManager;
	}


	public boolean checkPIN(String pin) throws IOException {		
		return accountHandler.loginTwitterNewUser(pin);
	}

	public String createRequestTokenURL(){
		return accountHandler.createRequestTokenURL();
	}

	public boolean isAuthExists() {
		boolean result = false;
		try {
			result = accountHandler.loginTwitterFromStorage();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}


	public DetailedAccount getDetailedAccount(long userId) {
		return accountHandler.getDetailedAccount(userId);
	}


	public DetailedAccount getCurrentUserDetailedAccount() {
		return accountHandler.getCurrentUserDetailedAccount();
	}


}
