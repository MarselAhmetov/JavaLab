<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Чат</title>
    <script
            src="https://code.jquery.com/jquery-3.4.1.js"
            integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
    <script>
        var webSocket;
        var currentRoom;
        function connect() {
            //webSocket = new WebSocket('ws://localhost:8080/chat');
            webSocket = new SockJS("http://localhost:8080/chat");

            webSocket.onmessage = function receiveMessage(response) {
                let data = response['data'];
                let json = JSON.parse(data);
                let messageData = json['payload'];
                $('#messagesList').first().after("<li>" + messageData['from'] + ' ' + messageData['text'] + "</li>")
            }
        }
        function connectToRoom(room, username) {
            currentRoom = room;
            let payload = {
                "username": username,
                "room": room
            };

            let message = {
                "header": "ConnectToRoom",
                "payload": payload
            }
            $('#roomName').first().after("<p>" + currentRoom + "</p>")
            webSocket.send(JSON.stringify(message));
        }

        function leaveFromRoom(room, username) {
            let payload = {
                "username": username,
                "room": room
            };

            let message = {
                "header": "LeaveFromRoom",
                "payload": payload
            }
            webSocket.send(JSON.stringify(message));
        }

        function sendMessage(text, username, room) {
            let payload = {
                "text": text,
                "from": username,
                "room": room
            };

            let message = {
                "header": "Message",
                "payload": payload
            };

            webSocket.send(JSON.stringify(message));
        }
    </script>
</head>
<body onload="connect()">
<div>
    <#list rooms as room>
        <button onclick="connectToRoom('${room.name}', '${username}')">Connect to room: ${room.name}</button>
    </#list>
    <button onclick="leaveFromRoom(currentRoom, '${username}')">Leave from room</button>
    <br>
    <label for="message">Message text</label>
    <input name="message" id="message" placeholder="Сообщение">
    <button onclick="sendMessage($('#message').val(), '${username}', currentRoom)">Send</button>
    <h3 id="roomName">Messages</h3>
    <ul id="messagesList">

    </ul>
</div>
</body>
</html>
