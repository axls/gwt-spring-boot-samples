package gwt.springboot.sample.polymer.client;

import static elemental2.dom.DomGlobal.*;

import elemental2.dom.HTMLElement;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

@JsType(isNative=true, name="HTMLElement", namespace=JsPackage.GLOBAL)
public class PaperCheckbox extends HTMLElement {
    @JsOverlay
    public static final String TAG_NAME = "paper-checkbox";

    public boolean checked;
    
    @JsOverlay
    public static PaperCheckbox create() {
        return (PaperCheckbox) document.createElement(TAG_NAME);
    }
}
