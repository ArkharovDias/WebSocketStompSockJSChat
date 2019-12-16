var stompClient = null;

function connect() {

    var socket = new SockJS('/messages');

    stompClient = Stomp.over(socket);

    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);

        stompClient.subscribe('/topic/message', function (message) {
            showMessage(message.body);
        });
    });
}

function sendMessage() {

    stompClient.send("/app/message", {}, $("#name").val());
}

function showMessage(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#send" ).click(function() { sendMessage(); });
});