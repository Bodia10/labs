package news;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import news.Article;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.awt.List;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class JSonArticle implements Serializer<Article>{

		@Override
		public void serialize(Article art, String name) throws IOException {
			File file= new File(name);
			ObjectMapper objectMapper = new ObjectMapper();
	        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
	        StringWriter stringArticle = new StringWriter();

	        objectMapper.writeValue(stringArticle, art);

	        FileWriter fw = new FileWriter(file);
	        fw.write(stringArticle.toString());
	        fw.close();
		}

		@Override
		public void serializeCollection(Collection<Article> objects, String name) throws IOException {
			File file= new File(name);
			ObjectMapper objectMapper = new ObjectMapper();
	        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
	        StringWriter stringArticle = new StringWriter();
	        objectMapper.writeValue(stringArticle, objects);
	        FileWriter fw = new FileWriter(file);
	        fw.write(stringArticle.toString());
	        fw.close();
		}

		@Override
		public Article deserialize(String name) throws IOException {
			    File file= new File(name);
			    byte[] mapData = Files.readAllBytes(Paths.get(file.toString()));

		        ObjectMapper objectMapper = new ObjectMapper();
		        Article Article = objectMapper.readValue(mapData, Article.class);
		        return Article;
		}

		@Override
		public Collection<Article> deserializeCollection(String name) throws IOException {
			 File file= new File(name);
			 ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(file,mapper.getTypeFactory().constructCollectionType(Set.class, Article.class));
			
		}



	}