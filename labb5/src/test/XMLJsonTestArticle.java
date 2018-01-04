package test;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

import org.testng.annotations.Test;

import news.Article;
import news.JSonArticle;
import news.Magazine;
import news.XMLArticle;


public class XMLJsonTestArticle {
 
	  @Test
	  public void WriteXMLTest() throws IOException {
		  Set<String> Writers = new TreeSet<>();
			Writers.add("petro");
			Writers.add("vasyl");
			Article a = new Article("football",Writers,LocalDate.of(2017, 10, 10), 5);
		  new XMLArticle().serialize(a,"xmloutput.xml");
	  }
	  
	  @Test
	  public void ReadXMLTest() throws IOException {
		  Set<String> Writers = new TreeSet<>();
		  Writers.add("petro");
		  Writers.add("vasyl");
		  Article article = new Article("football",Writers,LocalDate.of(2017, 10, 10), 5); 	  
		  Article articlexml =new XMLArticle().deserialize("xmloutput.xml");
		  assertEquals(article,articlexml);
	  }
	
	
	
  @Test
	public void WriteJSonTest() throws IOException {
	  Set<String> Writers = new TreeSet<>();
	  Writers.add("petro");
	  Writers.add("vasyl");
	  Article article = new Article("football",Writers,LocalDate.of(2017, 10, 10), 5); 	  
	  new JSonArticle().serialize(article,"JsonArticle.json");
  }
  
  @Test
  public void ReadJSonTest() throws IOException {
	  Set<String> Writers = new TreeSet<>();
	  Writers.add("petro");
	  Writers.add("vasyl");
	  Article article = new Article("football",Writers,LocalDate.of(2017, 10, 10), 5); 	   	  
	  Article articleJSon = new JSonArticle().deserialize("JsonArticle.json");
	  assertEquals(article,articleJSon);
  }
  
  @Test
  public void WriteJSonCollectionTest() throws IOException {
	  Set<String> Writers = new TreeSet<>();
	  Writers.add("petro");
	  Writers.add("vasyl");
	  Article article = new Article("football",Writers,LocalDate.of(2017, 10, 10), 5); 	  
	  
	  Article article2 = new Article("football2",Writers,LocalDate.of(2017, 10, 10), 7); 	  
	  
	  Set<Article> articles = new TreeSet<>();
	  articles.add(article);
	  articles.add(article2);
	  
	  new JSonArticle().serializeCollection(articles,"JsonArticle.json");
  }
  
  @Test
  public void ReadJSonCollectionTest() throws IOException {
	  
	  Set<String> Writers = new TreeSet<>();
	  Writers.add("petro");
	  Writers.add("vasyl");
	  Article article = new Article("football",Writers,LocalDate.of(2017, 10, 10), 5); 	  
	  
	  Article article2 = new Article("football2",Writers,LocalDate.of(2017, 10, 10), 7); 	  
	  
	  Set<Article> articles = new TreeSet<>();
	  articles.add(article);
	  articles.add(article2);
 	  
	  Collection<Article>  articles2 =new JSonArticle().
	  					deserializeCollection("JsonArticle.json");
	  assertEquals(articles,articles2);
  }
    
}