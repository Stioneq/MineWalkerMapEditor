package org.laptech.minewalker.mapeditor.data.io;

import org.laptech.minewalker.mapeditor.data.Map;
import org.laptech.minewalker.mapeditor.data.objects.Door;
import org.laptech.minewalker.mapeditor.data.objects.Floor;
import org.laptech.minewalker.mapeditor.data.objects.GameObject;
import org.laptech.minewalker.mapeditor.data.objects.SpikedFloor;
import org.laptech.minewalker.mapeditor.data.objects.TriggerPoint;
import org.laptech.minewalker.mapeditor.data.objects.Wall;
import org.laptech.minewalker.mapeditor.gui.EditorController;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Reads map from xml file
 *
 * @author rlapin
 */
public class XMLReader implements Reader {
    /**
     * Path to xml file
     */
    private String filePath;

    public XMLReader(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public Map read() {
        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
        try {

            Set<GameObject> gameObjects = new HashSet<>();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(new FileInputStream(filePath));
            Node mapNode = document.getElementsByTagName("map").item(0);
            System.out.println(mapNode.getAttributes().getNamedItem("name"));
            Node structure = document.getElementsByTagName("structure").item(0);
            NodeList childNodes = structure.getChildNodes();
            for(int i=0;i<childNodes.getLength();i++){
                Node item = childNodes.item(i);
                String type = item.getAttributes().getNamedItem("type").getNodeValue();
                GameObject gameObject = null;
                double x = Double.parseDouble(item.getAttributes().getNamedItem("x").getNodeValue());
                double y = Double.parseDouble(item.getAttributes().getNamedItem("y").getNodeValue());
                double width = Double.parseDouble(item.getAttributes().getNamedItem("width").getNodeValue());
                double height = Double.parseDouble(item.getAttributes().getNamedItem("height").getNodeValue());
                switch (type){
                    case "floor":
                        gameObject = new Floor(x,y,width,height);
                        break;
                    case "door":
                        gameObject = new Door(x,y,width,height,Boolean.valueOf(item.getAttributes().getNamedItem("isopened").getNodeValue()));
                        break;
                    case "spikedfloor":
                        gameObject = new SpikedFloor(x,y,width,height);
                        break;
                    case "triggerpoint":
                        gameObject = new TriggerPoint(x,y,width,height, TriggerPoint.TriggerType.valueOf(item.getAttributes().getNamedItem("triggertype").getNodeValue()));
                        break;
                    case "wall":
                        gameObject = new Wall(x,y,width,height);
                        break;

                }
                gameObjects.add(gameObject);
            }
            return new Map(gameObjects);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
