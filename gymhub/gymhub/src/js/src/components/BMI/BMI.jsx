import "./BMI.css";
import Header from "../Header/Header";
import AddGymForm from "./addGymForm";
import Table from "./Table";
// import WeightLineChart from "./WeightLineChart";

function BMI() {
  return (
    <div className="bmi-page">
      <Header />
      <AddGymForm />
      <Table />
    </div>
  );
}

export default BMI;
