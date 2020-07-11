package view;

public class MenuFactory {

	CommonMenu cm;
		
	public CommonMenu menuNavigate(String email) {
		
		if (email.contains("@asi.il")) {
			cm = new AdminMenu();
		}
		else {
			cm = new UserMenu();
		}
		return cm;
	}
	
}
