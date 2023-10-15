import React from "react";
import './Table.css'; // Import the CSS for styling

// Functional component for rendering a table
function Table() {
  return (
    <div className="table_container">
      <div className="row_container">
        <div className="col_container">

          {/* Table for displaying gym data */}
          <table className="custom-table">

            {/* Table header */}
            <thead className="custom-table-header">
              <tr>
                <th scope="col">Exercise Type</th>
                <th scope="col">Weight</th>
                <th scope="col">Sets</th>
                <th scope="col">Reps</th>
              </tr>
            </thead>

            {/* Table body for data rows */}
            <tbody>

              {/* Data row 1 - Cycling exercise */}
              <tr>
                <th scope="row">Cycling</th>
                <td>30</td>
                <td>3</td>
                <td>10</td>
              </tr>

              {/* Data row 2 - Push-Ups exercise */}
              <tr>
                <th scope="row">Push-Ups</th>
                <td>33</td>
                <td>5</td>
                <td>10</td>
              </tr>

              {/* Data row 3 - Squats exercise */}
              <tr>
                <th scope="row">Squats</th>
                <td>35</td>
                <td>5</td>
                <td>10</td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
}

// Export the Table component for use in other parts of the application
export default Table;
