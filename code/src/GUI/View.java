package GUI;

public class View {
	
	private MainFrame mainFrame;
	
	public View() {
		LoginPage loginPage = new LoginPage("https://www.google.com.tr/");
		loginPage.printLoginPage();
	}
	
	public static void main(String[] args) {
		View view = new View();
	}

}
