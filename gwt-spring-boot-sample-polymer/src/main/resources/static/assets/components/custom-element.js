import { html, PolymerElement } from "../@polymer/polymer/polymer-element.js";

class CustomElement extends PolymerElement {
  static get template() {
    return html`
      <style>
        :host {
          display: block;
        }
      </style>
      <h2>Hello [[greeting]]!</h2>
    `;
  }

  static get properties() {
    return {
      greeting: {
        type: String,
        value: 'World'
      }
    };
  }

}

window.customElements.define('custom-element', CustomElement);