package news;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Set;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import news.Article;
import news.Magazine;

public class JSonMagazine implements Serializer<Magazine>{
    public void serialize(Magazine magaz, String name) throws IOException {
    	File file= new File(name);
    	ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        StringWriter stringArt = new StringWriter();

        objectMapper.writeValue(stringArt, magaz);

        FileWriter fw = new FileWriter(file);
        fw.write(stringArt.toString());
        fw.close();
    }
    
    @Override
    public Magazine deserialize(String name) throws IOException {
    	File file= new File(name);
        byte[] mapData = Files.readAllBytes(Paths.get(file.toString()));

        ObjectMapper objectMapper = new ObjectMapper();
        Magazine Magazine = objectMapper.readValue(mapData, Magazine.class);

        return Magazine;
    }

	@Override
	public void serializeCollection(Collection<Magazine> objects, String name) throws IOException {
		File file= new File(name);
		ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        StringWriter stringArt = new StringWriter();

        objectMapper.writeValue(stringArt, objects);
        FileWriter fw = new FileWriter(file);
        fw.write(stringArt.toString());
        fw.close();
	}

	@Override
	public Collection<Magazine> deserializeCollection(String name) throws IOException {
		File file= new File(name);
		 ObjectMapper mapper = new ObjectMapper();
	return mapper.readValue(file,mapper.getTypeFactory().constructCollectionType(Set.class, Article.class));
	}
}