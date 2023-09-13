import React, { Component } from 'react';


class Appointments extends Component {
  constructor(props) {
    super(props);
    this.state = {
      selectedDate: null,
      selectedTime: null,
      selectedTrainer: null,
      availableTrainers: [], // Populate this array with available trainers
      // ...other necessary state variables
    };
  }

  handleDateChange = (date) => {
    this.setState({ selectedDate: date });
  };

  handleTimeChange = (time) => {
    this.setState({ selectedTime: time });
  };

  handleTrainerChange = (trainerId) => {
    this.setState({ selectedTrainer: trainerId });
  };

  handleAppointmentConfirmation = () => {
    // Implement logic to confirm the appointment and notify the trainer
    // For simplicity, just log the selected options
    console.log('Selected Date:', this.state.selectedDate);
    console.log('Selected Time:', this.state.selectedTime);
    console.log('Selected Trainer:', this.state.selectedTrainer);
  };

  render() {
    
    return (
      <div className="appointments-container">
        
        <h2>Check Trainer Availability</h2>
        <div>
          <label>Select Date:</label>
          <input type="date" onChange={(e) => this.handleDateChange(e.target.value)} />
        </div>
        <div>
          <label>Select Time:</label>
          <input type="time" onChange={(e) => this.handleTimeChange(e.target.value)} />
        </div>
        <div>
          <label>Select Trainer:</label>
          <select onChange={(e) => this.handleTrainerChange(e.target.value)}>
            <option value="">Select Trainer</option>
            {this.state.availableTrainers.map((trainer) => (
              <option key={trainer.id} value={trainer.id}>
                {trainer.name}
              </option>
            ))}
          </select>
        </div>
        <button onClick={this.handleAppointmentConfirmation}>Confirm Appointment</button>
      </div>
    );
  }
}

export default Appointments;
