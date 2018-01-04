package test;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

import org.testng.annotations.Test;

import news.Article;
import news.JSonArticle;
import news.JSonMagazine;
import news.Magazine;
import news.XMLArticle;
import news.XMLMagazine;


public class XMLJsonTestMagazine {
 
	@Test
	  public void WriteJSonTest() throws IOException {
		File file= new File("JsonMagazine.json");
		Set<String> Writers = new TreeSet<>();
		Writers.add("petro");
		Writers.add("vasyl");
		Article a = new Article("football",Writers,LocalDate.of(2017, 10, 10), 5);
		TreeSet<Article> Article1 = new TreeSet<>();
		Article1.add(a);
		Article1.add(a);
		Magazine m = new Magazine("sport",Article1,100,LocalDate.of(2017, 10, 11));
		  	
		 new JSonMagazine().serialize(m,"JsonMagazine.json");
	  }
	
	  @Test
	  public void ReadJSonTest() throws IOException {
		  Set<String> Writers = new TreeSet<>();
			Writers.add("petro");
			Writers.add("vasyl");
			Article a = new Article("football",Writers,LocalDate.of(2017, 10, 10), 5);
			TreeSet<Article> Article1 = new TreeSet<>();
			Article1.add(a);
			Article1.add(a);
			Magazine m = new Magazine("sport",Article1,100,LocalDate.of(2017, 10, 11));
		  	Magazine m2 =new JSonMagazine().deserialize("JsonMagazine.json");
		  	assertEquals(m,m2);
		  
	  }
	  
	  
	  @Test
	  public void WriteXMLTest() throws IOException {
		  Set<String> Writers = new TreeSet<>();
		  Writers.add("petro");
		  Writers.add("vasyl");
		  Article a = new Article("football",Writers,LocalDate.of(2017, 10, 10), 5);
		  TreeSet<Article> Article1 = new TreeSet<>();
		  Article1.add(a);
		  Article1.add(a);
		  Magazine m = new Magazine("sport",Article1,100,LocalDate.of(2017, 10, 11));	
		  new XMLMagazine().serialize(m,"XMLMagazine.xml");
	  }
	  
	  @Test
	  public void ReadXMLTest() throws IOException {
		  Set<String> Writers = new TreeSet<>();
		  Writers.add("petro");
		  Writers.add("vasyl");
		  Article a = new Article("football",Writers,LocalDate.of(2017, 10, 10), 5);
		  TreeSet<Article> Article1 = new TreeSet<>();
		  Article1.add(a);
		  Article1.add(a);
		  Magazine m = new Magazine("sport",Article1,100,LocalDate.of(2017, 10, 11));
		  Magazine magazinexml =new XMLMagazine().deserialize("XMLMagazine.xml");
		  assertEquals(m,magazinexml);
	  }
}