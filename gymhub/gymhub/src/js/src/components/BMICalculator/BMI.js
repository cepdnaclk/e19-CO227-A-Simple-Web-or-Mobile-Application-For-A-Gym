const weightInput = document.getElementById("weightKg");
const heightInput = document.getElementById("heightCm");
const bmiResultElement = document.getElementById("bmiResult");
const messageElement = document.getElementById("message");

document.getElementById("calculateButton").addEventListener("click", calculateBmi);
document.getElementById("clearButton").addEventListener("click", clearInputFields);

function calculateBmi() {
    const weightKg = parseFloat(weightInput.value);
    const heightCm = parseFloat(heightInput.value);

    const heightMeters = heightCm / 100;
    const bmi = weightKg / (heightMeters * heightMeters);

    bmiResultElement.textContent = `Your BMI is: ${bmi.toFixed(2)}`;

    if (bmi <= 24.9) {
        messageElement.textContent = 'You are underweight';
    } else if (bmi >= 25 && bmi <= 29.9) {
        messageElement.textContent = 'You are a healthy weight';
    } else {
        messageElement.textContent = 'You are overweight';
    }
}

function clearInputFields() {
    weightInput.value = "";
    heightInput.value = "";
    bmiResultElement.textContent = "";
    messageElement.textContent = "";
}
