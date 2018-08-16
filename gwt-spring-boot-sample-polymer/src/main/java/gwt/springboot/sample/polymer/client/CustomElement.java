package gwt.springboot.sample.polymer.client;

import static elemental2.dom.DomGlobal.*;

import elemental2.dom.HTMLElement;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

@JsType(isNative=true, name="HTMLElement", namespace=JsPackage.GLOBAL)
public class CustomElement extends HTMLElement {
    @JsOverlay
    public static final String TAG_NAME = "custom-element";

    @JsOverlay
    public static CustomElement create() {
        return (CustomElement) document.createElement(TAG_NAME);
    }
    
    @JsOverlay
    public final String getGreeting() {
        return getAttribute("grreting");
    }
    
    @JsOverlay
    public final void setGreeting(String greeting) {
        setAttribute("greeting", greeting);
    }
}
