package com.example.demo.dataController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.dataModel.DataModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.Service.ServiceImp;
import com.example.demo.Service.ServiceInt;

@Controller
public class CovidController {

	@Autowired
	ServiceImp service;
	
	@GetMapping({"/","/Cases"})
	public String Cases(Model m)
	{
		List<DataModel> list = service.getCasesData();
		int totalCases = list.stream().mapToInt(data -> data.getTotalCases()).sum();
		m.addAttribute("listOfCasesData", service.getCasesData());
		m.addAttribute("totalCases", totalCases);
		
		return "Cases";
	}
	
	
	@GetMapping("/Recovered")
	public String Recovered(Model m)
	{
		List<DataModel> list = service.getRecoveredData();
		int totalRecovered = list.stream().mapToInt(data -> data.getTotalRecovered()).sum();
		m.addAttribute("listOfRecoveredData", service.getRecoveredData());
		m.addAttribute("totalRecovered", totalRecovered);
		
		return "Recovered";
	}
	
	
	@GetMapping("/Deaths")
	public String Deaths(Model m)
	{
		List<DataModel> list = service.getDeathsData();
		int totalDeaths = list.stream().mapToInt(data -> data.getTotalDeaths()).sum();
		m.addAttribute("listOfDeathsData", service.getDeathsData());
		m.addAttribute("totalDeaths", totalDeaths);
		
		return "Deaths";
	}
	
	
	

	@PostMapping("/Search")
	@ResponseBody
	public String Search(@RequestParam("search") String search)
	{
		
		return search;
		
	}
}
