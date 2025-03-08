package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name="LoginData")
	public String[][] getdata() throws IOException {
		String path="./testData/login.xlsx";  //".//testData//login.xlsx"   ///C:\\Users\\Pemo\\Desktop\\login.xlsx
		ExcelUtils excelutil=new ExcelUtils(path);
		int totalrow=excelutil.getTotalRow("logi");
		int totalcell=excelutil.getTotalCell("logi", 1);
		
		String[][] logindata=new String[totalrow][totalcell];
		
		for(int i=1;i<=totalrow;i++) {
			for(int j=0;j<totalcell;j++) {
				logindata[i-1][j]=excelutil.getCellValue("logi", i, j);
			}
		}
		return logindata;
	}

}
