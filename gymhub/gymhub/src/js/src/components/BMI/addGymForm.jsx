import React, { useState } from "react";
import "./addGymForm.css";

function AddGymForm() {
  const [exerciseType, setExerciseType] = useState("");
  const [weight, setWeight] = useState("");
  const [numberOfSets, setNumberOfSets] = useState("");
  const [numberOfReps, setNumberOfReps] = useState("");

  function saveData(e) {
    e.preventDefault();
    const newGym = {
      exerciseType,
      weight,
      numberOfSets,
      numberOfReps,
    };
    console.log(newGym);
  }

  return (
    <div className="container_form">
      <div className="container_row">
        <div className="col_container">
          <form onSubmit={saveData} className="gym-form">
            <div className="form-group">
              <label className="form-label" htmlFor="exerciseType">
                Exercise Type:
              </label>
              <select
                className="form-control"
                id="exerciseType"
                onChange={(event) => {
                  setExerciseType(event.target.value);
                }}
              >
                <option value="Cycling">Cycling</option>
                <option value="Push-Ups">Push-Ups</option>
                <option value="Squats">Squats</option>
              </select>
            </div>
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
                  setWeight(event.target.value);
                }}
              />
            </div>
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
                  setNumberOfSets(event.target.value);
                }}
              />
            </div>
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
                  setNumberOfReps(event.target.value);
                }}
              />
            </div>
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
