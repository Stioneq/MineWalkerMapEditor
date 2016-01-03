package org.laptech.minewalker.mapeditor.data.io;

import org.laptech.minewalker.mapeditor.data.Map;
import org.laptech.minewalker.mapeditor.data.objects.GameObject;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

/**
 * Write map into xml file
 *
 * @author rlapin
 */
public class XMLWriter implements Writer {
    private static final Logger LOGGER = getLogger(XMLWriter.class.getName());
    /**
     * Path to xml file
     */
    private String filePath;

    public XMLWriter(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void write(Map map) {

        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = docFactory.newDocumentBuilder();
            Document doc = documentBuilder.newDocument();
            Element rootElement = doc.createElement("map");
            Attr nameAttr = doc.createAttribute("name");
            nameAttr.setValue(map.getName());
            rootElement.setAttributeNode(nameAttr);
            doc.appendChild(rootElement);
            Element mapStructureElement = doc.createElement("structure");
            rootElement.appendChild(mapStructureElement);
            for (GameObject gameObject : map.getObjects()) {
                Element gameObjectElement = doc.createElement("gameobject");
                Attr xAttr = doc.createAttribute("x");
                xAttr.setValue(String.valueOf(gameObject.getX()));
                Attr yAttr = doc.createAttribute("y");
                yAttr.setValue(String.valueOf(gameObject.getY()));
                Attr widthAttr = doc.createAttribute("width");
                widthAttr.setValue(String.valueOf(gameObject.getWidth()));
                Attr heightAttr = doc.createAttribute("height");
                heightAttr.setValue(String.valueOf(gameObject.getHeight()));
                Attr typeAttr = doc.createAttribute("type");
                typeAttr.setValue(gameObject.getType());
                mapStructureElement.appendChild(gameObjectElement);
                gameObjectElement.setAttributeNode(xAttr);
                gameObjectElement.setAttributeNode(yAttr);
                gameObjectElement.setAttributeNode(widthAttr);
                gameObjectElement.setAttributeNode(heightAttr);
                gameObjectElement.setAttributeNode(typeAttr);
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(System.out);
            transformer.transform(source, result);
        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }

    }
}
