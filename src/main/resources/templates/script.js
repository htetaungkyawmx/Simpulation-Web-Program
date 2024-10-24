// Sample log messages
const logs = [
    "User logged in successfully.",
    "Data retrieved from the database.",
    "Error: Unable to connect to the server.",
    "New user created: admin@example.com",
    "Settings updated successfully."
];

// Function to display logs in the admin dashboard
function displayLogs() {
    const logList = document.getElementById('logList');
    logs.forEach(log => {
        const li = document.createElement('li');
        li.textContent = log;
        logList.appendChild(li);
    });
}

// Call the function to display logs
displayLogs();
