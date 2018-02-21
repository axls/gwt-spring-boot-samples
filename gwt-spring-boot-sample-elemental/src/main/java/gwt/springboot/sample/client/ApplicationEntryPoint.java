package gwt.springboot.sample.client;

import static elemental2.dom.DomGlobal.document;
import static elemental2.dom.DomGlobal.window;

import com.google.gwt.core.client.EntryPoint;

import elemental2.dom.HTMLButtonElement;

public class ApplicationEntryPoint implements EntryPoint {

    @Override
    public void onModuleLoad() {
        HTMLButtonElement button = (HTMLButtonElement) document.createElement("button");
        button.textContent = "Click me";
        button.addEventListener("click", e -> window.alert("Hello, World!"));
        document.body.appendChild(button);
    }
}
