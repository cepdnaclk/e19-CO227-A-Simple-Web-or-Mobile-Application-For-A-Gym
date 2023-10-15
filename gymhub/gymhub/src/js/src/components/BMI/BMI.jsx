// Import the necessary CSS file for styling
import "./BMI.css";

// Import React components for the BMI page
import Header from "../Header/Header";
import AddGymForm from "./addGymForm";
import Table from "./Table";
// import WeightLineChart from "./WeightLineChart";

// Define the main BMI functional component
function BMI() {
  return (

    // Container for the entire BMI page
    <div className="bmi-page">
      {/* Display the Header component at the top of the page */}
      <Header />
      {/* Display the AddGymForm component for data input */}
      <AddGymForm />
      {/* Display the Table component for displaying gym data */}
      <Table />
    </div>

  );
}

// Export the BMI component for use in other parts of the application
export default BMI;
