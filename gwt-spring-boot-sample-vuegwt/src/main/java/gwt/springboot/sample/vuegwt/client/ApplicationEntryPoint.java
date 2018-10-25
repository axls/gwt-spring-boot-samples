package gwt.springboot.sample.vuegwt.client;

import com.axellience.vuegwt.core.client.Vue;
import com.axellience.vuegwt.core.client.VueGWT;
import com.google.gwt.core.client.EntryPoint;

public class ApplicationEntryPoint implements EntryPoint {

    @Override
    public void onModuleLoad() {
        VueGWT.init();
        Vue.attach("#root", SimpleComponentFactory.get());
    }
}
