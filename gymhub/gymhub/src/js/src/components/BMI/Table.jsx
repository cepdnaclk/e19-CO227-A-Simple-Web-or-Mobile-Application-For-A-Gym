import React from "react";
import './Table.css';

function Table() {
  return (
    <div className="table_container">
      <div className="row_container">
        <div className="col_container">
          <table className="custom-table">
            <thead className="custom-table-header">
              <tr>
                <th scope="col">Exercise Type</th>
                <th scope="col">Weight</th>
                <th scope="col">Sets</th>
                <th scope="col">Reps</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <th scope="row">Cycling</th>
                <td>30</td>
                <td>3</td>
                <td>10</td>
              </tr>
              <tr>
                <th scope="row">Push-Ups</th>
                <td>33</td>
                <td>5</td>
                <td>10</td>
              </tr>
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

export default Table;