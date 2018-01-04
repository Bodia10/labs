package news;


import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;


import news.Article;
import news.JSonArticle;
import news.JSonMagazine;
import news.Magazine;
import news.XMLArticle;
import news.XMLMagazine;

import news.JSonArticle;
import news.JSonMagazine;
import news.Magazine;
import news.XMLArticle;
import news.XMLMagazine;

public class Main {
	
	void main() throws IOException{
	  Magazine magazinexml =new XMLMagazine().deserialize("XMLMagazine.xml");
	  System.out.println(magazinexml.getTitle());
	}
	
}
