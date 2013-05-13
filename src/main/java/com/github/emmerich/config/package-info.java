@XmlSchema(
        namespace = "http://www.w3.org/ns/widgets",
        elementFormDefault = XmlNsForm.QUALIFIED,
        xmlns = {
                @XmlNs(prefix = "", namespaceURI = "http://www.w3.org/ns/widgets"),
                @XmlNs(prefix = "gap", namespaceURI = "http://phonegap.com/ns/1.0")
        }
)

package com.github.emmerich.config;

import javax.xml.bind.annotation.XmlNs;
import javax.xml.bind.annotation.XmlNsForm;
import javax.xml.bind.annotation.XmlSchema;