package com.example.testdistancecalculator.utils;

// package com.mkyong.xml.dom;

import com.example.testdistancecalculator.entity.models.CityParseModel;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@Component
public class XmlParser {

    public ArrayList<CityParseModel> parse(MultipartFile multipartFile) throws IOException {

        File file = new File("C:/Users/dimas/Desktop/test/currentFile.xml");
        multipartFile.transferTo(file);
        // Instantiate the Factory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        ArrayList<CityParseModel> cityParseModelArrayList = new ArrayList<>();
        try {
            // parse XML file
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);

            doc.getDocumentElement().normalize();

            System.out.println("Root Element :" + doc.getDocumentElement().getNodeName());
            System.out.println("------");

            // get <city>
            NodeList list = doc.getElementsByTagName("city");

            for (int temp = 0; temp < list.getLength(); temp++) {

                Node node = list.item(temp);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;

                    // get text
                    String name = element.getElementsByTagName("name").item(0).getTextContent();
                    String longtitude = element.getElementsByTagName("longtitude").item(0).getTextContent();
                    Double longtitudeD = Double.valueOf(longtitude);
                    String latitude = element.getElementsByTagName("latitude").item(0).getTextContent();
                    Double latitudeD = Double.valueOf(latitude);

                    cityParseModelArrayList.add(new CityParseModel(name, longtitudeD, latitudeD));

                    System.out.println("Name : " + name);
                    System.out.println("Longtitude: " + longtitude);
                    System.out.println("Latitude : " + latitude);
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return cityParseModelArrayList;
    }

}
