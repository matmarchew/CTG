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
    for (i=pawns.length-1; i >= 0; i--) {
        var color = pawns[i];
        var pawn = img.getElementById("FIELD" + fieldNumber + "-" + (pawns.length - 1 - i));
        pawn.style = "fill-opacity:1;fill:" + getColorToHash(color) + ";";
    }

    for (i=pawns.length; i < 5; i++) {
        var pawn = img.getElementById("FIELD" + fieldNumber + "-" + i);
        pawn.style = "fill-opacity:0;";
    }
}

function updateCube(img, color, value) {
    var cube = img.getElementById("cube_" + color);
    cube.textContent = value;
}

function setBetTileTextAction(img, color, value) {
    var betTile = img.getElementById("BET_TILE_" + color);
    betTile.textContent = convertToValue(value);
}

function updateDesertTileOnField(img, action, fieldNumber, page) {
    var desertTile = img.getElementById("DESERT_TILE" + fieldNumber);
    if (action === "GET DESERT TILE") {
        desertTile.style = "fill-opacity:0;";
    } else {
        if (page === "OASIS") {
            desertTile.style = "fill-opacity:1;fill:#33cc00;";
        } else {
            desertTile.style = "fill-opacity:1;fill:#cc3300;";
        }
    }
}

function refreshBetTile(img) {
    var betTileOrange = img.getElementById("BET_TILE_ORANGE");
    betTileOrange.textContent = "5";
    var betTileBlue = img.getElementById("BET_TILE_BLUE");
    betTileBlue.textContent = "5";
    var betTileYellow = img.getElementById("BET_TILE_YELLOW");
    betTileYellow.textContent = "5";
    var betTileGreen = img.getElementById("BET_TILE_GREEN");
    betTileGreen.textContent = "5";
    var betTileWhite = img.getElementById("BET_TILE_WHITE");
    betTileWhite.textContent = "5";
}

function refreshCubes(img) {
    var cubeOrange = img.getElementById("cube_ORANGE");
    cubeOrange.textContent = "";
    var cubeBlue = img.getElementById("cube_BLUE");
    cubeBlue.textContent = "";
    var cubeYellow = img.getElementById("cube_YELLOW");
    cubeYellow.textContent = "";
    var cubeGreen = img.getElementById("cube_GREEN");
    cubeGreen.textContent = "";
    var cubeWhite = img.getElementById("cube_WHITE");
    cubeWhite.textContent = "";
}

function updatePlayer(img, login) {
    var player = img.getElementById("player");
    player.textContent = login
}

function updateFinalResults(img, players) {
    updatePlayer(img, "");
    var finalResults = img.getElementById("FINAL_RESULTS");
    var x = parseFloat(finalResults.getAttribute('x'));
    var y = parseFloat(finalResults.getAttribute('y'));
    for (i=0; i<players.length; i++) {
        var player = players[i];
        var tspan = document.createElementNS("http://www.w3.org/2000/svg", "tspan");
        tspan.append(i+1 + ". " + player.LOGIN + " - " + player.POINTS);
        tspan.setAttribute('x', x);
        tspan.setAttribute('y', y + (i+1)*8);
        finalResults.appendChild(tspan);
    }
}

function doAction(img, json) {
    if (json.OBJECT_TYPE === "PAWNS") {
        updatePawnsOnField(img, json.PAWNS, json.FIELD_NUMBER);
    } else if (json.OBJECT_TYPE === "CUBE"){
         updateCube(img, json.COLOR, json.VALUE);
    } else if (json.OBJECT_TYPE === "BET_TILE"){
        setBetTileTextAction(img, json.COLOR, json.VALUE);
    } else if (json.OBJECT_TYPE === "DESERT_TILE") {
        updateDesertTileOnField(img, json.DESERT_TILE, json.FIELD_NUMBER, json.PAGE);
    } else if (json.OBJECT_TYPE === "REFRESH") {
        refreshBetTile(img);
        refreshCubes(img);
    } else if (json.OBJECT_TYPE === "PLAYER") {
        updatePlayer(img, json.LOGIN);
    } else if (json.OBJECT_TYPE === "FINAL_RESULTS") {
        updateFinalResults(img, json.PLAYERS);
    }
}

function connect() {
    var img = document.getElementById("board");
    var doc = null;
    img.addEventListener("load",function() {
          doc = img.contentDocument;
     }, false);

    var socket = new SockJS('/CTG');
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
