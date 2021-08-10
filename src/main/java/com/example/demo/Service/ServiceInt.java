package com.example.demo.Service;

import java.io.IOException;

import com.opencsv.exceptions.CsvException;

public interface ServiceInt {
	
	
	public void fetchData() throws IOException, InterruptedException, CsvException;

}
