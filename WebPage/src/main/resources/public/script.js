function getColorToHash(color) {
    switch (color) {
        case "ORANGE" : return "#f9812a";
        case "BLUE" : return "#0080fb";
        case "YELLOW" : return "#f8e473";
        case "GREEN" : return "#00a86b";
        case "WHITE" : return "#faf5ef";
    }
}

function convertToValue(value) {
    switch (value) {
        case "5" : return "3";
        case "3" : return "2";
        case "2" : return "0";
    }
}

function updatePawnsOnField(img, pawns, fieldNumber) {
    for(i=pawns.length-1; i >= 0; i--) {
        var color = pawns[i].COLOR;
        var pawn = img.getElementById("FIELD" + fieldNumber + "-" + (pawns.length - 1 - i));
        pawn.style="fill-opacity:1;fill:" + getColorToHash(color) + ";";
    }

    for(i=pawns.length; i < 5; i++) {
        var pawn = img.getElementById("FIELD" + fieldNumber + "-" + i);
        pawn.style="fill-opacity:0;";
    }
}

function setBetTileTextAction(img, color, value) {
    var betTile = img.getElementById("BET_TILE_" + color);
    betTile.textContent=convertToValue(value);
}

function updateDesertTileOnField(img, action, fieldNumber, page) {
    var desertTile = img.getElementById("DESERT_TILE" + fieldNumber);
    if (action === "GET DESERT TILE") {
        desertTile.style="fill-opacity:0;";
    } else {
        if (page === "OASIS") {
            desertTile.style="fill-opacity:1;fill:#33cc00;";
        } else {
            desertTile.style="fill-opacity:1;fill:#cc3300;";
        }
    }
}

function doAction(img, json) {
    if (json.OBJECT_TYPE === "PAWNS") {
        updatePawnsOnField(img, json.PAWNS, json.FIELD_NUMBER);
    } else if (json.OBJECT_TYPE === "BET_TILE"){
        setBetTileTextAction(img, json.COLOR, json.VALUE);
    } else if (json.OBJECT_TYPE === "DESERT_TILE") {
        updateDesertTileOnField(img, json.DESERT_TILE, json.FIELD_NUMBER, json.PAGE);
    }
}

function connect() {
    var img = document.getElementById("board");
    var doc = null;
    img.addEventListener("load",function() {
          doc = img.contentDocument;
     }, false);

    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        stompClient.subscribe('/topic/greetings', function (message) {
            doAction(doc, JSON.parse(message.body));
        });
    });
}

$( document ).ready(function() {
    connect();
});
