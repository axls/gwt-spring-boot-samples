package gwt.springboot.sample.sse.client;

import static elemental2.dom.DomGlobal.*;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

import elemental2.dom.EventSource;
import jsinterop.base.Js;

public class ApplicationEntryPoint implements EntryPoint {

    @Override
    public void onModuleLoad() {
        var status = document.getElementById("status");
        var lastEvent = document.getElementById("lastEvent");

        var eventSource = new EventSource(GWT.getHostPageBaseURL() + "events");
        eventSource.onopen = e -> {
            status.innerHTML = "Connected";
            return null;
        };
        eventSource.onerror = e -> {
            status.innerHTML = "Error/Reconnecting...";
            return null;
        };
        eventSource.onmessage = e -> {
            lastEvent.innerHTML = Js.cast(e.data);
            return null;
        };
    }
}
