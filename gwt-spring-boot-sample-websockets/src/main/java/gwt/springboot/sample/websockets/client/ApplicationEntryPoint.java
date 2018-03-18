package gwt.springboot.sample.websockets.client;

import static elemental2.dom.DomGlobal.document;
import static elemental2.dom.DomGlobal.location;

import com.google.gwt.core.client.EntryPoint;

import elemental2.dom.Element;
import elemental2.dom.HTMLTextAreaElement;
import elemental2.dom.MessageEvent;
import elemental2.dom.WebSocket;

public class ApplicationEntryPoint implements EntryPoint {

    private Element messages;
    private HTMLTextAreaElement messageText;
    private WebSocket socket;

    @Override
    public void onModuleLoad() {
        Element sendButton = document.getElementById("sendButton");
        sendButton.setAttribute("disabled", true);
        sendButton.onclick = e -> {
            sendMessage();
            return null;
        };

        socket = new WebSocket("ws://" + location.getHost() + "/messages");
        socket.onopen = e -> {
            sendButton.removeAttribute("disabled");
            return null;
        };
        socket.onmessage = this::onMessageRecieved;

        messageText = (HTMLTextAreaElement) document.getElementById("messageText");
        messages = document.getElementById("messages");
    }

    private Object onMessageRecieved(MessageEvent<Object> e) {
        Element li = document.createElement("li");
        li.innerHTML = String.valueOf(e.data);
        messages.appendChild(li);

        return null;
    }

    private void sendMessage() {
        socket.send(messageText.value);
        messageText.value = "";
    }
}
