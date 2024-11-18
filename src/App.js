import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import Dropdown from './components/Dropdown';
import SalesTable from './components/SalesTable';
import Statistics from './components/Statistics';
import Chart from './components/Chart';

const App = () => {
  return (
    <div className="container mt-4">
      <header className="text-center mb-4">
        <h1 className="display-6 text-primary">Sale Transactionmanager</h1>
      </header>
      <div className="row g-4">
        {/* Dropdown for selecting months */}
        <div className="col-md-3">
          <Dropdown />
        </div>
        {/* Sales Table */}
        <div className="col-md-6">
          <SalesTable />
        </div>
        {/* Statistics Section */}
        <div className="col-md-3">
          <Statistics />
        </div>
      </div>
      {/* Chart Section */}
      <div className="row mt-4">
        <div className="col-12">
          <Chart />
        </div>
      </div>
    </div>
  );
};

export default App;
