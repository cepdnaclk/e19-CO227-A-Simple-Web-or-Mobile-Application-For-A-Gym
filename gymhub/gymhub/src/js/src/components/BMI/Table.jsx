import React, { useEffect, useState } from "react";
import './Table.css';
import axios from "axios";

function Table() {
  
  const exercisesData = [
    {
      "gymId": 1,
      "exerciseType": "Push-ups",
      "weight": "50",
      "numberOfSets": "20",
      "numberOfReps": "120"
  
  },
    {
      "gymId": 2,
      "exerciseType": "Push-ups",
      "weight": "50",
      "numberOfSets": "20",
      "numberOfReps": "120"
  
  },
    {
      "gymId": 3,
      "exerciseType": "Push-ups",
      "weight": "50",
      "numberOfSets": "20",
      "numberOfReps": "120"
  
  },
    {
      "gymId": 4,
      "exerciseType": "Push-ups",
      "weight": "50",
      "numberOfSets": "20",
      "numberOfReps": "120"
  
  },
  ]
  const [exercises,setExercises] = useState(exercisesData);

  useEffect(() => {
    const fetchData = async () => {
      try {

        const response = await axios.get(
          "http://localhost:8080/api/v1/gym/getAllGym",
          
        );

        setExercises(response.data);
        console.log(response.data);
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    };

    fetchData();
    
  }, []);

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
              {exercises.map((exercise)=>(

                <tr key={exercise.gymId}>
                  <td>    {exercise.exerciseType}  </td>
                  <td>    {exercise.weight}  </td>
                  <td>    {exercise.numberOfSets}  </td>
                  <td>    {exercise.numberOfReps}  </td>

                </tr>
              ))}
              
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
}

export default Table;
