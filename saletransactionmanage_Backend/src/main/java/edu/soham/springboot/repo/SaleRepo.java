package edu.soham.springboot.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.soham.springboot.entity.Sale;


public interface SaleRepo extends JpaRepository<Sale, Long> {

	@Query("SELECT s FROM Sale s WHERE FUNCTION('MONTH', s.dateOfSale) = :month")
	List<Sale> finAllSaleByMonth(Integer month);
	
	@Query("SELECT " +
            "CASE " +
            "WHEN s.price BETWEEN 0 AND 100 THEN '0 - 100' " +
            "WHEN s.price BETWEEN 101 AND 200 THEN '101 - 200' " +
            "WHEN s.price BETWEEN 201 AND 300 THEN '201 - 300' " +
            "WHEN s.price BETWEEN 301 AND 400 THEN '301 - 400' " +
            "WHEN s.price BETWEEN 401 AND 500 THEN '401 - 500' " +
            "WHEN s.price BETWEEN 501 AND 600 THEN '501 - 600' " +
            "WHEN s.price BETWEEN 601 AND 700 THEN '601 - 700' " +
            "WHEN s.price BETWEEN 701 AND 800 THEN '701 - 800' " +
            "WHEN s.price BETWEEN 801 AND 900 THEN '801 - 900' " +
            "ELSE '901-above' END AS priceRange, COUNT(s) AS itemCount " +
            "FROM Sale s " +
            "WHERE MONTH(s.dateOfSale) = :month " +
            "GROUP BY priceRange")
    List<Object[]> findSalesCountByPriceRangeForMonth(Integer month);
    
    @Query("SELECT s.category, COUNT(s) " +
            "FROM Sale s " +
            "WHERE MONTH(s.dateOfSale) = :month " +
            "GROUP BY s.category")
     List<Object[]> findSalesCountByCategoryForMonth(Integer month);
	
	 

}
