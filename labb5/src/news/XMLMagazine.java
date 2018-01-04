package news;


import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import news.Article;
import news.Magazine;

public class XMLMagazine implements Serializer<Magazine>{
		
		private static Node getMagazine(Document doc,Magazine mag) {
	        Element Magazine = doc.createElement("magazine");
	        Magazine.appendChild(getMagazineElements(doc, Magazine, "magazineTitle", mag.getTitle()));
	        
	        String tempforpages = mag.getPages() + "";
	        Magazine.appendChild(getMagazineElements(doc, Magazine, "magazinePages", tempforpages));
	        
	        String tempfordate = mag.getPublishDate() + ""; 
	        Magazine.appendChild(getMagazineElements(doc, Magazine, "magazineDate", tempfordate));
	        
	        for(Article article : mag.getArticles()) {
	        	Magazine.appendChild(new XMLArticle().getArticle(doc, article));
	        }
	        return Magazine;
	    }
		
		
	    private static Node getMagazineElements(Document doc, Element element, String name, String value) {
	        Element node = doc.createElement(name);
	        node.appendChild(doc.createTextNode(value));
	        return node;
	    }
	    
	    public static Magazine getMagazine(Node node) throws NullPointerException{
	    	Magazine mag = new Magazine();
	        
	        if (node.getNodeType() == Node.ELEMENT_NODE) {
	            Element element = (Element) node;
	            mag.setTitle(getTagValue("magazineTitle", element));
	            mag.setPages(getTagValueInt("magazinePages", element));
	         //   mag.setPublishDate(getTagValueDate("magazineDate", element));
	            mag.setArticles(getTagValues("Article", element));
	           
	        }
	        return mag;
	    }
	    
	    private static String getTagValue(String tag, Element element) {
	        NodeList nodeList = element.getElementsByTagName(tag);
	        System.out.println(nodeList.item(0).getTextContent());
	        return nodeList.item(0).getTextContent();
	    }
	    
	    public static int getTagValueInt(String tag, Element element) {
	        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
	        Node node = (Node) nodeList.item(0);
	        String temp = node.getNodeValue();
	        int pages = Integer.parseInt(temp);
	        return pages;
	    }
	    
	    public static LocalDate getTagValueDate(String tag, Element element) {
	        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
	        Node node = (Node) nodeList.item(0);
	        String temp = node.getNodeValue();
	        char[] chars = temp.toCharArray();
	        String syear = chars[0] + chars[1] + chars[2] + chars[3] + "";
	        String smonth = chars[5] + chars[6] + "";
	        String sday = chars[8] + chars[9] + "";
	        int year = Integer.parseInt(syear);
	        int month = Integer.parseInt(smonth);
	        int day = Integer.parseInt(sday);
	        return LocalDate.of(year, month, day);
	    }
	    
	    private static TreeSet<Article> getTagValues(String tag, Element element) {
	        NodeList nodeList = element.getElementsByTagName(tag);
	        TreeSet<Article> str = new TreeSet<>();
	        
	        for(int i=0;i<nodeList.getLength();i++) {
				str.add(XMLArticle.getArticle(nodeList.item(i)));
	        }
	        return str;
	    }   
	    


		@Override
		public void serialize(Magazine object, String name) throws IOException {
			DocumentBuilderFactory dbmagtory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuilder;
			try {
				dBuilder = dbmagtory.newDocumentBuilder();
	            Document doc = dBuilder.newDocument();
				Element rootElement = doc.createElementNS("Magazine", "News");
				doc.appendChild(rootElement);
				rootElement.appendChild(getMagazine(doc, object));
				
				TransformerFactory transformermagtory = TransformerFactory.newInstance();
		        Transformer transformer = transformermagtory.newTransformer();
		        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		        DOMSource source = new DOMSource(doc);
		        
		        StreamResult console = new StreamResult(System.out);
		        StreamResult file = new StreamResult(new File(name));
		        
		        transformer.transform(source, console);
		        transformer.transform(source, file);
		
					} catch (Exception e) {
						e.printStackTrace();
			}
			
		}

		@Override
		public void serializeCollection(Collection<Magazine> objects, String name) throws IOException {
			DocumentBuilderFactory dbmagtory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuilder;
	        try {
				dBuilder = dbmagtory.newDocumentBuilder();
	            Document doc = dBuilder.newDocument();
				Element rootElement =
		                doc.createElementNS("Article", "News");
				doc.appendChild(rootElement);
				for(Magazine mag : objects)
					rootElement.appendChild(getMagazine(doc,mag));
				
				TransformerFactory transformermagtory = TransformerFactory.newInstance();
		        Transformer transformer = transformermagtory.newTransformer();
		        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		        DOMSource source = new DOMSource(doc);

		        StreamResult file = new StreamResult(new File(name));
		        transformer.transform(source, file);
		
					} catch (Exception e) {
						e.printStackTrace();
			}
			
		}

		@Override
		public Magazine deserialize(String name) throws IOException {
			File xmlFile = new File(name);
	    	DocumentBuilderFactory dbmagtory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuilder;
	        Magazine empList = new Magazine();
	        try {
	            dBuilder = dbmagtory.newDocumentBuilder();
	            Document doc = dBuilder.parse(xmlFile);
	            doc.getDocumentElement().normalize();
	            NodeList nodeList = doc.getElementsByTagName("Magazine");
	            
	                empList=getMagazine(nodeList.item(0));   
	        } catch (SAXException | ParserConfigurationException | IOException e1) {
	            e1.printStackTrace();
	        }
	    	return empList;
		}

		@Override
		public Collection<Magazine> deserializeCollection(String name) throws IOException {
			File xmlFile = new File(name);
	    	DocumentBuilderFactory dbmagtory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuilder;
	        List<Magazine> empList = new ArrayList<Magazine>();
	        try {
	            dBuilder = dbmagtory.newDocumentBuilder();
	            Document doc = dBuilder.parse(xmlFile);
	            doc.getDocumentElement().normalize();
	            NodeList nodeList = doc.getElementsByTagName("Magazine");

	            
	            for (int i = 0; i < nodeList.getLength(); i++) {
	                empList.add(getMagazine(nodeList.item(i)));
	            }
	        
	            
	        } catch (SAXException | ParserConfigurationException | IOException e1) {
	            e1.printStackTrace();
	        }
	    	return empList;
		}
}