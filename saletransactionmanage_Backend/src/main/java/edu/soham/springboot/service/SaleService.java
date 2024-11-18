package edu.soham.springboot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import edu.soham.springboot.entity.BarChartData;
import edu.soham.springboot.entity.PiChartData;
import edu.soham.springboot.entity.Response;
import edu.soham.springboot.entity.Sale;
import edu.soham.springboot.repo.SaleRepo;

@Service
public class SaleService {

    @Value("https://s3.amazonaws.com/roxiler.com/product_transaction.json")
    private String thirdPartyApiUrl;

    @Autowired
    private final SaleRepo saleRepository;
    
    @Autowired
    private final RestTemplate restTemplate;

    public SaleService(SaleRepo saleRepository, RestTemplate restTemplate) {
        this.saleRepository = saleRepository;
        this.restTemplate = restTemplate;
    }

    
  
    public Page<Sale> getListOfSale(Integer page, Integer pageSize)
		{
		
			Pageable pageable = PageRequest.of(page,pageSize);
		
			return  saleRepository.findAll(pageable);
		}
    // Fetch data from third-party API and save to the database
    public void initializeDatabaseWithSalesData() {
        Sale[] sales = restTemplate.getForObject(thirdPartyApiUrl, Sale[].class);

        if (sales != null) {
            for (Sale sale : sales) {
                saleRepository.save(sale);
            }
        }
    }



	public Response<Sale> findDataAboutMonth(Integer month) {
		
		List<Sale> selectedMonthData = saleRepository.finAllSaleByMonth(month);
		
		Integer totalunsold = 0 ;
		Integer totalSold = 0;
		Double totalAmount = 0.0;
		
		for (Sale sale : selectedMonthData) {
			
			if(sale.getSold())
			{
				totalSold = totalSold + 1;
				totalAmount = totalAmount + sale.getPrice();
			} else {
				totalunsold = totalunsold +1;
			}
					
		}
		Response<Sale> response = new Response<>();
		
		response.setNoNotSoldItems(totalunsold);
		response.setTotalSaleAmount(totalAmount);
		response.setTotalSoldItems(totalSold);
		response.setData(selectedMonthData);
		
		return response;
		
	}


	public List<BarChartData> findDataForBarGraph(Integer month) {
		List<Object[]> results = saleRepository.findSalesCountByPriceRangeForMonth(month);
		
		List<BarChartData> list = new ArrayList<>();
		 for (Object[] result : results) {
	            String priceRange = (String) result[0];
	            Long itemCount = (Long) result[1];
	            list.add(new BarChartData(priceRange, itemCount));
	        }
	        return list;
	}



	public List<PiChartData> findDataCategoryWiseByMonth(Integer month) {
		
		List<Object[]> results = saleRepository.findSalesCountByCategoryForMonth(month);
		List<PiChartData> list = new ArrayList<>();
		 for (Object[] result : results) {
	            String priceRange = (String) result[0];
	            Long itemCount = (Long) result[1];
	            list.add(new PiChartData(priceRange, itemCount));
	        }
		 
	        return list;
		
	}

    
}