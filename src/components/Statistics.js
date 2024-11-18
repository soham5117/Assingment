import React, { useState, useEffect } from 'react';
import SaleService from '../services/SaleService';

const Statistics = () => {
  const [statistics, setStatistics] = useState({
    totalSales: 0,
    soldItems: 0,
    unsoldItems: 0,
  });
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    fetchStatistics();
  }, []);

  const fetchStatistics = async () => {
    try {
      const data = await SaleService.fetchMonthlyStatistics(new Date().getMonth() + 1); // Get stats for the current month
      setStatistics({
        totalSales: data.totalSales,
        soldItems: data.soldItems,
        unsoldItems: data.unsoldItems,
      });
    } catch (error) {
      console.error('Error fetching statistics:', error);
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className="d-flex flex-column gap-3">
      {isLoading ? (
        <p>Loading...</p>
      ) : (
        <>
          <div className="card text-center bg-primary text-white">
            <div className="card-body">
              <h5>Total Sales</h5>
              <p>${statistics.totalSales}</p>
            </div>
          </div>
          <div className="card text-center bg-success text-white">
            <div className="card-body">
              <h5>Sold Items</h5>
              <p>{statistics.soldItems}</p>
            </div>
          </div>
          <div className="card text-center bg-danger text-white">
            <div className="card-body">
              <h5>Unsold Items</h5>
              <p>{statistics.unsoldItems}</p>
            </div>
          </div>
        </>
      )}
    </div>
  );
};

export default Statistics;
