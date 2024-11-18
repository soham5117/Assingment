import React, { useEffect, useRef } from 'react';
import {
  Chart as ChartJS,
  ArcElement, // Required for pie and doughnut charts
  BarElement, // Required for bar charts
  CategoryScale, // Required for the x-axis in bar charts
  LinearScale, // Required for the y-axis in bar charts
  Tooltip, // For tooltips
  Legend, // For the legend
} from 'chart.js';

// Register the required components
ChartJS.register(ArcElement, BarElement, CategoryScale, LinearScale, Tooltip, Legend);

const Chart = ({ pieData, barData }) => {
  const pieChartRef = useRef(null);
  const barChartRef = useRef(null);

  useEffect(() => {
    // Render Pie Chart
    if (pieChartRef.current && pieData) {
      new ChartJS(pieChartRef.current, {
        type: 'pie',
        data: pieData,
      });
    }

    // Render Bar Chart
    if (barChartRef.current && barData) {
      new ChartJS(barChartRef.current, {
        type: 'bar',
        data: barData,
        options: {
          scales: {
            x: { type: 'category' },
            y: { beginAtZero: true },
          },
        },
      });
    }
  }, [pieData, barData]);

  return (
    <div className="row">
      <div className="col-md-6">
        <div className="p-3 bg-light border rounded text-center">
          <h5>Pie Chart</h5>
          <canvas ref={pieChartRef}></canvas>
        </div>
      </div>
      <div className="col-md-6">
        <div className="p-3 bg-light border rounded text-center">
          <h5>Bar Chart</h5>
          <canvas ref={barChartRef}></canvas>
        </div>
      </div>
    </div>
  );
};

export default Chart;
