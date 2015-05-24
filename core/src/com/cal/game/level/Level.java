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
import java.util.Random;

/**
 * Created by acohen on 5/23/15.
 */
public class Level extends Stage {

    Layer front, back;

    public Level() {
        super(new StretchViewport(640, 480));
    }

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

        addActor(back);
        addActor(front);
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
            } else if(e.getTagName().contentEquals("Portal")) {
                float x = Float.parseFloat(e.getAttribute("x"));
                float y = Float.parseFloat(e.getAttribute("y"));

                Portal portal = new Portal(x, y);
                l.addActor(portal);
            }
        }

        return l;
    }

    public static Level generateRandomLevel(Random random) {
        Level l = new Level();

        boolean isFront = random.nextBoolean();

        l.front = new Layer();
        for(int i = 0; i < 5; i++) {
            int x = random.nextInt(500) + 50;
            int y = random.nextInt(350) + 50;
            l.front.addActor(new Platform(x, y, random.nextInt(200) + 100, 30));
            if(i == 4 && isFront) {
                MainCharacter character = new MainCharacter(x, y + 20);
                l.front.addActor(character);
                Gdx.input.setInputProcessor(new InputHandler(character));
                l.front.isCurrentLayer = true;
            }else if(i == 4 && !isFront) {
                Portal portal = new Portal(x, y + 20);
                l.front.addActor(portal);
            }
        }

        l.back = new Layer();
        for(int i = 0; i < 5; i++) {
            int x = random.nextInt(400) + 50;
            int y = random.nextInt(300) + 50;
            l.back.addActor(new Platform(x, y, random.nextInt(200) + 100, 30));
            if(i == 4 && !isFront) {
                MainCharacter character = new MainCharacter(x, y + 20);
                l.back.addActor(character);
                Gdx.input.setInputProcessor(new InputHandler(character));
                l.back.isCurrentLayer = true;
            }else if(i == 4 && isFront) {
                Portal portal = new Portal(x, y + 20);
                l.back.addActor(portal);
            }
        }

        l.front.isBackLayer = false;
        l.back.isBackLayer = true;

        l.addActor(l.back);
        l.addActor(l.front);
        l.front.otherLayer = l.back;
        l.back.otherLayer = l.front;

        return l;
    }

}
