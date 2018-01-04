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

public class XMLArticle implements Serializer<Article>{
	
	public static Node getArticle(Document doc,Article article) {
        Element Article = doc.createElement("article");
        Article.appendChild(getArticleElements(doc, Article, "title", article.getTitle()));
        
        String tempforpages = article.getPages() + "";
        Article.appendChild(getArticleElements(doc, Article, "pages", tempforpages));
        
        String tempfordate = article.getwritingDate() + ""; 
        Article.appendChild(getArticleElements(doc, Article, "date", tempfordate));
        
        
        for(String writers : article.getWriters()) {
        	Article.appendChild(getArticleElements(doc, Article, "Writer",writers));
        }
        return Article;
    }
	
	
    private static Node getArticleElements(Document doc, Element element, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }
	
  
    public static Article getArticle(Node node) {
    	
    	Article article = new Article();
        
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            article.setTitle(getTagValue("title", element));
            article.setPages(getTagValueInt("pages", element));
       //     article.setwritingDate(getTagValueDate("date", element));
            getTagValueDate("date",element);
            article.setWriters(getTagValues("writer", element));
        }
        return article;
    }

    public static String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nodeList.item(0);
        return node.getNodeValue();
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
        String syear = chars[0] + chars[1] + chars[2] + chars[3] + chars[4] + "";
        String smonth = chars[5] + chars[6] + "";
        String sday = chars[8] + chars[9] + "";
        int year = Integer.parseInt(syear);
        int month = Integer.parseInt(smonth);
        int day = Integer.parseInt(sday);
       // System.out.println(day);
        return LocalDate.of(2017, 10, 10);
    }
    
    public static TreeSet<String> getTagValues(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag);
        TreeSet<String> str = new TreeSet<>();
        
        for(int i=0;i<nodeList.getLength();i++) {
            str.add(nodeList.item(i).getTextContent());
        }
        return str;
    }

	@Override
	public void serialize(Article object, String name) throws IOException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();
			Element rootElement =
	                doc.createElementNS("Article", "Magazine");
			doc.appendChild(rootElement);
			rootElement.appendChild(getArticle(doc, object));
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        Transformer transformer = transformerFactory.newTransformer();
	        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	        DOMSource source = new DOMSource(doc);
	        StreamResult file = new StreamResult(new File(name));

	        transformer.transform(source, file);
	
				} catch (Exception e) {
					e.printStackTrace();
		}
		
	}

	@Override
	public void serializeCollection(Collection<Article> objects, String name) throws IOException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try {
			dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();
			Element rootElement =
	                doc.createElementNS("article", "magazine");
			doc.appendChild(rootElement);
			for(Article article : objects)
				rootElement.appendChild(getArticle(doc,article));
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        Transformer transformer = transformerFactory.newTransformer();
	        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	        DOMSource source = new DOMSource(doc);
	        StreamResult file = new StreamResult(new File(name));
	        transformer.transform(source, file);
	
				} catch (Exception e) {
					e.printStackTrace();
		}
	}



	@Override
	public Article deserialize(String name) throws IOException {
		File xmlFile = new File(name);
    	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        Article empList = new Article();
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("article");
            empList=getArticle(nodeList.item(0));
            
        } catch (SAXException | ParserConfigurationException | IOException e1) {
            e1.printStackTrace();}
		return empList;
	}

	@Override
	public Collection<Article> deserializeCollection(String name) throws IOException {
		File xmlFile = new File(name);
    	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        List<Article> empList = new ArrayList<Article>();
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("article");
            
            for (int i = 0; i < nodeList.getLength(); i++) {
                empList.add(getArticle(nodeList.item(i)));
            }      
        } catch (SAXException | ParserConfigurationException | IOException e1) {
            e1.printStackTrace();
        }
    	return empList;
		
	}   
     

}