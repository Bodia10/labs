package news;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;

import news.XMLMagazine;



import java.util.logging.Level;
import java.util.logging.Logger;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import news.Article;

public class Magazine{
	private String title;
	private int pages;
    private LocalDate publishDate;
    private int polka;
    private int tumbochka;
    TreeSet<Article> articles=new TreeSet<>(new Comparator<Article>(){
    	public int compare(Article obj1,Article obj2) {
		return obj1.getTitle().compareTo(obj2.getTitle());
    	}
    });
    
/////////////////////////////
 public Magazine() {
	this.title=" ";
	this.pages=0;
	this.publishDate = LocalDate.of(1990, 01, 01);
	}
    
 public Magazine(String name,TreeSet<Article> articles,int pages,LocalDate publishDate) {
		this.title=name;
		this.articles=articles;
		this.pages=this.countPagesFromArticles();
		this.publishDate = publishDate;
	}
 
 	/////////////////////////////
	
	//getters
	public String getTitle() {
	return title;
	}
	
	public int getPages() {
	return pages;
	}
	
	public LocalDate getPublishDate() {
	return publishDate;
	}
	
	public Set<Article> getArticles() {
	return articles;
	}
	/////////////////////////////
	
	//setters
	public void setTitle(String title) {
	this.title = title;
	}
	
	public void setPages(int pages) {
	this.pages = pages;
	}
	
	
	public void setPublishDate(LocalDate publishDate) {
	this.publishDate = publishDate;
	}

	public void setArticles(TreeSet<Article> articles) {
		this.articles.addAll(articles);
	}
	/////////////////////////////
	//methods
	
	public boolean findArticle(String name) {
		for(Article articles : this.articles) {
			if(articles.getTitle()==name)
				return true;
		}
		return false;
	}
	
	public boolean findWriterInArticle(String name) {
		for(Article articles : this.articles) {
			for(String i: articles.writers)
				if(i==name)
					return true;
		}
		return false;
	}
	
	
	
	public int countArticle() {
		int count=0;
		for(Article articles : this.articles) 
			count++;
		return count;
	}
	

	public int countPagesFromArticles() {
		int pagesCounter=0;
		for(Article articles : this.articles) 
			pagesCounter+=articles.getPages();
		return pagesCounter;
	}
	

	
	public void AddArticle(String name,Set<String> writers,LocalDate publishDate,int pages){
		articles.add(new Article(name,writers,publishDate,pages));
		this.pages+=pages;
	} 
	
	@Override
	public boolean equals(Object obj) {
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		  File file= new File("JsonMagazine.json");
		  Set<String> Writers = new TreeSet<>();
		  Writers.add("petro");
		  Writers.add("vasyl");
		  Article a = new Article("football",Writers,LocalDate.of(2017, 10, 10), 5);
		  TreeSet<Article> Article1 = new TreeSet<>();
		  Article1.add(a);
		  Article1.add(a);
		  Magazine m = new Magazine("sport",Article1,100,LocalDate.of(2017, 10, 11));
		  Magazine magazinexml =new XMLMagazine().deserialize("XMLMagazine.xml");
		  System.out.println(magazinexml.getTitle());
	}
}
