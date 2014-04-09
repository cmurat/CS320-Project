package GUI;

public class View {
	
	private MainFrame mainFrame;
	
	public View() {
		LoginPage loginPage = new LoginPage("www.google.com");
		loginPage.printLoginPage();
	}
	
	public static void main(String[] args) {
		View view = new View();
	}

}
