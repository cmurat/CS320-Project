package DataRequesterManager;

import java.io.IOException;

import DataRequester.AccountHandler;
import DataRequester.DetailedAccount;
import GUIManager.GUIManager;

public class AccountRequests {

	AccountHandler accountHandler;
 DataRequestManager dataRequestManager;
	public AccountRequests(AccountHandler accountHandler,DataRequestManager dataRequestManager) {
		this.accountHandler = accountHandler;
		this.dataRequestManager=dataRequestManager;
	}

	public boolean checkPIN(String pin) throws IOException {
		return accountHandler.loginTwitterNewUser(pin);
	}

	public String createRequestTokenURL() {
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
		return accountHandler.getHomeAccount();
	}

	public boolean logOutClicked() {
		accountHandler.logout();
		return true;
		
		
	}

}
