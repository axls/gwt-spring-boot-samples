package gwt.springboot.sample.vuegwt.client;

import com.axellience.vuegwt.core.annotations.component.Component;
import com.axellience.vuegwt.core.annotations.component.Data;
import com.axellience.vuegwt.core.client.component.IsVueComponent;

@Component
public class SimpleComponent implements IsVueComponent{
	@Data String message = "Hello from Vue GWT!";
}
