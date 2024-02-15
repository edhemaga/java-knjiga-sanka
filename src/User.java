import java.util.regex.*;

public class User {
	
	//Atributi uvijek trebaju biti private iz razloga da bismo osigurali da nema nezeljenih izmjena iz drugih dijelova naseg sistema
	private int id;
	private String name;
	private String lastname;
	private String username;
	private String password;
	private String type;
	
	//Konstruktor se UVIJEK PRVI poziva, u nasem slucaju dali smo mu parametre (String name, String lastname, ..., String type) koji ce se iskoristiti da se vrijednost nasih atributa napuni sa nekom vrijednoscu
	//this se koristi za pristup atributima
	public User(String name, String lastname, String username, String password, String type) {
		if(!isValidPassword(password))
			throw new Error("Password not valid!");
		this.name = name;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.type = type;
	}
	
	//Getere koristimo da bismo drugim klasama ili dijelovima koda omogocili pristup nasem atributu
	public String getName() {
		return name;
	}
	
	//TODO implementirati ostale gettere
	
	public String getLastname() {
		return lastname;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getType () {
		return type;
	}
	
    public static boolean isValidPassword(String password) {
    	//Regex, minimalno mora sadrzavati mala slova, velika slova, specijalne znakove i biti duzine 8 karaktera
    	String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";

        Pattern pattern = Pattern.compile(passwordRegex);
        Matcher matcher = pattern.matcher(password);

        return matcher.matches();
    }
    
	//Setter nam omogucava da iz neke druge klase odredenu vrijednost u nasoj klasi promijenimo
	//sa thisom isto slucaj kao i u konstruktoru
	public void setName(String name) {
		this.name = name;
	}
	
	//TODO implementirati ostale settere
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	
	//TODO napraviti provjeru da li je korisnik admin if koristiti
	
	/*
	 public boolean daLiJeKorisnikAdmin() {
		if (type = "Admin") {
			return true;
		}	else {
			return false;
		}
	}*/
	
	
	
	
	
}
