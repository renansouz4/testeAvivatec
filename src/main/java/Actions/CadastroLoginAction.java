package Actions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.LogStatus;

import ActionFindElementSelenium.AcoesFindElement;
import ConstantsSuiteTests.ConstantValidation;
import ConstantsSuiteTests.ConstanteUrlBroweser;
import PageObjects.CadastroLoginPageObject;
import Report.ExtentReport;
import Report.Report;

public class CadastroLoginAction extends AcoesFindElement {

	String MSG_VALIDA = ConstantValidation.WELCOME;
	String EMAIL_RANDON = null;
	
	String URL = ConstanteUrlBroweser.URL_SITE;
	private WebDriver driver;
	private ExtentReport reportHTML;
	private Report report;

	/**
	 * - Construtor da Class.
	 */
	public CadastroLoginAction(WebDriver driver, ExtentReport reportHTML, Report report) {
		this.driver = driver;
		this.reportHTML = reportHTML;
		this.report = report;
	}

	public void reportPass(String texto) throws InterruptedException {
		System.out.println(texto);
		reportHTML.test.log(LogStatus.PASS, texto);
		reportHTML.test.log(LogStatus.PASS, reportHTML.test.addScreenCapture(report.capturar()));
		report.capturarText(texto);
		sleep(1000);
	}

	public void reportFail(String texto) throws InterruptedException {
		System.out.println(texto);
		reportHTML.test.log(LogStatus.FAIL, texto);
		reportHTML.test.log(LogStatus.FAIL, reportHTML.test.addScreenCapture(report.capturar()));
		report.capturarText(texto);
		Assert.fail();
	}

	public void acessarSite() throws InterruptedException {
		openURL(driver, URL);	
		
		// Realiza o logout caso algum usuário ja esteja logado.
		if (exist(driver, CadastroLoginPageObject.logOut) != 0) {
			click(driver, CadastroLoginPageObject.logOut);
			sleep(2000);			
		}	
		
		reportPass("Tela inicial");
	}

	public void acessarTelalogin() {
		click(driver, CadastroLoginPageObject.btnLogIn);
	}

	public void realizarCadastro(String FIRST_NAME, String LAST_NAME, String SENHA, String DN_DIA, String DN_MES,
			String DN_ANO, String ENDERECO, String CIDADE, String ESTADO, String CEP, String CELULAR, String ENDERECO2,
			String EMAIL, String SEXO) throws InterruptedException {
		
		int n = (int)(Math.random()*1000);
		EMAIL_RANDON = n + EMAIL;
		
		// Insere o email e clica em cria em create account.
		input(driver, CadastroLoginPageObject.emailCreate, EMAIL_RANDON);
		reportPass("Iniciando cadastro");
				
		click(driver,CadastroLoginPageObject.btnCreate);
		
		scroll(driver, 400);
		
		// Preenche todos os campos obrigatórios		
		if (SEXO.equalsIgnoreCase("Masculino")) {
			click(driver, CadastroLoginPageObject.tituloMasculino);
			
		} else {
			click(driver, CadastroLoginPageObject.tituloFeminino);
		}		
		
		input(driver, CadastroLoginPageObject.firstname, FIRST_NAME);
		input(driver, CadastroLoginPageObject.lastName, LAST_NAME);
		input(driver, CadastroLoginPageObject.senha, SENHA);
		selectElementByValueMethod(driver, CadastroLoginPageObject.dia, DN_DIA);
		selectElementByValueMethod(driver, CadastroLoginPageObject.mes, DN_MES);
		selectElementByValueMethod(driver, CadastroLoginPageObject.ano, DN_ANO);
		
		reportPass("Dados preenchidos - parte 1");
		
		input(driver, CadastroLoginPageObject.endereco, ENDERECO);
		input(driver, CadastroLoginPageObject.cidade, CIDADE);
		selectElementVisibleText(driver, CadastroLoginPageObject.estado, ESTADO);
		input(driver, CadastroLoginPageObject.cep, CEP);
		input(driver, CadastroLoginPageObject.celular, CELULAR);
		input(driver, CadastroLoginPageObject.endereco2, ENDERECO2);
		
		scroll(driver, 150);
		reportPass("Dados preenchidos - parte 2");
		
		// Clica em registrar
		click(driver, CadastroLoginPageObject.btnCadastrar);		
	}
	
	public void validaCadastroSucesso() throws InterruptedException {
		try {
			
			String validaCadastro = getText(driver, CadastroLoginPageObject.validaMsg);
			assertTrue(validaCadastro.equalsIgnoreCase(MSG_VALIDA));
			reportPass("Cadastro efetuado com sucesso.");
			
		} catch (Exception e) {
			reportFail("Falha ao realizar cadastro " + e);
		}
	}

	public void realizarLogin(String SENHA) throws InterruptedException {
		try {
			
			input(driver, CadastroLoginPageObject.email, EMAIL_RANDON);
			input(driver, CadastroLoginPageObject.senha, SENHA);
			click(driver, CadastroLoginPageObject.btnLogin);
			
			reportPass("Login realizado com sucesso.");

		} catch (Exception e) {
			reportFail("Falha ao realizar login. " + e);
		}
	}

	public void realizarLogOut() throws InterruptedException {
		try {
			click(driver, CadastroLoginPageObject.logOut);
			sleep(2000);
			
			if (exist(driver, CadastroLoginPageObject.autenticacao) != 0) {
				reportPass("Logout realizado com sucesso.");
			}
			
		} catch (Exception e) {
			reportFail("Falha ao realizar logout.");
		}		
	}

}
