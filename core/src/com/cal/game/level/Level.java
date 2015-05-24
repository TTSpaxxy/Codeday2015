package com.cal.game.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.cal.game.entity.MainCharacter;
import com.cal.game.input.InputHandler;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.Node;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by acohen on 5/23/15.
 */
public class Level extends Stage {

    Layer front, back;

    public Level(String filePath) {
        super(new StretchViewport(640, 480));
        parseXML(filePath);
    }

    public void parseXML(String path) {
        Document doc = null;
        DocumentBuilderFactory docBuilder = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder db = docBuilder.newDocumentBuilder();

            doc = db.parse(path);
        }catch(ParserConfigurationException pce) {
            pce.printStackTrace();
        }catch(SAXException se) {
            se.printStackTrace();
        }catch(IOException ioe) {
            ioe.printStackTrace();
        }

        Element docElement = doc.getDocumentElement();
        NodeList nl = docElement.getElementsByTagName("Layer");
        front = parseLayer((Element) nl.item(0));
        front.isBackLayer = false;
        back = parseLayer((Element) nl.item(1));
        back.isBackLayer = true;

        addActor(front);
        addActor(back);
        front.otherLayer = back;
        back.otherLayer = front;

        if(front.isCurrentLayer) back.addActor(((MainCharacter) front.findActor("Michael Jackson")).indicator);
        else if(back.isCurrentLayer) front.addActor(((MainCharacter) back.findActor("Michael Jackson")).indicator);
    }

    public Layer parseLayer(Element layerElement) {
        Layer l = new Layer();
        NodeList items = layerElement.getChildNodes();
        for(int i = 0; i < items.getLength(); i++) {
            if(items.item(i).getNodeType() != Node.ELEMENT_NODE) continue;
            Element e = (Element) items.item(i);
            if(e.getTagName().contentEquals("Platform")) {
                float x = Float.parseFloat(e.getAttribute("x"));
                float y = Float.parseFloat(e.getAttribute("y"));
                float w = Float.parseFloat(e.getAttribute("w"));

                l.addActor(new Platform(x, y, w, 30));
            } else if(e.getTagName().contentEquals("PlayerStart")) {
                float x = Float.parseFloat(e.getAttribute("x"));
                float y = Float.parseFloat(e.getAttribute("y"));

                MainCharacter character = new MainCharacter(x, y);
                l.addActor(character);
                Gdx.input.setInputProcessor(new InputHandler(character));
                l.isCurrentLayer = true;
            }
        }

        return l;
    }

}
