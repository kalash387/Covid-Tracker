package com.example.demo.Service;

import com.example.demo.dataModel.DataModel;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


@Service
public class ServiceImp implements ServiceInt {

	
	private static String Cases_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
	private static String Recovered_URL = "https://data.humdata.org/hxlproxy/api/data-preview.csv?url=https%3A%2F%2Fraw.githubusercontent.com%2FCSSEGISandData%2FCOVID-19%2Fmaster%2Fcsse_covid_19_data%2Fcsse_covid_19_time_series%2Ftime_series_covid19_recovered_global.csv&filename=time_series_covid19_recovered_global.csv";
	private static String Deaths_URL ="https://data.humdata.org/hxlproxy/api/data-preview.csv?url=https%3A%2F%2Fraw.githubusercontent.com%2FCSSEGISandData%2FCOVID-19%2Fmaster%2Fcsse_covid_19_data%2Fcsse_covid_19_time_series%2Ftime_series_covid19_deaths_global.csv&filename=time_series_covid19_deaths_global.csv";
	
	private List<DataModel> casesData = new ArrayList<>(); 
	private List<DataModel> recoveredData = new ArrayList<>(); 
	private List<DataModel> deathsData = new ArrayList<>(); 
//	private List<Integer> recoveredList = new ArrayList<>();
//	
//	public List<Integer> getRecoveredList() {
//		return recoveredList;
//	}
	
	public List<DataModel> getCasesData() {
		return casesData;
	}


    public List<DataModel> getRecoveredData() {
		return recoveredData;
	}


    public List<DataModel> getDeathsData() {
		return deathsData;
	}


	
	
	@PostConstruct
	@Scheduled(cron = "30 * * * * *")
	public void fetchData() throws IOException, InterruptedException, CsvException
	{
		List<DataModel> CasesData = new ArrayList<>(); 
		List<DataModel> RecoveredData = new ArrayList<>(); 
		List<DataModel> DeathsData = new ArrayList<>(); 
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest req_cases = HttpRequest.newBuilder()
					.uri(URI.create(Cases_URL))
					.build();
		
		HttpRequest req_recovered = HttpRequest.newBuilder()
				.uri(URI.create(Recovered_URL))
				.build();
		
		
		HttpRequest req_Deaths = HttpRequest.newBuilder()
				.uri(URI.create(Deaths_URL))
				.build();
		
	
		
		HttpResponse<String> res_cases = client.send(req_cases, HttpResponse.BodyHandlers.ofString());
		StringReader cases = new StringReader(res_cases.body() );
		
		HttpResponse<String> res_recovered = client.send(req_recovered, HttpResponse.BodyHandlers.ofString());
		StringReader recovered = new StringReader(res_recovered.body() );
		
		
		HttpResponse<String> res_deaths= client.send(req_Deaths, HttpResponse.BodyHandlers.ofString());
		StringReader deaths = new StringReader(res_deaths.body() );
		
	
		
		Iterable<CSVRecord> cases_Records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(cases);
		Iterable<CSVRecord> recovered_Records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(recovered);
		Iterable<CSVRecord> deaths_Records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(deaths);
	
		
//		for (CSVRecord records : recovered_Records) {
//			
//			recoveredList.add(Integer.parseInt(records.get(records.size()-1)));
//		}
		
//		int in = 0;
		for (CSVRecord record : cases_Records) {
			DataModel data = new DataModel();
			
		    data.setState(record.get("Province/State")); 
		    data.setCountry(record.get("Country/Region"));
		    data.setTotalCases(Integer.parseInt(record.get(record.size() - 1)));
		   
		    
		    
		    CasesData.add(data);
		}
		
		for (CSVRecord records : recovered_Records) {
			
			DataModel data = new DataModel();
			data.setState(records.get("Province/State")); 
			data.setCountry(records.get("Country/Region"));
			//data.setTotalCases(Integer.parseInt(record.get(record.size() - 1)));
		
			data.setTotalRecovered(Integer.parseInt(records.get(records.size()-1)));
			RecoveredData.add(data);
//		    if(in < 263)
//		    	in++;
		}
		
		for (CSVRecord records : deaths_Records) {
			
			DataModel data = new DataModel();
			data.setState(records.get("Province/State")); 
			data.setCountry(records.get("Country/Region"));
			//data.setTotalCases(Integer.parseInt(record.get(record.size() - 1)));
		
			data.setTotalDeaths(Integer.parseInt(records.get(records.size()-1)));
			DeathsData.add(data);
//		    if(in < 263)
//		    	in++;
			}
		
		
		
		        
		this.casesData = CasesData;
		this.recoveredData = RecoveredData;
		this.deathsData = DeathsData;
		
		
		
		
	}




	




	






	



	
	
	
}
