import React from 'react';

const Dropdown = () => {
  return (
    <div className="p-3 bg-light border rounded">
      <h5 className="mb-3">Filters</h5>
      <form>
        {/* Dropdown for Months */}
        <div className="mb-3">
          <label htmlFor="months" className="form-label">Months</label>
          <select id="months" className="form-select" defaultValue="">
            <option value="" disabled>Select a month</option>
            <option value="January">January</option>
            <option value="February">February</option>
            <option value="March">March</option>
          </select>
        </div>

        {/* Dropdown for Sales */}
        <div className="mb-3">
          <label htmlFor="sales" className="form-label">Sales</label>
          <select id="sales" className="form-select" defaultValue="">
            <option value="" disabled>Select a sales type</option>
            <option value="all">All Sales</option>
            <option value="sold">Sold</option>
            <option value="unsold">Unsold</option>
          </select>
        </div>

        {/* Dropdown for Categories */}
        <div className="mb-3">
          <label htmlFor="categories" className="form-label">Category</label>
          <select id="categories" className="form-select" defaultValue="">
            <option value="" disabled>Select a category</option>
            <option value="all">All Categories</option>
            <option value="electronics">Electronics</option>
            <option value="fashion">Fashion</option>
          </select>
        </div>
      </form>
    </div>
  );
};

export default Dropdown;
