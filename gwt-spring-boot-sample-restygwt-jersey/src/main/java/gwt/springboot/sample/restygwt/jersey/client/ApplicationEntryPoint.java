package gwt.springboot.sample.restygwt.jersey.client;

import org.fusesource.restygwt.client.Defaults;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.REST;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;

import gwt.springboot.sample.restygwt.jersey.api.Greeting;
import gwt.springboot.sample.restygwt.jersey.api.GreetingsRest;

public class ApplicationEntryPoint implements EntryPoint {

    private final GreetingsRest rest = GWT.create(GreetingsRest.class);

    @Override
    public void onModuleLoad() {
        Defaults.setServiceRoot("/api");
        RootPanel.get().add(new Button("Click me", this::handleClick));
    }

    private void handleClick(ClickEvent event) {
        REST.withCallback(new MethodCallback<Greeting>() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                Window.alert("Error: " + exception);
            }

            @Override
            public void onSuccess(Method method, Greeting response) {
                Window.alert(response.getMessage());
            }
        }).call(rest).getGreeting("World");
    }
}
