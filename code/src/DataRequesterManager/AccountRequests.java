package DataRequesterManager;

import java.io.IOException;

import DataRequester.AccountHandler;
import DataRequester.DetailedAccount;
import GUIManager.GUIManager;

public class AccountRequests {

	AccountHandler accountHandler;

	public AccountRequests(AccountHandler accountHandler) {
		this.accountHandler = accountHandler;
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

}
