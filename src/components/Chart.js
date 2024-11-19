import React, { useEffect, useRef, useState } from "react";
import { Chart as ChartJS, ArcElement, Tooltip, Legend } from "chart.js";
import SaleService from "../services/SaleService";

// Register the required components
ChartJS.register(ArcElement, Tooltip, Legend);

const Chart = ({ selectedMonth }) => {
  const pieChartRef = useRef(null);
  const [pieData, setPieData] = useState([]);
  const [totalItems, setTotalItems] = useState(0);
  const [error, setError] = useState(null);

  const fetchPieChartData = async () => {
    try {
      const data = await SaleService.fetchPieChartData(selectedMonth);
      if (!data || Object.keys(data).length === 0) {
        setError("No data available for the selected month.");
        return;
      }

      const total = Object.values(data).reduce((sum, count) => sum + count, 0);
      const chartData = Object.entries(data).map(([category, count]) => ({
        category,
        count,
        percentage: ((count / total) * 100).toFixed(2),
      }));

      setPieData(chartData);
      setTotalItems(total);
      setError(null);

      if (pieChartRef.current) {
        new ChartJS(pieChartRef.current, {
          type: "pie",
          data: {
            labels: chartData.map((item) => item.category),
            datasets: [
              {
                data: chartData.map((item) => item.count),
                backgroundColor: chartData.map((_, index) => getColor(index)),
              },
            ],
          },
          options: {
            responsive: true,
            plugins: {
              legend: {
                display: true,
                position: "top",
              },
              tooltip: {
                callbacks: {
                  label: function (context) {
                    const label = context.label || "";
                    const value = context.raw || 0;
                    const percentage = chartData[context.dataIndex]?.percentage || 0;
                    return `${label}: ${value} (${percentage}%)`;
                  },
                },
              },
            },
          },
        });
      }
    } catch (err) {
      console.error("Error fetching pie chart data:", err);
      setError("Error fetching data. Please try again later.");
    }
  };

  useEffect(() => {
    fetchPieChartData();
  }, [selectedMonth]);

  return (
    <div className="container my-4">
      <h3 className="text-center mb-4">Category Distribution Pie Chart</h3>

      {error && <p className="text-danger text-center">{error}</p>}

      <div className="d-flex justify-content-center">
        {pieData.length > 0 ? (
          <canvas
            ref={pieChartRef}
            aria-label={`Pie chart for month ${selectedMonth}`}
            className="w-75"
          ></canvas>
        ) : (
          <p className="text-center">No data available to display the pie chart.</p>
        )}
      </div>

      {pieData.length > 0 && (
        <div className="mt-4">
          <h5 className="text-center">Legend</h5>
          <div className="d-flex flex-column align-items-center">
            {pieData.map((data, index) => (
              <div
                key={data.category}
                className="d-flex align-items-center mb-2"
                style={{ maxWidth: "300px", width: "100%" }}
              >
                <div
                  style={{
                    width: "20px",
                    height: "20px",
                    backgroundColor: getColor(index),
                    marginRight: "10px",
                  }}
                ></div>
                <span>
                  {data.category}: {data.percentage}%
                </span>
              </div>
            ))}
          </div>
        </div>
      )}

      <p className="mt-3 text-center">
        <strong>Total Items:</strong> {totalItems}
      </p>
    </div>
  );
};

const getColor = (index) => {
  const colors = ["#FF5733", "#33FF57", "#5733FF", "#FFD700", "#FF33A6"];
  return colors[index % colors.length];
};

export default Chart;
