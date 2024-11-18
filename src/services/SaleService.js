import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080'; // Replace with your Spring Boot server URL

const SaleService = {
  /**
   * Initialize the database with sales data.
   * @returns {Promise<string>} - Message from the server.
   */
  initializeDatabase: async () => {
    const response = await axios.post(`${API_BASE_URL}/initialize-db`);
    return response.data;
  },

  /**
   * Fetch all sales with pagination.
   * @param {number} page - Page number for the sales data (default 0).
   * @param {number} pageSize - Number of items per page (default 10).
   * @returns {Promise<object>} - Paginated sales data.
   */
  fetchAllSales: async (page = 0, pageSize = 10) => {
    const response = await axios.get(`${API_BASE_URL}/sales/all`, {
      params: { page, pageSize },
    });
    return response.data;
  },

  /**
   * Fetch monthly statistics.
   * @param {number} selectedMonth - Month number (1-12).
   * @returns {Promise<object>} - Data about sales for the selected month.
   */
  fetchMonthlyStatistics: async (selectedMonth) => {
    const response = await axios.get(`${API_BASE_URL}/sales/month/stat-data`, {
      params: { selectdmonth: selectedMonth },
    });
    return response.data;
  },

  /**
   * Fetch bar graph data for a selected month.
   * @param {number} selectedMonth - Month number (1-12).
   * @returns {Promise<object>} - Bar chart data.
   */
  fetchBarGraphData: async (selectedMonth) => {
    const response = await axios.get(`${API_BASE_URL}/sales/month/bar-graph`, {
      params: { selectdmonth: selectedMonth },
    });
    return response.data;
  },

  /**
   * Fetch pie chart data for a selected month.
   * @param {number} selectedMonth - Month number (1-12).
   * @returns {Promise<object>} - Pie chart data.
   */
  fetchPieChartData: async (selectedMonth) => {
    const response = await axios.get(`${API_BASE_URL}/sales/month/pi-graph`, {
      params: { selectdmonth: selectedMonth },
    });
    return response.data;
  },

  /**
   * Fetch all combined data (monthly statistics, bar graph, and pie chart) for a selected month.
   * @param {number} selectedMonth - Month number (1-12).
   * @returns {Promise<object>} - Combined data for the selected month.
   */
  fetchAllCombinedData: async (selectedMonth) => {
    const response = await axios.get(`${API_BASE_URL}/sales/month/all-combine`, {
      params: { selectdmonth: selectedMonth },
    });
    return response.data;
  },
};

export default SaleService;
