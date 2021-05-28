package Report;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class ExtentReport {
	public static ExtentReports extent;
	public ExtentTest test;
	
	/**
	 * Construtor da classe ExtentReport para gerar relatório HTML para Selenium.
	 * @author rodrigo.c.almeida
	 */
	public ExtentReport() {

		DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH-mm-ss");
		Date date = new Date();
		
		if (extent==null) {

		extent = new ExtentReports(
				System.getProperty("user.dir") + "/resources/reports/relatorio/ReportHTML_"+dateFormat.format(date) +".html",
				//System.getProperty("user.dir") + "/resources/reports/ReportHTML_.html",
				true);
		extent.addSystemInfo("Host Name", "O2M").addSystemInfo("Environment", "QA").addSystemInfo("User Name",
				System.getProperty("user.name"));
		extent.loadConfig(new File(System.getProperty("user.dir") + "\\resources\\configs\\extent-config.xml"));
		}

	}

}
