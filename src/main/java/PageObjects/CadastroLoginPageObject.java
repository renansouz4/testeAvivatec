package PageObjects;

import org.openqa.selenium.By;

public class CadastroLoginPageObject {
	
	public static final By btnLogIn = By.xpath("//*[@class='login']");
	public static final By emailCreate = By.xpath("//*[@id='email_create']");
	public static final By btnCreate = By.xpath("//*[@id='SubmitCreate']");	
	
	public static final By tituloMasculino = By.xpath("//*[@id='uniform-id_gender1']");
	public static final By tituloFeminino = By.xpath("//*[@id='uniform-id_gender2']");
	public static final By firstname = By.xpath("//*[@id='customer_firstname']");
	public static final By lastName = By.xpath("//*[@id='customer_lastname']");	
	public static final By senha = By.xpath("//*[@id='passwd']");
	public static final By dia = By.xpath("//*[@id='days']");
	public static final By mes = By.xpath("//*[@id='months']");
	public static final By ano = By.xpath("//*[@id='years']");
	public static final By endereco = By.xpath("//*[@id='address1']");
	public static final By cidade = By.xpath("//*[@id='city']");
	public static final By estado = By.xpath("//*[@id='id_state']");
	public static final By cep = By.xpath("//*[@id='postcode']");
	public static final By celular = By.xpath("//*[@id='phone_mobile']");
	public static final By endereco2 = By.xpath("//*[@id='alias']");
	public static final By btnCadastrar = By.xpath("//*[@id='submitAccount']");
	
	public static final By logOut = By.xpath("//*[@class='logout']");
	
	
	public static final By asaGsdasd = By.xpath("//*[@id='']");
	public static final By asasHdasd = By.xpath("//*[@id='']");
	
	public static final By email = By.xpath("//*[@id='email']");
	public static final By btnLogin = By.xpath("//*[@id='SubmitLogin']");
	
	public static final By validaMsg = By.xpath("//*[@class='info-account']");
	
	public static final By autenticacao = By.xpath("//*[text()='Authentication']");
	


}
