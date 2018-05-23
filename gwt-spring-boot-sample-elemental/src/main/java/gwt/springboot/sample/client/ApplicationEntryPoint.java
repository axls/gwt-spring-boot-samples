package gwt.springboot.sample.client;

import static elemental2.dom.DomGlobal.*;

import com.google.gwt.core.client.EntryPoint;

public class ApplicationEntryPoint implements EntryPoint {

    @Override
    public void onModuleLoad() {
        var button = document.createElement("button");
        button.textContent = "Click me";
        button.addEventListener("click", e -> window.alert("Hello, World!"));
        document.body.appendChild(button);
    }
}
