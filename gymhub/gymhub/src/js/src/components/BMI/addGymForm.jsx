import React, { useState } from "react";
import "./addGymForm.css";

function AddGymForm() {

  // Define the variables for form input values using the useState hook
  const [exerciseType, setExerciseType] = useState("");
  const [weight, setWeight] = useState("");
  const [numberOfSets, setNumberOfSets] = useState("");
  const [numberOfReps, setNumberOfReps] = useState("");

  // Function to save the form data when the submit button is clocked
  function saveData(e) {

    // Prevent the default form submission behaviour
    e.preventDefault(); 

    // Create a new gym object with the form input values
    const newGym = {

      exerciseType,
      weight,
      numberOfSets,
      numberOfReps,

    };

    // Log the new gym data to the console
    console.log(newGym);
  }

  return (

    <div className="container_form">
      <div className="container_row">
        <div className="col_container">

          <form onSubmit={saveData} className="gym-form">

            {/* Form input for Exercise Type */}
            <div className="form-group">
              <label className="form-label" htmlFor="exerciseType">
                Exercise Type:
              </label>
              <select
                className="form-control"
                id="exerciseType"
                onChange={(event) => {
                  // Update exerciseType state on change
                  setExerciseType(event.target.value);
                }}
              >
                <option value="Cycling">Cycling</option>
                <option value="Push-Ups">Push-Ups</option>
                <option value="Squats">Squats</option>
              </select>
            </div>

            {/* Form input for Trained Weight */}
            <div className="form-group">
              <label className="form-label" htmlFor="weight">
                Trained Weight:
              </label>
              <input
                type="text"
                className="form-control"
                name="weight"
                id="weight"
                placeholder="Weight"
                onChange={(event) => {
                  // Update weight state on change
                  setWeight(event.target.value);
                }}
              />
            </div>

            {/* Form input for Number Of Sets */}
            <div className="form-group">
              <label className="form-label" htmlFor="numberOfSets">
                Number Of Sets:
              </label>
              <input
                type="text"
                className="form-control"
                name="numberOfSets"
                id="numberOfSets"
                placeholder="Number Of Sets"
                onChange={(event) => {
                  // Update numberOfSets state on change
                  setNumberOfSets(event.target.value);
                }}
              />
            </div>

            {/* Form input for Number of Reps */}
            <div className="form-group">
              <label className="form-label" htmlFor="numberOfReps">
                Number Of Reps:
              </label>
              <input
                type="text"
                className="form-control"
                name="numberOfReps"
                id="numberOfReps"
                placeholder="Number Of Reps"
                onChange={(event) => {
                  // Update numberOfReps state on change
                  setNumberOfReps(event.target.value);
                }}
              />
            </div>

            {/* Form submit button */}
            <div className="button-container">
                <button type="submit" className="btn-primary">
                  Submit
                </button>
            </div>
            
          </form>
        </div>
      </div>
    </div>
  );
}

export default AddGymForm;
