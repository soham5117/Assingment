package edu.soham.springboot.controller;

import java.util.List;

import edu.soham.springboot.entity.BarChartData;
import edu.soham.springboot.entity.PiChartData;
import edu.soham.springboot.entity.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.soham.springboot.entity.Sale;
import edu.soham.springboot.service.SaleService;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class SaleController {

	@Autowired
    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    
    @PostMapping("/initialize-db")
    public String initializeDatabase() {
        saleService.initializeDatabaseWithSalesData();
        return "Database initialized with sales data";
    }
    
    @GetMapping("/sales/all")
    public ResponseEntity<Response<Sale>> getdata(@RequestParam(value="page" ,defaultValue = "0", required = false)Integer page) {
        Integer pageSize = 5;
    	Page<Sale> listOfSale = saleService.getListOfSale(page,pageSize);
    	List<Sale> content = listOfSale.getContent();
    	Integer number = listOfSale.getNumber();
    	Integer totalPages = listOfSale.getTotalPages();
    	
    	Response<Sale> response = new Response<>();
    	response.setData(content);
    	response.setCurrentPage(number);
    	response.setTotalPages(totalPages);
    	
    	return new ResponseEntity<>(response, HttpStatus.OK);
            	
        
    }
    
    @GetMapping("/sales/month/stat-data")
    public ResponseEntity<Response<Sale>> getMonthStatic(@RequestParam(value="selectdmonth")Integer month) {
    	
    	Response<Sale> dataAboutMonth = saleService.findDataAboutMonth(month);
    	
       return new ResponseEntity<>(dataAboutMonth, HttpStatus.OK);
    }

    @GetMapping("/sales/month/bar-graph")
    public ResponseEntity<Response<Sale>> getBargraphData(@RequestParam(value="selectdmonth")Integer month) {
    	
    	  List<BarChartData> dataForBarGraph = saleService.findDataForBarGraph(month);
    	  
    	  Response<Sale> response = new Response<>();
    	  response.setBarChartData(dataForBarGraph);
    	
      return new ResponseEntity<>(response,HttpStatus.OK);
     
        
    }
    

    @GetMapping("/sales/month/pi-graph")
    public ResponseEntity<Response<Sale>> getDataForPichart(@RequestParam(value="selectdmonth")Integer month) {
    	
    
    	List<PiChartData> dataCategoryWiseByMonth = saleService.findDataCategoryWiseByMonth(month);
    	
    	Response<Sale> response = new Response<>();
    	response.setPiChartData(dataCategoryWiseByMonth);
      return new ResponseEntity<>(response,HttpStatus.OK);
    }
    
    @GetMapping("/sales/month/all-combine")
    public ResponseEntity<Response<Sale>> getAllData(@RequestParam(value="selectdmonth")Integer month) {
    	
    
    	Response<Sale> response = saleService.findDataAboutMonth(month);
    	List<BarChartData> dataForBarGraph = saleService.findDataForBarGraph(month);
    	List<PiChartData> dataCategoryWiseByMonth = saleService.findDataCategoryWiseByMonth(month);
    	
    	response.setBarChartData(dataForBarGraph);
    	response.setPiChartData(dataCategoryWiseByMonth);
    	
   
      return new ResponseEntity<>(response,HttpStatus.OK);
    }
    

   
}