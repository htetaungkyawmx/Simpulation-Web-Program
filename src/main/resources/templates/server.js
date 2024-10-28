const express = require('express');
const http = require('http');
const WebSocket = require('ws');

const app = express();
const server = http.createServer(app);
const wss = new WebSocket.Server({ server });

app.use(express.static('public'));

wss.on('connection', (ws) => {
    console.log('Client connected');

    ws.on('message', (message) => {
        console.log('Received:', message);

        // Process the message or simulate processing (e.g., delay)
        setTimeout(() => {
            ws.send(`Processed: ${message}`);
        }, 500);
    });

    ws.on('close', () => {
        console.log('Client disconnected');
    });
});

// Use 8081 instead of 8080 if you previously had conflicts on 8080
const PORT = 8081;
server.listen(PORT, () => {
    console.log(`Server is listening on port ${PORT}`);
});
