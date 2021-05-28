package TestCases;

import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.LogStatus;

import Actions.CadastroLoginAction;
import ConstantsSuiteTests.ConstantsSpreadsheet;
import ManagersDriver.WebDriverManager;
import PageObjects.CadastroLoginPageObject;
import Report.ExtentReport;
import Report.Report;
import Spreadsheet.ObjectEnvironment;
import Spreadsheet.Spreadsheet;

@RunWith(Parameterized.class)
public class CadastroLogin {

	@Rule
	public TestWatcher watchman = new TestWatcher() {
		@Override
		protected void failed(Throwable e, Description description) {
			reportHTML.test.log(LogStatus.FAIL, "TestCase finalizado com erro: " + e.getMessage());
		}

		@Override
		protected void succeeded(Description description) {
			reportHTML.test.log(LogStatus.PASS, "TestCase finalizado com sucesso");
		}
	};

	private WebDriver driver;
	CadastroLoginAction cadastro;
	ExtentReport reportHTML = new ExtentReport();
	Report report;
	ObjectEnvironment environment;
	String TEST_CASE_NAME = "Cadastro e Login";

	private String AMBIENTE;
	private String FIRST_NAME;
	private String LAST_NAME;
	private String SENHA;
	private String DN_DIA;
	private String DN_MES;
	private String DN_ANO;
	private String ENDERECO;
	private String CIDADE;
	private String ESTADO;
	private String CEP;
	private String CELULAR;
	private String ENDERECO2;
	private String EMAIL;
	private String SEXO;

	public CadastroLogin(String AMBIENTE, String FIRST_NAME, String LAST_NAME, String SENHA, String DN_DIA, String DN_MES, String DN_ANO, String ENDERECO, String CIDADE, String ESTADO, String CEP, String CELULAR, String ENDERECO2, String EMAIL, String SEXO) {
		
		this.AMBIENTE = AMBIENTE;
		this.FIRST_NAME = FIRST_NAME;
		this.LAST_NAME = LAST_NAME;
		this.SENHA = SENHA;
		this.DN_DIA = DN_DIA;
		this.DN_MES = DN_MES;
		this.DN_ANO = DN_ANO;
		this.ENDERECO = ENDERECO;
		this.CIDADE = CIDADE;
		this.ESTADO = ESTADO;
		this.CEP = CEP;
		this.CELULAR = CELULAR;
		this.ENDERECO2 = ENDERECO2;
		this.EMAIL = EMAIL;
		this.SEXO = SEXO;
	}

	@Before
	public void setUp() {
		WebDriverManager webDriverManager = new WebDriverManager();
		driver = webDriverManager.getDriver();
		report = new Report();
		environment = new ObjectEnvironment(Arrays.asList(Spreadsheet.getStringSpreadsheetAmbiente()));
		cadastro = new CadastroLoginAction(driver, reportHTML, report);

		reportHTML.test = ExtentReport.extent.startTest(AMBIENTE);
		reportHTML.test.log(LogStatus.INFO, "Iniciando a execucao do teste: " + TEST_CASE_NAME + " ...");
	}

	@Parameterized.Parameters
	public static Collection<Object> input() {
		Object[] objeto = Spreadsheet.getStringSpreadsheetCenario(ConstantsSpreadsheet.EXCEL_CT01);
		return Arrays.asList(objeto);
	}

	@Test
	public void RodaTest() throws InterruptedException {

		cadastro.acessarSite();
		cadastro.acessarTelalogin();
		cadastro.realizarCadastro(FIRST_NAME, LAST_NAME, SENHA, DN_DIA, DN_MES, DN_ANO, ENDERECO, CIDADE, ESTADO, CEP, CELULAR, ENDERECO2, EMAIL, SEXO);
		cadastro.validaCadastroSucesso();
		cadastro.realizarLogOut();
		cadastro.realizarLogin(SENHA);		
	}

	@After
	public void fim() throws Exception {
		driver.close();
		ExtentReport.extent.flush();
		report.createReportDoc("Cadastro_e_Login");
	}
}
