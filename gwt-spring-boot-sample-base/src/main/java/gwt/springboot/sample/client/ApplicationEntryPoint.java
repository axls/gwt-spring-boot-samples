package gwt.springboot.sample.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;

public class ApplicationEntryPoint implements EntryPoint {

    @Override
    public void onModuleLoad() {
        RootPanel.get().add(new Button("Click me", (ClickHandler) event -> Window.alert("Hello, World!")));
    }
}
