// Get references to HTML elements
const weightInput = document.getElementById("weightKg");
const heightInput = document.getElementById("heightCm");
const bmiResultElement = document.getElementById("bmiResult");
const messageElement = document.getElementById("message"); // Add this line

// Function to calculate BMI
function calculateBmi() {
    // Get weight (kg) and height (cm) from input fields
    const weightKg = parseFloat(weightInput.value);
    const heightCm = parseFloat(heightInput.value);

    // Convert height from cm to meters
    const heightMeters = heightCm / 100;

    // Calculate BMI
    const bmi = weightKg / (heightMeters * heightMeters);

    // Display the BMI result
    bmiResultElement.textContent = `Your BMI is: ${bmi.toFixed(2)}`;

    // Determine and display BMI message
    if (bmi <= 24.9) {
        messageElement.textContent = 'You are underweight';
    } else if (bmi >= 25 && bmi <= 29.9) {
        messageElement.textContent = 'You are a healthy weight';
    } else {
        messageElement.textContent = 'You are overweight';
    }
}

// Function to clear input fields, BMI result, and message
function clearInputFields() {
    weightInput.value = "";
    heightInput.value = "";
    bmiResultElement.textContent = "";
    messageElement.textContent = ""; // Clear the message as well
}

