package gwt.springboot.sample.polymer.client;

import static elemental2.dom.DomGlobal.*;

import com.google.gwt.core.client.EntryPoint;

import elemental2.dom.Event;
import elemental2.dom.HTMLBodyElement;
import elemental2.dom.HTMLButtonElement;

public class ApplicationEntryPoint implements EntryPoint {

    @Override
    public void onModuleLoad() {
        HTMLBodyElement body = document.body;
        
        PaperCheckbox paperCheckbox = PaperCheckbox.create();
        paperCheckbox.textContent = "Paper Checkbox";
        paperCheckbox.checked = true;
        paperCheckbox.addEventListener("checked-changed", this::onCheckedChanged);
        body.appendChild(paperCheckbox);
        
        HTMLButtonElement button = (HTMLButtonElement) document.createElement("button");
        button.textContent = "Toggle";
        button.addEventListener("click", e -> paperCheckbox.checked = !paperCheckbox.checked);
        body.appendChild(button);
        
        CustomElement customElement = CustomElement.create();
        customElement.setGreeting("from GWT");
        body.appendChild(customElement);
    }

    private void onCheckedChanged(Event evt) {
        PaperCheckbox target = (PaperCheckbox) evt.target;
        console.log("onCheckedChanged: checked=" + target.checked);
    }
}
