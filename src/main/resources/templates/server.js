const express = require('express');
const http = require('http');
const path = require('path');
const WebSocket = require('ws');

const app = express();
const server = http.createServer(app);
const wss = new WebSocket.Server({ server });

// Serve the client dashboard
app.get('/client-dashboard', (req, res) => {
    res.sendFile(path.join(__dirname, 'client-dashboard.html'));
});

// Serve the server dashboard with embedded HTML
app.get('/server-dashboard', (req, res) => {
    res.send(`
        <!DOCTYPE html>
        <html lang="en">
        <head>
          <meta charset="UTF-8">
          <meta name="viewport" content="width=device-width, initial-scale=1.0">
          <title>Simulation Server Dashboard</title>
          <style>
            body {
              font-family: Arial, sans-serif;
              background-color: #f4f4f4;
              margin: 0;
              padding: 20px;
            }
            .container {
              max-width: 900px;
              margin: 0 auto;
              padding: 20px;
              background: #fff;
              border-radius: 8px;
              box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }
            h1 {
              color: #333;
            }
            .form-group {
              margin-bottom: 15px;
            }
            label {
              display: block;
              margin-bottom: 5px;
              font-weight: bold;
            }
            button {
              padding: 10px 15px;
              background-color: #007bff;
              color: white;
              border: none;
              border-radius: 4px;
              cursor: pointer;
            }
            button:hover {
              background-color: #0056b3;
            }
            #fileStatus, #logs, #transferStatus {
              margin-top: 20px;
            }
            #logs, #transferStatus {
              white-space: pre-wrap;
              border: 1px solid #ccc;
              padding: 10px;
              height: 300px;
              overflow-y: scroll;
              background-color: #f9f9f9;
            }
          </style>
        </head>
        <body>
        
        <div class="container">
          <h1>Server Dashboard</h1>
        
          <div class="form-group">
            <label for="serverIp">Server IP Address:</label>
            <input type="text" id="serverIp" value="localhost:8081" readonly>
          </div>
        
          <div class="form-group">
            <label>File Status:</label>
            <div id="fileStatus">Waiting for files...</div>
          </div>
        
          <div class="form-group">
            <label>Transfer Logs:</label>
            <div id="transferStatus"></div>
          </div>
        
          <div class="form-group">
            <label>Real-Time Logs:</label>
            <div id="logs"></div>
          </div>
        
          <div class="form-group">
            <button id="downloadBtn">Download Received File</button>
          </div>
        </div>
        
        <script>
          const transferStatusDiv = document.getElementById('transferStatus');
          const logsDiv = document.getElementById('logs');
          const fileStatusDiv = document.getElementById('fileStatus');
        
          // WebSocket connection to the server
          const socket = new WebSocket('ws://localhost:8081');
        
          socket.addEventListener('open', function() {
            logMessage("WebSocket connection established.");
          });
        
          socket.addEventListener('message', function(event) {
            const data = event.data;
        
            // Log the message received from the client
            logsDiv.innerText += data + "\\n";
            logsDiv.scrollTop = logsDiv.scrollHeight;
        
            // Update file status based on received messages
            if (data.startsWith("Processed row:")) {
              updateFileStatus(\`Received: \${data}\`);
            } else if (data === "End of CSV") {
              updateFileStatus("File transfer completed.");
            } else if (data.startsWith("ExpectedCount:")) {
              updateFileStatus(\`Expected rows: \${data.split(':')[1]}\`);
            }
          });
        
          function logMessage(message) {
            const timestamp = new Date().toLocaleTimeString();
            logsDiv.innerText += \`[\${timestamp}] \${message}\\n\`;
            logsDiv.scrollTop = logsDiv.scrollHeight;
          }
        
          function updateFileStatus(status) {
            fileStatusDiv.innerText = status;
          }
        
          document.getElementById('downloadBtn').addEventListener('click', function() {
            const fileContent = "Simulated received data...\\nRow 1 content\\nRow 2 content\\n...";
            const blob = new Blob([fileContent], { type: "text/plain" });
            const link = document.createElement("a");
            link.href = URL.createObjectURL(blob);
            link.download = "receive.txt";
            link.click();
          });
        </script>
        </body>
        </html>
    `);
});

// Redirect root URL to server dashboard
app.get('/', (req, res) => {
    res.redirect('/server-dashboard');
});

// Handle WebSocket connections
wss.on('connection', (ws) => {
    console.log('Server connected');

    ws.on('message', (message) => {
        console.log(`Received: ${message}`);

        // Broadcast to all connected clients (including the server dashboard)
        wss.clients.forEach(client => {
            if (client.readyState === WebSocket.OPEN) {
                client.send(message);
            }
        });
    });

    ws.on('close', () => {
        console.log('Server disconnected');
    });
});

const PORT = 8081;
server.listen(PORT, () => {
    console.log(`Server is running on http://localhost:${PORT}`);
});
